package ru.zaochno.zaochno.rest.category;

/**
 * Created by Anton_MoApps on 08.06.2017.
 */

public class CategorySendData {
    private String token;

    public CategorySendData(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
