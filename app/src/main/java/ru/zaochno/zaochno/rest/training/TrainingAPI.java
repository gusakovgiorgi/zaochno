package ru.zaochno.zaochno.rest.training;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.zaochno.zaochno.rest.category.CategoryGetData;
import ru.zaochno.zaochno.rest.category.CategorySendData;


public interface TrainingAPI {
    @POST("/api/trennings")
    Call<TrainingsGetData> getTrainings(@Body TrainingsSendData param);
}
