package ru.zaochno.zaochno.rest.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Anton_MoApps on 08.06.2017.
 */

public class LoginSendData {
    @SerializedName("userNick")
    private String email;
    private String password;

    public LoginSendData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
