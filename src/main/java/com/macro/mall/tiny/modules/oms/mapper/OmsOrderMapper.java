package com.macro.mall.tiny.modules.oms.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.macro.mall.tiny.modules.oms.dto.OmsOrderDeliveryParam;
import com.macro.mall.tiny.modules.oms.dto.OmsOrderDetail;
import com.macro.mall.tiny.modules.oms.dto.OmsOrderQueryParam;
import com.macro.mall.tiny.modules.oms.model.OmsOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-02
 */
public interface OmsOrderMapper extends BaseMapper<OmsOrder> {
    /**
     * 条件查询订单
     */
    IPage<OmsOrder> getList(IPage<OmsOrder> page,@Param("queryParam") OmsOrderQueryParam queryParam);

    /**
     * 批量发货
     */
    int delivery(@Param("list") List<OmsOrderDeliveryParam> deliveryParamList);

    /**
     * 获取订单详情
     */
    OmsOrderDetail getDetail(@Param("id") Long id);
}
