package com.odk.apps.baseservice.impl.user;

import com.odk.apps.baseapi.inter.user.UserProfileApi;
import com.odk.apps.basemanager.api.user.IUserProfileManager;
import com.odk.apps.baseservice.mix.AbstractApiImpl;
import com.odk.apps.baseutil.dto.user.UserProfileDTO;
import com.odk.apps.baseutil.enums.BizScene;
import com.odk.apps.baseutil.mapper.UserProfileRequestMapper;
import com.odk.apps.baseutil.request.UserProfileRequest;
import com.odk.apps.baseutil.userinfo.SessionContext;
import com.odk.base.vo.request.BaseRequest;
import com.odk.base.vo.response.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserProfileService
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/8/4
 */
@Slf4j
@Service
public class UserProfileService extends AbstractApiImpl implements UserProfileApi {

    private UserProfileRequestMapper userProfileRequestMapper;

    private IUserProfileManager userProfileManager;

    @Override
    public ServiceResponse<Boolean> updateUserProfile(UserProfileRequest userProfileRequest) {
        return super.strictBizProcess(BizScene.USER_PROFILE_UPDATE, userProfileRequest, new StrictApiCallBack<Boolean, Boolean>() {

            @Override
            protected Object convert(BaseRequest request) {
                UserProfileRequest profileRequest = (UserProfileRequest) request;
                UserProfileDTO dto = userProfileRequestMapper.toDTO(profileRequest);
                dto.setUserId(SessionContext.getLoginIdWithCheck());
                return dto;
            }

            @Override
            protected Boolean doProcess(Object args) {
                UserProfileDTO profileRequest = (UserProfileDTO) args;
                return userProfileManager.updateUserProfile(profileRequest);
            }

            @Override
            protected Boolean convertResult(Boolean apiResult) {
                return apiResult;
            }
        });
    }


    @Autowired
    public void setUserProfileRequestMapper(UserProfileRequestMapper userProfileRequestMapper) {
        this.userProfileRequestMapper = userProfileRequestMapper;
    }

    @Autowired
    public void setUserProfileManager(IUserProfileManager userProfileManager) {
        this.userProfileManager = userProfileManager;
    }
}
