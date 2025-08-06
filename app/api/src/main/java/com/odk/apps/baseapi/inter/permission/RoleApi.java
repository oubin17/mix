package com.odk.apps.baseapi.inter.permission;


import com.odk.apps.baseutil.dto.permission.UserRoleDTO;
import com.odk.apps.baseutil.request.role.RoleAddRequest;
import com.odk.apps.baseutil.request.role.UserRoleRelaRequest;
import com.odk.apps.baseutil.response.PermissionQueryResponse;
import com.odk.base.vo.response.ServiceResponse;

import java.util.List;

/**
 * RoleApi
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/2/27
 */
public interface RoleApi {

    /**
     * 用户权限
     *
     * @param userId
     * @return
     */
    ServiceResponse<PermissionQueryResponse> userRoles(String userId);

    /**
     * 添加角色
     *
     * @param roleAddRequest
     * @return
     */
    ServiceResponse<String> addRole(RoleAddRequest roleAddRequest);

    /**
     * 添加角色
     *
     * @param roleId
     * @return
     */
    ServiceResponse<Boolean> deleteRole(String roleId);

    /**
     * 角色列表
     *
     * @return
     */
    ServiceResponse<List<UserRoleDTO>> roleList();

    /**
     * 添加用户角色
     *
     * @param relaRequest
     * @return
     */
    ServiceResponse<String> addRoleRela(UserRoleRelaRequest relaRequest);

    /**
     * 去除用户角色
     *
     * @param relaRequest
     * @return
     */
    ServiceResponse<Boolean> deleteRoleRela(UserRoleRelaRequest relaRequest);
}
