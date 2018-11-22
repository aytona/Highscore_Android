package com.example.vanessali.highscores_li.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Score {

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

    private Score(Parcel p) {

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

    public int compareTo(Score score) {
        return 0;   // TODO
    }

    public int describeContents() {
        return 0; // TODO Parcelable
    }

    public String toFile(){
        return String.format("%d,%s", score, name );
    }

    private String name;
    private int score;

    // TODO: Remove
    @Override
    public String toString() {
        return "Score{" +
                "name='" + name + '\'' +
                ", number=" + score +
                '}';
    }
}
