package com.macro.mall.tiny.modules.ums.service;

import com.macro.mall.tiny.modules.ums.model.UmsMemberLevel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 会员等级表 服务类
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-02
 */
public interface UmsMemberLevelService extends IService<UmsMemberLevel> {
    /**
     * 获取所有会员等级
     * @param defaultStatus 是否为默认会员
     */
    List<UmsMemberLevel> list(Integer defaultStatus);
}
