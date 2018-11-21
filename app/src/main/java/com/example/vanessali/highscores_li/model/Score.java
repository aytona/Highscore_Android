package com.example.vanessali.highscores_li.model;

public class Score {

    private String name;
    private int number;
    //private String displayString;


    public Score(String userName ,int userNumber){ //if using to string you need to add display string in the paramters
        name = userName;
        number = userNumber;
        //displayString = display;


    }

    /*public String toString(){
        return String.format(displayString, name, number);

    }*/


}
