package ru.zaochno.zaochno.rest.register;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.zaochno.zaochno.rest.login.LoginGetData;
import ru.zaochno.zaochno.rest.login.LoginSendData;

/**
 * Created by Anton_MoApps on 22.06.2017.
 */

public interface RegisterAPI {
    @POST("/api/register")
    Call<RegisterGetData> registerUser(@Body RegisterSendData registerData);
}
