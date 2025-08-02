package com.macro.mall.tiny.modules.pms.dto;

import com.macro.mall.tiny.modules.pms.model.PmsProductAttribute;
import com.macro.mall.tiny.modules.pms.model.PmsProductAttributeCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 带有属性的商品属性分类
 * @author Sicecream
 * @date 2025/08/02
 */
public class PmsProductAttributeCategoryItem extends PmsProductAttributeCategory {
    @Getter
    @Setter
    @ApiModelProperty(value = "商品属性列表")
    private List<PmsProductAttribute> productAttributeList;
}
