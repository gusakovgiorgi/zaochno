package ru.zaochno.zaochno.rest.training;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

import ru.zaochno.zaochno.model.Training;
import ru.zaochno.zaochno.rest.BaseResponce;

/**
 * Created by Anton_MoApps on 13.06.2017.
 */

public class TrainingsGetData extends BaseResponce {
    @SerializedName("data")
    private Training[] trainings;

    public Training[] getTrainings() {
        return trainings;
    }

    public void setTrainings(Training[] trainings) {
        this.trainings = trainings;
    }

    @Override
    public String toString() {
        return "TrainingsGetData{" +
                "trainings=" + Arrays.toString(trainings) +
                '}';
    }
}
