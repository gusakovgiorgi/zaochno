package ru.zaochno.zaochno.model.user;

/**
 * Created by Anton_MoApps on 22.06.2017.
 */

public class UserUtils {

    public static String getUserTypeString(UserType type){
        if(type==UserType.PHYSICAL_USER){
            return "Физ. лицо";
        }else if(type==UserType.LEGAL_USER){
            return "Юр. лицо";
        }
        return "undefined";
    }
}
