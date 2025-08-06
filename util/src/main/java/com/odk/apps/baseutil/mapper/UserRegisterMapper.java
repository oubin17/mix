package com.odk.apps.baseutil.mapper;

import com.odk.apps.baseutil.dto.user.UserRegisterDTO;
import com.odk.apps.baseutil.request.UserRegisterRequest;
import org.mapstruct.Mapper;

/**
 * UserRegisterMapper
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/3/23
 */
@Mapper(componentModel = "spring")
public interface UserRegisterMapper {

    UserRegisterDTO toDTO(UserRegisterRequest userRegisterRequest);
}
