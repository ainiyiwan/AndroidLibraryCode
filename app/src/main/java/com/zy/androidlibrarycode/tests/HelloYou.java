package com.zy.androidlibrarycode.tests;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.0.0
 * 描    述：
 * ================================================
 */
public class HelloYou implements Cloneable {
    public String name;

    @Override
    public HelloYou clone() {
        HelloYou stu = null;
        try{
            stu = (HelloYou)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return stu;
    }
//    版权声明：本文为CSDN博主「chun_soft」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/ztchun/article/details/79110096
}
