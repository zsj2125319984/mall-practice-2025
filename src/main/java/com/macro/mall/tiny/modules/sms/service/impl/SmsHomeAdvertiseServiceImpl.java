package com.macro.mall.tiny.modules.sms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.sms.model.SmsHomeAdvertise;
import com.macro.mall.tiny.modules.sms.mapper.SmsHomeAdvertiseMapper;
import com.macro.mall.tiny.modules.sms.service.SmsHomeAdvertiseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务实现类
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-04
 */
@Service
public class SmsHomeAdvertiseServiceImpl extends ServiceImpl<SmsHomeAdvertiseMapper, SmsHomeAdvertise> implements SmsHomeAdvertiseService {

    @Override
    public int create(SmsHomeAdvertise advertise) {
        advertise.setClickCount(0);
        advertise.setOrderCount(0);
        return save(advertise) ? 1 : 0;
    }

    @Override
    public int delete(List<Long> ids) {
        QueryWrapper<SmsHomeAdvertise> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(SmsHomeAdvertise::getId,ids);

        return remove(wrapper) ? ids.size() : 0;
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsHomeAdvertise record = new SmsHomeAdvertise();
        record.setId(id);
        record.setStatus(status);
        return updateById(record) ? 1 : 0;
    }

    @Override
    public SmsHomeAdvertise getItem(Long id) {
        return getById(id);
    }

    @Override
    public int update(Long id, SmsHomeAdvertise advertise) {
        advertise.setId(id);
        return updateById(advertise) ? 1 : 0;
    }

    @Override
    public Page<SmsHomeAdvertise> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNum) {
        Page<SmsHomeAdvertise> page = new Page<>(pageNum,pageSize);
        QueryWrapper<SmsHomeAdvertise> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SmsHomeAdvertise> lambda = wrapper.lambda();
        if (!StrUtil.isEmpty(name)) {
            lambda.and(w ->
                    w.like(SmsHomeAdvertise::getName,name));
        }
        if (type != null) {
            lambda.and(w ->
                    w.eq(SmsHomeAdvertise::getType,type));
        }
        if (!StrUtil.isEmpty(endTime)) {
            String startStr = endTime + " 00:00:00";
            String endStr = endTime + " 23:59:59";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = null;
            try {
                start = sdf.parse(startStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date end = null;
            try {
                end = sdf.parse(endStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (start != null && end != null) {
                Date finalStart = start;
                Date finalEnd = end;
                lambda.and(w ->
                        w.between(SmsHomeAdvertise::getEndTime, finalStart, finalEnd));
            }
        }
        wrapper.orderByDesc("sort");

        return page(page,wrapper);
    }
}
