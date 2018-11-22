package com.example.vanessali.highscores_li;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.myViewHolder> {
    //hold the data in the adapter
    private String[] namesOfScores;

    //constructor that accepts the Scores array
    public ScoreAdapter(String[] adaptScores){
        namesOfScores = adaptScores;
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{
        public TextView sTextView;

        //constructor
        public myViewHolder(TextView textView){
            super(textView);
            sTextView = textView;

        }

    }

    /*
    * @param parent the parent viewgroup the recycler view
    * @param number is view type of the new view*/

    @Override
    public ScoreAdapter.myViewHolder onCreateViewHolder (ViewGroup parent, int  viewType) {
        //retrieve the Textview we created
        TextView  txtScores = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_display, parent, false);

        //create an instance of myViewHolder

        myViewHolder vholder = new myViewHolder(txtScores);

        return vholder;

    }

    /*this is called by the layout manager when it needs a new  view to display  in the recycler view
    * @param holder the older that id being reused
    * @position is the position in the recycler View*/
    @Override
    public void onBindViewHolder(ScoreAdapter.myViewHolder holder , int position){
        holder.sTextView.setText(namesOfScores[position]); //setting text to the position

    }

    @Override
    public int getItemCount(){
        return namesOfScores.length; //total number of elements in the the list
    }
}
