package com.zy.serviceimpl;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import androidx.appcompat.app.AppCompatActivity;

@Route(path = Constant.SERVICE_IMPL_ACT)
public class ServiceImplActivity extends AppCompatActivity {

    @Autowired
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_impl);
        ARouter.getInstance().inject(this);
        String s = name;
    }
}
