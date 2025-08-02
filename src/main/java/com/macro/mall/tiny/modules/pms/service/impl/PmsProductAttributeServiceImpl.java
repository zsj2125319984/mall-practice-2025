package com.macro.mall.tiny.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.pms.dto.PmsProductAttributeParam;
import com.macro.mall.tiny.modules.pms.dto.ProductAttrInfo;
import com.macro.mall.tiny.modules.pms.model.PmsProductAttribute;
import com.macro.mall.tiny.modules.pms.mapper.PmsProductAttributeMapper;
import com.macro.mall.tiny.modules.pms.model.PmsProductAttributeCategory;
import com.macro.mall.tiny.modules.pms.service.PmsProductAttributeCategoryService;
import com.macro.mall.tiny.modules.pms.service.PmsProductAttributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
@Service
public class PmsProductAttributeServiceImpl extends ServiceImpl<PmsProductAttributeMapper, PmsProductAttribute> implements PmsProductAttributeService {
    @Autowired
    PmsProductAttributeCategoryService productAttributeCategoryService;

    @Override
    public Page<PmsProductAttribute> getList(Long cid, Integer type, Integer pageSize, Integer pageNum) {
        Page<PmsProductAttribute> page = new Page<>(pageNum,pageSize);
        QueryWrapper<PmsProductAttribute> wrapper = new QueryWrapper<>();
        wrapper.lambda().and(w ->
                w.eq(PmsProductAttribute::getProductAttributeCategoryId,cid)
                        .eq(PmsProductAttribute::getType,type));
        wrapper.orderByDesc("sort");

        return page(page,wrapper);
    }

    @Override
    public int create(PmsProductAttributeParam pmsProductAttributeParam) {
        PmsProductAttribute pmsProductAttribute = new PmsProductAttribute();
        BeanUtils.copyProperties(pmsProductAttributeParam, pmsProductAttribute);
        boolean success = save(pmsProductAttribute);
        //新增商品属性以后需要更新商品属性分类数量
        PmsProductAttributeCategory pmsProductAttributeCategory = productAttributeCategoryService.getById(pmsProductAttribute.getProductAttributeCategoryId());
        if(pmsProductAttribute.getType()==0){
            pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount()+1);
        }else if(pmsProductAttribute.getType()==1){
            pmsProductAttributeCategory.setParamCount(pmsProductAttributeCategory.getParamCount()+1);
        }
        productAttributeCategoryService.updateById(pmsProductAttributeCategory);
        return success ? 1 : 0;
    }

    @Override
    public int update(Long id, PmsProductAttributeParam productAttributeParam) {
        PmsProductAttribute pmsProductAttribute = new PmsProductAttribute();
        pmsProductAttribute.setId(id);
        BeanUtils.copyProperties(productAttributeParam, pmsProductAttribute);

        boolean success = updateById(pmsProductAttribute);

        return success ? 1 : 0;
    }

    @Override
    public PmsProductAttribute getItem(Long id) {
        return getById(id);
    }

    @Override
    public int delete(List<Long> ids) {
        //获取分类
        PmsProductAttribute pmsProductAttribute = getById(ids.get(0));
        Integer type = pmsProductAttribute.getType();
        PmsProductAttributeCategory pmsProductAttributeCategory = productAttributeCategoryService.getById(pmsProductAttribute.getProductAttributeCategoryId());

        QueryWrapper<PmsProductAttribute> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(PmsProductAttribute::getId,ids);
        boolean success = remove(wrapper);

        int count = success ? ids.size() : 0;
        //删除完成后修改数量
        if(type==0){
            if(pmsProductAttributeCategory.getAttributeCount()>=count){
                pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount()-count);
            }else{
                pmsProductAttributeCategory.setAttributeCount(0);
            }
        }else if(type==1){
            if(pmsProductAttributeCategory.getParamCount()>=count){
                pmsProductAttributeCategory.setParamCount(pmsProductAttributeCategory.getParamCount()-count);
            }else{
                pmsProductAttributeCategory.setParamCount(0);
            }
        }
        productAttributeCategoryService.updateById(pmsProductAttributeCategory);
        return count;
    }

    @Override
    public List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId) {
        return baseMapper.getProductAttrInfo(productCategoryId);
    }
}
