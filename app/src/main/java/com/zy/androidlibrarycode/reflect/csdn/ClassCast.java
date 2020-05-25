package com.zy.androidlibrarycode.reflect.csdn;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.0.1
 * 描    述：
 * ================================================
 */
public class ClassCast {

    public void cast(){

        Animal animal= new Dog();
        //强制转换
        Dog dog = (Dog) animal;
    }

    public void cast2(Object obj){
        if(obj instanceof Animal){
            Animal animal= (Animal) obj;
        }
    }

    public void cast3(Object obj){
        //instanceof关键字
        if(obj instanceof Animal){
            Animal animal= (Animal) obj;
        }

        //isInstance方法
        if(Animal.class.isInstance(obj)){
            Animal animal= (Animal) obj;
        }
    }
}

interface Animal{ }

class Dog implements  Animal{ }
