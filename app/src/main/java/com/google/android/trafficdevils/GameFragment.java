package com.google.android.trafficdevils;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameFragment extends Fragment {
    private RelativeLayout rlGame;
    private LinearLayout llFooter;
    private Game game;

    private int gameFieldHeight;
    private int gameFieldWidth;

    private TextView tvPoints;
    private TextView tvShape1;
    private TextView tvShape2;
    private TextView tvShape3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        rlGame = view.findViewById(R.id.rlGame);
        llFooter = view.findViewById(R.id.llFooter);
        tvPoints = view.findViewById(R.id.tvPoints);
        tvShape1 = view.findViewById(R.id.tvShape1);
        tvShape2 = view.findViewById(R.id.tvShape2);
        tvShape3 = view.findViewById(R.id.tvShape3);

        game = new Game(this);
        game.start();
        setWorkSize();
        return view;
    }

    public void setTextPoint(int point){
        tvPoints.setText(String.valueOf(point));
    }

    private void setWorkSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        gameFieldHeight = displayMetrics.heightPixels;
        gameFieldWidth = displayMetrics.widthPixels;
        gameFieldHeight -= gameFieldHeight * 0.25;
    }

    public RelativeLayout getLlGame() {
        return rlGame;
    }

    public LinearLayout getLlFooter() {
        return llFooter;
    }

    public int getGameFieldHeight() {
        return gameFieldHeight;
    }

    public int getGameFieldWidth() {
        return gameFieldWidth;
    }

    public void setPointsToTv(int... points) {
        tvShape1.setText(String.valueOf(points[0]));
        tvShape2.setText(String.valueOf(points[1]));
        tvShape3.setText(String.valueOf(points[2]));
    }
}