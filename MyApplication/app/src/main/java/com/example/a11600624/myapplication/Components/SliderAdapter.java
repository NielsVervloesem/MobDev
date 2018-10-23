package com.example.a11600624.myapplication.Components;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a11600624.myapplication.Database.DatabaseHelper;
import com.example.a11600624.myapplication.R;

public class SliderAdapter extends PagerAdapter {
    private Context context;
    private DatabaseHelper databaseHelper;

    private LayoutInflater layoutInflater;

    private ImageView slideImageView;
    private TextView slideHeading;
    private TextView slideDescription;

    public SliderAdapter(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    @Override
    public int getCount() {
        return databaseHelper.getNumberOfCharacters();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        slideImageView = view.findViewById(R.id.slideImage);
        slideHeading = view.findViewById(R.id.slideHeading);
        slideDescription = view.findViewById(R.id.slideDescription);

        Cursor data = databaseHelper.getCharacterById(position+1);

        if (data.moveToFirst()) {
            slideImageView.setImageResource(Integer.parseInt(data.getString(4)));
            slideHeading.setText(data.getString(1));
            slideDescription.setText(data.getString(2));
        }

        data.close();

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
