package com.example.vanessali.highscores_li;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.vanessali.highscores_li.model.Score;
import java.util.ArrayList;

public class DisplayActivity extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent intent = getIntent();

        ArrayList<Score> scoreList = intent.getParcelableArrayListExtra(MainActivity.LIST_NAME);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);     // If content doesn't change layout size

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new ScoreAdapter(scoreList);
        recyclerView.setAdapter(recyclerAdapter);


    }
}
