package com.zy.androidlibrarycode.lifecycle;

import android.os.Bundle;

import com.zy.androidlibrarycode.R;

import androidx.appcompat.app.AppCompatActivity;

public class LifeCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        TextDelegate textDelegate = new TextDelegate(this);
    }
}
