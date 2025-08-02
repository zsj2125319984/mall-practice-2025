package com.macro.mall.tiny.modules.pms.mapper;

import com.macro.mall.tiny.modules.pms.dto.PmsProductAttributeCategoryItem;
import com.macro.mall.tiny.modules.pms.model.PmsProductAttributeCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
public interface PmsProductAttributeCategoryMapper extends BaseMapper<PmsProductAttributeCategory> {
    /**
     * 获取包含属性的商品属性分类
     */
    List<PmsProductAttributeCategoryItem> getListWithAttr();
}
