package com.example.a11600624.myapplication.Pages;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.a11600624.myapplication.Database.DatabaseSeeder;
import com.example.a11600624.myapplication.R;

public class MainActivity extends AppCompatActivity {
    public static Button playButton;
    private Button aboutButton;
    private Button highscoreButton;
    private Button settingsButton;
    private Typeface tf1;

    private DatabaseSeeder databaseSeeder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        aboutButton = findViewById(R.id.aboutButton);
        highscoreButton = findViewById(R.id.highscoreButton);
        settingsButton = findViewById(R.id.settingsButton);

        databaseSeeder = new DatabaseSeeder(this);

        tf1 = Typeface.createFromAsset(getAssets(), "font1.ttf");

        playButton.setTypeface(tf1);
        aboutButton.setTypeface(tf1);
        highscoreButton.setTypeface(tf1);
        settingsButton.setTypeface(tf1);
    }

    public void openAboutPage(View view) {
        Intent startNewActivity = new Intent(this, DisplayAboutPage.class);
        startActivity(startNewActivity);
    }

    public void openCharacterSelectorPage(View view) {
        Intent startNewActivity = new Intent(this, SelectCharacterPage.class);
        startActivity(startNewActivity);
    }

    public void openSettingsPage(View view) {
        Intent startNewActivity = new Intent(this, SettingsPage.class);
        startActivity(startNewActivity);
    }

    public void openHighscore(View view) {
        Intent startNewActivity = new Intent(this, HighscorePage.class);
        startActivity(startNewActivity);
    }
}
