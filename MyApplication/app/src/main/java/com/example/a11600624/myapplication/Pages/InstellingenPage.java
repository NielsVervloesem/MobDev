

package com.example.a11600624.myapplication.Pages;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.a11600624.myapplication.Models.GlobalSettings;
import com.example.a11600624.myapplication.R;

public class InstellingenPage extends AppCompatActivity {

    private Switch aSwitch;
    private CheckBox checkBox;
    private RadioGroup radioGroup;
    private Button button;

    RadioButton radioButton;
    RadioButton radioButton2 ;
    RadioButton radioButton3;

    GlobalSettings globalVariable = new GlobalSettings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instellingen_page);

        globalVariable = (GlobalSettings) getApplicationContext();

        checkBox = findViewById(R.id.muteCheckBox);
        radioGroup = (RadioGroup) findViewById(R.id.radio);

        radioButton = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);


        button = findViewById(R.id.button5);

        if (globalVariable.isMute()) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);

        }

        button.setText(""+globalVariable.getModifier());
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


    }
}
