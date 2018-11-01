package com.example.a11600624.myapplication.Database;

import android.provider.BaseColumns;

public class HighscoreContract {


    private HighscoreContract(){}

    public static final class Highscore implements BaseColumns{
        public static final String TABLE_NAME = "highscores";
        public static  final String COLUMN_NAME = "name";
        public static  final String COLUMN_SCORE = "score";


    }
}
