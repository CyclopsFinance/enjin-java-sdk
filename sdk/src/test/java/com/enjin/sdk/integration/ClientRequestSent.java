package com.enjin.sdk.integration;

import com.enjin.sdk.PlatformUtils;
import com.enjin.sdk.RetrofitUtils.StubFailedCall;
import com.enjin.sdk.RetrofitUtils.StubSuccessfulCall;
import com.enjin.sdk.graphql.GraphQLResponse;
import com.enjin.sdk.http.HttpCallback;
import com.enjin.sdk.schemas.BaseSchema;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import retrofit2.Call;

@ExtendWith(EasyMockExtension.class)
public class ClientRequestSent {

    @TestSubject
    private final TestableSchema schema = new TestableSchema();

    @Mock
    private HttpCallback<GraphQLResponse<Object>> mockCallback;

    @BeforeEach
    void beforeEach() {
        reset(mockCallback);
    }

    @Test
    void test_SuccessfulCall_CallbackUsed() {
        // Arrange - Data
        final StubSuccessfulCall<Object> stubCall = new StubSuccessfulCall<>();

        // Arrange - Expectations
        mockCallback.onComplete(anyObject());
        replay(mockCallback);

        // Act
        schema.sendRequest(stubCall, mockCallback);

        // Assert
        verify(mockCallback);
    }

    @Test
    void test_FailedCall_CallbackNotUsed() {
        // Arrange - Data
        final StubFailedCall<Object> stubCall = new StubFailedCall<>();

        // Arrange - Expectations
        replay(mockCallback);

        // Act
        schema.sendRequest(stubCall, mockCallback);

        // Assert
        verify(mockCallback);
    }

    private static class TestableSchema extends BaseSchema {

        public TestableSchema() {
            super(PlatformUtils.createMockMiddleware(), "");
        }

        @Override
        public <T> void sendRequest(Call<GraphQLResponse<T>> call, final HttpCallback<GraphQLResponse<T>> callback) {
            super.sendRequest(call, callback);
        }

    }

}
