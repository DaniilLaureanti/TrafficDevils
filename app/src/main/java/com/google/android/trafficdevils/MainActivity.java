package com.google.android.trafficdevils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Game");
        menu.add(0, 1, 1, "Web View");
        menu.add(0, 2, 2, "Load Server");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == 0){

            toastShow(item);

        }else if (item.getItemId() == 1){

            toastShow(item);

        }else if (item.getItemId() == 2){
            toastShow(item);

        }
        return super.onOptionsItemSelected(item);
    }

    private void toastShow(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void toastShow(MenuItem item) {
        toastShow(item.getTitle().toString());
    }




}