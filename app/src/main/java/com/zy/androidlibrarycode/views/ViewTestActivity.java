package com.zy.androidlibrarycode.views;

import android.os.Bundle;
import android.view.View;

import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.views.custom.SpecialPriceLimitedView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewTestActivity extends AppCompatActivity {
    private SpecialPriceLimitedView limitedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);

        limitedView = findViewById(R.id.special_price_limit_view);
    }

    public void clickView(View view) {
        limitedView.setGone();
    }
}
