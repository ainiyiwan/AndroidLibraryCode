package com.zy.androidlibrarycode.databinding;

import android.os.Bundle;

import com.zy.androidlibrarycode.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class DataBindingActivity extends AppCompatActivity {
    ActivityDataBindingBinding activityDataBindingBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDataBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
//        activityDataBindingBinding
//                .setUserInfo(User.builder().title("我是标题").subTitle("我是subTitle").build());
    }
}
