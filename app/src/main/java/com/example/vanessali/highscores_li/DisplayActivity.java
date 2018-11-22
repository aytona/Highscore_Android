package com.example.vanessali.highscores_li;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.vanessali.highscores_li.ScoreAdapter;
import com.example.vanessali.highscores_li.model.Score;

import java.util.ArrayList;

public class DisplayActivity extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Score> scoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent intent = getIntent();
        scoreList = intent.getParcelableArrayListExtra("ScoreList");

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);     // If content doesn't change layout size

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new ScoreAdapter(scoreList.toArray(new String[0]));
        recyclerView.setAdapter(recyclerAdapter);

    }
}
