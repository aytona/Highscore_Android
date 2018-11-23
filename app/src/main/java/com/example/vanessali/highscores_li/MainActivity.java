package com.example.vanessali.highscores_li;

import com.example.vanessali.highscores_li.model.Score;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
    private File file;

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

        scoreList = new ArrayList<Score>();

        file = new File(getFilesDir().getAbsolutePath() + "/" + FILE_NAME);
        if (file.exists()) {
            ReadFileTask readerTask = new ReadFileTask();
            readerTask.execute();
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
                scoreList.clear();
                ClearFileTask clearTaskObj = new ClearFileTask();
                clearTaskObj.execute(file);
                updateTextView();
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

    private void updateTextView() {
        StringBuilder scoreText = new StringBuilder();
        scoreText.append("High score: ");
        StringBuilder nameText = new StringBuilder();
        nameText.append("By: ");
        if (scoreList.size() > 0) {
            scoreText.append(scoreList.get(0).getScore());
            nameText.append(scoreList.get(0).getName());
        }
        highScore.setText(scoreText.toString());
        scorePerson.setText(nameText.toString());
    }

    public void addScore(String _name, int _number) {
        Score score = new Score(_name, _number);
        scoreList.add(score);
        Collections.sort(scoreList);    // Sorts list by score

        updateTextView();

        // Writing to a file
        WriteFileTask writerTaskObject = new WriteFileTask();
        writerTaskObject.execute(score);
    }

    public class WriteFileTask extends AsyncTask<Score, Void, String> {
        @Override
        protected String doInBackground(Score... newScore) {
            try {
                FileOutputStream fos = new FileOutputStream(file,true);
                PrintWriter outputWriter = new PrintWriter(fos);
                outputWriter.println(newScore[0].toFile());
                outputWriter.close();
            } catch(FileNotFoundException e) {
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

    public class ReadFileTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                FileInputStream fis = new FileInputStream(file);
                Scanner scanner = new Scanner(fis);


                while (scanner.hasNextLine()) {//Keep reading the file as long as there's data
                    String line = scanner.nextLine();
                    String[] data = line.split(",");
                    Score newScore = new Score(data[1], Integer.valueOf(data[0]));
                    scoreList.add(newScore);
                }
                scanner.close();//close scanner = close fis

                Collections.sort(scoreList);
                updateTextView();

                return "Existing data read";
            } catch(FileNotFoundException e) {
                return "File cannot be found";
            }
        }

        @Override
        protected void onPostExecute(String msg) {
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        }
    }

    public class ClearFileTask extends AsyncTask<File, Integer, String> {
        @Override
        protected String doInBackground(File... files) {
            try {
                FileOutputStream fos = new FileOutputStream(files[0], false);
                fos.close();
            } catch (FileNotFoundException e) {
                return "File not Found";
            } catch (IOException e) {
                return "Can't access file";
            }
            return "Cleared File";
        }

        @Override
        protected void onPostExecute(String msg) {
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        }
    }
}