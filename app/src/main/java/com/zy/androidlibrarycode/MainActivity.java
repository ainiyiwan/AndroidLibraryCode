package com.zy.androidlibrarycode;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zy.androidlibrarycode.arouter.constant.ARouterPath;
import com.zy.androidlibrarycode.fastjson.FastjsonActivity;
import com.zy.androidlibrarycode.lombok.LombokActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String manufacturer = DeviceUtils.getManufacturer();
        String pdaType = DeviceUtils.getPdaType();
        Log.e("device", "manufacturer = " + manufacturer + " pdaType = " + pdaType);

        TextView textView3 = findViewById(R.id.tv);
        //设置字体大小为58（单位为物理像素），设置字体为红色，字体背景为黄色
        textView3.setText("北京市发布霾黄色预警，外出携带好口罩");
        Spannable span = new SpannableString(textView3.getText());
        span.setSpan(new AbsoluteSizeSpan(58), 11, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(Color.RED), 11, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new BackgroundColorSpan(Color.YELLOW), 11, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView3.setText(span);
//        版权声明：本文为CSDN博主「singwhatiwanna」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/singwhatiwanna/article/details/18363899
    }

    public void fastjson(View view) {
        startActivity(new Intent(this, FastjsonActivity.class));
    }

    public void lombok(View view) {
        startActivity(new Intent(this, LombokActivity.class));
    }

    public void arouter(View view) {
        // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
        ARouter.getInstance().build(ARouterPath.AROUTER_ACTIVITY).navigation(this, new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {

            }

            @Override
            public void onLost(Postcard postcard) {
                ARouter.getInstance().build(ARouterPath.AROUTER_JUMP_ACTIVITY).navigation();
            }

            @Override
            public void onArrival(Postcard postcard) {

            }

            @Override
            public void onInterrupt(Postcard postcard) {

            }
        });

//        // 2. 跳转并携带参数
//        ARouter.getInstance().build("/test/1")
//                .withLong("key1", 666L)
//                .withString("key3", "888")
//                .navigation();
    }
}
