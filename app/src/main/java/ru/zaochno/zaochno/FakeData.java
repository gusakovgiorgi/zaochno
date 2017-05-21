package ru.zaochno.zaochno;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton_MoApps on 18.05.2017.
 */

public class FakeData {

    private static boolean isUserLogin=false;

    public static List<String> getDefaultTrenings(){
        List<String> data=new ArrayList<>();
        data.add("Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала XVI века. В то время некий безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum");
        data.add("для распечатки образцов. Lorem Ipsum не только успешно пережил без заметных изменений пять веков, но и перешагнул в электронный дизайн. Его популяризации в новое время послужили публикация листов Letraset с образцами Lorem Ipsum в 60-х годах и, в более недавнее время, программы электронной вёрстки типа Aldus PageMaker, в шаблонах которых используется Lorem Ipsum.");
        return data;
    }
    public static boolean isUserLogin(){
        return isUserLogin;
    }

    public static void setIsUserLogin(boolean loginValue){
        isUserLogin=loginValue;
    }
}
