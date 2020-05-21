package com.zy.androidlibrarycode.annotation.test;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Objects;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.0.1
 * 描    述：原文链接：https://blog.csdn.net/javazejian/article/details/71860633
 * ================================================
 */
public class XinTest {
    public static void main(String[] args) {
//        Class<?> clazz = DocumentDemo.class;
//        //根据指定注解类型获取该注解
//        DocumentA documentA=clazz.getAnnotation(DocumentA.class);
//        System.out.println("A:"+documentA);
//
//        //获取该元素上的所有注解，包含从父类继承
//        Annotation[] an= clazz.getAnnotations();
//        System.out.println("an:"+ Arrays.toString(an));
//        //获取该元素上的所有注解，但不包含继承！
//        Annotation[] an2=clazz.getDeclaredAnnotations();
//        System.out.println("an2:"+ Arrays.toString(an2));
//
//        //判断注解DocumentA是否在该元素上
//        boolean b=clazz.isAnnotationPresent(DocumentA.class);
//        System.out.println("b:"+b);

        Class<?> clazz = XinModel.class;
        Xin xin = clazz.getAnnotation(Xin.class);
        System.out.println("A:"+xin + " " + Objects.requireNonNull(xin).value() + " " + xin.value1());
        Annotation[] an = clazz.getAnnotations();
        System.out.println("an:"+ Arrays.toString(an));
        boolean b = clazz.isAnnotationPresent(Xin.class);
        System.out.println("b:"+b);

    }
}
