package com.macro.mall.tiny.modules.oms.service.impl;

import com.macro.mall.tiny.modules.oms.model.OmsOrderItem;
import com.macro.mall.tiny.modules.oms.mapper.OmsOrderItemMapper;
import com.macro.mall.tiny.modules.oms.service.OmsOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单中所包含的商品 服务实现类
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-02
 */
@Service
public class OmsOrderItemServiceImpl extends ServiceImpl<OmsOrderItemMapper, OmsOrderItem> implements OmsOrderItemService {

}
