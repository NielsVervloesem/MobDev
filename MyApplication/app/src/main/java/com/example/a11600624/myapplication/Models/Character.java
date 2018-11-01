package com.example.a11600624.myapplication.Models;

import android.app.Application;

public class Character extends Application {
    private String name;
    private String description;
    private String thumbnailSource;
    private int imageSource;

    public Character(){

    }

    public Character(String name, int imageSource) {
        this.name = name;
        this.imageSource = imageSource;
    }

    public Character(String name, String description, String thumbnailSource, int imageSource) {
        this.name = name;
        this.description = description;
        this.thumbnailSource = thumbnailSource;
        this.imageSource = imageSource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailSource() {
        return thumbnailSource;
    }

    public void setThumbnailSource(String thumbnailSource) {
        this.thumbnailSource = thumbnailSource;
    }

    public int getImageSource() {
        return imageSource;
    }

    public void setImageSource(int imageSource) {
        this.imageSource = imageSource;
    }
}
