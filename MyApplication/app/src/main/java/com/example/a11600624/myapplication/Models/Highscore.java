package com.example.a11600624.myapplication.Models;

import android.app.Application;

public class Highscore extends Application {

    private String name;
    private int score;

    public Highscore(){

    }

    public Highscore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
