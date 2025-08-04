package com.macro.mall.tiny.modules.sms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.sms.model.SmsCouponHistory;
import com.macro.mall.tiny.modules.sms.mapper.SmsCouponHistoryMapper;
import com.macro.mall.tiny.modules.sms.service.SmsCouponHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠券使用、领取历史表 服务实现类
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-04
 */
@Service
public class SmsCouponHistoryServiceImpl extends ServiceImpl<SmsCouponHistoryMapper, SmsCouponHistory> implements SmsCouponHistoryService {

    @Override
    public Page<SmsCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum) {
        Page<SmsCouponHistory> page = new Page<>(pageNum,pageSize);
        QueryWrapper<SmsCouponHistory> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SmsCouponHistory> lambda = wrapper.lambda();
        if(couponId != null){
            lambda.and(w ->
                    w.eq(SmsCouponHistory::getCouponId,couponId));
        }
        if(useStatus != null){
            lambda.and(w ->
                    w.eq(SmsCouponHistory::getUseStatus,useStatus));
        }
        if(StrUtil.isNotEmpty(orderSn)){
            lambda.and(w ->
                    w.eq(SmsCouponHistory::getOrderSn,orderSn));
        }

        return page(page,wrapper);
    }
}
