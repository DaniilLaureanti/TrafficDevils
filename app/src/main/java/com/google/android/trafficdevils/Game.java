package com.google.android.trafficdevils;

import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;

public class Game {

    private GameFragment gf;
    private int point;
    private Drawable tempImage;
    private LinearLayout llGame;
    private LinearLayout llFooter;

    public Game(GameFragment gf) {
        this.gf = gf;
        llGame = gf.getLlGame();
        llFooter = gf.getLlFooter();
    }

    public void start(){

    }

    public void stop(){

    }

    private void addImage(){

    }

    public int random(int min, int max){

    }

}
