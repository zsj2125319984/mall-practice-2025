package com.macro.mall.tiny.modules.sms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.sms.model.SmsHomeRecommendSubject;
import com.macro.mall.tiny.modules.sms.mapper.SmsHomeRecommendSubjectMapper;
import com.macro.mall.tiny.modules.sms.service.SmsHomeRecommendSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页推荐专题表 服务实现类
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-04
 */
@Service
public class SmsHomeRecommendSubjectServiceImpl extends ServiceImpl<SmsHomeRecommendSubjectMapper, SmsHomeRecommendSubject> implements SmsHomeRecommendSubjectService {

    @Override
    public int create(List<SmsHomeRecommendSubject> recommendSubjectList) {
        for (SmsHomeRecommendSubject recommendSubject : recommendSubjectList) {
            recommendSubject.setRecommendStatus(1);
            recommendSubject.setSort(0);
            save(recommendSubject);
        }
        return recommendSubjectList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeRecommendSubject recommendSubject = new SmsHomeRecommendSubject();
        recommendSubject.setId(id);
        recommendSubject.setSort(sort);
        return updateById(recommendSubject) ? 1 : 0;
    }

    @Override
    public int delete(List<Long> ids) {
        QueryWrapper<SmsHomeRecommendSubject> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(SmsHomeRecommendSubject::getId,ids);

        return remove(wrapper) ? ids.size() : 0;
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeRecommendSubject record = new SmsHomeRecommendSubject();
        record.setRecommendStatus(recommendStatus);

        QueryWrapper<SmsHomeRecommendSubject> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(SmsHomeRecommendSubject::getId,ids);

        return update(record,wrapper) ? ids.size() : 0;
    }

    @Override
    public Page<SmsHomeRecommendSubject> list(String subjectName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Page<SmsHomeRecommendSubject> page = new Page<>(pageNum,pageSize);
        QueryWrapper<SmsHomeRecommendSubject> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SmsHomeRecommendSubject> lambda = wrapper.lambda();
        if(!StrUtil.isEmpty(subjectName)){
            lambda.and(w ->
                    w.like(SmsHomeRecommendSubject::getSubjectName,subjectName));
        }
        if(recommendStatus!=null){
            lambda.and(w ->
                    w.eq(SmsHomeRecommendSubject::getRecommendStatus,recommendStatus));
        }
        wrapper.orderByDesc("sort");

        return page(page,wrapper);
    }
}
