package com.macro.mall.tiny.modules.pms.mapper;

import com.macro.mall.tiny.modules.pms.dto.ProductAttrInfo;
import com.macro.mall.tiny.modules.pms.model.PmsProductAttribute;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
public interface PmsProductAttributeMapper extends BaseMapper<PmsProductAttribute> {
    /**
     * 获取商品属性信息
     */
    List<ProductAttrInfo> getProductAttrInfo(@Param("id") Long productCategoryId);
}
