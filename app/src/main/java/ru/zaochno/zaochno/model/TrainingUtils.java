package ru.zaochno.zaochno.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton_MoApps on 13.06.2017.
 */

public class TrainingUtils {

    public static List<Training> getFavoriteTraining(Training[] trainings) {
        List<Training> data = new ArrayList<>();
        if (trainings == null) {
            return data;
        }

        for (int i = 0; i < trainings.length; i++) {
            if (trainings[i].isFavorite()) {
                data.add(trainings[i]);
            }
        }
        return data;
    }

    public static List<Training> getBoughtTraining(Training[] trainings) {
        List<Training> data = new ArrayList<>();
        if (trainings == null) {
            return data;
        }

        for (int i = 0; i < trainings.length; i++) {
            if (trainings[i].isBought()) {
                data.add(trainings[i]);
            }
        }
        return data;
    }
}
