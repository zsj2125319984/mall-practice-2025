package com.macro.mall.tiny.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.macro.mall.tiny.modules.sms.dto.SmsFlashPromotionSessionDetail;
import com.macro.mall.tiny.modules.sms.model.SmsFlashPromotionSession;
import com.macro.mall.tiny.modules.sms.mapper.SmsFlashPromotionSessionMapper;
import com.macro.mall.tiny.modules.sms.service.SmsFlashPromotionProductRelationService;
import com.macro.mall.tiny.modules.sms.service.SmsFlashPromotionSessionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 限时购场次表 服务实现类
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-04
 */
@Service
public class SmsFlashPromotionSessionServiceImpl extends ServiceImpl<SmsFlashPromotionSessionMapper, SmsFlashPromotionSession> implements SmsFlashPromotionSessionService {

    @Autowired
    private SmsFlashPromotionProductRelationService flashPromotionProductRelationService;

    @Override
    public int create(SmsFlashPromotionSession promotionSession) {
        promotionSession.setCreateTime(new Date());
        return save(promotionSession) ? 1: 0;
    }

    @Override
    public int update(Long id, SmsFlashPromotionSession promotionSession) {
        promotionSession.setId(id);
        return updateById(promotionSession) ? 1 : 0;
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsFlashPromotionSession promotionSession = new SmsFlashPromotionSession();
        promotionSession.setId(id);
        promotionSession.setStatus(status);
        return updateById(promotionSession) ? 1 : 0;
    }

    @Override
    public int delete(Long id) {
        return removeById(id) ? 1 : 0;
    }

    @Override
    public SmsFlashPromotionSession getItem(Long id) {
        return getById(id);
    }

    @Override
    public List<SmsFlashPromotionSessionDetail> selectList(Long flashPromotionId) {
        List<SmsFlashPromotionSessionDetail> result = new ArrayList<>();

        QueryWrapper<SmsFlashPromotionSession> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SmsFlashPromotionSession::getStatus,1);
        List<SmsFlashPromotionSession> list = list(wrapper);

        for (SmsFlashPromotionSession promotionSession : list) {
            SmsFlashPromotionSessionDetail detail = new SmsFlashPromotionSessionDetail();
            BeanUtils.copyProperties(promotionSession, detail);
            long count = flashPromotionProductRelationService.getCount(flashPromotionId, promotionSession.getId());
            detail.setProductCount(count);
            result.add(detail);
        }
        return result;
    }
}
