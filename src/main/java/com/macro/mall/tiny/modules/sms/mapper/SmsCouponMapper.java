package com.macro.mall.tiny.modules.sms.mapper;

import com.macro.mall.tiny.modules.sms.dto.SmsCouponParam;
import com.macro.mall.tiny.modules.sms.model.SmsCoupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 优惠券表 Mapper 接口
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-04
 */
public interface SmsCouponMapper extends BaseMapper<SmsCoupon> {
    /**
     * 获取优惠券详情包括绑定关系
     */
    SmsCouponParam getItem(@Param("id") Long id);
}
