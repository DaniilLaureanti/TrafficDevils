package com.google.android.trafficdevils;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Game {

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
        addImage();
    }

    public void stop() {

    }

    private void addImage() {
        RelativeLayout.LayoutParams params = getLayoutParams();
        Drawable drawable = randomImage();
        createNewImage(drawable, params);
    }

    private void createNewImage(Drawable drawable, RelativeLayout.LayoutParams params) {
        newImageView = new ImageView(gf.getContext());
        newImageView.setImageDrawable(drawable);
        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        newImageView.setLayoutParams(params);
        rlGame.addView(newImageView);

        newImageView.setOnClickListener(v -> {
            removeImage();
            addImage();
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private Drawable randomImage() {
        return gf.getResources().getDrawable(R.drawable.ic_launcher_background);
    }

    public RelativeLayout.LayoutParams getLayoutParams() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.width = NEW_IMAGE_SIZE_WIDTH;
        params.height = NEW_IMAGE_SIZE_HEIGHT;

        params.leftMargin = random(NEW_IMAGE_SIZE_WIDTH, gf.getRlGameWorkWidth() - NEW_IMAGE_SIZE_WIDTH - 10);
        params.topMargin = random(NEW_IMAGE_SIZE_HEIGHT, gf.getRlGameWorkHeight() - NEW_IMAGE_SIZE_HEIGHT - 10);//

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

    public void removeImage(){
        rlGame.removeViewAt(0);
    }

}
