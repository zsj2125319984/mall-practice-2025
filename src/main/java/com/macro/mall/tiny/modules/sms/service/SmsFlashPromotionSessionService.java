package com.macro.mall.tiny.modules.sms.service;

import com.macro.mall.tiny.modules.sms.dto.SmsFlashPromotionSessionDetail;
import com.macro.mall.tiny.modules.sms.model.SmsFlashPromotionSession;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 限时购场次表 服务类
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-04
 */
public interface SmsFlashPromotionSessionService extends IService<SmsFlashPromotionSession> {
    /**
     * 添加场次
     */
    int create(SmsFlashPromotionSession promotionSession);

    /**
     * 修改场次
     */
    int update(Long id, SmsFlashPromotionSession promotionSession);

    /**
     * 修改场次启用状态
     */
    int updateStatus(Long id, Integer status);

    /**
     * 删除场次
     */
    int delete(Long id);

    /**
     * 获取场次详情
     */
    SmsFlashPromotionSession getItem(Long id);

    /**
     * 获取全部可选场次及其数量
     */
    List<SmsFlashPromotionSessionDetail> selectList(Long flashPromotionId);
}
