package io.enjincoin.sdk.client.service.legacy.events;

import io.enjincoin.sdk.client.vo.event.GetEventRequestVO;
import io.enjincoin.sdk.client.vo.event.GetEventResponseVO;

import java.util.concurrent.CompletableFuture;

/**
 * Asynchronous Events service interface.
 */
public interface AsynchronousEventsService {

    /**
     * Method to get an event.
     *
     * @param request - get event request vo
     *
     * @return - GetEventResponseVO
     */
    CompletableFuture<GetEventResponseVO[]> getEventsAsync(GetEventRequestVO request);
}
