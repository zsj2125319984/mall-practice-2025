package com.macro.mall.tiny.modules.sms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.sms.model.SmsHomeBrand;
import com.macro.mall.tiny.modules.sms.mapper.SmsHomeBrandMapper;
import com.macro.mall.tiny.modules.sms.service.SmsHomeBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页推荐品牌表 服务实现类
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-04
 */
@Service
public class SmsHomeBrandServiceImpl extends ServiceImpl<SmsHomeBrandMapper, SmsHomeBrand> implements SmsHomeBrandService {

    @Override
    public int create(List<SmsHomeBrand> homeBrandList) {
        for (SmsHomeBrand smsHomeBrand : homeBrandList) {
            smsHomeBrand.setRecommendStatus(1);
            smsHomeBrand.setSort(0);
            save(smsHomeBrand);
        }
        return homeBrandList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeBrand homeBrand = new SmsHomeBrand();
        homeBrand.setId(id);
        homeBrand.setSort(sort);
        return updateById(homeBrand) ? 1 : 0;
    }

    @Override
    public int delete(List<Long> ids) {
        QueryWrapper<SmsHomeBrand> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(SmsHomeBrand::getId,ids);

        return remove(wrapper) ? ids.size() : 0;
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeBrand record = new SmsHomeBrand();
        record.setRecommendStatus(recommendStatus);

        QueryWrapper<SmsHomeBrand> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(SmsHomeBrand::getId,ids);

        return update(record,wrapper) ? ids.size() : 0;
    }

    @Override
    public Page<SmsHomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Page<SmsHomeBrand> page = new Page<>(pageNum,pageSize);
        QueryWrapper<SmsHomeBrand> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SmsHomeBrand> lambda = wrapper.lambda();
        if(StrUtil.isNotEmpty(brandName)){
            lambda.and(w ->
                    w.like(SmsHomeBrand::getBrandName,brandName));
        }
        if(recommendStatus != null){
            lambda.and(w ->
                    w.eq(SmsHomeBrand::getRecommendStatus,recommendStatus));
        }
        wrapper.orderByDesc("sort");

        return page(page,wrapper);
    }
}
