package ru.zaochno.zaochno.rest.category;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

import ru.zaochno.zaochno.model.Category;
import ru.zaochno.zaochno.rest.BaseResponce;

/**
 * Created by Anton_MoApps on 08.06.2017.
 */

public class CategoryGetData  extends BaseResponce{
    @SerializedName("data")
    private Category[] categories;

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "CategoryGetData{" +
                "categories=" + Arrays.toString(categories) +
                '}';
    }
}
