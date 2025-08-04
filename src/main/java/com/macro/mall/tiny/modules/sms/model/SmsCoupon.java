package com.macro.mall.tiny.modules.sms.model;

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
 * 优惠券表
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-04
 */
@Getter
@Setter
@TableName("sms_coupon")
@ApiModel(value = "SmsCoupon对象", description = "优惠券表")
public class SmsCoupon implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("优惠券类型；0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券")
    private Integer type;

    private String name;

    @ApiModelProperty("使用平台：0->全部；1->移动；2->PC")
    private Integer platform;

    @ApiModelProperty("数量")
    private Integer count;

    @ApiModelProperty("金额")
    private BigDecimal amount;

    @ApiModelProperty("每人限领张数")
    private Integer perLimit;

    @ApiModelProperty("使用门槛；0表示无门槛")
    private BigDecimal minPoint;

    private Date startTime;

    private Date endTime;

    @ApiModelProperty("使用类型：0->全场通用；1->指定分类；2->指定商品")
    private Integer useType;

    @ApiModelProperty("备注")
    private String note;

    @ApiModelProperty("发行数量")
    private Integer publishCount;

    @ApiModelProperty("已使用数量")
    private Integer useCount;

    @ApiModelProperty("领取数量")
    private Integer receiveCount;

    @ApiModelProperty("可以领取的日期")
    private Date enableTime;

    @ApiModelProperty("优惠码")
    private String code;

    @ApiModelProperty("可领取的会员类型：0->无限时")
    private Integer memberLevel;


}
