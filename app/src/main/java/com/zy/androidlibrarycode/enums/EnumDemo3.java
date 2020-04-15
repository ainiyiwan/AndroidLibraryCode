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
 * Created by zejian on 2017/5/9.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 */
public enum EnumDemo3 {

    FIRST{
        @Override
        public String getInfo() {
            return "FIRST TIME";
        }
    },
    SECOND{
        @Override
        public String getInfo() {
            return "SECOND TIME";
        }
    }

    ;

    /**
     * 定义抽象方法
     * @return
     */
    public abstract String getInfo();

    //测试
    public static void main(String[] args){
        System.out.println("F:"+EnumDemo3.FIRST.getInfo());
        System.out.println("S:"+EnumDemo3.SECOND.getInfo());
        /**
         输出结果:
         F:FIRST TIME
         S:SECOND TIME
         */
    }
}
