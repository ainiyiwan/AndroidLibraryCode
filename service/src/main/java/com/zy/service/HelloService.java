package com.zy.service;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * ================================================
 * 作    者：zhangyang
 * 创建日期：2019/11/7
 * 描    述：
 * ================================================
 */
// 声明接口,其他组件通过接口来调用服务
public interface HelloService extends IProvider {
    String sayHello(String name);
}
