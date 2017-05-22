package ru.zaochno.zaochno;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.zaochno.zaochno.model.Training;
import ru.zaochno.zaochno.model.testing.Question;
import ru.zaochno.zaochno.model.testing.Test;

/**
 * Created by Anton_MoApps on 18.05.2017.
 */

public class FakeData {

    private static boolean isUserLogin = false;
    private static List<Training> trainings = new ArrayList<>();
    private static List<Test> tests=new ArrayList<>();

    static {
        List<Question> questions=new ArrayList<>();
        List<String> answers=new ArrayList<>();
        answers.add("ответ1");
        answers.add("ответ2");
        answers.add("правильный ответ");
        questions.add(new Question(1,"вопрос1",answers,2));

        answers=new ArrayList<>();
        answers.add("ответ500");
        answers.add("ответ200");
        answers.add("правильный ответ34");

        questions.add(new Question(2,"вопросс2",answers,2));

        tests.add(new Test("test1",1,questions,"Короткое описание теста номер 1",new Date(),null,12));

        questions=new ArrayList<>();
        answers=new ArrayList<>();
        answers.add("ответ100");
        answers.add("ответ200");
        answers.add("правильный ответ");
        answers.add("ответ300");
        questions.add(new Question(1,"вопрос1",answers,2));

        tests.add(new Test("test2",2,questions,"Короткое описание теста номер 2",new Date(),null,0));

        trainings.add(new

                Training(0, "trening1", "Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала XVI века. В то время некий безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum",
                null, false, false, null, "245",tests));
        trainings.add(new

                Training(1, "trening2", "це с начала XVI века. В то время некий безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum",
                null, false, false, null, "999",null));
        trainings.add(new

                Training(2, "trening3", " часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала XVI века. В то время некий безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum",
                null, true, true, null, "500",null));
        trainings.add(new

                Training(3, "trening4", "некий безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum",
                null, false, true, null, "450",null));
        trainings.add(new

                Training(4, "trening5", " является стандартной \"рыбой\" для текстов на латинице с начала XVI века. В то время некий безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum",
                null, false, true, null, "300",null));
    }

    public static List<Training> getFavoritesTraining(){
        List<Training> favTraining=new ArrayList<>();
        for(Training training:trainings){
            if(training.isFavorite()){
                favTraining.add(training);
            }
        }
        return favTraining;
    }
    public static List<Training> getBoughtTraining(){
        List<Training> boughtTrainings=new ArrayList<>();
        for(Training training:trainings){
            if(training.isBought()){
                boughtTrainings.add(training);
            }
        }
        return boughtTrainings;
    }

    public static boolean isUserLogin() {
        return isUserLogin;
    }

    public static void setIsUserLogin(boolean loginValue) {
        isUserLogin = loginValue;
    }

    public static List<Training> getTrainings() {

        return trainings;
    }

}
