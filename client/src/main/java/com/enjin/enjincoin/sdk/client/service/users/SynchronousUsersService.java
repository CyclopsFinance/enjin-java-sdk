package com.enjin.enjincoin.sdk.client.service.users;

import com.google.gson.JsonElement;
import retrofit2.Response;

import java.io.IOException;

public interface SynchronousUsersService {

    Response<JsonElement> createUserSync(String name, String email, String password) throws IOException;

    Response<JsonElement> loginUserSync(String name, String email, String password) throws IOException;

    Response<JsonElement> getAllUsersSync() throws IOException;

    Response<JsonElement> getUsersSync(Integer id, String name, String email) throws IOException;
}
