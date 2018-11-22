package com.example.vanessali.highscores_li;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class DisplayActivity extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);     // If content doesn't change layout size

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // recyclerAdapter = new ScoreAdapter();
        recyclerView.setAdapter(recyclerAdapter);

    }
}
