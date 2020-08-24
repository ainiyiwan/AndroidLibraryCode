package com.zy.androidlibrarycode.web;

import android.os.Bundle;
import android.view.View;

import com.zy.androidlibrarycode.R;

import androidx.appcompat.app.AppCompatActivity;

public class TestWebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_web);
    }

    public void jump(View view) {
        ByWebViewActivity.loadUrl(this, "https://github.com/youlookwhat/WebViewStudy", "WebViewStudy");
    }
}
