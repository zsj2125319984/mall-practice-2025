package com.macro.mall.tiny.modules.oms.dto;

import com.macro.mall.tiny.modules.oms.model.OmsOrder;
import com.macro.mall.tiny.modules.oms.model.OmsOrderItem;
import com.macro.mall.tiny.modules.oms.model.OmsOrderOperateHistory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class OmsOrderDetail extends OmsOrder {
    @Getter
    @Setter
    @ApiModelProperty("订单商品列表")
    private List<OmsOrderItem> orderItemList;
    @Getter
    @Setter
    @ApiModelProperty("订单操作记录列表")
    private List<OmsOrderOperateHistory> historyList;
}
