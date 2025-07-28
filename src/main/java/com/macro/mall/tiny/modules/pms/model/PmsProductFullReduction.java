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
 * 产品满减表(只针对同商品)
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
@Getter
@Setter
@TableName("pms_product_full_reduction")
@ApiModel(value = "PmsProductFullReduction对象", description = "产品满减表(只针对同商品)")
public class PmsProductFullReduction implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    private BigDecimal fullPrice;

    private BigDecimal reducePrice;


}
