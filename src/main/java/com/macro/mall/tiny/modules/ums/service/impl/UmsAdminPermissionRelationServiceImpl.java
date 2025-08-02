package com.macro.mall.tiny.modules.ums.service.impl;

import com.macro.mall.tiny.modules.ums.model.UmsAdminPermissionRelation;
import com.macro.mall.tiny.modules.ums.mapper.UmsAdminPermissionRelationMapper;
import com.macro.mall.tiny.modules.ums.service.UmsAdminPermissionRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限) 服务实现类
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-02
 */
@Service
public class UmsAdminPermissionRelationServiceImpl extends ServiceImpl<UmsAdminPermissionRelationMapper, UmsAdminPermissionRelation> implements UmsAdminPermissionRelationService {

}
