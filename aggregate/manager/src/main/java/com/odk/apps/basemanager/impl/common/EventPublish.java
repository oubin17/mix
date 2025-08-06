package com.odk.apps.basemanager.impl.common;

import com.odk.apps.basemanager.api.common.IEventPublish;
import com.odk.apps.baseutil.event.UserCacheCleanEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * EventPublish
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/8/5
 */
@Service
public class EventPublish implements IEventPublish {

    private final ApplicationEventPublisher eventPublisher;

    public EventPublish(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void publish(UserCacheCleanEvent event) {
        eventPublisher.publishEvent(event);
    }
}
