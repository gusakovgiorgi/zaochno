package ru.zaochno.zaochno.rest.register;

import ru.zaochno.zaochno.rest.BaseResponce;

/**
 * Created by Anton_MoApps on 22.06.2017.
 */

public class RegisterGetData extends BaseResponce {
    private String token;
    private boolean registered;
    private String message;

    public RegisterGetData(String token, boolean registered, String message) {
        this.token = token;
        this.registered = registered;
        this.message = message;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
