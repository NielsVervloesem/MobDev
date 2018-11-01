package com.example.a11600624.myapplication.Pages;

import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a11600624.myapplication.Database.DatabaseHelper;
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


    GlobalSettings globalVariable = new GlobalSettings();

    MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_page);

        globalVariable = (GlobalSettings) getApplicationContext();

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
                        writeToDataBase();
                    }
                };
                timer.start();
            }
        });
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

        if(!(globalVariable.isMute())){
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
        int oppenentDamage = (int) (modifier *rand.nextInt((clicksCounter + 10) - (clicksCounter - 10)) + clicksCounter - 10);

        myHealth -= oppenentDamage;
        oppHealth -= myDamage;

        opponentHealth.setText(oppHealth + "/100");
        characterHealth.setText(myHealth + "/100");


        oppDamageText.setText(""+myDamage);
        myDamageText.setText(""+oppenentDamage);



    }

    private void writeToDataBase() {
        //TODO

    }
}
