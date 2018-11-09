

package com.example.a11600624.myapplication.Pages;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.a11600624.myapplication.Models.GlobalSettings;
import com.example.a11600624.myapplication.R;

public class SettingsPage extends AppCompatActivity {
    private TextView settingsHeading;
    private Switch aSwitch;
    private CheckBox checkBox;
    private RadioGroup radioGroup;

    private Button backgroundButton;
    private Button backButton;
    private Button saveButton;

    private Typeface tf1;

    RadioButton radioButton;
    RadioButton radioButton2 ;
    RadioButton radioButton3;

    GlobalSettings globalVariable = new GlobalSettings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        globalVariable = (GlobalSettings) getApplicationContext();

        settingsHeading = findViewById(R.id.settingsHeading);

        checkBox = findViewById(R.id.muteCheckBox);
        radioGroup = findViewById(R.id.radio);

        radioButton = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);

        backgroundButton = findViewById(R.id.backgroundButton);
        backButton = findViewById(R.id.backButton);
        saveButton = findViewById(R.id.saveChanges);

        tf1 = Typeface.createFromAsset(getAssets(), "font1.ttf");

        settingsHeading.setTypeface(tf1);
        backgroundButton.setTypeface(tf1);
        backButton.setTypeface(tf1);
        saveButton.setTypeface(tf1);

        if (globalVariable.isMute()) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);

        }

        switch ("" + globalVariable.getModifier()) {
            case "0.8":
                radioButton.setChecked(true);
                break;
            case "1.0":
                radioButton2.setChecked(true);
                break;
            case "1.2":
                radioButton3.setChecked(true);
                break;
        }

    }

    public void saveChanges(View view) {

        if (checkBox.isChecked()) {
            globalVariable.setMute(true);
        } else {
            globalVariable.setMute(false);
        }


        if(radioButton.isChecked()){
            globalVariable.setModifier(0.8);
        }
        if(radioButton2.isChecked()){
            globalVariable.setModifier(1.0);
        }
        if(radioButton3.isChecked()){
            globalVariable.setModifier(1.2);
        }


        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void openBackGroundSelector(View view) {
        Intent i = new Intent(this, BackgroundPage.class);
        startActivity(i);
    }

    public void openMainPage(View view) {
        Intent startNewActivity = new Intent(this, MainActivity.class);
        startActivity(startNewActivity);
    }
}
