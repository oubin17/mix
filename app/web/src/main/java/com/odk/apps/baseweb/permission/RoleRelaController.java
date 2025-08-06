package com.odk.apps.baseweb.permission;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.odk.apps.baseapi.inter.permission.RoleApi;
import com.odk.apps.baseutil.request.role.UserRoleRelaRequest;
import com.odk.apps.baseutil.response.PermissionQueryResponse;
import com.odk.base.vo.response.ServiceResponse;
import org.springframework.web.bind.annotation.*;

/**
 * 用户-角色关系管理
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/4/23
 */
@RestController
@RequestMapping("/role/rela")
public class RoleRelaController {

    private final RoleApi roleApi;

    public RoleRelaController(RoleApi roleApi) {
        this.roleApi = roleApi;
    }

    /**
     * 给用户添加角色
     *
     * @param relaRequest
     * @return
     */
    @SaCheckRole(value = {"ADMIN"})
    @PostMapping("/add")
    public ServiceResponse<String> addRoleRela(UserRoleRelaRequest relaRequest) {
        return this.roleApi.addRoleRela(relaRequest);
    }

    /**
     * 去除用户角色
     *
     * @param relaRequest
     * @return
     */
    @SaCheckRole(value = {"ADMIN"})
    @PostMapping("/delete")
    public ServiceResponse<Boolean> deleteRoleRela(UserRoleRelaRequest relaRequest) {
        return this.roleApi.deleteRoleRela(relaRequest);
    }

    /**
     * 查询当前用户角色权限
     *
     * @return
     */
    @GetMapping("/info")
    public ServiceResponse<PermissionQueryResponse> currentUserRoles() {
        return this.roleApi.userRoles(null);
    }

    /**
     * 查询用户角色权限
     *
     * @param userId
     * @return
     */
    @SaCheckRole(value = {"ADMIN"})
    @GetMapping("/userId")
    public ServiceResponse<PermissionQueryResponse> queryUserPermission(@RequestParam("userId") String userId) {
        return this.roleApi.userRoles(userId);
    }

}
