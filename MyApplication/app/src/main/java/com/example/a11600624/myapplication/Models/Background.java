package com.example.a11600624.myapplication.Models;

import android.app.Application;

public class Background extends Application {
    private String title;
    private String description;
    private int imageSource;

    public Background(String title, String description, int imageSource) {
        this.title = title;
        this.description = description;
        this.imageSource = imageSource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageSource() {
        return imageSource;
    }

    public void setImageSource(int imageSource) {
        this.imageSource = imageSource;
    }
}
