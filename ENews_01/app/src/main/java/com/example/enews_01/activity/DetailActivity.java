package com.example.enews_01.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.enews_01.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        WebView webView = findViewById(R.id.wv_content);
        String content = getIntent().getStringExtra("content");
        boolean isurl = getIntent().getBooleanExtra("isurl", false);
        if (!isurl) {
            webView.setWebViewClient(new WebViewClient());
            webView.loadDataWithBaseURL("http://www.jcodecraeer.com", content, "text/html", "utf-8", null);
        } else {
            webView.loadUrl(content);
        }
    }
}
