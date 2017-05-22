package ru.zaochno.zaochno.model.testing;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

/**
 * Created by notbl on 5/21/2017.
 */

public class Test implements Parcelable{

    private String testName;
    private int testId;
    private List<Question> questions;
    private String shortDescription;
    private Date expireDate;
    private String imageUrl;
    private int testProgress;

    public Test(String testName, int testId, List<Question> questions, String shortDescription, Date expireDate,String imageUrl,int testProgress) {
        this.testName = testName;
        this.testId = testId;
        this.questions = questions;
        this.shortDescription = shortDescription;
        this.expireDate = expireDate;
        this.imageUrl=imageUrl;
        this.testProgress=testProgress;
    }

    protected Test(Parcel in) {
        testName = in.readString();
        testId = in.readInt();
        questions = in.createTypedArrayList(Question.CREATOR);
        shortDescription = in.readString();
        expireDate=new Date(in.readLong());
        imageUrl=in.readString();
        testProgress=in.readInt();
    }


    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getTestProgress() {
        return testProgress;
    }

    public void setTestProgress(int testProgress) {
        this.testProgress = testProgress;
    }

    public static final Creator<Test> CREATOR = new Creator<Test>() {
        @Override
        public Test createFromParcel(Parcel in) {
            return new Test(in);
        }

        @Override
        public Test[] newArray(int size) {
            return new Test[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(testName);
        dest.writeInt(testId);
        dest.writeTypedList(questions);
        dest.writeString(shortDescription);
        dest.writeLong(expireDate.getTime());
        dest.writeString(imageUrl);
        dest.writeInt(testProgress);
    }
}
