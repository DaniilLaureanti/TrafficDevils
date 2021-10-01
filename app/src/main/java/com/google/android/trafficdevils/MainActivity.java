package com.google.android.trafficdevils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.net.InetSocketAddress;

public class MainActivity extends AppCompatActivity {

    private final int ITEM_GAME = 0;
    private final int ITEM_WEB = 1;
    private final int ITEM_LOAD_SERVER = 2;

    public static String APP_PREFERENCES = "settings";// это будет именем файла настроек
    public static String APP_PREFERENCES_SWITCH = "switch"; // имя переключателя

    private WebViewFragment webViewFragment;
    private GameFragment gameFragment;
    private EmptyFragment emptyFragment;

    private String serverResult = "false";

    private SharedPreferences switchSettings;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webViewFragment = new WebViewFragment();
        gameFragment = new GameFragment();
        emptyFragment = new EmptyFragment();

        switchSettings = getSharedPreferences(APP_PREFERENCES, Activity.MODE_PRIVATE);

        start();
        editSettings();

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

        } else if (item.getItemId() == ITEM_WEB) {
            showFragmentWebView();

        } else if (item.getItemId() == ITEM_LOAD_SERVER) {
            readFromServer();
            choiceMode(serverResult);
            editSettings();
        }
        return super.onOptionsItemSelected(item);
    }

    private void start(){
        checksApplicationSettings();
    }

    private void checksApplicationSettings(){
        if (switchSettings.getString(APP_PREFERENCES_SWITCH, "false").equals("false")) {
            readFromServer();
        } else {
            if (serverResult.equals("true")) {
                showFragmentWebView();
            } else {
                showFragmentGame();
            }
        }
    }

    private void readFromServer() {
        Client client = new Client();
        client.setListener(this::getServerResultAndChoiceMode);
        client.read(new InetSocketAddress("192.168.0.7", 6788));
    }

    private void toastShow(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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

    private void showEmptyFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fcView, emptyFragment)
                .commit();
    }

    public void getServerResultAndChoiceMode(String serverResult) {
        saveServerResult(serverResult);
        choiceMode(serverResult);
    }

    private void choiceMode(String s) {
        if (s.equals("true")) {
            showFragmentWebView();
        } else if (s.equals("false")) {
            showFragmentGame();
        } else {
            toastShow("Failed read from Server");
            showEmptyFragment();
        }
    }

    public void saveServerResult(String s) {
        serverResult = s;
    }

    public void editSettings(){
        editor = switchSettings.edit();
        editor.putString(APP_PREFERENCES_SWITCH, "true");
        editor.apply();
    }
}