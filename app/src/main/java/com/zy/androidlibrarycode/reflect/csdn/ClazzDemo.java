package com.zy.androidlibrarycode.reflect.csdn;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.0.1
 * 描    述：
 * ================================================
 */
public class ClazzDemo {

    public static void main(String[] args){
        //没有泛型
        Class intClass = int.class;

        //带泛型的Class对象
        Class<Integer> integerClass = int.class;

        integerClass = Integer.class;

        //没有泛型的约束,可以随意赋值
        intClass= double.class;

        //编译期错误,无法编译通过
        //integerClass = double.class

        //编译无法通过
//        Class<Number> numberClass=Integer.class;

        Class<?> clazz = int.class;
        clazz = double.class;

        Class<? extends Number> clazz1 = int.class;
        clazz1 = Number.class;


    }
}
