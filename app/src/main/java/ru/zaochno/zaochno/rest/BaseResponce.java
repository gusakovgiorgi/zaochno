package ru.zaochno.zaochno.rest;

/**
 * Created by Anton_MoApps on 13.06.2017.
 */

public class BaseResponce {
    private boolean error;
    private int code;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
