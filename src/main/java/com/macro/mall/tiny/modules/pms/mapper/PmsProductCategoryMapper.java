package com.macro.mall.tiny.modules.pms.mapper;

import com.macro.mall.tiny.modules.pms.dto.PmsProductCategoryWithChildrenItem;
import com.macro.mall.tiny.modules.pms.model.PmsProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 产品分类 Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
public interface PmsProductCategoryMapper extends BaseMapper<PmsProductCategory> {
    List<PmsProductCategoryWithChildrenItem> listWithChildren();

}
