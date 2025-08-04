package com.macro.mall.tiny.modules.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.sms.model.SmsFlashPromotion;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 限时购表 服务类
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-04
 */
public interface SmsFlashPromotionService extends IService<SmsFlashPromotion> {
    /**
     * 添加活动
     */
    int create(SmsFlashPromotion flashPromotion);

    /**
     * 修改指定活动
     */
    int update(Long id, SmsFlashPromotion flashPromotion);

    /**
     * 删除单个活动
     */
    int delete(Long id);

    /**
     * 修改活动上下线状态
     */
    int updateStatus(Long id, Integer status);

    /**
     * 获取活动详情
     */
    SmsFlashPromotion getItem(Long id);

    /**
     * 根据关键字分页查询活动
     */
    Page<SmsFlashPromotion> list(String keyword, Integer pageSize, Integer pageNum);
}
