package com.example.a11600624.myapplication.Components;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a11600624.myapplication.R;


public class HighscoreAdapter extends RecyclerView.Adapter<HighscoreAdapter.HighscoreViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public HighscoreAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }

    public class HighscoreViewHolder extends RecyclerView.ViewHolder{
        public TextView nameText;
        public TextView scoreText;

        public HighscoreViewHolder(View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.textview_name);
            scoreText = itemView.findViewById(R.id.textview_score);
        }
    }

    @Override
    public HighscoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.highscore_item, parent, false);
        return new HighscoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HighscoreViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){
            return;
        }

        String name = mCursor.getString(1);
        String score = mCursor.getString(2);

        holder.nameText.setText(name);
        holder.scoreText.setText(score);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor != null){
            mCursor.close();
        }
        mCursor = newCursor;
        if(newCursor != null){
            notifyDataSetChanged();
        }
    }
}
