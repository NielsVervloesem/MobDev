package com.example.a11600624.myapplication.Pages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;

import com.example.a11600624.myapplication.R;

public class BackgroundDetail extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.background_detail);

        Intent intent = getIntent();
        String item = intent.getStringExtra("item");

        ((EditText) findViewById(R.id.editTextItem)).setText(item);
    }
}
