package com.google.android.trafficdevils;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class GameFragment extends Fragment {
    private LinearLayout llGame;
    private LinearLayout llFooter;
    private Game game;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        llGame = view.findViewById(R.id.llGame);
        llFooter = view.findViewById(R.id.llFooter);

        game = new Game(this);
        game.start();

        return view;
    }

    public LinearLayout getLlGame() {
        return llGame;
    }

    public LinearLayout getLlFooter() {
        return llFooter;
    }

    public void setImage(){

    }
}