package com.macro.mall.tiny.modules.pms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品属性参数表
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
@Getter
@Setter
@TableName("pms_product_attribute")
@ApiModel(value = "PmsProductAttribute对象", description = "商品属性参数表")
public class PmsProductAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productAttributeCategoryId;

    private String name;

    @ApiModelProperty("属性选择类型：0->唯一；1->单选；2->多选")
    private Integer selectType;

    @ApiModelProperty("属性录入方式：0->手工录入；1->从列表中选取")
    private Integer inputType;

    @ApiModelProperty("可选值列表，以逗号隔开")
    private String inputList;

    @ApiModelProperty("排序字段：最高的可以单独上传图片")
    private Integer sort;

    @ApiModelProperty("分类筛选样式：1->普通；1->颜色")
    private Integer filterType;

    @ApiModelProperty("检索类型；0->不需要进行检索；1->关键字检索；2->范围检索")
    private Integer searchType;

    @ApiModelProperty("相同属性产品是否关联；0->不关联；1->关联")
    private Integer relatedStatus;

    @ApiModelProperty("是否支持手动新增；0->不支持；1->支持")
    private Integer handAddStatus;

    @ApiModelProperty("属性的类型；0->规格；1->参数")
    private Integer type;


}
