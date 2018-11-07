package com.example.a11600624.myapplication.Pages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.a11600624.myapplication.Database.DatabaseSeeder;
import com.example.a11600624.myapplication.R;

public class MainActivity extends AppCompatActivity {
    public static Button nextButton;
    private DatabaseSeeder databaseSeeder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextButton = findViewById(R.id.button4);
        databaseSeeder = new DatabaseSeeder(this);
    }

    public void openAboutPage(View view) {
        Intent startNewActivity = new Intent(this, DisplayAboutPage.class);
        startActivity(startNewActivity);
    }

    public void openCharacterSelectorPage(View view) {
        Intent startNewActivity = new Intent(this, SelectCharacterPage.class);
        startActivity(startNewActivity);
    }

    public void openInstellingenPage(View view) {
        Intent startNewActivity = new Intent(this, SettingsPage.class);
        startActivity(startNewActivity);
    }

    public void openHighscore(View view) {
        Intent startNewActivity = new Intent(this, HighscorePage.class);
        startActivity(startNewActivity);
    }
}
