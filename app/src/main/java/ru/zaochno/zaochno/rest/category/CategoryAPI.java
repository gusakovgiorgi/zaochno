package ru.zaochno.zaochno.rest.category;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.zaochno.zaochno.model.Category;
import ru.zaochno.zaochno.rest.login.LoginGetData;
import ru.zaochno.zaochno.rest.login.LoginSendData;

/**
 * Created by Anton_MoApps on 08.06.2017.
 */

public interface CategoryAPI {
    @POST("/api/thematics")
    Call<CategoryGetData> getThematics(@Body CategorySendData param);
}
