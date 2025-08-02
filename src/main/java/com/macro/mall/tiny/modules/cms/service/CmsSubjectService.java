package com.macro.mall.tiny.modules.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.cms.model.CmsSubject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 专题表 服务类
 * </p>
 *
 * @author Sicecream
 * @since 2025-07-28
 */
public interface CmsSubjectService extends IService<CmsSubject> {
    /**
     * 查询所有专题
     */
    List<CmsSubject> listAll();

    /**
     * 分页查询专题
     */
    Page<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize);
}
