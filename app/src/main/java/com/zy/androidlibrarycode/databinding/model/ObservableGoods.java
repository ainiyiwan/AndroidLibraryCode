package com.zy.androidlibrarycode.databinding.model;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V3.7.0
 * 描    述：
 * ================================================
 */
public class ObservableGoods {
    private ObservableInt age;
    private ObservableField<String> name;

    public ObservableGoods(int age, String name) {
        this.age = new ObservableInt(age);
        this.name = new ObservableField<>(name);
    }
}
