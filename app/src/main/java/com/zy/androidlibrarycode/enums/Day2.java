package com.zy.androidlibrarycode.enums;

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

/**
 * Created by zejian on 2017/5/8.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 */
public enum Day2 {
    MONDAY("星期一"),
    TUESDAY("星期二"),
    WEDNESDAY("星期三"),
    THURSDAY("星期四"),
    FRIDAY("星期五"),
    SATURDAY("星期六"),
    SUNDAY("星期日");//记住要用分号结束

    private String desc;//中文描述

    /**
     * 私有构造,防止被外部调用
     * @param desc
     */
    private Day2(String desc){
        this.desc=desc;
    }

    /**
     * 定义方法,返回描述,跟常规类的定义没区别
     * @return
     */
    public String getDesc(){
        return desc;
    }

    public static void main(String[] args){
        for (Day2 day:Day2.values()) {
            System.out.println("name:"+day.name()+
                    ",desc:"+day.getDesc());
        }
    }

    /**
     输出结果:
     name:MONDAY,desc:星期一
     name:TUESDAY,desc:星期二
     name:WEDNESDAY,desc:星期三
     name:THURSDAY,desc:星期四
     name:FRIDAY,desc:星期五
     name:SATURDAY,desc:星期六
     name:SUNDAY,desc:星期日
     */
}
