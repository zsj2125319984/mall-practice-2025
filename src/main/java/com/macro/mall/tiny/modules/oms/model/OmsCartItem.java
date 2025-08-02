package com.macro.mall.tiny.modules.oms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 购物车表
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-02
 */
@Getter
@Setter
@TableName("oms_cart_item")
@ApiModel(value = "OmsCartItem对象", description = "购物车表")
public class OmsCartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    private Long productSkuId;

    private Long memberId;

    @ApiModelProperty("购买数量")
    private Integer quantity;

    @ApiModelProperty("添加到购物车的价格")
    private BigDecimal price;

    @ApiModelProperty("商品主图")
    private String productPic;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品副标题（卖点）")
    private String productSubTitle;

    @ApiModelProperty("商品sku条码")
    private String productSkuCode;

    @ApiModelProperty("会员昵称")
    private String memberNickname;

    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("修改时间")
    private Date modifyDate;

    @ApiModelProperty("是否删除")
    private Integer deleteStatus;

    @ApiModelProperty("商品分类")
    private Long productCategoryId;

    private String productBrand;

    private String productSn;

    @ApiModelProperty("商品销售属性:[{\"key\":\"颜色\",\"value\":\"颜色\"},{\"key\":\"容量\",\"value\":\"4G\"}]")
    private String productAttr;


}
