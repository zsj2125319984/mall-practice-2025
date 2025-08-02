package com.macro.mall.tiny.modules.pms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.pms.dto.PmsProductAttributeCategoryItem;
import com.macro.mall.tiny.modules.pms.model.PmsProductAttributeCategory;
import com.macro.mall.tiny.modules.pms.mapper.PmsProductAttributeCategoryMapper;
import com.macro.mall.tiny.modules.pms.service.PmsProductAttributeCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
@Service
public class PmsProductAttributeCategoryServiceImpl extends ServiceImpl<PmsProductAttributeCategoryMapper, PmsProductAttributeCategory> implements PmsProductAttributeCategoryService {

    @Override
    public int create(String name) {
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName(name);
        return save(productAttributeCategory) ? 1 : 0;
    }

    @Override
    public int update(Long id, String name) {
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName(name);
        productAttributeCategory.setId(id);
        return updateById(productAttributeCategory) ? 1 : 0;
    }

    @Override
    public int delete(Long id) {
        return removeById(id) ? 1 : 0;
    }

    @Override
    public PmsProductAttributeCategory getItem(Long id) {
        return getById(id);
    }

    @Override
    public Page<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum) {
        Page<PmsProductAttributeCategory> page = new Page<>(pageNum,pageSize);

        return page(page);
    }

    @Override
    public List<PmsProductAttributeCategoryItem> getListWithAttr() {
        return baseMapper.getListWithAttr();
    }
}
