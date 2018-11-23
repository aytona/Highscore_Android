package com.example.vanessali.highscores_li.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

public class Score implements Parcelable, Comparable<Score> {

    private String name;
    private int score;

    public Score(String userName ,int userNumber){ //if using to string you need to add display string in the paramters
        name = userName;
        score = userNumber;
    }

    public Score(String string){
        //separating string values
        String[] scoreAttributes = string.split(",");

        //sets the attributes
        score =Integer.parseInt(scoreAttributes[0]);//
        name = scoreAttributes[1];
    }

    protected Score(Parcel in) {
        name = in.readString();
        score = in.readInt();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int n_score) {
        score = n_score;
    }

    public String getName() {
        return name;
    }

    public void setName(String n_Name) {
        name = n_Name;
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(score);
        Log.i("Score", String.valueOf(flags));
    }

    public String toFile(){
        return String.format("%d,%s", score, name );
    }

    public static final Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel source) {
            return new Score(source); // return the inflated object
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };

    @Override
    public int compareTo(@NonNull Score compareScore) {
        int compScore = compareScore.getScore();
        return compScore-this.score;
    }
}