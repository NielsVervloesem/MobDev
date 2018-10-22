package com.example.a11600624.myapplication.Pages;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.a11600624.myapplication.Database.DatabaseHelper;
import com.example.a11600624.myapplication.R;

import java.util.Random;

public class FightPage extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight_page);

        databaseHelper = new DatabaseHelper(this);

        TextView textView = findViewById(R.id.textView);

        Cursor data = databaseHelper.getCharacterById(SelectCharacterPage.id+1);
        String chosenCharacter = null;

        if (data.moveToFirst()) {
            chosenCharacter = data.getString(1);
        }

        data = databaseHelper.getRandomCharacter(SelectCharacterPage.id+1);
        String opponentCharacter = null;

        if (data.moveToFirst()) {
            opponentCharacter = data.getString(1);
        }

        textView.setText("Chosen: " + chosenCharacter + "\nOpponent: " + opponentCharacter);
    }
}
