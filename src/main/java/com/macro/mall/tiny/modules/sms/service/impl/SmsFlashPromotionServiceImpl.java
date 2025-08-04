package com.macro.mall.tiny.modules.sms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.sms.model.SmsFlashPromotion;
import com.macro.mall.tiny.modules.sms.mapper.SmsFlashPromotionMapper;
import com.macro.mall.tiny.modules.sms.service.SmsFlashPromotionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 限时购表 服务实现类
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-04
 */
@Service
public class SmsFlashPromotionServiceImpl extends ServiceImpl<SmsFlashPromotionMapper, SmsFlashPromotion> implements SmsFlashPromotionService {

    @Override
    public int create(SmsFlashPromotion flashPromotion) {
        flashPromotion.setCreateTime(new Date());
        return save(flashPromotion) ? 1 : 0;
    }

    @Override
    public int update(Long id, SmsFlashPromotion flashPromotion) {
        flashPromotion.setId(id);
        return updateById(flashPromotion) ? 1 : 0;
    }

    @Override
    public int delete(Long id) {
        return removeById(id) ? 1 : 0;
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsFlashPromotion flashPromotion = new SmsFlashPromotion();
        flashPromotion.setId(id);
        flashPromotion.setStatus(status);
        return updateById(flashPromotion) ? 1 : 0;
    }

    @Override
    public SmsFlashPromotion getItem(Long id) {
        return getById(id);
    }

    @Override
    public Page<SmsFlashPromotion> list(String keyword, Integer pageSize, Integer pageNum) {
        Page<SmsFlashPromotion> page = new Page<>(pageNum,pageSize);
        QueryWrapper<SmsFlashPromotion> wrapper = new QueryWrapper<>();
        if(StrUtil.isNotEmpty(keyword)){
            wrapper.lambda().like(SmsFlashPromotion::getTitle,keyword);
        }

        return page(page,wrapper);
    }
}
