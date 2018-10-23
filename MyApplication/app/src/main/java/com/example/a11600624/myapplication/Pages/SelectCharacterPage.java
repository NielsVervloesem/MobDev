package com.example.a11600624.myapplication.Pages;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a11600624.myapplication.Components.SliderAdapter;
import com.example.a11600624.myapplication.Database.DatabaseHelper;
import com.example.a11600624.myapplication.R;

public class SelectCharacterPage extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private TextView mHeading;

    private TextView[] mDots;
    private SliderAdapter sliderAdapter;

    private Button mNextButton;
    private Button mBackButton;

    private Typeface tf1;

    public static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_character_page);

        databaseHelper = new DatabaseHelper(this);

        mSlideViewPager = findViewById(R.id.slideView);
        mDotLayout = findViewById(R.id.dotsLayout);
        mHeading = findViewById(R.id.selectHeading);

        mNextButton = findViewById(R.id.nextButton);
        mBackButton = findViewById(R.id.backButton);

        sliderAdapter = new SliderAdapter(this);

        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        tf1 = Typeface.createFromAsset(getAssets(), "font1.ttf");

        mHeading.setTypeface(tf1);
        mNextButton.setTypeface(tf1);
        mBackButton.setTypeface(tf1);

        id = 0;
    }

    public void addDotsIndicator(int position) {
        mDots = new TextView[databaseHelper.getNumberOfCharacters()];
        mDotLayout.removeAllViews();

        for(int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextColor(getResources().getColor(R.color.grey));
            mDots[i].setTextSize(40);

            mDotLayout.addView(mDots[i]);
        }

        if(mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            id = i;
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    public void openMainPage(View view) {
        Intent startNewActivity = new Intent(this, MainActivity.class);
        startActivity(startNewActivity);
    }

    public void openFightPage(View view) {
        Intent startNewActivity = new Intent(this, FightPage.class);
        startActivity(startNewActivity);
    }
}
