package com.enjin.sdk;

import com.enjin.sdk.events.PusherEventListener;
import com.enjin.sdk.events.PusherNotificationService;
import com.pusher.client.channel.PusherEvent;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class PusherUtils {

    public PusherNotificationService defaultPusherNotificationService() {
        return new PusherNotificationService(PlatformUtils.KOVAN);
    }

    public PusherEvent createValidFakePusherEvent() {
        Map<String, Object> data = new HashMap<>();
        data.put("event", "EnjinCloud\\Events\\AppCreated");
        data.put("channel", "");
        data.put("data", "");

        return new PusherEvent(data);
    }

    public PusherEvent createInvalidFakePusherEvent() {
        Map<String, Object> data = new HashMap<>();
        data.put("event", "");
        data.put("channel", "");
        data.put("data", "");

        return new PusherEvent(data);
    }

    @SneakyThrows
    public PusherEventListener getListenerFromService(@NonNull PusherNotificationService service) {
        Class<? extends PusherNotificationService> c = service.getClass();
        Field listenerField = c.getDeclaredField("listener");
        listenerField.setAccessible(true);
        return (PusherEventListener) listenerField.get(service);
    }

}
