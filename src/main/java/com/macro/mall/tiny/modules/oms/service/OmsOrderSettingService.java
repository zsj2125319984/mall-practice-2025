package com.macro.mall.tiny.modules.oms.service;

import com.macro.mall.tiny.modules.oms.model.OmsOrderSetting;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单设置表 服务类
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-02
 */
public interface OmsOrderSettingService extends IService<OmsOrderSetting> {
    /**
     * 获取指定订单设置
     */
    OmsOrderSetting getItem(Long id);

    /**
     * 修改指定订单设置
     */
    int update(Long id, OmsOrderSetting orderSetting);
}
