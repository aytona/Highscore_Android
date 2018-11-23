package com.example.vanessali.highscores_li;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Parcel;

import android.os.Parcelable;
import android.print.pdf.PrintedPdfDocument;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import com.example.vanessali.highscores_li.model.Score;

import org.w3c.dom.Text;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MainActivity extends Activity {
    private Button submitButton;
    private Button viewAllButton;
    private Button resetButton;
    private EditText scoreInput;
    private EditText nameInput;
    private TextView highScore;
    private TextView scorePerson;
    private ArrayList<Score> scoreList;

    private TextView DummyOutput;


    // use a constant for the file name
    private static final String FILE_NAME = "scores.txt";
    public static final String LIST_NAME = "ScoreList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = findViewById(R.id.btn_submit);
        viewAllButton = findViewById(R.id.btn_view);
        resetButton = findViewById(R.id.btn_reset);
        scoreInput = findViewById(R.id.edt_score);
        nameInput = findViewById(R.id.edt_name);
        highScore = findViewById(R.id.txt_score);
        scorePerson = findViewById(R.id.txt_person);


        DummyOutput = findViewById(R.id.tester);

        scoreList = new ArrayList<Score>();

        ReadFileTask readerTask = new ReadFileTask();
        readerTask.execute();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String score = scoreInput.getText().toString();
                String name = nameInput.getText().toString();
                if (validateInput(name, score)) {
                    addScore(name, Integer.parseInt(score));
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to add record", Toast.LENGTH_LONG).show();
                }
                scoreInput.setText(null);
                nameInput.setText(null);
            }
        });

        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
                intent.putParcelableArrayListExtra(LIST_NAME, scoreList);
                startActivity(intent);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public boolean validateInput(String name, String score) {
        boolean isInputValid = true;
        if (score.isEmpty()) {
            scoreInput.setError("Must enter valid input");
            isInputValid = false;
        }
        if (name.isEmpty()) {
            nameInput.setError("Must Enter valid input");
            isInputValid = false;
        }
        return isInputValid;
    }

    public void addScore(String _name, int _number) {
        Score score = new Score(_name, _number);
        highScore.setText("High score:" + String.valueOf(_number));
        scorePerson.setText("By:" + _name);
        scoreList.add(score);

        Collections.sort(scoreList);

        // Writing to a file
        WriteFileTask writerTaskObject = new WriteFileTask();
        writerTaskObject.execute(score);
    }

    public class WriteFileTask extends AsyncTask<Score, Void, String> {
        @Override
        protected String doInBackground(Score... newScore) {
            FileOutputStream fos = null;
            try {
                fos = openFileOutput(FILE_NAME, MODE_APPEND);
                PrintWriter outputWriter = new PrintWriter(fos); //converts from character stream to byte streams
                String scoreToFile = newScore[0].toFile(); //converts score into string representation, accessing at index [0]  bc newScore is an array ,
                outputWriter.println(scoreToFile);//writing the score to the file
                outputWriter.close();
            } catch (FileNotFoundException e) {
                return "File not found";
            }
            return "Score added";
        }

        @Override
        protected void onPostExecute(String s) {
            // Show message if data is inputted or not
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
    }

    public class ReadFileTask extends AsyncTask<String, Integer, String[]> {
        @Override
        protected String[] doInBackground(String... strings) {
            //Reading the file and putting it the array list
            FileInputStream fis;
            //getting the fis from the context method
            try {
                fis = openFileInput(FILE_NAME); //reading the stream = scores.txt
            } catch (FileNotFoundException e) {
                return new String[0];
            }

            Scanner scanner = new Scanner(fis);
            StringBuilder file = new StringBuilder();

            while (scanner.hasNextLine()) {//Keep reading the file as long as there's data
                file.append(scanner.hasNextLine()).append("\n");//append string with line break
            }
            scanner.close();//close scanner = close fis

            // splitting up strings OF scores.txt into individual strings
            String fileString = file.toString();
            String[] scoresArray = fileString.split("\\n");
            return scoresArray;
        }

        @Override
        protected void onPostExecute(String[] scoresArray) {
            //loops through the string array turning each one into score object and adding to array list (ScoreList)
            for(int i =0; i< scoresArray.length; i++){
                Score newScore = new Score(scoresArray[i]); // converts line to object
                scoreList.add(newScore);//gives object to arraylist
            }
        }
    }
}



































