package com.zy.androidlibrarycode.arouter.impl;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.zy.androidlibrarycode.arouter.constant.ARouterPath;

/**
 * ================================================
 * 作    者：zhangyang
 * 创建日期：2019/11/7
 * 描    述：自定义全局降级策略
 * ================================================
 */
// 实现DegradeService接口，并加上一个Path内容任意的注解即可
@Route(path = ARouterPath.AROUTER_JUMP_ACTIVITY)
public class DegradeServiceImpl implements DegradeService {
    @Override
    public void onLost(Context context, Postcard postcard) {
        // do something.
        Log.d("param", "DegradeServiceImpl onLost");
    }

    @Override
    public void init(Context context) {
        Log.d("param", "DegradeServiceImpl init");
    }
}
