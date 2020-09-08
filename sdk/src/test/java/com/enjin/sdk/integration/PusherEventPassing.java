package com.enjin.sdk.integration;

import com.enjin.sdk.PlatformUtils;
import com.enjin.sdk.events.NotificationListener;
import com.enjin.sdk.events.PusherEventListener;
import com.enjin.sdk.events.PusherNotificationService;
import com.pusher.client.channel.PusherEvent;
import lombok.NonNull;
import lombok.SneakyThrows;
import static org.easymock.EasyMock.*;
import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(EasyMockExtension.class)
public class PusherEventPassing {

    @TestSubject
    private PusherNotificationService service;

    @Mock
    private NotificationListener mockListener;

    @BeforeEach
    void beforeEach() {
        reset(mockListener);
    }

    @Test
    void test_AllowedEvent_RegisteredListenerReceivesEvent() {
        // Arrange - Data
        service = defaultPusherNotificationService();
        service.registerListenerWithMatcher(mockListener, event -> true);
        service.start();
        final PusherEventListener eventListener = getListenerFromService(service);
        final PusherEvent fakeEvent = createValidFakePusherEvent();

        // Arrange - Expectations
        mockListener.notificationReceived(anyObject());
        replay(mockListener);

        // Act
        eventListener.onEvent(fakeEvent);

        // Assert
        verify(mockListener);
    }

    @Test
    void test_IgnoredEvent_RegisteredListenerDoesNotReceiveEvent() {
        // Arrange - Data
        service = defaultPusherNotificationService();
        service.registerListenerWithMatcher(mockListener, event -> false);
        service.start();
        final PusherEventListener eventListener = getListenerFromService(service);
        final PusherEvent fakeEvent = createValidFakePusherEvent();

        // Arrange - Expectations
        replay(mockListener);

        // Act
        eventListener.onEvent(fakeEvent);

        // Assert
        verify(mockListener);
    }

    @Test
    void test_UnknownEvent_RegisteredListenerDoesNotReceiveEvent() {
        // Arrange - Data
        service = defaultPusherNotificationService();
        service.registerListenerWithMatcher(mockListener, event -> false);
        service.start();
        final PusherEventListener eventListener = getListenerFromService(service);
        final PusherEvent fakeEvent = createInvalidFakePusherEvent();

        // Arrange - Expectations
        replay(mockListener);

        // Act
        eventListener.onEvent(fakeEvent);

        // Assert
        verify(mockListener);
    }

    private static PusherNotificationService defaultPusherNotificationService() {
        return new PusherNotificationService(PlatformUtils.KOVAN);
    }

    private static PusherEvent createValidFakePusherEvent() {
        Map<String, Object> data = new HashMap<>();
        data.put("event", "EnjinCloud\\Events\\AppCreated");
        data.put("channel", "");
        data.put("data", "");

        return new PusherEvent(data);
    }

    private static PusherEvent createInvalidFakePusherEvent() {
        Map<String, Object> data = new HashMap<>();
        data.put("event", "");
        data.put("channel", "");
        data.put("data", "");

        return new PusherEvent(data);
    }

    @SneakyThrows
    private static PusherEventListener getListenerFromService(@NonNull PusherNotificationService service) {
        Class<? extends PusherNotificationService> c = service.getClass();
        Field listenerField = c.getDeclaredField("listener");
        listenerField.setAccessible(true);
        return (PusherEventListener) listenerField.get(service);
    }

}
