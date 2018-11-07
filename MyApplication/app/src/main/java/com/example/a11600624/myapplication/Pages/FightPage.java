package com.example.a11600624.myapplication.Pages;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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
import com.example.a11600624.myapplication.Models.GlobalSettings;
import com.example.a11600624.myapplication.Models.Highscore;
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
        mp = MediaPlayer.create(this, R.raw.punch);


        characterView = findViewById(R.id.characterView);
        opponentView = findViewById(R.id.opponentView);
        characterName = findViewById(R.id.characterName);
        opponentName = findViewById(R.id.opponentName);
        characterHealth = findViewById(R.id.characterHealth);
        characterHealth.setText(myHealth + getString(R.string.maxHealth));
        opponentHealth = findViewById(R.id.oppenentHealth);
        opponentHealth.setText(oppHealth + getString(R.string.maxHealth));
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


        fight.setEnabled(false);
        score.setVisibility(View.INVISIBLE);
        time.setVisibility(View.INVISIBLE);

        fight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicksCounter++;
                score.setText(getString(R.string.clicks) + clicksCounter);
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
                score.setText(getString(R.string.clicks) + clicksCounter);

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

                    }
                };
                timer.start();
            }
        });
    }


    private void resetButtonsAndTextViews() {
        fight.setEnabled(false);
        score.setVisibility(View.INVISIBLE);
        time.setVisibility(View.INVISIBLE);
        start.setEnabled(true);
    }

    private void playSound() {
        if (!(globalVariable.isMute())) {
            mp.start();
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

        flashAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                updateHealth();
                highscoreCheck();
            }
        });

    }
    
    private void updateHealth() {
        double modifier = globalVariable.getModifier();

        int myDamage = rand.nextInt(clicksCounter) +10;
        int oppenentDamage = (int) (modifier * (rand.nextInt(clicksCounter) +10));

        myHealth -= oppenentDamage;
        oppHealth -= myDamage * 1.5;

        opponentHealth.setText(oppHealth + getString(R.string.maxHealth));
        characterHealth.setText(myHealth + getString(R.string.maxHealth));
        oppDamageText.setText( myDamage +getString(R.string.damage));
        myDamageText.setText(oppenentDamage+ getString(R.string.damage));
    }

    private void highscoreCheck() {
        Cursor data = databaseHelper.getAllHighscoresAsc();
        Boolean isHighscore = false;

        if (data.getCount() < 15) {
            isHighscore = true;
        }
        else {
            if (data.moveToFirst()) {
                int lowest = data.getInt(data.getColumnIndex("score"));
                if (clicksCounter > lowest) {
                    databaseHelper.deleteHighscoreByScore(lowest);
                    isHighscore = true;
                }
            }
        }
        data.close();
        promptUser(isHighscore);
    }


    private void promptUser(Boolean isHighscore) {
        if(isHighscore){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.NewHighscore));
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
            builder.setPositiveButton(getString(R.string.Ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(!input.getText().toString().equals("")) {
                        Highscore highscore = new Highscore(input.getText().toString(), clicksCounter);
                        databaseHelper.addHighscore(highscore);
                        checkForWinner();
                    }
                }
            });
            builder.setNegativeButton(getString(R.string.Cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    checkForWinner();

                }
            });
            builder.show();
        } else {
            checkForWinner();
        }

    }

    private void checkForWinner() {
        Intent startNewActivity = new Intent(this, EndScreen.class);
        Bundle b = new Bundle();

        if(myHealth <= 0 || oppHealth <= 0) {
            if(myHealth < oppHealth) {
                b.putInt("fightResult", -1);
            } else {
                if(myHealth > oppHealth){
                    b.putInt("fightResult", 1);
                } else {
                    b.putInt("fightResult", 0);
                }
            }
            startNewActivity.putExtras(b); //Put your id to your next Intent
            startActivity(startNewActivity);
        }
    }





}
