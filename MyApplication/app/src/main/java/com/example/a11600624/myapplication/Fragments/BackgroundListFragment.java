package com.example.a11600624.myapplication.Fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.a11600624.myapplication.Components.BackgroundCursorAdapter;
import com.example.a11600624.myapplication.Database.DatabaseHelper;
import com.example.a11600624.myapplication.Pages.BackgroundDetail;
import com.example.a11600624.myapplication.R;

public class BackgroundListFragment extends Fragment {
    private ListView backgroundListView;
    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.background_list_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        backgroundListView = (ListView) getView().findViewById(R.id.BackgroundListView);
        databaseHelper = new DatabaseHelper(getActivity());

        Cursor cursor = databaseHelper.getAllBackgrounds();
        BackgroundCursorAdapter backgroundCursorAdapter = new BackgroundCursorAdapter(getActivity(),cursor);
        backgroundListView.setAdapter(backgroundCursorAdapter);

        backgroundListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) backgroundListView.getItemAtPosition(i);
                //Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();

                BackgroundDetailFragment detailFragment = (BackgroundDetailFragment) getFragmentManager().findFragmentById(R.id.detail);
                if (detailFragment != null && detailFragment.isVisible()) {
                    // Visible: send bundle
                    BackgroundDetailFragment newFragment = new BackgroundDetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("item", item);
                    newFragment.setArguments(bundle);

                    android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(detailFragment.getId(), newFragment);
                    transaction.addToBackStack(null);

                    // Commit the transaction
                    transaction.commit();
                } else {
                    // Not visible: start as intent
                    Intent intent = new Intent(getActivity().getBaseContext(), BackgroundDetail.class);
                    intent.putExtra("item", item);
                    getActivity().startActivity(intent);
                }
            }
        });
    }
}
