package com.zy.androidlibrarycode.lombok;

import android.os.Bundle;

import com.zy.androidlibrarycode.R;

import androidx.appcompat.app.AppCompatActivity;

public class LombokActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lombok);
        LombokEntity lombokEntity = new LombokEntity("", 2);
        LombokEntity lombokEntity1 = new LombokEntity("", 2);
        LombokEntity lombokEntity2= LombokEntity.builder()
                .name("")
                .age(1)
                .build();
        lombokEntity.getName();
    }
}
