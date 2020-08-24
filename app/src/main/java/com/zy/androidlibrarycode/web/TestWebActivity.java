package com.zy.androidlibrarycode.web;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.web.tencentx5.X5WebViewActivity;

import androidx.appcompat.app.AppCompatActivity;

public class TestWebActivity extends AppCompatActivity {

    private static ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_web);
        imageView = findViewById(R.id.image);
    }

    public void jump(View view) {
//        ByWebViewActivity.loadUrl(this, "https://github.com/youlookwhat/WebViewStudy", "WebViewStudy");
//        ByWebViewActivity.loadUrl(this, "https://juejin.im/post/6844903509310078984", "WebViewStudy");
//        ByWebViewActivity.loadUrl(this, "https://www.baidu.com/", "WebViewStudy");
        X5WebViewActivity.loadUrl(this, "https://www.baidu.com/", "WebViewStudy");
    }

    public static void showImage(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
