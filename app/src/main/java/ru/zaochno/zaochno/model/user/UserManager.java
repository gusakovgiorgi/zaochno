package ru.zaochno.zaochno.model.user;

import android.content.Context;
import android.content.SharedPreferences;

import ru.zaochno.zaochno.MainApplication;
import ru.zaochno.zaochno.constants.SharedPreferencesConstants;

/**
 * Created by Anton_MoApps on 08.06.2017.
 */

public class UserManager implements SharedPreferencesConstants {



    private User mUser;

    private static final UserManager INSTANCE = new UserManager();

    private UserManager(){
    }

    public static UserManager getInstance(){
        return INSTANCE;
    }

    public User getUser(){
        return mUser;
    }

    public void initializeUser(){
        SharedPreferences preferences= MainApplication.getInstance().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        int id=preferences.getInt(PREF_USER_ID,ID_DEF_VALUE);
        if(id!=ID_DEF_VALUE){
            String name=preferences.getString(PREF_USER_NAME,null);
            String email=preferences.getString(PREF_USER_EMAIL,null);
            String region=preferences.getString(PREF_USER_REGION,null);
            String phone=preferences.getString(PREF_USER_PHONE,null);
            String token=preferences.getString(PREF_USER_TOKEN,null);
            UserType type=UserType.valueOf(preferences.getString(PREF_USER_TYPE,null));
            if (type==UserType.PHYSICAL_USER){
                mUser=new PhysicalUser(name,email,"",region,phone,id,token);
            }
        }
    }

    public boolean isUserLogIn(){
        return mUser!=null;
    }

    public void createUser(String token){
        SharedPreferences preferences= MainApplication.getInstance().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        //todo save other data
        editor.putInt(PREF_USER_ID,0);
        editor.putString(PREF_USER_TOKEN,token);
        editor.putString(PREF_USER_TYPE,UserType.PHYSICAL_USER.toString());
        editor.apply();

        mUser=new PhysicalUser("default name",null,null,null,null,0,token);
    }

    public void logout(){
        SharedPreferences preferences= MainApplication.getInstance().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.clear();
        editor.apply();

    }

}
