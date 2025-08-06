package com.odk.apps.basedomain.domain;


import com.odk.apps.baseutil.entity.PermissionEntity;

/**
 * PermissionDomain
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2024/12/4
 */
public interface PermissionDomain {

    /**
     * 根据用户ID查找权限
     *
     * @param userId
     * @return
     */
    PermissionEntity getPermissionByUserId(String userId);
}
