package com.odk.apps.baseapi.inter.user;


import com.odk.apps.baseutil.request.UserRegisterRequest;
import com.odk.base.vo.response.ServiceResponse;

/**
 * UserRegisterApi
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2024/11/5
 */
public interface UserRegisterApi {

    /**
     * 用户注册
     *
     * @param userRegisterRequest
     * @return
     */
    ServiceResponse<String> userRegister(UserRegisterRequest userRegisterRequest);

}
