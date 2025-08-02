package com.macro.mall.tiny.modules.cms.service.impl;

import com.macro.mall.tiny.modules.cms.model.CmsPrefrenceArea;
import com.macro.mall.tiny.modules.cms.mapper.CmsPrefrenceAreaMapper;
import com.macro.mall.tiny.modules.cms.service.CmsPrefrenceAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 优选专区 服务实现类
 * </p>
 *
 * @author Sicecream
 * @since 2025-07-28
 */
@Service
public class CmsPrefrenceAreaServiceImpl extends ServiceImpl<CmsPrefrenceAreaMapper, CmsPrefrenceArea> implements CmsPrefrenceAreaService {

    @Override
    public List<CmsPrefrenceArea> listAll() {
        return list();
    }
}
