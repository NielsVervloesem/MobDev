package com.example.a11600624.myapplication.Fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a11600624.myapplication.Models.Background;
import com.example.a11600624.myapplication.Models.GlobalSettings;
import com.example.a11600624.myapplication.Pages.SettingsPage;
import com.example.a11600624.myapplication.R;

public class BackgroundDetailFragment extends Fragment {
    private LinearLayout linearLayoutBackground;
    private TextView backgroundTitle;
    private TextView backgroundDescription;
    private Button saveButton;

    private Typeface tf1;

    private int backgroundURI;
    GlobalSettings globalVariable = new GlobalSettings();
    private Background item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.background_detail_fragment, container, false);

        linearLayoutBackground = view.findViewById(R.id.linear_layout_background);
        backgroundTitle = view.findViewById(R.id.background_title);
        backgroundDescription = view.findViewById(R.id.background_description);
        saveButton = view.findViewById(R.id.save_button);

        tf1 = Typeface.createFromAsset(getActivity().getAssets(), "font1.ttf");
        saveButton.setTypeface(tf1);

        Bundle bundle = getArguments();
        if(bundle != null) {
            item = (Background) bundle.getSerializable("background");

            linearLayoutBackground.setBackgroundResource(item.getImageSource());
            backgroundTitle.setText(item.getTitle());
            backgroundDescription.setText(item.getDescription());

            backgroundURI = item.getImageSource();
        }

        saveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Drawable background = linearLayoutBackground.getBackground();
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        "Background saved",
                        Toast.LENGTH_SHORT);

                toast.show();

                globalVariable = (GlobalSettings) getActivity().getApplicationContext();
                globalVariable.setBackground(background);
                }
        });

        return view;
    }



}
