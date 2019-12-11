package com.zy.androidlibrarycode.fastjson;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * ================================================
 * 作    者：zhangyang
 * 创建日期：2019/11/6
 * 描    述：
 * ================================================
 */
public class Person {

    @JSONField(name = "AGE", ordinal = 1)
    private int age;

    @JSONField(name = "FULL NAME", ordinal = 0)
    private String fullName;

    @JSONField(name = "isHuman", ordinal = 2)
    private boolean isHuman;

    public Person() {
    }

    public Person(int age) {
        this.age = age;
    }

    public Person(String fullName) {
        this.fullName = fullName;
    }

    public Person(int age, String fullName) {
        this.age = age;
        this.fullName= fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public void setHuman(boolean human) {
        isHuman = human;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", fullName='" + fullName + '\'' +
                ", isHuman=" + isHuman +
                '}';
    }
}
