package com.odk.apps.basemanager.api.user;


import com.odk.apps.baseutil.entity.UserEntity;

/**
 * IUserQueryManager
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/8/1
 */
public interface IUserQueryManager {

    UserEntity queryByUserId(String userId);

    UserEntity queryBySession();

    UserEntity queryByAccessToken(String tokenType, String tokenValue);
}
