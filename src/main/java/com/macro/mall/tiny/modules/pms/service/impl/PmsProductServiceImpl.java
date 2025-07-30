package com.macro.mall.tiny.modules.pms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.cms.mapper.CmsPrefrenceAreaProductRelationMapper;
import com.macro.mall.tiny.modules.cms.mapper.CmsSubjectProductRelationMapper;
import com.macro.mall.tiny.modules.cms.service.CmsPrefrenceAreaProductRelationService;
import com.macro.mall.tiny.modules.cms.service.CmsSubjectProductRelationService;
import com.macro.mall.tiny.modules.pms.dto.PmsProductParam;
import com.macro.mall.tiny.modules.pms.dto.PmsProductQueryParam;
import com.macro.mall.tiny.modules.pms.mapper.*;
import com.macro.mall.tiny.modules.pms.model.PmsMemberPrice;
import com.macro.mall.tiny.modules.pms.model.PmsProduct;
import com.macro.mall.tiny.modules.pms.model.PmsSkuStock;
import com.macro.mall.tiny.modules.pms.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
@Service
@Slf4j
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {

    @Autowired
    private PmsMemberPriceService memberPriceService;
    @Autowired
    private PmsProductLadderService productLadderService;
    @Autowired
    private PmsProductFullReductionService productFullReductionService;
    @Autowired
    private PmsSkuStockService skuStockService;
    @Autowired
    private PmsProductAttributeValueService productAttributeValueService;
    @Autowired
    private CmsSubjectProductRelationService subjectProductRelationService;
    @Autowired
    private CmsPrefrenceAreaProductRelationService prefrenceAreaProductRelationService;

    /**
     * 根据商品名称或货号模糊查询
     * @param keyword
     * @return {@link List }<{@link PmsProduct }>
     */
    @Override
    public List<PmsProduct> list(String keyword) {
        QueryWrapper<PmsProduct> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<PmsProduct> lambda = wrapper.lambda();

        lambda.and(w -> w.eq(PmsProduct::getDeleteStatus,0));
        if(StrUtil.isNotEmpty(keyword)){
            lambda.and(w -> w.like(PmsProduct::getName,keyword));
            lambda.or(w -> w.like(PmsProduct::getProductSn,keyword)
                    .eq(PmsProduct::getDeleteStatus,0));

            //select * from product where delete_status = ? and name like ? or (product_sn like ? and delete_status = ?)
        }

        return list(wrapper);
    }

    /**
     * 查询商品
     * @param productQueryParam
     * @param pageNum
     * @param pageSize
     * @return {@link Page }<{@link PmsProduct }>
     */
    @Override
    public Page<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageNum, Integer pageSize) {
        //设置查询条件
        Page<PmsProduct> page = new Page<>(pageNum,pageSize);
        QueryWrapper<PmsProduct> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<PmsProduct> lambda = wrapper.lambda();
        //删除状态为0
        lambda.and(w -> w.eq(PmsProduct::getDeleteStatus,0));
        //根据Publish、Verify、名称关键字、ProductSn、BrandId、CategoryId多条件查询
        if(productQueryParam.getPublishStatus() != null){
            lambda.and(w ->
                    w.eq(PmsProduct::getPublishStatus,productQueryParam.getPublishStatus()));
        }
        if(productQueryParam.getVerifyStatus() != null){
            lambda.and(w ->
                    w.eq(PmsProduct::getVerifyStatus,productQueryParam.getVerifyStatus()));
        }
        if(StrUtil.isNotEmpty(productQueryParam.getKeyword())){
            lambda.and(w ->
                    w.like(PmsProduct::getName,productQueryParam.getKeyword()));
        }
        if(StrUtil.isNotEmpty(productQueryParam.getProductSn())){
            lambda.and(w ->
                    w.eq(PmsProduct::getProductSn,productQueryParam.getProductSn()));
        }
        if(productQueryParam.getBrandId() != null){
            lambda.and(w ->
                    w.eq(PmsProduct::getBrandId,productQueryParam.getBrandId()));
        }
        if(productQueryParam.getProductCategoryId() != null){
            lambda.and(w ->
                    w.eq(PmsProduct::getProductCategoryId,productQueryParam.getProductCategoryId()));
        }
        //分页查询并返回结果
        return page(page,wrapper);
    }



    /**
     * 创建商品
     * @param pmsProductParam
     * @return int
     */
    @Override
    public int create(PmsProductParam pmsProductParam) {
        //获取商品实体类
        PmsProduct product;
        product = pmsProductParam;
        product.setId(null);
        //插入数据库
        save(product);

        Long id = product.getId();
        //关联会员价格
        relateAndInsertList(memberPriceService,pmsProductParam.getMemberPriceList(),id);
        //关联阶梯价格
        relateAndInsertList(productLadderService,pmsProductParam.getProductLadderList(),id);
        //关联满减价格
        relateAndInsertList(productFullReductionService,pmsProductParam.getProductFullReductionList(),id);
        //处理sku编码
        handleSkuStockCode(pmsProductParam.getSkuStockList(),id);
        //关联sku库存
        relateAndInsertList(skuStockService,pmsProductParam.getSkuStockList(),id);
        //关联商品参数
        relateAndInsertList(productAttributeValueService,pmsProductParam.getProductAttributeValueList(),id);
        //关联专题
        relateAndInsertList(subjectProductRelationService,pmsProductParam.getSubjectProductRelationList(),id);
        //关联优选
        relateAndInsertList(prefrenceAreaProductRelationService,pmsProductParam.getPrefrenceAreaProductRelationList(),id);
        //返回结果
        return 1;
    }

    private void handleSkuStockCode(List<PmsSkuStock> skuStockList, Long productId) {
        //如果集合为空直接返回
        if(CollectionUtils.isEmpty(skuStockList)) return;
        //遍历集合，为每条记录生成一个skuCode
        for(int i=0;i<skuStockList.size();i++){
            PmsSkuStock skuStock = skuStockList.get(i);

            if(StrUtil.isEmpty(skuStock.getSkuCode())){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(sdf.format(new Date()));
                //四位商品id
                sb.append(String.format("%04d", productId));
                //三位索引id
                sb.append(String.format("%03d", i+1));

                skuStock.setSkuCode(sb.toString());
            }
        }
    }

    private void relateAndInsertList(Object service, List dataList, Long productId) {
        try {
            //集合为空直接返回
            if(CollUtil.isEmpty(dataList)){
                return;
            }
            //遍历集合，反射dataList设置id和productId
            for (Object item : dataList) {
                Method setId = item.getClass().getMethod("setId", Long.class);
                Method setProductId = item.getClass().getMethod("setProductId", Long.class);
                setId.invoke(item,(Long)null);
                setProductId.invoke(item,productId);
            }
            //反射mapper批量插入数据
            Method saveBatch = service.getClass().getMethod("saveBatch", Collection.class);
            saveBatch.invoke(service,dataList);
        } catch (Exception e) {
            log.warn("创建商品时出错: {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
