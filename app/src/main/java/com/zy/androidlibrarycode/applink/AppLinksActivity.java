package com.zy.androidlibrarycode.applink;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.zy.androidlibrarycode.R;

import androidx.appcompat.app.AppCompatActivity;

public class AppLinksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_links);

        ImageView imageView = findViewById(R.id.home_fragment_address_bar_iv_bg);
        ImageView imageView1 = findViewById(R.id.home_fragment_tools_iv_bg);

        Glide.with(this)
//                .load("https://resource.pagoda.com.cn/group1/M19/30/B6/CmiWa19qo9aAa9yaAD0JQbCNo88734.gif?width=750&height=186")
                .load("http://chuantu.xyz/t6/741/1600831627x992248267.png")
                .into(imageView);

        Glide.with(this)
                .load("https://resource.pagoda.com.cn/group1/M19/31/35/CmiU8F9plRmAIbiAACF5lFhERZs095.gif?width=750&height=204")
                .into(imageView1);

        imageView.post(new Runnable() {
            @Override
            public void run() {
                Log.i("image", "height " + imageView.getHeight());
                Log.i("image1", "height " + imageView1.getHeight());
                Log.i("image1", "density " + ScreenUtils.getScreenDensity());
                Log.i("image1", "density " + ScreenUtils.getScreenDensity() * 95);
            }
        });
    }
}
