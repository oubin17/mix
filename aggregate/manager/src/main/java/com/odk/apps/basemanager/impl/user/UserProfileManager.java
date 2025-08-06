package com.odk.apps.basemanager.impl.user;

import com.odk.apps.basedomain.mapper.UserProfileMapper;
import com.odk.apps.basedomain.model.user.UserProfileDO;
import com.odk.apps.basedomain.repository.user.UserProfileRepository;
import com.odk.apps.basemanager.api.common.IEventPublish;
import com.odk.apps.basemanager.api.user.IUserProfileManager;
import com.odk.apps.baseutil.dto.user.UserProfileDTO;
import com.odk.apps.baseutil.event.UserCacheCleanEvent;
import com.odk.base.context.TenantIdContext;
import com.odk.base.enums.cache.CacheActionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* UserProfileManager
*   
* @description:    
* @version:        1.0
* @author:         oubin on 2025/8/4
*/
@Slf4j
@Service
public class UserProfileManager implements IUserProfileManager {

    private UserProfileRepository userProfileRepository;

    private UserProfileMapper userProfileMapper;

    private IEventPublish eventPublish;


    @Override
    public boolean updateUserProfile(UserProfileDTO userProfileDTO) {
        UserProfileDO userProfileDO = userProfileRepository.findByUserIdAndTenantId(userProfileDTO.getUserId(), TenantIdContext.getTenantId());
        userProfileMapper.merge(userProfileDTO, userProfileDO);
        userProfileRepository.save(userProfileDO);
        eventPublish.publish(new UserCacheCleanEvent(userProfileDTO.getUserId(), CacheActionEnum.UPDATE));

        return true;
    }

    @Autowired
    public void setUserProfileRepository(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Autowired
    public void setUserProfileMapper(UserProfileMapper userProfileMapper) {
        this.userProfileMapper = userProfileMapper;
    }

    @Autowired
    public void setEventPublish(IEventPublish eventPublish) {
        this.eventPublish = eventPublish;
    }
}
