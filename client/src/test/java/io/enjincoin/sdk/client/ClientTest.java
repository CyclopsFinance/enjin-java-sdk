package io.enjincoin.sdk.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import io.enjincoin.sdk.client.config.Config;
import io.enjincoin.sdk.client.config.ImmutableConfig;
import io.enjincoin.sdk.client.config.ImmutablePlatform;
import io.enjincoin.sdk.client.config.Platform;
import io.enjincoin.sdk.client.service.events.SynchronousEventsService;
import io.enjincoin.sdk.client.service.identities.SynchronousIdentitiesService;
import io.enjincoin.sdk.client.service.notifications.NotificationsService;
import io.enjincoin.sdk.client.service.platform.SynchronousPlatformService;
import io.enjincoin.sdk.client.service.tokens.SynchronousTokensService;
import io.enjincoin.sdk.client.service.transactionrequests.SynchronousTransactionRequestsService;
import io.enjincoin.sdk.client.vo.identity.GetIdentityResponseVO;
import io.enjincoin.sdk.client.vo.identity.ImmutableFieldVO;

public class ClientTest {

    private ClientImpl client;
    private Config config;

    //TODO: Damien - temp details added for testing
    @Before
    public void setUp() {
       /* Platform trustedPlatform = ImmutablePlatform.builder()
                .setHost("localhost")
                .setPort(80)
                .setProtocol("http")
                .setBaseApiEndpoint("api")
                .build(); */
        Platform trustedPlatform = ImmutablePlatform.builder()
                .setHost("enjin.v16studios.co.uk")
                .setPort(443)
                .setProtocol("https")
                .setBaseApiEndpoint("api/v1")
                .build();
        this.config = ImmutableConfig.builder()
                .setInTestMode(false)
                .setTotalExecutors(1)
                .setTrustedPlatform(trustedPlatform)
                .build();

        this.client = new ClientImpl(this.config);
    }

    //TODO: Damien Remove - only temp
    @Test
    public void testTempIdentitiesService_GetIdentities() {
        SynchronousIdentitiesService identitiesService = this.client.getIdentitiesService();
        assertThat(identitiesService).isNotNull();

        GetIdentityResponseVO[] getIdentityResponseVOArray = identitiesService.getIdentitiesSync();
        for (GetIdentityResponseVO getIdentityResponseVO : getIdentityResponseVOArray) {
            System.out.println("getIdentityResponseVO:" + getIdentityResponseVO.getFields().get());
            if (getIdentityResponseVO.getFields().isPresent()) {

                for (ImmutableFieldVO fieldVO : getIdentityResponseVO.getFields().get()) {
                    System.out.println("fieldVO:" + fieldVO);
                }
            }
        }
    }

    //TODO: Damien Remove - only temp
    @Test
    public void testTempIdentitiesService_GetIdentity() {
        SynchronousIdentitiesService identitiesService = this.client.getIdentitiesService();
        assertThat(identitiesService).isNotNull();

        Integer identityId = 1;

        GetIdentityResponseVO getIdentityResponseVO = identitiesService.getIdentitySync(identityId);
        System.out.println("getIdentityResponseVO:" + getIdentityResponseVO);
        if (getIdentityResponseVO.getFields().isPresent()) {

            for (ImmutableFieldVO fieldVO : getIdentityResponseVO.getFields().get()) {
                System.out.println("fieldVO:" + fieldVO);
            }
        }
     }

    @Test
    public void testContructor_EnjinConfigIsNull() {
        this.config = null;
        this.client = new ClientImpl(this.config);
        assertThat(this.client).isNotNull();
    }

    @Test
    public void testContructor() {
        this.client = new ClientImpl(this.config);
        assertThat(this.client).isNotNull();
    }

    @Test
    public void testGetEventsService() {
        SynchronousEventsService eventsService = this.client.getEventsService();
        assertThat(eventsService).isNotNull();

        //Second time around - the existing service should be returned
        SynchronousEventsService eventsService2 = this.client.getEventsService();
        assertThat(eventsService2).isNotNull().isSameAs(eventsService);
    }

    @Test
    public void testGetIdentitiesService() {
        SynchronousIdentitiesService identitiesService = this.client.getIdentitiesService();
        assertThat(identitiesService).isNotNull();

        //Second time around - the existing service should be returned
        SynchronousIdentitiesService identitiesService2 = this.client.getIdentitiesService();
        assertThat(identitiesService2).isNotNull().isSameAs(identitiesService);
    }

    @Test
    public void testGetTokensService() {
        SynchronousTokensService tokensService = this.client.getTokensService();
        assertThat(tokensService).isNotNull();

        //Second time around - the existing service should be returned
        SynchronousTokensService tokensService2 = this.client.getTokensService();
        assertThat(tokensService2).isNotNull().isSameAs(tokensService);
    }

    @Test
    public void testGetTransactionRequestsService() {
        SynchronousTransactionRequestsService transactionRequestsService = this.client.getTransactionRequestsService();
        assertThat(transactionRequestsService).isNotNull();

        //Second time around - the existing service should be returned
        SynchronousTransactionRequestsService transactionRequestsService2 = this.client.getTransactionRequestsService();
        assertThat(transactionRequestsService2).isNotNull().isSameAs(transactionRequestsService);
    }


    @Test
    public void testGetNotificationsService() {
        NotificationsService notificationsService = this.client.getNotificationsService();
        assertThat(notificationsService).isNotNull();

        //Second time around - the existing service should be returned
        NotificationsService notificationsService2 = this.client.getNotificationsService();
        assertThat(notificationsService2).isNotNull().isSameAs(notificationsService);
    }

    @Test
    public void testGetPlatformService() {
        SynchronousPlatformService platformService = this.client.getPlatformService();
        assertThat(platformService).isNotNull();

        //Second time around - the existing service should be returned
        SynchronousPlatformService platformService2 = this.client.getPlatformService();
        assertThat(platformService2).isNotNull().isSameAs(platformService2);
    }
}
