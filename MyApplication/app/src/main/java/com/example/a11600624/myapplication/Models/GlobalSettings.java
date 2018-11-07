package com.example.a11600624.myapplication.Models;

import android.app.Application;

public class GlobalSettings extends Application {
    private double modifier = 1;
    private String background;
    private boolean mute = false;

    public GlobalSettings() {

    }

    public GlobalSettings(double modifier, String background, boolean mute) {
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

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }
}


