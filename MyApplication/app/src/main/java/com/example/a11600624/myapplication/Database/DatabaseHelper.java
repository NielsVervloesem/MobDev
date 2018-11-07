package com.example.a11600624.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a11600624.myapplication.Models.Background;
import com.example.a11600624.myapplication.Models.Character;
import com.example.a11600624.myapplication.Models.Highscore;

import java.util.Random;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MarvelDB";

    private static final String TABLE_CHARACTERS = "characters";
    private static final String TABLE_HIGHSCORES = "highscores";
    private static final String TABLE_BACKGROUNDS = "backgrounds";

    private static final String CHARACTERS_COL1 = "ID";
    private static final String CHARACTERS_COL2 = "name";
    private static final String CHARACTERS_COL3 = "description";
    private static final String CHARACTERS_COL4 = "thumbnailsource";
    private static final String CHARACTERS_COL5 = "imagesource";

    private static final String HIGHSCORES_COL1 = "ID";
    private static final String HIGHSCORES_COL2 = "name";
    private static final String HIGHSCORES_COL3 = "score";

    private static final String BACKGROUNDS_COL1 = "ID";
    private static final String BACKGROUNDS_COL2 = "title";
    private static final String BACKGROUNDS_COL3 = "description";
    private static final String BACKGROUNDS_COL4 = "imagesource";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_CHARACTERS + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + CHARACTERS_COL2 + " TEXT, " + CHARACTERS_COL3 + " TEXT, " + CHARACTERS_COL4 + " TEXT, " + CHARACTERS_COL5 + " TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_HIGHSCORES + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + HIGHSCORES_COL2 + " TEXT, " + HIGHSCORES_COL3 + " TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_BACKGROUNDS + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + BACKGROUNDS_COL2 + " TEXT, " + BACKGROUNDS_COL3 + " TEXT, " + BACKGROUNDS_COL4 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHARACTERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIGHSCORES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BACKGROUNDS);
        onCreate(db);
    }

    public boolean addCharacter(Character character) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CHARACTERS_COL2, character.getName());
        contentValues.put(CHARACTERS_COL3, character.getDescription());
        contentValues.put(CHARACTERS_COL4, character.getThumbnailSource());
        contentValues.put(CHARACTERS_COL5, character.getImageSource());

        long result = db.insert(TABLE_CHARACTERS, null, contentValues);

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int getNumberOfCharacters() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CHARACTERS;
        Cursor data = db.rawQuery(query, null);

        int count = data.getCount();
        data.close();

        return count;
    }

    public Cursor getAllCharacters() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CHARACTERS;
        Cursor data = db.rawQuery(query, null);

        return data;
    }

    public Cursor getCharacterById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CHARACTERS + " WHERE ID = " + id;
        Cursor data = db.rawQuery(query, null);

        return data;
    }

    public Cursor getRandomCharacter(int id) {
        Random random = new Random();
        int countCharacters = getNumberOfCharacters();

        int randomId = random.nextInt(countCharacters) + 1;

        while(randomId == id) {
            randomId = random.nextInt(countCharacters) + 1;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CHARACTERS + " WHERE ID = " + randomId;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getAllHighscoresDesc() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_HIGHSCORES + " ORDER BY " + HIGHSCORES_COL3 + " DESC";
        Cursor data = db.rawQuery(query, null);

        return data;
    }

    public Cursor getAllHighscoresAsc() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_HIGHSCORES + " ORDER BY " + HIGHSCORES_COL3 + " ASC";
        Cursor data = db.rawQuery(query, null);

        return data;
    }

    public void deleteHighscoreByScore(int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_HIGHSCORES + " WHERE " + HIGHSCORES_COL3 + " = " + score);
    }

    public void addHighscore(Highscore highscore) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(HIGHSCORES_COL2, highscore.getName());
        contentValues.put(HIGHSCORES_COL3, highscore.getScore());

        db.insert(TABLE_HIGHSCORES, null, contentValues);
    }

    public void addBackground(Background background) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(BACKGROUNDS_COL2, background.getTitle());
        contentValues.put(BACKGROUNDS_COL3, background.getDescription());
        contentValues.put(BACKGROUNDS_COL4, background.getImageSource());

        db.insert(TABLE_BACKGROUNDS, null, contentValues);
    }

    public Cursor getAllBackgrounds() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_BACKGROUNDS;
        Cursor data = db.rawQuery(query, null);

        return data;
    }
}
