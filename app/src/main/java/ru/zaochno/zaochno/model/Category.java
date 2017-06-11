package ru.zaochno.zaochno.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category implements Parcelable {
    @SerializedName("id")
    private int categoryId;
    @SerializedName("name")
    private String categoryName;
    @SerializedName("subcats")
    List<Category> subcategories;

    public Category(int categoryId, String categoryName, List<Category> subcategories) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (categoryId != category.categoryId) return false;
        return categoryName != null ? categoryName.equals(category.categoryName) : category.categoryName == null;

    }

    @Override
    public int hashCode() {
        int result = categoryId;
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", subcategories=" + subcategories +
                '}';
    }
}
