package com.zy.androidlibrarycode.dagger.component;

import com.zy.androidlibrarycode.dagger.DaggerActivity;
import com.zy.androidlibrarycode.dagger.module.MainModule;

import dagger.Component;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V3.8.0
 * 描    述：
 * ================================================
 */
//第一步 添加@Component
//第二步 添加module
@Component(modules = {MainModule.class})
public interface MainComponent {
    //第三步  写一个方法 绑定Activity /Fragment
    void inject(DaggerActivity activity);
}
