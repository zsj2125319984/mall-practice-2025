package com.macro.mall.tiny.modules.sms.model;

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
 * 商品限时购与商品关系表
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-04
 */
@Getter
@Setter
@TableName("sms_flash_promotion_product_relation")
@ApiModel(value = "SmsFlashPromotionProductRelation对象", description = "商品限时购与商品关系表")
public class SmsFlashPromotionProductRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long flashPromotionId;

    @ApiModelProperty("编号")
    private Long flashPromotionSessionId;

    private Long productId;

    @ApiModelProperty("限时购价格")
    private BigDecimal flashPromotionPrice;

    @ApiModelProperty("限时购数量")
    private Integer flashPromotionCount;

    @ApiModelProperty("每人限购数量")
    private Integer flashPromotionLimit;

    @ApiModelProperty("排序")
    private Integer sort;


}
