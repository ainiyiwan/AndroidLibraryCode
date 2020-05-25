package com.zy.androidlibrarycode.reflect.imooc;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.0.1
 * 描    述：
 * ================================================
 */
public class ReflectDemo1 {
    public static void main(String[] args) {
        Foo foo = new Foo();
        Class clazz = foo.getClass();
        Class clazz1 = Foo.class;
        print(clazz.getName() + " " + clazz.getSimpleName());
        print(clazz1.getName() + " " + clazz1.getSimpleName());
        try {
            Class clazz2 = Class.forName("com.zy.androidlibrarycode.reflect.imooc.ReflectDemo1$Foo");
            print(clazz2.getName() + " " + clazz2.getSimpleName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void print(String msg) {
        System.out.println(msg);
    }

    static class Foo{}
}
