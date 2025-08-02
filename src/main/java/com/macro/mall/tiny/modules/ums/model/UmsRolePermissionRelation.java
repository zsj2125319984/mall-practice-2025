package com.macro.mall.tiny.modules.ums.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 后台用户角色和权限关系表
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-02
 */
@Getter
@Setter
@TableName("ums_role_permission_relation")
@ApiModel(value = "UmsRolePermissionRelation对象", description = "后台用户角色和权限关系表")
public class UmsRolePermissionRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long roleId;

    private Long permissionId;


}
