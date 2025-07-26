package com.macro.mall.tiny.modules.ums.controller;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.common.api.CommonPage;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.modules.ums.dto.UmsAdminLoginParam;
import com.macro.mall.tiny.modules.ums.dto.UmsAdminParam;
import com.macro.mall.tiny.modules.ums.dto.UpdateAdminPasswordParam;
import com.macro.mall.tiny.modules.ums.model.UmsAdmin;
import com.macro.mall.tiny.modules.ums.model.UmsRole;
import com.macro.mall.tiny.modules.ums.service.UmsAdminService;
import com.macro.mall.tiny.modules.ums.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 后台用户管理
 * Created by macro on 2018/4/26.
 */
@Controller
@Api(tags = "UmsAdminController")
@Tag(name = "UmsAdminController",description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private UmsRoleService roleService;

    /**
     * 用户注册
     * @param umsAdminParam
     * @return {@link CommonResult }<{@link UmsAdmin }>
     */
    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@Validated @RequestBody UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);

        //结果为空则注册失败
        if(umsAdmin == null){
            return CommonResult.failed();
        }

        return CommonResult.success(umsAdmin);
    }

    /**
     * 登录以后返回token
     * @param umsAdminLoginParam
     * @return {@link CommonResult }
     */
    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam) {
        //登录操作
        String token = adminService.login(umsAdminLoginParam.getUsername(),
                umsAdminLoginParam.getPassword());

        //验证token
        if(token == null){
            return CommonResult.validateFailed("用户名或密码错误");
        }

        //包装token
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        map.put("tokenHead",tokenHead);

        return CommonResult.success(map);
    }

    /**
     * 刷新token
     * @param request
     * @return {@link CommonResult }
     */
    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult refreshToken(HttpServletRequest request) {
        String refreshedToken = adminService.refreshToken(request.getHeader(tokenHeader));

        if(refreshedToken == null){
            CommonResult.failed("token已过期");
        }

        Map<String,Object> map = new HashMap<>();
        map.put("token",refreshedToken);
        map.put("tokenHead",tokenHead);

        return CommonResult.success(map);
    }

    /**
     * 获取当前登录用户信息
     * @param principal
     * @return {@link CommonResult }
     */
    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAdminInfo(Principal principal) {
        //验证用户名是否为空
        if(principal == null){
            return CommonResult.unauthorized(null);
        }
        //获得data信息并返回
        UmsAdmin admin = adminService.getAdminByUsername(principal.getName());

        Map<String,Object> data = new HashMap<>();
        data.put("username",admin.getUsername());
        data.put("menus",roleService.getMenuList(admin.getId()));
        data.put("icon",admin.getIcon());

        List<UmsRole> roleList = adminService.getRoleList(admin.getId());
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }

        return CommonResult.success(data);
    }

    /**
     * 登出
     * @return {@link CommonResult }
     */
    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout() {
        return CommonResult.success(null);
    }

    /**
     * 根据用户名或姓名分页获取用户列表
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return {@link CommonResult }<{@link CommonPage }<{@link UmsAdmin }>>
     */
    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsAdmin>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<UmsAdmin> page = adminService.list(keyword, pageSize, pageNum);

        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation("获取指定用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsAdmin> getItem(@PathVariable Long id) {
        UmsAdmin umsAdmin = adminService.getById(id);

        return CommonResult.success(umsAdmin);
    }

    /**
     * 修改指定用户信息
     * @param id
     * @param admin
     * @return {@link CommonResult }
     */
    @ApiOperation("修改指定用户信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody UmsAdmin admin) {
        boolean success = adminService.update(id, admin);

        if(success){
            return CommonResult.success(null);
        }

        return CommonResult.failed();
    }

    /**
     * 修改指定用户密码
     * @param updatePasswordParam
     * @return {@link CommonResult }
     */
    @ApiOperation("修改指定用户密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@Validated @RequestBody UpdateAdminPasswordParam updatePasswordParam) {
        int status = adminService.updatePassword(updatePasswordParam);

        if(status > 0){
            return CommonResult.success(status);
        } else if (status == -1) {
            return CommonResult.failed("参数有误");
        }else if(status == -2){
            return CommonResult.failed("用户不存在");
        }else if(status == -3){
            return CommonResult.failed("旧密码错误");
        }

        return CommonResult.failed();
    }

    /**
     * 删除指定用户信息
     * @param id
     * @return {@link CommonResult }
     */
    @ApiOperation("删除指定用户信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        boolean success = adminService.delete(id);

        if(success){
            CommonResult.success(null);
        }

        return CommonResult.failed();
    }

    /**
     * 修改帐号状态
     * @param id
     * @param status
     * @return {@link CommonResult }
     */
    @ApiOperation("修改帐号状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable Long id,@RequestParam(value = "status") Integer status) {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setStatus(status);

        boolean success = adminService.update(id,umsAdmin);

        if (success) {
            return CommonResult.success(null);
        }

        return CommonResult.failed();
    }

    /**
     * 给管理分配角色
     * @param adminId
     * @param roleIds
     * @return {@link CommonResult }
     */
    @ApiOperation("给管理分配角色")
    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateRole(@RequestParam("adminId") Long adminId,
                                   @RequestParam("roleIds") List<Long> roleIds) {
        int count = adminService.updateRole(adminId, roleIds);

        if(count >= 0){
            return CommonResult.success(count);
        }

        return CommonResult.failed();
    }

    /**
     * 获取指定用户的角色
     * @param adminId
     * @return {@link CommonResult }<{@link List }<{@link UmsRole }>>
     */
    @ApiOperation("获取指定用户的角色")
    @RequestMapping(value = "/role/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsRole>> getRoleList(@PathVariable Long adminId) {
        List<UmsRole> roleList = adminService.getRoleList(adminId);

        return CommonResult.success(roleList);
    }
}
