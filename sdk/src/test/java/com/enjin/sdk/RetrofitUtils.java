package com.enjin.sdk;

import com.enjin.sdk.graphql.GraphQLResponse;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;

@UtilityClass
public class RetrofitUtils {

    @SneakyThrows
    private <T> Response<GraphQLResponse<T>> createFakeResponse() {
        Class<Response> c = Response.class;
        Constructor<Response> constructor = c.getDeclaredConstructor(okhttp3.Response.class,
                                                                     Object.class,
                                                                     ResponseBody.class);
        constructor.setAccessible(true);
        okhttp3.Response response = new okhttp3.Response(createFakeRequest(),
                                                         Protocol.HTTP_1_0,
                                                         "",
                                                         0,
                                                         null,
                                                         createFakeHeaders(),
                                                         null,
                                                         null,
                                                         null,
                                                         null,
                                                         0,
                                                         0,
                                                         null);

        return (Response<GraphQLResponse<T>>) constructor.newInstance(response, null, null);
    }

    private Request createFakeRequest() {
        return new Request(HttpUrl.get("http://localhost/"),
                           "",
                           createFakeHeaders(),
                           createFakeRequestBody(),
                           new HashMap<>());
    }

    private RequestBody createFakeRequestBody() {
        return new RequestBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException { }
        };
    }

    @SneakyThrows
    private Headers createFakeHeaders() {
        Class<Headers> c = Headers.class;
        Constructor<Headers> constructor = c.getDeclaredConstructor(String[].class);
        constructor.setAccessible(true);

        return constructor.newInstance((Object) new String[0]);
    }

    public static abstract class StubCall<T> implements Call<GraphQLResponse<T>> {

        protected static Request request = createFakeRequest();

        @Override
        public boolean isExecuted() {
            return false;
        }

        @Override
        public void cancel() { }

        @Override
        public boolean isCanceled() {
            return false;
        }

        @Override
        public Call<GraphQLResponse<T>> clone() {
            return null;
        }

        @Override
        public Request request() {
            return request;
        }

        @Override
        public Timeout timeout() {
            return null;
        }

    }

    public static class StubSuccessfulCall<T> extends StubCall<T> {

        @Override
        public Response<GraphQLResponse<T>> execute() throws IOException {
            return null;
        }

        @Override
        public void enqueue(Callback<GraphQLResponse<T>> callback) {
            callback.onResponse(this, createFakeResponse());
        }

    }

    public static class StubFailedCall<T> extends StubCall<T> {

        @Override
        public Response<GraphQLResponse<T>> execute() throws IOException {
            return null;
        }

        @Override
        public void enqueue(Callback<GraphQLResponse<T>> callback) {
            callback.onFailure(this, new Exception("Stub always fails"));
        }

    }

}
