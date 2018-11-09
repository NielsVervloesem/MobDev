package com.example.a11600624.myapplication.Pages;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.a11600624.myapplication.R;

import org.w3c.dom.Text;

public class DisplayAboutPage extends AppCompatActivity {

    private Typeface tf1;
    private TextView title;
    private TextView about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);

        title = findViewById(R.id.aboutHeading);
        about = findViewById(R.id.aboutText);

        tf1 = Typeface.createFromAsset(getAssets(), "font1.ttf");

        about.setTypeface(tf1);
        title.setTypeface(tf1);

    }

    public void openMainPage(View view) {

        Intent startNewActivity = new Intent(this, MainActivity.class);
        startActivity(startNewActivity);
    }
}
