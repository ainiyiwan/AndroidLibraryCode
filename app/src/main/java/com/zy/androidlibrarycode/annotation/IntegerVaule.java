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
 * 版权声明：本文为CSDN博主「zejian_」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 *         原文链接：https://blog.csdn.net/javazejian/article/details/71860633
 * ================================================
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SuppressWarnings("uncheck")
@interface IntegerVaule{
    int value() default 0;
    String name() default "";
}
