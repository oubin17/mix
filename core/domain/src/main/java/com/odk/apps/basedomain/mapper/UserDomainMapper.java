package com.odk.apps.basedomain.mapper;


import com.odk.apps.basedomain.model.permission.UserRoleDO;
import com.odk.apps.basedomain.model.user.UserAccessTokenDO;
import com.odk.apps.basedomain.model.user.UserBaseDO;
import com.odk.apps.basedomain.model.user.UserProfileDO;
import com.odk.apps.baseutil.dto.permission.UserRoleDTO;
import com.odk.apps.baseutil.entity.AccessTokenEntity;
import com.odk.apps.baseutil.entity.UserEntity;
import com.odk.apps.baseutil.entity.UserProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * UserDomainMapper
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/3/23
 */
@Mapper(componentModel = "spring")
public interface UserDomainMapper {

    @Mapping(source = "id", target = "userId")
    UserEntity toEntity(UserBaseDO baseDO);

    AccessTokenEntity toEntity(UserAccessTokenDO userAccessTokenDO);

    UserProfileEntity toEntity(UserProfileDO userProfileDO);

    UserRoleDTO toDTO(UserRoleDO userRoleDO);
}
