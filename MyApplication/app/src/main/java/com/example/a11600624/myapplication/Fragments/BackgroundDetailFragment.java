package com.example.a11600624.myapplication.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a11600624.myapplication.Models.Background;
import com.example.a11600624.myapplication.R;

public class BackgroundDetailFragment extends Fragment {
    private LinearLayout linearLayoutBackground;
    private TextView backgroundTitle;
    private TextView backgroundDescription;
    private Button saveButton;

    private Typeface tf1;

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
            Background item = (Background) bundle.getSerializable("background");

            linearLayoutBackground.setBackgroundResource(item.getImageSource());
            backgroundTitle.setText(item.getTitle());
            backgroundDescription.setText(item.getDescription());
        }

        return view;
    }
}
