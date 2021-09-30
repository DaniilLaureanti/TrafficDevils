package com.google.android.trafficdevils;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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

    private ArrayList<Integer> shapePoints;

    public Game(GameFragment gf) {
        this.gf = gf;
        rlGame = gf.getLlGame();
        llFooter = gf.getLlFooter();
        shapePoints = new ArrayList<>();
        shapePoints.add(1);
        shapePoints.add(2);
        shapePoints.add(3);

    }

    public void start() {
        addImagesAndSetPoins();
    }

    public void stop() {

    }

    private ImageView addImage(int num) {
        Drawable drawable = getDrawGreen();
        if (num == DRAW_GREEN) {
            drawable = getDrawGreen();
        } else if (num == DRAW_RED) {
            drawable = getDrawRed();
        } else if (num == DRAW_BLUE) {
            drawable = getDrawBlue();
        }
        RelativeLayout.LayoutParams params = getLayoutParams();
        return createNewImage(drawable, params);
    }

    private ImageView createNewImage(Drawable drawable, RelativeLayout.LayoutParams params) {
        newImageView = new ImageView(gf.getContext());
        newImageView.setImageDrawable(drawable);
        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        newImageView.setLayoutParams(params);
        rlGame.addView(newImageView);

        newImageView.setOnClickListener(v -> {
            removeImages();
            addImagesAndSetPoins();
            point += (Integer) v.getTag();
            gf.setTextPoint(point);
        });

        return newImageView;
    }


    private void addImagesAndSetPoins() {
        Collections.shuffle(shapePoints);

        ImageView iv;
        iv = addImage(DRAW_GREEN);
        iv.setTag(shapePoints.get(0));

        iv = addImage(DRAW_BLUE);
        iv.setTag(shapePoints.get(1));

        iv = addImage(DRAW_RED);
        iv.setTag(shapePoints.get(2));

        gf.setPointsToTv(
                shapePoints.get(0),
                shapePoints.get(1),
                shapePoints.get(2));
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
