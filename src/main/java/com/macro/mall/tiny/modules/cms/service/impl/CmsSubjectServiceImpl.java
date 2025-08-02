package com.macro.mall.tiny.modules.cms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.cms.model.CmsSubject;
import com.macro.mall.tiny.modules.cms.mapper.CmsSubjectMapper;
import com.macro.mall.tiny.modules.cms.service.CmsSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 专题表 服务实现类
 * </p>
 *
 * @author Sicecream
 * @since 2025-07-28
 */
@Service
public class CmsSubjectServiceImpl extends ServiceImpl<CmsSubjectMapper, CmsSubject> implements CmsSubjectService {

    @Override
    public List<CmsSubject> listAll() {
        return list();
    }

    @Override
    public Page<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize) {
        Page<CmsSubject> page = new Page<>(pageNum,pageSize);
        QueryWrapper<CmsSubject> wrapper = new QueryWrapper<>();

        if(StrUtil.isNotEmpty(keyword)){
            wrapper.lambda().like(CmsSubject::getTitle,keyword);
        }

        return page(page,wrapper);
    }
}
