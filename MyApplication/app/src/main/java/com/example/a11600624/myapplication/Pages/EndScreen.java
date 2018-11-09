package com.example.a11600624.myapplication.Pages;

import android.arch.lifecycle.Lifecycle;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a11600624.myapplication.Models.GlobalSettings;
import com.example.a11600624.myapplication.R;
import com.squareup.picasso.Picasso;

import java.net.URI;

public class EndScreen extends AppCompatActivity {

    private TextView status;
    private boolean playedSound = false;

    MediaPlayer mp = new MediaPlayer();

    GlobalSettings globalVariable = new GlobalSettings();
    private Uri charUri;
    private ImageView opponentView;

    private Typeface tf1;

    Button fightAgainButton;
    Button quitButton;





    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("soundplayed", playedSound);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endscreen_page);

        status = findViewById(R.id.status);
        globalVariable = (GlobalSettings) getApplicationContext();
        opponentView = findViewById(R.id.characterView);
        fightAgainButton = findViewById(R.id.fightAgain);
        quitButton = findViewById(R.id.Quit);

        tf1 = Typeface.createFromAsset(getAssets(), "font1.ttf");
        fightAgainButton.setTypeface(tf1);
        quitButton.setTypeface(tf1);

        if(savedInstanceState != null){
            if(savedInstanceState.containsKey("soundplayed")){
                playedSound = savedInstanceState.getBoolean("soundplayed");
            }
        }

        Bundle b = getIntent().getExtras();
        int fightResult = -2; // or other values
        if (b != null) {
            fightResult = b.getInt("fightResult");
            charUri = Uri.parse(b.getString("charURI"));

        }




//        opponentView.setImageURI(charUri);
        Picasso.get().load(charUri).into(opponentView);

        switch (fightResult) {
            case -1:
                if (!(playedSound)) {
                    playLosingSound();
                    playedSound = true;
                }
                status.setText("You Lose!");
                break;
            case 0:
                if (!(playedSound)) {
                    playDrawSound();
                    playedSound =true;
                }
                status.setText("It's a draw!");

                break;
            case 1:
                if (!(playedSound)) {
                    playWinningSound();
                    playedSound = true;
                }
                status.setText("You Win!");

                break;
        }

        tf1 = Typeface.createFromAsset(getAssets(), "font1.ttf");
        status.setTypeface(tf1);

    }

    private void playWinningSound() {
        if (!(globalVariable.isMute())) {
            mp = MediaPlayer.create(this, R.raw.win);
            mp.start();
        }
    }

    private void playDrawSound() {
        if (!(globalVariable.isMute())) {
            mp = MediaPlayer.create(this, R.raw.draw);
            mp.start();
        }
    }

    private void playLosingSound() {
        if (!(globalVariable.isMute())) {
            mp = MediaPlayer.create(this, R.raw.lose);
            mp.start();
        }
    }

    public void quit(View view) {
        Intent startNewActivity = new Intent(this, MainActivity.class);
        startActivity(startNewActivity);
    }

    public void fightAgain(View view) {
        Intent startNewActivity = new Intent(this, SelectCharacterPage.class);
        startActivity(startNewActivity);
    }
}

