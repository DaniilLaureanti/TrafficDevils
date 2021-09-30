package com.google.android.trafficdevils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.net.InetSocketAddress;

public class MainActivity extends AppCompatActivity {

    private final int ITEM_GAME = 0;
    private final int ITEM_WEB = 1;
    private final int ITEM_LOAD_SERVER = 2;

    private WebViewFragment webViewFragment;
    private GameFragment gameFragment;
    private EmptyFragment emptyFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webViewFragment = new WebViewFragment();
        gameFragment = new GameFragment();
        emptyFragment = new EmptyFragment();

        readFromServer();
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
        if (item.getItemId() == ITEM_GAME) {
            showFragmentGame();
            toastShow(item);

        } else if (item.getItemId() == ITEM_WEB) {
            showFragmentWebView();
            toastShow(item);

        } else if (item.getItemId() == ITEM_LOAD_SERVER) {
            toastShow(item);
                readFromServer();
        }
        return super.onOptionsItemSelected(item);
    }

    private void readFromServer() {
        Client client = new Client();
        client.setListener(this::choiceMode);
        client.read(new InetSocketAddress("192.168.0.7", 6788));
    }

    private void toastShow(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void toastShow(MenuItem item) {
        toastShow(item.getTitle().toString());
    }

    private void showFragmentWebView() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fcView, webViewFragment)
                .commit();
    }

    private void showFragmentGame() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fcView, gameFragment)
                .commit();
    }

    private void showEmptyFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fcView, emptyFragment)
                .commit();
    }

    public void choiceMode(String serverResult){
        if (serverResult.equals("true")){
            showFragmentWebView();
        } else if(serverResult.equals("false")) {
            showFragmentGame();
        } else {
            toastShow("Failed read from Server");
            showEmptyFragment();
        }
    }

}