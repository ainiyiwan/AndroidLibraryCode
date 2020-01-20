package com.zy.androidlibrarycode.dagger;

import android.os.Bundle;

import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.dagger.component.DaggerMainComponent;
import com.zy.androidlibrarycode.dagger.entity.DaggerEntity;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;

public class DaggerActivity extends AppCompatActivity {
    /***
     * 第二步  使用Inject 注解，获取到A 对象的实例
     */
    @Inject
    DaggerEntity entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

        /***
         * 第一步 添加依赖关系
         */
        //第一种方式
        DaggerMainComponent.create().inject(this);

        /***
         * 第三步  调用A 对象的方法
         */
        entity.dagger();
    }
}
