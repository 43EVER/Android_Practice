package com.example.rubikscube_test;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class GameWindow extends AppCompatActivity {

    private WebView game_window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_window);
        init();
    }
    private void init(){
        Intent intent = getIntent();
        String order = "3";
        if(intent != null){
            order = intent.getStringExtra("order");
        }
        game_window = (WebView) findViewById(R.id.game_window);
        game_window.loadUrl("http://cube.imust-s.com/#/pages/magic_cube/magic_cube?order=" + order);

        //支持JavaScript
        WebSettings setting = game_window.getSettings();
        setting.setJavaScriptEnabled(true);
    }
}
