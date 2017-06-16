package ru.zaochno.zaochno.rest.training;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Anton_MoApps on 13.06.2017.
 */

public class TrainingsSendData {
    /*
    take : 1000,
    token : "b63279fe-d2b4-43da-b5e2-11725c4a94af",
    "thematics" : "23,34,54", //# id категорий через запятую или пустая строка
    "priceStart" : "100", //# или пустая строка
    "priceEnd" : "500"   //# или пустая строка
     */

    @SerializedName("take")
    private int limit;
    private String token;
    private String thematics;
    private int priceStart;
    private int priceEnd;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getThematics() {
        return thematics;
    }

    public void setThematics(String thematics) {
        this.thematics = thematics;
    }

    public int getPriceStart() {
        return priceStart;
    }

    public void setPriceStart(int priceStart) {
        this.priceStart = priceStart;
    }

    public int getPriceEnd() {
        return priceEnd;
    }

    public void setPriceEnd(int priceEnd) {
        this.priceEnd = priceEnd;
    }

    @Override
    public String toString() {
        return "TrainingsSendData{" +
                "limit=" + limit +
                ", token='" + token + '\'' +
                ", thematics='" + thematics + '\'' +
                ", priceStart=" + priceStart +
                ", priceEnd=" + priceEnd +
                '}';
    }
}
