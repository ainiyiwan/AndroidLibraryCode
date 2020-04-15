package com.zy.androidlibrarycode.annotation;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.0.0
 * 描    述：
 * ================================================
 */
//使用注解
public class QuicklyWay {

    //当只想给value赋值时,可以使用以下快捷方式
    @IntegerVaule(20)
    public int age;

    //当name也需要赋值时必须采用key=value的方式赋值
    @IntegerVaule(value = 10000,name = "MONEY")
    public int money;

}
