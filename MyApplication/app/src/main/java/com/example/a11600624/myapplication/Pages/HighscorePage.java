package com.example.a11600624.myapplication.Pages;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.a11600624.myapplication.Components.HighscoreAdapter;
import com.example.a11600624.myapplication.Database.DatabaseHelper;
import com.example.a11600624.myapplication.R;

public class HighscorePage extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private HighscoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore_page);

        databaseHelper = new DatabaseHelper(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HighscoreAdapter(this, databaseHelper.getAllHighscoresDesc());
        recyclerView.setAdapter(adapter);
        adapter.swapCursor(databaseHelper.getAllHighscoresDesc());
    }
}
