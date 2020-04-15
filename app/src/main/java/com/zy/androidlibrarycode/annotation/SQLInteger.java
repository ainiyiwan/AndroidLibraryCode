package com.zy.androidlibrarycode.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


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
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLInteger {
    //该字段对应数据库表列名
    String name() default "";
    //嵌套注解
    Constraints constraint() default @Constraints;
}
