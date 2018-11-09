package com.example.a11600624.myapplication.Pages;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a11600624.myapplication.Components.HighscoreAdapter;
import com.example.a11600624.myapplication.Database.DatabaseHelper;
import com.example.a11600624.myapplication.R;

public class HighscorePage extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private HighscoreAdapter adapter;
    private LinearLayoutManager mLayoutManager;

    private TextView highscoreHeading;
    private Button backButton;
    private Typeface tf1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore_page);

        databaseHelper = new DatabaseHelper(this);

        highscoreHeading = findViewById(R.id.highscoreHeading);
        backButton = findViewById(R.id.backButton);

        tf1 = Typeface.createFromAsset(getAssets(), "font1.ttf");
        highscoreHeading.setTypeface(tf1);
        backButton.setTypeface(tf1);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HighscoreAdapter(this, databaseHelper.getAllHighscoresDesc());

        mLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(divider);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);
        adapter.swapCursor(databaseHelper.getAllHighscoresDesc());
    }

    public void openMainPage(View view) {
        Intent startNewActivity = new Intent(this, MainActivity.class);
        startActivity(startNewActivity);
    }
}
