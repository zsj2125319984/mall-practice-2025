package com.macro.mall.tiny.modules.pms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.cms.mapper.CmsPrefrenceAreaProductRelationMapper;
import com.macro.mall.tiny.modules.cms.mapper.CmsSubjectProductRelationMapper;
import com.macro.mall.tiny.modules.cms.model.CmsPrefrenceAreaProductRelation;
import com.macro.mall.tiny.modules.cms.model.CmsSubjectProductRelation;
import com.macro.mall.tiny.modules.cms.service.CmsPrefrenceAreaProductRelationService;
import com.macro.mall.tiny.modules.cms.service.CmsSubjectProductRelationService;
import com.macro.mall.tiny.modules.pms.dto.PmsProductParam;
import com.macro.mall.tiny.modules.pms.dto.PmsProductQueryParam;
import com.macro.mall.tiny.modules.pms.dto.PmsProductResult;
import com.macro.mall.tiny.modules.pms.mapper.*;
import com.macro.mall.tiny.modules.pms.model.*;
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
import java.util.stream.Collectors;

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
    @Autowired
    private PmsProductVertifyRecordService productVertifyRecordService;

    /**
     * 更新商品
     * @param id
     * @param productParam
     * @return int
     */
    @Override
    public int update(Long id, PmsProductParam productParam) {
        //获取实体类并更新商品
        PmsProduct product;
        product = productParam;
        product.setId(id);
        updateById(product);
        //会员价格
        QueryWrapper<PmsMemberPrice> wrapperMemberPrice = new QueryWrapper<>();
        wrapperMemberPrice.lambda().eq(PmsMemberPrice::getProductId,id);
        memberPriceService.remove(wrapperMemberPrice);
        relateAndInsertList(memberPriceService,productParam.getMemberPriceList(),id);
        //阶梯价格
        QueryWrapper<PmsProductLadder> wrapperProductLadder = new QueryWrapper<>();
        wrapperProductLadder.lambda().eq(PmsProductLadder::getProductId,id);
        productLadderService.remove(wrapperProductLadder);
        relateAndInsertList(productLadderService,productParam.getProductLadderList(),id);
        //满减价格
        QueryWrapper<PmsProductFullReduction> wrapperProductFullReduction = new QueryWrapper<>();
        wrapperProductFullReduction.lambda().eq(PmsProductFullReduction::getProductId,id);
        productFullReductionService.remove(wrapperProductFullReduction);
        relateAndInsertList(productFullReductionService,productParam.getProductFullReductionList(),id);
        //修改sku信息
        handleUpdateSkuStockList(id, productParam);
        //商品参数
        QueryWrapper<PmsProductAttributeValue> wrapperProductAttributeValue = new QueryWrapper<>();
        wrapperProductAttributeValue.lambda().eq(PmsProductAttributeValue::getProductId,id);
        productAttributeValueService.remove(wrapperProductAttributeValue);
        relateAndInsertList(productAttributeValueService,productParam.getProductAttributeValueList(),id);
        //关联专题
        QueryWrapper<CmsSubjectProductRelation> wrapperSubjectProductRelation = new QueryWrapper<>();
        wrapperSubjectProductRelation.lambda().eq(CmsSubjectProductRelation::getProductId,id);
        subjectProductRelationService.remove(wrapperSubjectProductRelation);
        relateAndInsertList(subjectProductRelationService,productParam.getSubjectProductRelationList(),id);
        //关联优选
        QueryWrapper<CmsPrefrenceAreaProductRelation> wrapperPrefrenceAreaProductRelation = new QueryWrapper<>();
        wrapperPrefrenceAreaProductRelation.lambda().eq(CmsPrefrenceAreaProductRelation::getProductId,id);
        prefrenceAreaProductRelationService.remove(wrapperPrefrenceAreaProductRelation);
        relateAndInsertList(prefrenceAreaProductRelationService,productParam.getPrefrenceAreaProductRelationList(),id);

        return 1;
    }

    /**
     * 处理sku的更新
     * @param id
     * @param productParam
     */
    private void handleUpdateSkuStockList(Long id, PmsProductParam productParam) {
        //获取当前sku信息
        List<PmsSkuStock> skuStockList = productParam.getSkuStockList();
        //若为空则直接删除
        if(CollUtil.isEmpty(skuStockList)){
            QueryWrapper<PmsSkuStock> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(PmsSkuStock::getProductId,id);
            skuStockService.remove(wrapper);
            return;
        }
        //从数据库获取原始sku信息
        QueryWrapper<PmsSkuStock> wrapper2 = new QueryWrapper<>();
        wrapper2.lambda().eq(PmsSkuStock::getProductId,id);
        List<PmsSkuStock> originSkuStockList = skuStockService.list(wrapper2);
        //获取新增的信息
        List<PmsSkuStock> insertSkuStockList = skuStockList.stream()
                .filter(pmsSkuStock -> pmsSkuStock.getId() == null)
                .collect(Collectors.toList());
        //获取更新的信息
        List<PmsSkuStock> updateSkustockList = skuStockList.stream()
                .filter(pmsSkuStock -> pmsSkuStock.getId() != null)
                .collect(Collectors.toList());
        List<Long> updateIds = updateSkustockList.stream()
                .map(PmsSkuStock::getId)
                .collect(Collectors.toList());
        //获取删除的信息
        List<PmsSkuStock> removeSkuStockList = originSkuStockList.stream()
                .filter(pmsSkuStock -> !updateIds.contains(pmsSkuStock.getId()))
                .collect(Collectors.toList());
        //处理新增和更新列表的skuStockCode
        handleSkuStockCode(insertSkuStockList,id);
        handleSkuStockCode(updateSkustockList,id);
        //新增sku
        if(CollUtil.isNotEmpty(insertSkuStockList)){
            relateAndInsertList(skuStockService,insertSkuStockList,id);
        }
        //删除sku
        if(CollUtil.isNotEmpty(removeSkuStockList)){
            List<Long> removeIds = removeSkuStockList.stream()
                    .map(PmsSkuStock::getId)
                    .collect(Collectors.toList());
            QueryWrapper<PmsSkuStock> wrapper3 = new QueryWrapper<>();
            wrapper3.lambda().in(PmsSkuStock::getId,removeIds);
            skuStockService.remove(wrapper3);
        }
        //更新sku
        if(CollUtil.isNotEmpty(updateSkustockList)){
            skuStockService.updateBatchById(updateSkustockList);
        }
    }

    /**
     * 批量修改审核状态
     * @param detail
     * @param ids
     * @param verifyStatus
     * @return int
     */
    @Override
    public int updateVerifyStatus(String detail, List<Long> ids, Integer verifyStatus) {
        //修改审核状态
        List<PmsProduct> productList = ids.stream().map(id -> {
            PmsProduct product = new PmsProduct();
            product.setId(id);
            product.setVerifyStatus(verifyStatus);
            return product;
        }).collect(Collectors.toList());

        boolean success = updateBatchById(productList);
        //插入审核记录
        List<PmsProductVertifyRecord> recordList = ids.stream().map(id -> {
            PmsProductVertifyRecord productVertifyRecord = new PmsProductVertifyRecord();
            productVertifyRecord.setProductId(id);
            productVertifyRecord.setCreateTime(new Date());
            productVertifyRecord.setDetail(detail);
            productVertifyRecord.setStatus(verifyStatus);
            productVertifyRecord.setVertifyMan("test");
            return productVertifyRecord;
        }).collect(Collectors.toList());

        productVertifyRecordService.saveBatch(recordList);

        return success ? ids.size() : 0;
    }

    /**
     * 根据商品id获取商品编辑信息
     * @param id
     * @return {@link PmsProductResult }
     */
    @Override
    public PmsProductResult updateInfo(Long id) {
        return baseMapper.getUpdateInfo(id);
    }

    /**
     * 批量推荐商品
     * @param ids
     * @param recommendStatus
     * @return int
     */
    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        List<PmsProduct> productList = ids.stream().map(id -> {
            PmsProduct product = new PmsProduct();
            product.setId(id);
            product.setRecommandStatus(recommendStatus);
            return product;
        }).collect(Collectors.toList());

        boolean success = updateBatchById(productList);

        return success ? ids.size() : 0;
    }

    /**
     * 批量上下架商品
     * @param ids
     * @param publishStatus
     * @return int
     */
    @Override
    public int updatePublishStatus(List<Long> ids, Integer publishStatus) {
        List<PmsProduct> productList = ids.stream().map(id -> {
            PmsProduct product = new PmsProduct();
            product.setId(id);
            product.setPublishStatus(publishStatus);
            return product;
        }).collect(Collectors.toList());

        boolean success = updateBatchById(productList);

        return success ? ids.size() : 0;
    }

    /**
     * 批量修改删除状态
     * @param ids
     * @param deleteStatus
     * @return int
     */
    @Override
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        List<PmsProduct> productList = ids.stream().map(id -> {
            PmsProduct product = new PmsProduct();
            product.setId(id);
            product.setDeleteStatus(deleteStatus);
            return product;
        }).collect(Collectors.toList());

        boolean success = updateBatchById(productList);

        return success ? ids.size() : 0;
    }

    /**
     * 批量设为新品
     * @param ids
     * @param newStatus
     * @return int
     */
    @Override
    public int updateNewStatus(List<Long> ids, Integer newStatus) {
        List<PmsProduct> productList = ids.stream().map(id -> {
            PmsProduct product = new PmsProduct();
            product.setId(id);
            product.setNewStatus(newStatus);
            return product;
        }).collect(Collectors.toList());

        boolean success = updateBatchById(productList);

        return success ? ids.size() : 0;
    }

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
