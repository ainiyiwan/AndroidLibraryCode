package com.zy.androidlibrarycode.retrofit.proxy;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.2.0
 * 描    述：
 * ================================================
 */
public class ManProxy implements IPerson{

    private IPerson target;

    public IPerson getTarget() {
        return target;
    }

    public ManProxy setTarget(IPerson target) {
        this.target = target;
        return this;
    }

    @Override
    public void say() {
        if (target != null) {
            System.out.println("man say invoked at : " + System.currentTimeMillis());
            target.say();
        }
    }
}
