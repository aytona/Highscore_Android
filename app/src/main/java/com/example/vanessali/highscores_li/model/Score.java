package com.example.vanessali.highscores_li.model;

public class Score {

    private String name;
    private int number;

    public Score(String userName ,int userNumber){ //if using to string you need to add display string in the paramters
        name = userName;
        number = userNumber;


    }

    public Score(String string){
        //separating string values
        String[] scoreAttributes = string.split(",");

        //sets the attributes
        number =Integer.parseInt(scoreAttributes[0]);//
        name = scoreAttributes[1];


    }

    public String toFile(){
        return String.format("%d,%s", number, name );
    }


    @Override
    public String toString() {
        return "Score{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
