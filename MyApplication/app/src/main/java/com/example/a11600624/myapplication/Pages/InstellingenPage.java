

package com.example.a11600624.myapplication.Pages;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.a11600624.myapplication.Models.GlobalSettings;
import com.example.a11600624.myapplication.R;

public class InstellingenPage extends AppCompatActivity {

    private Switch aSwitch;
    private CheckBox checkBox;
    private RadioGroup radioGroup;
    private Button button;


    GlobalSettings globalVariable = new GlobalSettings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instellingen_page);

        globalVariable = (GlobalSettings) getApplicationContext();

        checkBox = findViewById(R.id.muteCheckBox);
        radioGroup = (RadioGroup) findViewById(R.id.radio);
        button = findViewById(R.id.button5);

        if (globalVariable.isMute()) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);

        }

    }

    public void saveChanges(View view) {

        if (checkBox.isChecked()) {
            globalVariable.setMute(true);
        } else {
            globalVariable.setMute(false);
        }


    }
}
