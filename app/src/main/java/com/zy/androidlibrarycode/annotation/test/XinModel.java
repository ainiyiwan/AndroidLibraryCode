package com.zy.androidlibrarycode.annotation.test;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.0.1
 * 描    述：
 * ================================================
 */
@Xin(value = "类", value1 = "hh")
public class XinModel {
    @Xin("name")
    public String name;

    @Xin("method")
    public void getName() {

    }
}
