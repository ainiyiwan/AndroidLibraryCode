package com.zy.serviceimpl;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zy.service.HelloService;

/**
 * ================================================
 * 作    者：zhangyang
 * 创建日期：2019/11/7
 * 描    述：
 * ================================================
 */
// 实现接口
@Route(path = "/yourservicegroupname/hello", name = "测试服务")
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        Log.d("param", "hello, " + name);
        return "hello, " + name;
    }

    @Override
    public void init(Context context) {

    }
}