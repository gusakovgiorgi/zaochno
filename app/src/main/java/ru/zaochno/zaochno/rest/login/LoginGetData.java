package ru.zaochno.zaochno.rest.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Anton_MoApps on 08.06.2017.
 */

public class LoginGetData {
    @SerializedName("data")
    private String token;
    private boolean err;
    private int code;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isErr() {
        return err;
    }

    public void setErr(boolean err) {
        this.err = err;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
