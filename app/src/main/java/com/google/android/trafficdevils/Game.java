package com.google.android.trafficdevils;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Game {

    private static final int DRAW_GREEN = 0;
    private static final int DRAW_RED = 1;
    private static final int DRAW_BLUE = 2;

    private final int NEW_IMAGE_SIZE_WIDTH = 200;
    private final int NEW_IMAGE_SIZE_HEIGHT = 200;

    private GameFragment gf;
    private int point;

    private RelativeLayout rlGame;
    private LinearLayout llFooter;
    private ImageView newImageView;


    public Game(GameFragment gf) {
        this.gf = gf;
        rlGame = gf.getLlGame();
        llFooter = gf.getLlFooter();
    }

    public void start() {
        addImages();
    }

    public void stop() {

    }

    private void addImage(int num) {
        Drawable drawable = getDrawGreen();
        if (num == DRAW_GREEN) {
            drawable = getDrawGreen();
        } else if (num == DRAW_RED) {
            drawable = getDrawRed();
        } else if (num == DRAW_BLUE) {
            drawable = getDrawBlue();
        }
        RelativeLayout.LayoutParams params = getLayoutParams();
        createNewImage(drawable, params);
    }

    private void createNewImage(Drawable drawable, RelativeLayout.LayoutParams params) {
        newImageView = new ImageView(gf.getContext());
        newImageView.setImageDrawable(drawable);
        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        newImageView.setLayoutParams(params);
        rlGame.addView(newImageView);

        newImageView.setOnClickListener(v -> {
            removeImages();
            addImages();
            point++;
            gf.setTextPoint(point);
        });
    }

    private void addImages() {
        addImage(DRAW_GREEN);
        addImage(DRAW_BLUE);
        addImage(DRAW_RED);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private Drawable getDrawGreen() {
        return gf.getResources().getDrawable(R.drawable.draw_green);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private Drawable getDrawRed() {
        return gf.getResources().getDrawable(R.drawable.draw_red);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private Drawable getDrawBlue() {
        return gf.getResources().getDrawable(R.drawable.draw_blue);
    }

    public RelativeLayout.LayoutParams getLayoutParams() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.width = NEW_IMAGE_SIZE_WIDTH;
        params.height = NEW_IMAGE_SIZE_HEIGHT;

        params.leftMargin = random(NEW_IMAGE_SIZE_WIDTH, gf.getGameFieldWidth() - NEW_IMAGE_SIZE_WIDTH);
        params.topMargin = random(NEW_IMAGE_SIZE_HEIGHT, gf.getGameFieldHeight() - NEW_IMAGE_SIZE_HEIGHT);
//        Toast.makeText(rlGame.getContext(), params.topMargin  + " x " + params.leftMargin, Toast.LENGTH_SHORT).show();

        return params;
    }

    public static int random(int min, int max) {
        if (min > max) {
            int tmp = min;
            min = max;
            max = tmp;
        }
        return (int) (Math.random() * (max - min)) + min;
    }

    public static int random(int max) {
        return random(0, max);
    }

    public void removeImages(){
        rlGame.removeAllViews();
    }

}
