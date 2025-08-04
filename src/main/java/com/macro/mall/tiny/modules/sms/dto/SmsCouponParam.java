package com.macro.mall.tiny.modules.sms.dto;

import com.macro.mall.tiny.modules.sms.model.SmsCoupon;
import com.macro.mall.tiny.modules.sms.model.SmsCouponProductCategoryRelation;
import com.macro.mall.tiny.modules.sms.model.SmsCouponProductRelation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 优惠券信息封装，包括绑定商品和分类
 * @author Sicecream
 * @date 2025/08/04
 */
public class SmsCouponParam extends SmsCoupon {
    @Getter
    @Setter
    @ApiModelProperty("优惠券绑定的商品")
    private List<SmsCouponProductRelation> productRelationList;
    @Getter
    @Setter
    @ApiModelProperty("优惠券绑定的商品分类")
    private List<SmsCouponProductCategoryRelation> productCategoryRelationList;
}
