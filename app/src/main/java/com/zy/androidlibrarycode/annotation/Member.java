package com.zy.androidlibrarycode.annotation;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.0.0
 * 描    述：
 * ================================================
 */
@DBTable(name = "MEMBER")
public class Member {
    //主键ID
    @SQLString(name = "ID",value = 50, constraint = @Constraints(primaryKey = true))
    private String id;

    @SQLString(name = "NAME" , value = 30)
    private String name;

    @SQLInteger(name = "AGE")
    private int age;

    @SQLString(name = "DESCRIPTION" ,value = 150 , constraint = @Constraints(allowNull = true))
    private String description;//个人描述

    //省略set get.....
}