package com.google.android.trafficdevils;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class GameFragment extends Fragment {
    private RelativeLayout rlGame;
    private LinearLayout llFooter;
    private Game game;

    private int rlGameWorkH;
    private int rlGameWorkW;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        rlGame = view.findViewById(R.id.rlGame);
        llFooter = view.findViewById(R.id.llFooter);

        game = new Game(this);
        game.start();
        setWorkSize();
        toastShow(rlGameWorkH + " " + rlGameWorkW);
        return view;
    }

    private void setWorkSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        rlGameWorkH = displayMetrics.heightPixels;
        rlGameWorkW = displayMetrics.widthPixels;
        rlGameWorkH -= rlGameWorkH * 0.25;
    }

    public RelativeLayout getLlGame() {
        return rlGame;
    }

    public LinearLayout getLlFooter() {
        return llFooter;
    }

    public int getRlGameWorkHeight() {
        return rlGameWorkH;
    }

    public int getRlGameWorkWidth() {
        return rlGameWorkW;
    }

    public void setImage() {

    }

    private void toastShow(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}