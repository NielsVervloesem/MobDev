package com.example.a11600624.myapplication.Models;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.example.a11600624.myapplication.R;

public class GlobalSettings extends Application {
    private double modifier = 1;
    private Drawable background;
    private boolean mute = false;

    public GlobalSettings() {

    }

    public GlobalSettings(double modifier, Drawable background, boolean mute) {
        this.modifier = modifier;
        this.background = background;
        this.mute = mute;
    }

    public double getModifier() {
        return modifier;
    }

    public void setModifier(double modifier) {
        this.modifier = modifier;
    }

    public Drawable getBackground() {
        return background;
    }

    public void setBackground(Drawable background) {
        this.background = background;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }
}


