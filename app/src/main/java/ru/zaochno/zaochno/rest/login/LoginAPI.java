package ru.zaochno.zaochno.rest.login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Anton_MoApps on 08.06.2017.
 */

public interface LoginAPI {

    @POST("/api/account/login")
    Call<LoginGetData> loginAttempt(@Body LoginSendData loginData);
}
