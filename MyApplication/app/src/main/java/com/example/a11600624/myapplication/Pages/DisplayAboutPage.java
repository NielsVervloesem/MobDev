package com.example.a11600624.myapplication.Pages;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.a11600624.myapplication.R;

public class DisplayAboutPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_about_page);

/*
        TextView tx = (TextView)findViewById(R.id.aboutText);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "font1.ttf");

        tx.setShadowLayer(1.6f,1.5f,1.3f,Color.BLACK);

        tx.setTypeface(custom_font);
        */

    }
}
