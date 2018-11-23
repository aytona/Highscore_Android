package com.example.vanessali.highscores_li;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vanessali.highscores_li.model.Score;

import java.util.ArrayList;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.myViewHolder> {

    public ArrayList<Score> namesOfScores;  // Data passed on by the intent

    public ScoreAdapter(ArrayList<Score> adaptScores)
    {
        namesOfScores = adaptScores;
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{
        private TextView nameView;
        private TextView scoreView;

        //constructor
        public myViewHolder(LinearLayout layout){
            super(layout);
            nameView = layout.findViewById(R.id.textName);
            scoreView = layout.findViewById(R.id.textScore);
        }

    }

    @Override
    public ScoreAdapter.myViewHolder onCreateViewHolder (ViewGroup parent, int  viewType) {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_display, parent, false);
        myViewHolder viewHolder = new myViewHolder(layout);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder , int position){
        Score score = namesOfScores.get(position);      // The individual score
        holder.nameView.setText(score.getName());
        StringBuilder scoreText = new StringBuilder();
        scoreText.append("Score: ");
        scoreText.append(score.getScore());
        holder.scoreView.setText(scoreText.toString());
    }

    @Override
    public int getItemCount(){
        return namesOfScores.size();
    }
}
