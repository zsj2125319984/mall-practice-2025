package com.macro.mall.tiny.modules.pms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
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

    /**
     * 查询商品
     * @param productQueryParam
     * @param pageNum
     * @param pageSize
     * @return {@link Page }<{@link PmsProduct }>
     */
    @Override
    public Page<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageNum, Integer pageSize) {
        return null;
    }

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
