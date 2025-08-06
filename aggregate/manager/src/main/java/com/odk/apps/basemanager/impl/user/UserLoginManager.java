package com.odk.apps.basemanager.impl.user;

import com.odk.apps.basedomain.domain.UserQueryDomain;
import com.odk.apps.basedomain.domain.criteria.UserQueryCriteria;
import com.odk.apps.basedomain.model.user.UserBaseDO;
import com.odk.apps.basedomain.model.user.UserIdentificationDO;
import com.odk.apps.basedomain.repository.user.UserBaseRepository;
import com.odk.apps.basedomain.repository.user.UserIdentificationRepository;
import com.odk.apps.baseinfra.security.IEncryption;
import com.odk.apps.basemanager.api.common.IEventPublish;
import com.odk.apps.basemanager.api.user.IUserLoginManager;
import com.odk.apps.basemanager.impl.verificationcode.VerificationCodeManager;
import com.odk.apps.baseutil.dto.user.UserLoginDTO;
import com.odk.apps.baseutil.entity.UserEntity;
import com.odk.apps.baseutil.enums.UserQueryTypeEnum;
import com.odk.apps.baseutil.event.UserCacheCleanEvent;
import com.odk.apps.baseutil.userinfo.SessionContext;
import com.odk.base.context.TenantIdContext;
import com.odk.base.enums.cache.CacheActionEnum;
import com.odk.base.enums.user.IdentificationTypeEnum;
import com.odk.base.exception.AssertUtil;
import com.odk.base.exception.BizErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * UserLoginManager
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2024/11/5
 */
@Service
public class UserLoginManager implements IUserLoginManager {

    private UserBaseRepository baseRepository;

    private VerificationCodeManager verificationCodeManager;

    private UserQueryDomain userQueryDomain;

    private UserIdentificationRepository identificationRepository;

    private IEncryption encryption;

    private IEventPublish eventPublish;

    /**
     * 用户登录
     *
     * @param userLoginDTO
     * @return
     */
    @Override
    public UserEntity userLogin(UserLoginDTO userLoginDTO) {
        UserEntity userEntity = null;
        String identifyType = userLoginDTO.getIdentifyType();
        if (IdentificationTypeEnum.PASSWORD.getCode().equals(identifyType)) {
            UserQueryCriteria build = UserQueryCriteria.builder()
                    .queryType(UserQueryTypeEnum.LOGIN_ID)
                    .loginId(userLoginDTO.getLoginId())
                    .loginType(userLoginDTO.getLoginType())
                    .build();
            userEntity = userQueryDomain.queryUser(build);
            UserIdentificationDO userIdentificationDO = identificationRepository.findByUserIdAndIdentifyTypeAndTenantId(userEntity.getUserId(), userLoginDTO.getIdentifyType(), TenantIdContext.getTenantId());

            String decrypt = encryption.rsaDecode(userLoginDTO.getIdentifyValue());
            AssertUtil.isTrue(encryption.bcryptMatches(decrypt, userIdentificationDO.getIdentifyValue()), BizErrorCode.IDENTIFICATION_NOT_MATCH);
        } else if (IdentificationTypeEnum.VERIFICATION_CODE.getCode().equals(identifyType)) {
            verificationCodeManager.compareAndIncr(userLoginDTO.getVerificationCode());
            UserQueryCriteria build = UserQueryCriteria.builder()
                    .queryType(UserQueryTypeEnum.LOGIN_ID)
                    .loginId(userLoginDTO.getLoginId())
                    .loginType(userLoginDTO.getLoginType())
                    .build();
            userEntity = userQueryDomain.queryUser(build);
        }
        if (userEntity != null) {
            //设置登录session
            SessionContext.createLoginSession(userEntity.getUserId());
        }
        return userEntity;
    }

    @Override
    public Boolean userLogout() {
        Optional<UserBaseDO> byUserId = baseRepository.findByIdAndTenantId(SessionContext.getLoginIdWithCheck(), TenantIdContext.getTenantId());
        AssertUtil.isTrue(byUserId.isPresent(), BizErrorCode.USER_NOT_EXIST, "用户ID不存在");
        eventPublish.publish(new UserCacheCleanEvent(byUserId.get().getId(), CacheActionEnum.DELETE));
        SessionContext.logOut();
        return true;
    }


    @Autowired
    public void setBaseRepository(UserBaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Autowired
    public void setVerificationCodeManager(VerificationCodeManager verificationCodeManager) {
        this.verificationCodeManager = verificationCodeManager;
    }

    @Autowired
    public void setUserQueryDomain(UserQueryDomain userQueryDomain) {
        this.userQueryDomain = userQueryDomain;
    }

    @Autowired
    public void setIdentificationRepository(UserIdentificationRepository identificationRepository) {
        this.identificationRepository = identificationRepository;
    }

    @Autowired
    public void setEncryption(IEncryption encryption) {
        this.encryption = encryption;
    }

    @Autowired
    public void setEventPublish(IEventPublish eventPublish) {
        this.eventPublish = eventPublish;
    }
}
