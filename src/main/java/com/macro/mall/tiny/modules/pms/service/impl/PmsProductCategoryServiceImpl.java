package com.macro.mall.tiny.modules.pms.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.pms.dto.PmsProductCategoryParam;
import com.macro.mall.tiny.modules.pms.dto.PmsProductCategoryWithChildrenItem;
import com.macro.mall.tiny.modules.pms.model.PmsProduct;
import com.macro.mall.tiny.modules.pms.model.PmsProductCategory;
import com.macro.mall.tiny.modules.pms.mapper.PmsProductCategoryMapper;
import com.macro.mall.tiny.modules.pms.model.PmsProductCategoryAttributeRelation;
import com.macro.mall.tiny.modules.pms.service.PmsProductCategoryAttributeRelationService;
import com.macro.mall.tiny.modules.pms.service.PmsProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macro.mall.tiny.modules.pms.service.PmsProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
@Service
public class PmsProductCategoryServiceImpl extends ServiceImpl<PmsProductCategoryMapper, PmsProductCategory> implements PmsProductCategoryService {
    @Autowired
    private PmsProductService productService;
    @Autowired
    private PmsProductCategoryAttributeRelationService productCategoryAttributeRelationService;

    @Override
    public int create(PmsProductCategoryParam pmsProductCategoryParam) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setProductCount(0);
        BeanUtils.copyProperties(pmsProductCategoryParam, productCategory);
        //没有父分类时为一级分类
        setCategoryLevel(productCategory);
        boolean success = save(productCategory);
        //创建筛选属性关联
        List<Long> productAttributeIdList = pmsProductCategoryParam.getProductAttributeIdList();
        if(!CollUtil.isEmpty(productAttributeIdList)){
            insertRelationList(productCategory.getId(), productAttributeIdList);
        }
        return success ? 1 : 0;
    }

    private void insertRelationList(Long productCategoryId, List<Long> productAttributeIdList) {
        List<PmsProductCategoryAttributeRelation> relationList = new ArrayList<>();
        for (Long productAttrId : productAttributeIdList) {
            PmsProductCategoryAttributeRelation relation = new PmsProductCategoryAttributeRelation();
            relation.setProductAttributeId(productAttrId);
            relation.setProductCategoryId(productCategoryId);
            relationList.add(relation);
        }
        productCategoryAttributeRelationService.saveBatch(relationList);
    }

    private void setCategoryLevel(PmsProductCategory productCategory) {
        //没有父分类时为一级分类
        if (productCategory.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            //有父分类时选择根据父分类level设置
            PmsProductCategory parentCategory = getById(productCategory.getParentId());
            if (parentCategory != null) {
                productCategory.setLevel(parentCategory.getLevel() + 1);
            } else {
                productCategory.setLevel(0);
            }
        }
    }

    @Override
    public int update(Long id, PmsProductCategoryParam pmsProductCategoryParam) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setId(id);
        BeanUtils.copyProperties(pmsProductCategoryParam, productCategory);
        setCategoryLevel(productCategory);
        //更新商品分类时要更新商品中的名称
        PmsProduct product = new PmsProduct();
        product.setProductCategoryName(productCategory.getName());
        QueryWrapper<PmsProduct> wrapper1 = new QueryWrapper<>();
        wrapper1.lambda().eq(PmsProduct::getProductCategoryId,id);
        productService.update(product,wrapper1);
        //同时更新筛选属性的信息
        if(!CollUtil.isEmpty(pmsProductCategoryParam.getProductAttributeIdList())){
            QueryWrapper<PmsProductCategoryAttributeRelation> wrapper2 = new QueryWrapper<>();
            wrapper2.lambda().eq(PmsProductCategoryAttributeRelation::getProductCategoryId,id);
            productCategoryAttributeRelationService.remove(wrapper2);
            insertRelationList(id,pmsProductCategoryParam.getProductAttributeIdList());
        }else{
            QueryWrapper<PmsProductCategoryAttributeRelation> wrapper2 = new QueryWrapper<>();
            wrapper2.lambda().eq(PmsProductCategoryAttributeRelation::getProductCategoryId,id);
            productCategoryAttributeRelationService.remove(wrapper2);
        }

        boolean success = updateById(productCategory);

        return success ? 1 : 0;
    }

    @Override
    public Page<PmsProductCategory> getList(Long parentId, Integer pageSize, Integer pageNum) {
        Page<PmsProductCategory> page = new Page<>(pageNum,pageSize);
        QueryWrapper<PmsProductCategory> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PmsProductCategory::getParentId,parentId);
        wrapper.orderByDesc("sort");
        return page(page,wrapper);
    }

    @Override
    public int delete(Long id) {
        return removeById(id) ? 1 : 0;
    }

    @Override
    public PmsProductCategory getItem(Long id) {
        return getById(id);
    }

    @Override
    public int updateNavStatus(List<Long> ids, Integer navStatus) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setNavStatus(navStatus);

        QueryWrapper<PmsProductCategory> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(PmsProductCategory::getId,ids);
        boolean success = update(productCategory, wrapper);

        return success ? 1 : 0;
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setShowStatus(showStatus);

        QueryWrapper<PmsProductCategory> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(PmsProductCategory::getId,ids);
        boolean success = update(productCategory, wrapper);

        return success ? 1 : 0;
    }

    @Override
    public List<PmsProductCategoryWithChildrenItem> listWithChildren() {
        return baseMapper.listWithChildren();
    }
}
