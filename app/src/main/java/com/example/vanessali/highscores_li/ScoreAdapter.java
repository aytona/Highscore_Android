package com.example.vanessali.highscores_li;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vanessali.highscores_li.model.Score;


import java.util.ArrayList;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.myViewHolder> {
    //hold the data in the adapter
    public ArrayList<Score> namesOfScores;

    //constructor that accepts the Scores array
    public ScoreAdapter(ArrayList<Score> adaptScores){

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

    /*
    * @param parent the parent viewgroup the recycler view
    * @param number is view type of the new view*/

    @Override
    public ScoreAdapter.myViewHolder onCreateViewHolder (ViewGroup parent, int  viewType) {

        LinearLayout layout = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_display, parent, false);

        myViewHolder viewHolder = new myViewHolder(layout);

        return viewHolder;

    }

    /*this is called by the layout manager when it needs a new  view to display  in the recycler view
    * @param holder the older that id being reused
    * @position is the position in the recycler View*/
    @Override
    public void onBindViewHolder(ScoreAdapter.myViewHolder holder , int position){
        holder.nameView.setText(namesOfScores.get(position).getName());
        holder.scoreView.setText(namesOfScores.get(position).getScore());
    }

    @Override
    public int getItemCount(){
        return namesOfScores.size(); //total number of elements in the the list
    }
}
