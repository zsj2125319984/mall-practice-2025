package com.macro.mall.tiny.modules.oms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.oms.model.OmsOrderReturnReason;
import com.macro.mall.tiny.modules.oms.mapper.OmsOrderReturnReasonMapper;
import com.macro.mall.tiny.modules.oms.service.OmsOrderReturnReasonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 退货原因表 服务实现类
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-02
 */
@Service
public class OmsOrderReturnReasonServiceImpl extends ServiceImpl<OmsOrderReturnReasonMapper, OmsOrderReturnReason> implements OmsOrderReturnReasonService {

    @Override
    public int create(OmsOrderReturnReason returnReason) {
        returnReason.setCreateTime(new Date());
        return save(returnReason) ? 1 : 0;
    }

    @Override
    public int update(Long id, OmsOrderReturnReason returnReason) {
        returnReason.setId(id);
        return updateById(returnReason) ? 1 : 0;
    }

    @Override
    public int delete(List<Long> ids) {
        QueryWrapper<OmsOrderReturnReason> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(OmsOrderReturnReason::getId,ids);

        return remove(wrapper) ? ids.size() : 0;
    }

    @Override
    public Page<OmsOrderReturnReason> list(Integer pageSize, Integer pageNum) {
        Page<OmsOrderReturnReason> page = new Page<>(pageNum,pageSize);

        QueryWrapper<OmsOrderReturnReason> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("sort");

        return page(page,wrapper);
    }

    @Override
    public int updateStatus(List<Long> ids, Integer status) {
        if(!status.equals(0)&&!status.equals(1)){
            return 0;
        }
        OmsOrderReturnReason record = new OmsOrderReturnReason();
        record.setStatus(status);

        QueryWrapper<OmsOrderReturnReason> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(OmsOrderReturnReason::getId,ids);

        return update(record,wrapper) ? ids.size() : 0;
    }

    @Override
    public OmsOrderReturnReason getItem(Long id) {
        return getById(id);
    }
}
