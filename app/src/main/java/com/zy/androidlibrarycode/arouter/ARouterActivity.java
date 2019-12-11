package com.zy.androidlibrarycode.arouter;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.arouter.constant.ARouterPath;
import com.zy.androidlibrarycode.arouter.entity.TestObj;
import com.zy.androidlibrarycode.arouter.entity.TestParcel;
import com.zy.service.HelloService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = ARouterPath.AROUTER_ACTIVITY)
public class ARouterActivity extends AppCompatActivity {
    @Autowired
    HelloService helloService;

    @Autowired(name = "/yourservicegroupname/hello")
    HelloService helloService2;

    HelloService helloService3;

    HelloService helloService4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arouter);
        ARouter.getInstance().inject(this);
        testService();
    }

    public void testService() {
        // 1. (推荐)使用依赖注入的方式发现服务,通过注解标注字段,即可使用，无需主动获取
        // Autowired注解中标注name之后，将会使用byName的方式注入对应的字段，不设置name属性，会默认使用byType的方式发现服务(当同一接口有多个实现的时候，必须使用byName的方式发现服务)
        helloService.sayHello("Vergil");
        helloService2.sayHello("Vergil");

        // 2. 使用依赖查找的方式发现服务，主动去发现服务并使用，下面两种方式分别是byName和byType
        helloService3 = ARouter.getInstance().navigation(HelloService.class);
        helloService4 = (HelloService) ARouter.getInstance().build("/yourservicegroupname/hello").navigation();
//        helloService3.sayHello("Vergil");
//        helloService4.sayHello("Vergil");
    }

    public void arouter(View view) {
        TestObj testObj = new TestObj("data");
//        testObj.setData("data");
        TestParcel testParcel = new TestParcel("parcel");
//        testParcel.setParcel("parcel");

        List<TestParcel> list = new ArrayList<>();
        list.add(testParcel);

        Map<String, List<TestParcel>> map = new HashMap<>();
        map.put("key", list);

        // 2. 跳转并携带参数
        ARouter.getInstance().build(ARouterPath.AROUTER_JUMP_ACTIVITY)
                .withString("name", "zhangyang")
                .withInt("age", 18)
                .withBoolean("girl", true)
                .withParcelable("parcel", testParcel)
                .withSerializable("obj", testObj)
//                .withObject("obj", testObj)
                .withObject("list", list)
                .withObject("map", map)
//                .withObject("obj1", testObj)
                .navigation();
    }
}
