package com.example.a11600624.myapplication.Pages;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a11600624.myapplication.Database.DatabaseHelper;
import com.example.a11600624.myapplication.Database.HighscoreContract;
import com.example.a11600624.myapplication.Database.HighscoreDBHelper;
import com.example.a11600624.myapplication.Models.GlobalSettings;
import com.example.a11600624.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class FightPage extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    private ImageView characterView;
    private ImageView opponentView;

    private TextView characterName;
    private TextView opponentName;

    private TextView characterHealth;
    private TextView opponentHealth;

    private TextView myDamageText;
    private TextView oppDamageText;

    TextView time;
    TextView score;
    Button fight;
    Button start;

    Random rand = new Random();
    int myHealth = 100;
    int oppHealth = 100;

    CountDownTimer timer;
    int timeLeft;
    int clicksCounter;

    private SQLiteDatabase database;


    GlobalSettings globalVariable = new GlobalSettings();

    MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_page);

        globalVariable = (GlobalSettings) getApplicationContext();
        HighscoreDBHelper dbHelper = new HighscoreDBHelper(this);
        database = dbHelper.getWritableDatabase();

        databaseHelper = new DatabaseHelper(this);

        characterView = findViewById(R.id.characterView);
        opponentView = findViewById(R.id.opponentView);
        characterName = findViewById(R.id.characterName);
        opponentName = findViewById(R.id.opponentName);
        characterHealth = findViewById(R.id.characterHealth);
        characterHealth.setText(myHealth + "/100");
        opponentHealth = findViewById(R.id.oppenentHealth);
        opponentHealth.setText(oppHealth + "/100");
        oppDamageText = findViewById(R.id.oppDamage);
        myDamageText = findViewById(R.id.myDamage);


        Cursor data = databaseHelper.getCharacterById(SelectCharacterPage.id + 1);

        if (data.moveToFirst()) {
            Picasso.get().load(data.getString(3)).into(characterView);
            characterName.setText(data.getString(1));
        }
        data.close();

        data = databaseHelper.getRandomCharacter(SelectCharacterPage.id + 1);

        if (data.moveToFirst()) {
            Picasso.get().load(data.getString(3)).into(opponentView);
            opponentName.setText(data.getString(1));
        }
        data.close();

        score = (TextView) findViewById(R.id.score);
        time = (TextView) findViewById(R.id.time);
        fight = (Button) findViewById(R.id.click);
        start = (Button) findViewById(R.id.start);

        mp = MediaPlayer.create(this, R.raw.punch);

        fight.setEnabled(false);
        score.setVisibility(View.INVISIBLE);
        time.setVisibility(View.INVISIBLE);

        fight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicksCounter++;
                score.setText("Clicks: " + clicksCounter);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicksCounter = 0;
                timeLeft = 5;
                fight.setEnabled(true);
                score.setVisibility(View.VISIBLE);
                time.setVisibility(View.VISIBLE);
                start.setEnabled(false);
                score.setText("Clicks: " + clicksCounter);

                timer = new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        timeLeft--;
                        time.setText("Time: " + timeLeft);
                    }

                    @Override
                    public void onFinish() {
                        resetButtonsAndTextViews();
                        playSound();
                        playFlashingAnimation();

                        updateHealth(clicksCounter);
                        highscoreCheck(clicksCounter);

                        checkForWinner();
                    }
                };
                timer.start();
            }
        });
    }

    private void checkForWinner() {
        Intent startNewActivity = new Intent(this, EndScreen.class);
        Bundle b = new Bundle();

        if(myHealth < 0 || oppHealth < 0){
                if(myHealth < oppHealth){
                    b.putString("status", "You lose!");
                } else {
                    if(myHealth > oppHealth){
                        b.putString("status", "You win!");
                    } else {
                        b.putString("status", "It's a draw!");
                    }
                }
            startNewActivity.putExtras(b); //Put your id to your next Intent
            startActivity(startNewActivity);
            }
        }



    private void playFlashingAnimation() {
        final Animation flashAnimation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        flashAnimation.setDuration(500); // duration - half a second
        flashAnimation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        flashAnimation.setRepeatCount(2); // Repeat animation infinitely
        flashAnimation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
        characterView.startAnimation(flashAnimation);
        opponentView.startAnimation(flashAnimation);
    }

    private void playSound() {
        if (!(globalVariable.isMute())) {
            mp.start();
        }
    }

    private void resetButtonsAndTextViews() {
        fight.setEnabled(false);
        score.setVisibility(View.INVISIBLE);
        time.setVisibility(View.INVISIBLE);
        start.setEnabled(true);
    }

    private void updateHealth(int clicksCounter) {
        double modifier = globalVariable.getModifier();

        int myDamage = rand.nextInt((clicksCounter + 10) - (clicksCounter - 10)) + clicksCounter - 10;
        int oppenentDamage = (int) (modifier * rand.nextInt((clicksCounter + 10) - (clicksCounter - 10)) + clicksCounter - 10);

        myHealth -= oppenentDamage;
        oppHealth -= myDamage;

        opponentHealth.setText(oppHealth + "/100");
        characterHealth.setText(myHealth + "/100");

        oppDamageText.setText("" + myDamage);
        myDamageText.setText("" + oppenentDamage);
    }

    private void highscoreCheck(int clicksCounter) {
        Cursor mCursor = database.query(HighscoreContract.Highscore.TABLE_NAME, null, null, null, null, null, HighscoreContract.Highscore.COLUMN_SCORE);
        mCursor.getCount();
        mCursor.moveToFirst();
        int lowest = mCursor.getInt(mCursor.getColumnIndex("score"));
        if (mCursor.getCount() < 15) {
            promptUser(clicksCounter);
        } else {
            if (clicksCounter > lowest) {
                database.execSQL("DELETE FROM " + HighscoreContract.Highscore.TABLE_NAME + " WHERE " + HighscoreContract.Highscore.COLUMN_SCORE + "='" + lowest + "'");
                promptUser(clicksCounter);
            }
        }

        mCursor.close();
    }

    private void promptUser(final int clicksCounter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Highscore, enter your name!");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateDatabase(input.getText().toString(), clicksCounter);

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void updateDatabase(String name, int clicksCounter) {

        if (name.trim().length() == 0) {
            return;
        }

        ContentValues cv = new ContentValues();
        cv.put(HighscoreContract.Highscore.COLUMN_NAME, name);
        cv.put(HighscoreContract.Highscore.COLUMN_SCORE, clicksCounter);
        database.insert(HighscoreContract.Highscore.TABLE_NAME, null, cv);

    }
}
