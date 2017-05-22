package ru.zaochno.zaochno.model.testing;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by notbl on 5/21/2017.
 */

public class Question implements Parcelable{
    private int questionId;
    private String question;
    private List<String> answers;
    private int correctAnswerId;

    protected Question(Parcel in) {
        questionId = in.readInt();
        question = in.readString();
        answers = in.createStringArrayList();
        correctAnswerId = in.readInt();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public int getCorrectAnswerId() {
        return correctAnswerId;
    }

    public void setCorrectAnswerId(int correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
    }

    public Question(int questionId, String question, List<String> answers, int correctAnswerId) {

        this.questionId = questionId;
        this.question = question;
        this.answers = answers;
        this.correctAnswerId = correctAnswerId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(questionId);
        dest.writeString(question);
        dest.writeStringList(answers);
        dest.writeInt(correctAnswerId);
    }
}
