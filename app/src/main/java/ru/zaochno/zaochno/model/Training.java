package ru.zaochno.zaochno.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import ru.zaochno.zaochno.model.testing.Test;

/**
 * Created by Anton_MoApps on 18.05.2017.
 */

public class Training implements Parcelable{
    private int id;
    private String title;
    private String description;
    private String imageUrl;
    private boolean isBought;
    private boolean isFavorite;
    private Category category;
    private List<Chapter> chapters;
    private String price;
    private List<Test> tests;

    public Training(int id, String title, String description, String imageUrl, boolean isBought, boolean isFavorite, Category category, String price,List<Test> tests) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.isBought = isBought;
        this.isFavorite = isFavorite;
        this.category = category;
        this.price = price;
        this.tests=tests;
    }


    protected Training(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        isBought = in.readByte() != 0;
        isFavorite = in.readByte() != 0;
        category = in.readParcelable(Category.class.getClassLoader());
        price = in.readString();
        tests = in.createTypedArrayList(Test.CREATOR);
    }

    public static final Creator<Training> CREATOR = new Creator<Training>() {
        @Override
        public Training createFromParcel(Parcel in) {
            return new Training(in);
        }

        @Override
        public Training[] newArray(int size) {
            return new Training[size];
        }
    };

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(imageUrl);
        dest.writeByte((byte) (isBought ? 1 : 0));
        dest.writeByte((byte) (isFavorite ? 1 : 0));
        dest.writeParcelable(category, flags);
        dest.writeString(price);
        dest.writeTypedList(tests);
    }

}
