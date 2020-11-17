package com.zy.androidlibrarycode.retrofit.proxy;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.2.0
 * 描    述：
 * ================================================
 */
public class Test {
    public static void main(String[] args) {
        //目标对象
        Man target = new Man();

//        //代理对象,把目标对象传给代理对象,建立代理关系
//        ManProxy proxy = new ManProxy();
//        proxy.setTarget(target);
//
//        proxy.say();//执行的是代理的方法

        // 给目标对象，创建代理对象
        IPerson proxy = (IPerson) new ProxyFactory(target).getProxyInstance();
        // class $Proxy0   内存中动态生成的代理对象
        System.out.println(proxy.getClass());

        // 执行方法   【代理对象】
        proxy.say();
    }
}
