package com.zy.androidlibrarycode.annotation;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.0.0
 * 描    述：
 * ================================================
 * // <editor-folder desc="静态成员变量区域">
 * // </editor-folder>
 * <p>
 * // <editor-folder desc="View区域">
 * // </editor-folder>
 * <p>
 * // <editor-folder desc="对象区域">
 * // </editor-folder>
 * <p>
 * // <editor-folder desc="基础类型区域">
 * // </editor-folder>
 */
@DocumentA
class A1{ }

//继承了A类
@DocumentB
public class DocumentDemo1 extends A1{

    public static void main(String... args){

        Class<?> clazz = DocumentDemo1.class;
        //根据指定注解类型获取该注解
        DocumentA documentA=clazz.getAnnotation(DocumentA.class);
        System.out.println("A:"+documentA);

        //获取该元素上的所有注解，包含从父类继承
        Annotation[] an= clazz.getAnnotations();
        System.out.println("an:"+ Arrays.toString(an));
        //获取该元素上的所有注解，但不包含继承！
        Annotation[] an2=clazz.getDeclaredAnnotations();
        System.out.println("an2:"+ Arrays.toString(an2));

        //判断注解DocumentA是否在该元素上
        boolean b=clazz.isAnnotationPresent(DocumentA.class);
        System.out.println("b:"+b);

        /**
         * 执行结果:
         A:@com.zejian.annotationdemo.DocumentA()
         an:[@com.zejian.annotationdemo.DocumentA(), @com.zejian.annotationdemo.DocumentB()]
         an2:@com.zejian.annotationdemo.DocumentB()
         b:true
         */
    }
}
