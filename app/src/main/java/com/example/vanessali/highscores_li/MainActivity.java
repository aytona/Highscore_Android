package com.example.vanessali.highscores_li;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
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

    private  TextView DummyOutput;



    // use a constant for the file name
    private static final  String FILE_NAME = "scores.txt";


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




        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInput();
                scoreInput.setText("");
                nameInput.setText("");

            }
        });

        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //WriteFile();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //
            }
        });


    }

    public boolean validateInput() {

        boolean isInputValid = true;

        String score = scoreInput.getText().toString();
        String name = nameInput.getText().toString();

        if (score.isEmpty()) {
            scoreInput.setError("Must enter valid input");
            isInputValid = false;
        }
        if (name.isEmpty()) {
            nameInput.setError("Must Enter valid input");
            isInputValid = false;
        }else {
            addScore(name, Integer.parseInt(score));
        }

        return isInputValid;
    }

    public void addScore(String _name, int _number){

        //accessing Score class
        Score score = new Score(_name, _number);

        highScore.setText("High score:" + String.valueOf(_number));
        scorePerson.setText("By:" + _name);

        Toast.makeText(this," New High Score Added ", Toast.LENGTH_LONG).show();

        scoreList.add(score);

    }


    public void write(){
        ;


    }


    public void read(){

    }


    public class WriteFileTask extends AsyncTask<String, Integer, String>{


        @Override
        protected String doInBackground(String... strings) {
            FileOutputStream fos = null; //



            String score = scoreInput.getText().toString();
            String name = nameInput.getText().toString();

            try {
                fos = openFileOutput(FILE_NAME,MODE_PRIVATE);
                OutputStreamWriter outputWriter = new OutputStreamWriter(fos); //converts from character stream to byte streams
                outputWriter.write(score);
                outputWriter.write(name);
                outputWriter.close();


                //display file saved message
                Toast.makeText(getApplicationContext(), "File Saved!!!!!", Toast.LENGTH_LONG).show();

            } catch (FileNotFoundException e) {
                DummyOutput.setText("file did not write \n" + e.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }


    }
    //Read text from file
    public class ReadFileTask extends AsyncTask<String, Integer, String >{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //called before doInBackground() is started
        }

        @Override
        protected String doInBackground(String... strings) {
            FileInputStream fis;//declaring the input stream

            //getting the fis from the context method
            try {
                 fis = openFileInput(FILE_NAME); //reading the stream = scores.txt
            } catch (FileNotFoundException e) {
                DummyOutput.setText("No File Found \n" + e.toString()); //try and catch for error handling
              return null;
            }

            Scanner scanner = new Scanner(fis);
            StringBuilder text= new StringBuilder();

            while(scanner.hasNextLine()){//Keep reading the file as long as there's data
                text.append(scanner.hasNextLine()).append("\n");//append string with line break
            }
            scanner.close();//close scanner = close fis
            return text.toString(); //return the string that was read in
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //called after doInBackground() is started

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //called when the background task makes any progress
        }
    }

}


































