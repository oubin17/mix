package com.odk.apps.baseutil.mapper;

import com.odk.apps.baseutil.dto.user.UserLoginDTO;
import com.odk.apps.baseutil.entity.UserEntity;
import com.odk.apps.baseutil.request.UserLoginRequest;
import com.odk.apps.baseutil.response.UserLoginResponse;
import org.mapstruct.Mapper;

/**
 * UserLoginMapper
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/3/23
 */
@Mapper(componentModel = "spring")
public interface UserLoginMapper {

    UserLoginDTO toDTO(UserLoginRequest userLoginRequest);

    UserLoginResponse toResponse(UserEntity userEntity);
}
