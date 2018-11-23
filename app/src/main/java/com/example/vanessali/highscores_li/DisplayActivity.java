package com.example.vanessali.highscores_li;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

        //Retrieving the data from the array list from main activity
        Intent intent = getIntent();
        ArrayList<Score> scoreList = intent.getParcelableArrayListExtra(MainActivity.LIST_NAME);

        //Formatting the recycler View
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);     // If content doesn't change layout size

        layoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);

        // This is how to make divider line from
        // https://stackoverflow.com/questions/24618829/how-to-add-dividers-and-spaces-between-items-in-recyclerview
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                ((LinearLayoutManager) layoutManager).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setLayoutManager(layoutManager);

        //Getting the score list from the array adapter and setting it inside the recycler view
        recyclerAdapter = new ScoreAdapter(scoreList);
        recyclerView.setAdapter(recyclerAdapter);




    }
}
