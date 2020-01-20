package com.zy.androidlibrarycode.dagger.module;

import com.zy.androidlibrarycode.dagger.entity.DaggerEntity;

import dagger.Module;
import dagger.Provides;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V3.8.0
 * 描    述：
 * ================================================
 */
//第一步 添加@Module 注解
@Module
public class MainModule {
    //第二步 使用Provider 注解 实例化对象
    @Provides
    DaggerEntity providerDaggerEntity() {
        return new DaggerEntity();
    }
}
