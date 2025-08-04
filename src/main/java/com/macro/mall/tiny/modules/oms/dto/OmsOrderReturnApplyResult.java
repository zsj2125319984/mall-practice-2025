package com.macro.mall.tiny.modules.oms.dto;

import com.macro.mall.tiny.modules.oms.model.OmsCompanyAddress;
import com.macro.mall.tiny.modules.oms.model.OmsOrderReturnApply;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 订单退货申请结果封装
 * @author Sicecream
 * @date 2025/08/04
 */
public class OmsOrderReturnApplyResult extends OmsOrderReturnApply {
    @Getter
    @Setter
    @ApiModelProperty(value = "公司收货地址")
    private OmsCompanyAddress companyAddress;
}
