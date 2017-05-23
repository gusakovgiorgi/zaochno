package ru.zaochno.zaochno.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Category implements Parcelable {
    private int categoryId;
    private String categoryName;
    List<Category> subcategories;

    public Category(int categoryId, String categoryName, String htmlText, List<Category> subcategories) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.subcategories = subcategories;
    }


    protected Category(Parcel in) {
        categoryId = in.readInt();
        categoryName = in.readString();
        subcategories = in.createTypedArrayList(Category.CREATOR);
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Category> subcategories) {
        this.subcategories = subcategories;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(categoryId);
        dest.writeString(categoryName);
        dest.writeTypedList(subcategories);
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
