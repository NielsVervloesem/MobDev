package com.example.a11600624.myapplication.Pages;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a11600624.myapplication.Models.Background;
import com.example.a11600624.myapplication.R;

public class BackgroundDetailPage extends FragmentActivity {
    private LinearLayout linearLayoutBackground;
    private TextView backgroundTitle;
    private TextView backgroundDescription;
    private Button saveButton;

    private Typeface tf1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.background_detail_item);

        linearLayoutBackground = findViewById(R.id.linear_layout_background);
        backgroundTitle = findViewById(R.id.background_title);
        backgroundDescription = findViewById(R.id.background_description);
        saveButton = findViewById(R.id.save_button);

        tf1 = Typeface.createFromAsset(getAssets(), "font1.ttf");
        saveButton.setTypeface(tf1);

        Intent intent = getIntent();
        Background item = (Background) intent.getSerializableExtra("background");

        linearLayoutBackground.setBackgroundResource(item.getImageSource());
        backgroundTitle.setText(item.getTitle());
        backgroundDescription.setText(item.getDescription());
    }
}
