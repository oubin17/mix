package com.odk.apps.basemanager.impl.user;

import com.odk.apps.basedomain.domain.UserQueryDomain;
import com.odk.apps.basedomain.domain.criteria.UserQueryCriteria;
import com.odk.apps.basedomain.model.user.UserAccessTokenDO;
import com.odk.apps.basedomain.repository.user.UserAccessTokenRepository;
import com.odk.apps.basemanager.api.user.IUserQueryManager;
import com.odk.apps.baseutil.entity.UserEntity;
import com.odk.apps.baseutil.enums.UserQueryTypeEnum;
import com.odk.base.context.TenantIdContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserQueryManager
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2024/11/5
 */
@Slf4j
@Service
public class UserQueryManager implements IUserQueryManager {

    private UserAccessTokenRepository accessTokenRepository;

    private UserQueryDomain userQueryDomain;

    /**
     * 根据userId查找用户
     *
     * @param userId
     * @return
     */
    @Override
    public UserEntity queryByUserId(String userId) {
        return this.userQueryDomain.queryUser(
                UserQueryCriteria.builder()
                .queryType(UserQueryTypeEnum.USER_ID)
                .userId(userId)
                .build()
        );
    }

    /**
     * 从 session 中获取用户信息
     *
     * @return
     */
    @Override
    public UserEntity queryBySession() {
        return this.userQueryDomain.queryUser(
                UserQueryCriteria.builder()
                .queryType(UserQueryTypeEnum.SESSION)
                .build()
        );
    }

    /**
     * 根据登录凭证查找用户
     *
     * @param tokenType
     * @param tokenValue
     * @return
     */
    @Override
    public UserEntity queryByAccessToken(String tokenType, String tokenValue) {

        UserAccessTokenDO userAccessTokenDO = accessTokenRepository.findByTokenTypeAndTokenValueAndTenantId(tokenType, tokenValue, TenantIdContext.getTenantId());
        if (null == userAccessTokenDO) {
            return null;
        }
        UserQueryCriteria build = UserQueryCriteria.builder()
                .queryType(UserQueryTypeEnum.USER_ID)
                .userId(userAccessTokenDO.getUserId())
                .build();
        return this.userQueryDomain.queryUser(build);
    }

    @Autowired
    public void setAccessTokenRepository(UserAccessTokenRepository accessTokenRepository) {
        this.accessTokenRepository = accessTokenRepository;
    }

    @Autowired
    public void setUserQueryDomain(UserQueryDomain userQueryDomain) {
        this.userQueryDomain = userQueryDomain;
    }
}
