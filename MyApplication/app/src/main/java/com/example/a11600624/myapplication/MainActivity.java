package com.example.a11600624.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openAboutPage(View view){
        Intent startNewActivity = new Intent(this, DisplayAboutPage.class);
        startActivity(startNewActivity);
    }

    public void openCharacterSelectorPage(View view) {
        Intent startNewActivity = new Intent(this, SelectCharacterPage.class);
        startActivity(startNewActivity);
    }
}
