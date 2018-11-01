package com.example.a11600624.myapplication.Pages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.a11600624.myapplication.Database.DatabaseSeeder;
import com.example.a11600624.myapplication.R;

public class MainActivity extends AppCompatActivity {
    DatabaseSeeder databaseSeeder;
    Boolean firstInitialisation = false;

    public static Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!firstInitialisation) {
            databaseSeeder = new DatabaseSeeder(this);

            nextButton = findViewById(R.id.button4);
            nextButton.setEnabled(false);
            nextButton.setText("Loading characters...");

            firstInitialisation = true;
        }
    }

    public void openAboutPage(View view) {
        Intent startNewActivity = new Intent(this, DisplayAboutPage.class);
        startActivity(startNewActivity);
    }

    public void openCharacterSelectorPage(View view) {
        Intent startNewActivity = new Intent(this, SelectCharacterPage.class);
        startActivity(startNewActivity);
    }


}
