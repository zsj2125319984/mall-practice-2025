package com.macro.mall.tiny.modules.pms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * sku的库存
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
@Getter
@Setter
@TableName("pms_sku_stock")
@ApiModel(value = "PmsSkuStock对象", description = "sku的库存")
public class PmsSkuStock implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    @ApiModelProperty("sku编码")
    private String skuCode;

    private BigDecimal price;

    @ApiModelProperty("库存")
    private Integer stock;

    @ApiModelProperty("预警库存")
    private Integer lowStock;

    @ApiModelProperty("展示图片")
    private String pic;

    @ApiModelProperty("销量")
    private Integer sale;

    @ApiModelProperty("单品促销价格")
    private BigDecimal promotionPrice;

    @ApiModelProperty("锁定库存")
    private Integer lockStock;

    @ApiModelProperty("商品销售属性，json格式")
    private String spData;


}
