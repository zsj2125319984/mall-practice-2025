package com.macro.mall.tiny.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.sms.dto.SmsFlashPromotionProduct;
import com.macro.mall.tiny.modules.sms.model.SmsFlashPromotionProductRelation;
import com.macro.mall.tiny.modules.sms.mapper.SmsFlashPromotionProductRelationMapper;
import com.macro.mall.tiny.modules.sms.service.SmsFlashPromotionProductRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 商品限时购与商品关系表 服务实现类
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-04
 */
@Service
public class SmsFlashPromotionProductRelationServiceImpl extends ServiceImpl<SmsFlashPromotionProductRelationMapper, SmsFlashPromotionProductRelation> implements SmsFlashPromotionProductRelationService {

    @Override
    public int create(List<SmsFlashPromotionProductRelation> relationList) {
        return saveBatch(relationList) ? relationList.size() : 0;
    }

    @Override
    public int update(Long id, SmsFlashPromotionProductRelation relation) {
        relation.setId(id);
        return updateById(relation) ? 1 : 0;
    }

    @Override
    public int delete(Long id) {
        return removeById(id) ? 1 : 0;
    }

    @Override
    public SmsFlashPromotionProductRelation getItem(Long id) {
        return getById(id);
    }

    @Override
    public Page<SmsFlashPromotionProduct> list(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize, Integer pageNum) {
        Page<SmsFlashPromotionProduct> page = new Page<>(pageNum,pageSize);

        return (Page<SmsFlashPromotionProduct>) baseMapper.getList(page,flashPromotionId,flashPromotionSessionId);
    }

    @Override
    public long getCount(Long flashPromotionId, Long flashPromotionSessionId) {
        QueryWrapper<SmsFlashPromotionProductRelation> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SmsFlashPromotionProductRelation::getFlashPromotionId,flashPromotionId)
                .eq(SmsFlashPromotionProductRelation::getFlashPromotionSessionId,flashPromotionSessionId);

        return count(wrapper);
    }
}
