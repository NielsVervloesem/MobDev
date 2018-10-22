package com.example.a11600624.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a11600624.myapplication.Models.Character;

import java.util.Random;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "characters";

    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "description";
    private static final String COL4 = "thumbnailsource";
    private static final String COL5 = "imagesource";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addCharacter(Character character) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2, character.getName());
        contentValues.put(COL3, character.getDescription());
        contentValues.put(COL4, character.getThumbnailSource());
        contentValues.put(COL5, character.getImageSource());

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int getNumberOfCharacters() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);

        return data.getCount();
    }

    public Cursor getAllCharacters() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getCharacterById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID = " + id;
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
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID = " + randomId;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void dropCharacterTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
