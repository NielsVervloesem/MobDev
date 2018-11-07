package com.example.a11600624.myapplication.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.a11600624.myapplication.R;

public class BackgroundDetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.background_detail_fragment, container, false);

        Bundle bundle = getArguments();
        EditText editText = (EditText) view.findViewById(R.id.editTextItem);
        String item = "";

        if(bundle != null){
            item = getArguments().getString("item");
        }

        editText.setText(item);

        return view;
    }
}
