

package com.example.a11600624.myapplication.Pages;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.a11600624.myapplication.R;

public class InstellingenPage extends AppCompatActivity {

    private Switch aSwitch;
    private CheckBox checkBox;
    private RadioGroup radioGroup;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instellingen_page);

        checkBox = findViewById(R.id.muteCheckBox);
        radioGroup = (RadioGroup) findViewById(R.id.radio);
        button = findViewById(R.id.button5);
    }

    public void saveChanges(View view) {

    }
}
