package com.example.a11600624.myapplication.Pages;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.a11600624.myapplication.R;

public class EndScreen extends AppCompatActivity {

    private TextView status;
    private static int TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endscreen_page);

        status = findViewById(R.id.status);

        Bundle b = getIntent().getExtras();
        String value = ""; // or other values
        if (b != null)
            value = b.getString("status");

        status.setText(value);

        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                doStuff();
            }

        }, 4000);

    }

    private void doStuff() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}

