package com.zy.androidlibrarycode.reflect.csdn;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zejian on 2017/5/1.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 */
public class ReflectMethod  {


    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {

        Class clazz = Class.forName("com.zy.androidlibrarycode.reflect.csdn.Circle");

        //根据参数获取public的Method,包含继承自父类的方法
        Method method = clazz.getMethod("draw",int.class,String.class);
        try {
            Circle circle = (Circle) clazz.newInstance();
            method.invoke(circle, 1, "hello");

            //获取当前类的方法包含private,该方法无法获取继承自父类的method
            Method method1 = clazz.getDeclaredMethod("drawCircle");
            System.out.println("method1::"+method1);
            method1.setAccessible(true);
            method1.invoke(circle);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        System.out.println("method:"+method);

        //获取所有public的方法:
        Method[] methods =clazz.getMethods();
        for (Method m:methods){
            System.out.println("m::"+m);
        }

        System.out.println("=========================================");

        //获取当前类的所有方法包含private,该方法无法获取继承自父类的method
        Method[] methods1=clazz.getDeclaredMethods();
        for (Method m:methods1){
            System.out.println("m1::"+m);
        }
    }

}

class Shape {
    public void draw(){
        System.out.println("draw");
    }

    public void draw(int count , String name){
        System.out.println("draw "+ name +",count="+count);
    }

}
class Circle extends Shape{

    private void drawCircle(){
        System.out.println("drawCircle");
    }
    public int getAllCount(){
        return 100;
    }
}
