package com.enjin.sdk.integration;

import com.enjin.sdk.PusherUtils;
import com.enjin.sdk.events.NotificationListener;
import com.enjin.sdk.events.PusherEventListener;
import com.enjin.sdk.events.PusherNotificationService;
import com.pusher.client.channel.PusherEvent;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(EasyMockExtension.class)
public class PusherEventPassingUnknownEvent {

    @TestSubject
    private final PusherNotificationService service = PusherUtils.defaultPusherNotificationService();

    @Mock
    private NotificationListener mockListener;

    @Test
    void test() {
        // Arrange - Data
        service.registerListenerWithMatcher(mockListener, event -> true);
        service.start();
        final PusherEventListener pusherListener = PusherUtils.getListenerFromService(service);
        final PusherEvent fakeEvent = PusherUtils.createInvalidFakePusherEvent();

        // Arrange - Expectations
        replay(mockListener);

        // Act
        pusherListener.onEvent(fakeEvent);

        // Assert
        verify(mockListener);
    }

}
