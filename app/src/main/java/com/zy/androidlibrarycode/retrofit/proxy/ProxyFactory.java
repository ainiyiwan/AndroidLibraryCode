package com.zy.androidlibrarycode.retrofit.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.2.0
 * 描    述：https://www.cnblogs.com/cenyu/p/6289209.html
 *
 *  // Android-added: Helper method invoke(Proxy, Method, Object[]) for ART native code.
 *  Proxy
 *     private static Object invoke(Proxy proxy, Method method, Object[] args) throws Throwable {
 *         InvocationHandler h = proxy.h;
 *         return h.invoke(proxy, method, args);
 *     }
 * ================================================
 */
public class ProxyFactory {
    //维护一个目标对象
    private Object target;
    public ProxyFactory(Object target){
        this.target=target;
    }

    //给目标对象生成代理对象
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("开始事务2");
                        //执行目标对象方法
                        Object returnValue = method.invoke(target, args);
                        System.out.println("提交事务2");
                        return returnValue;
                    }
                }
        );
    }
}
