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
        if(intent != null){
            String order = intent.getStringExtra("order");

            if(Integer.valueOf(order) > 5 && Integer.valueOf(order) < 20 ){
                game_window = (WebView) findViewById(R.id.game_window);
                game_window.loadUrl("http://imust-s.com/#/pages/sudoku/sudoku?order=" + order);
            }
            else if(Integer.valueOf(order) <= 5){
                game_window = (WebView) findViewById(R.id.game_window);
                game_window.loadUrl("http://imust-s.com/#/pages/magic_cube/magic_cube?order=" + order);
            }
            else if (Integer.valueOf(order) == 30){
                game_window = (WebView) findViewById(R.id.game_window);
                game_window.loadUrl("http://imust-s.com/#/pages/wuzieqi/wuziqi");
            }
            else if (Integer.valueOf(order) == 40){
                game_window = (WebView) findViewById(R.id.game_window);
                game_window.loadUrl("http://imust-s.com/#/pages/huarongdao/huarongdao");
            }
        }


        //支持JavaScript
        WebSettings setting = game_window.getSettings();
        setting.setJavaScriptEnabled(true);
    }
}
