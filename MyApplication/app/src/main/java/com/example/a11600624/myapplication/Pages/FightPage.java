package com.example.a11600624.myapplication.Pages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.a11600624.myapplication.R;

public class FightPage extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight_page);

        TextView textView = (TextView) findViewById(R.id.textView);
        ViewPager viewPager = (ViewPager) findViewById(R.id.slideView);

        int id = SelectCharacterPage.id;

        textView.setText("Id: " + id);
    }
}
