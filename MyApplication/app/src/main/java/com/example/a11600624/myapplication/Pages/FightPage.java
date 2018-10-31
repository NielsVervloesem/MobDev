package com.example.a11600624.myapplication.Pages;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a11600624.myapplication.Database.DatabaseHelper;
import com.example.a11600624.myapplication.R;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class FightPage extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    private ImageView characterView;
    private ImageView opponentView;

    private TextView characterName;
    private TextView opponentName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight_page);

        databaseHelper = new DatabaseHelper(this);

        characterView = findViewById(R.id.characterView);
        opponentView = findViewById(R.id.opponentView);

        characterName = findViewById(R.id.characterName);
        opponentName = findViewById(R.id.opponentName);

        Cursor data = databaseHelper.getCharacterById(SelectCharacterPage.id+1);

        if (data.moveToFirst()) {
            Picasso.get().load(data.getString(3)).into(characterView);
            characterName.setText(data.getString(1));
        }
        data.close();

        data = databaseHelper.getRandomCharacter(SelectCharacterPage.id+1);

        if (data.moveToFirst()) {
            Picasso.get().load(data.getString(3)).into(opponentView);
            opponentName.setText(data.getString(1));
        }
        data.close();
    }
}
