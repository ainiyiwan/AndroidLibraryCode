package com.zy.androidlibrarycode.reflect.imooc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.0.1
 * 描    述：
 * ================================================
 */
public class ReflectUtil {

    public static <E> void getMethod(E e) {
        Class clazz = e.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            print(method.getName() + " " + method.getModifiers() + " " + method.getReturnType());
        }
    }

    public static <E> void getFields(E e) {
        Class clazz = e.getClass();
        Field[] fields = clazz.getFields();
//        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Class fieldClazz = field.getClass();
            print(field.getName() + " " +field.getType() +" " + field.getModifiers());
        }
    }

    public static <E> void getConstructors(E e) {
        Class clazz = e.getClass();
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            Class[] parameters = constructor.getParameterTypes();
            print(constructor.getName());
        }
    }

    static void print(String... msgs) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String msg : msgs) {
            stringBuilder.append(msg).append(" ");
        }
        System.out.println(stringBuilder.toString());
    }
}
