package com.zy.androidlibrarycode.tests;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.0.0
 * 描    述：
 * ================================================
 */
public class MethodTest {

    public static void main(String[] args) {
        HelloYou helloYou = new HelloYou();
        helloYou.name = "zhang";
        System.out.println(helloYou.name +" first");
        setName(helloYou);
        System.out.println(helloYou.name +" second");
    }

    public static void setName(HelloYou helloYou) {
        HelloYou helloYou1 = helloYou.clone();
        helloYou1.name = "yang";
    }
}
