package com.example.a11600624.myapplication.Components;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a11600624.myapplication.Models.Background;
import com.example.a11600624.myapplication.R;

public class BackgroundRecyclerViewAdapter extends RecyclerView.Adapter<BackgroundRecyclerViewAdapter.backgroundViewHolder>  {
    private onItemClickListener clickListener;
    private Cursor backgroundCursor;
    private Context mContext;

    public BackgroundRecyclerViewAdapter(onItemClickListener listener, Context context, Cursor cursor) {
        clickListener = listener;
        mContext = context;
        backgroundCursor = cursor;
    }

    public static class backgroundViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitleText;

        public backgroundViewHolder(View itemView) {
            super(itemView);
            mTitleText = itemView.findViewById(R.id.background_title);
        }
    }

    @Override
    public backgroundViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.background_list_item, parent, false);
        return new BackgroundRecyclerViewAdapter.backgroundViewHolder(view);
    }

    @Override
    public void onBindViewHolder(backgroundViewHolder holder, int position) {
        if(!backgroundCursor.moveToPosition(position)){
            return;
        }

        String title = backgroundCursor.getString(1);
        String description = backgroundCursor.getString(2);
        int imageSource =  backgroundCursor.getInt(3);

        holder.mTitleText.setText(title);

        View currentItem = holder.itemView;
        final Background currentListItem = new Background(title,description,imageSource);

        currentItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(currentListItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return backgroundCursor.getCount();
    }

    public interface onItemClickListener {
        void onItemClick(Background item);
    }

    public void swapCursor(Cursor newCursor){
        if(backgroundCursor != null){
            backgroundCursor.close();
        }
        backgroundCursor = newCursor;
        if(newCursor != null){
            notifyDataSetChanged();
        }
    }
}
