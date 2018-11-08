package com.example.a11600624.myapplication.Pages;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.a11600624.myapplication.Components.BackgroundRecyclerViewAdapter;
import com.example.a11600624.myapplication.Database.DatabaseHelper;
import com.example.a11600624.myapplication.Fragments.BackgroundDetailFragment;
import com.example.a11600624.myapplication.Models.Background;
import com.example.a11600624.myapplication.R;

public class BackgroundPage extends AppCompatActivity {
    private TextView backgroundHeader;
    private Typeface tf1;


    private RecyclerView mRecyclerView;
    private BackgroundRecyclerViewAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);

        databaseHelper = new DatabaseHelper(this);

        backgroundHeader = findViewById(R.id.background_heading);
        tf1 = Typeface.createFromAsset(getAssets(), "font1.ttf");

        backgroundHeader.setTypeface(tf1);

        mRecyclerView = findViewById(R.id.backgroundRecyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration divider = new DividerItemDecoration(mRecyclerView.getContext(), mLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(divider);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new BackgroundRecyclerViewAdapter(new BackgroundRecyclerViewAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Background item) {
                ConstraintLayout detailFragment = findViewById(R.id.detail);
                if (detailFragment != null && detailFragment.isEnabled()) {
                    BackgroundDetailFragment fragment = new BackgroundDetailFragment();

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("background",item);
                    fragment.setArguments(bundle);

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.detail, fragment);
                    transaction.addToBackStack(null);

                    transaction.commit();
                } else {
                    Intent intent = new Intent(getApplicationContext(), BackgroundDetailPage.class);
                    intent.putExtra("background", item);
                    startActivity(intent);
                }
            }
        }, this, databaseHelper.getAllBackgrounds());

        mRecyclerView.setAdapter(mAdapter);
    }
}
