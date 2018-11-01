package com.example.a11600624.myapplication.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a11600624.myapplication.Database.HighscoreContract.Highscore;

public class HighscoreDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "highscore.db";
    public static final int DATABASE_VERSION = 1;
    private SQLiteDatabase database;



    public HighscoreDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_LIGHT_CREATE_HIGHSCORELIST_TABLE = "CREATE TABLE " +
                Highscore.TABLE_NAME + " (" +
                Highscore._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Highscore.COLUMN_NAME + " TEXT NOT NULL, " +
                Highscore.COLUMN_SCORE + " INTEGER NOT NULL" +
                ");";

        db.execSQL(SQL_LIGHT_CREATE_HIGHSCORELIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + Highscore.TABLE_NAME);
    onCreate(db);
    }

}
