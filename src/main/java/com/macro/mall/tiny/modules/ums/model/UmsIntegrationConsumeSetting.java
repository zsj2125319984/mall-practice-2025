package com.macro.mall.tiny.modules.ums.model;

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
 * 积分消费设置
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-02
 */
@Getter
@Setter
@TableName("ums_integration_consume_setting")
@ApiModel(value = "UmsIntegrationConsumeSetting对象", description = "积分消费设置")
public class UmsIntegrationConsumeSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("每一元需要抵扣的积分数量")
    private Integer deductionPerAmount;

    @ApiModelProperty("每笔订单最高抵用百分比")
    private Integer maxPercentPerOrder;

    @ApiModelProperty("每次使用积分最小单位100")
    private Integer useUnit;

    @ApiModelProperty("是否可以和优惠券同用；0->不可以；1->可以")
    private Integer couponStatus;


}
