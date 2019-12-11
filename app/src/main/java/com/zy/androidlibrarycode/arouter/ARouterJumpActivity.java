package com.zy.androidlibrarycode.arouter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.arouter.constant.ARouterPath;
import com.zy.androidlibrarycode.arouter.entity.TestObj;
import com.zy.androidlibrarycode.arouter.entity.TestParcel;
import com.zy.serviceimpl.Constant;

import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

// 为每一个参数声明一个字段，并使用 @Autowired 标注
// URL中不能传递Parcelable类型数据，通过ARouter api可以传递Parcelable对象
@Route(path = ARouterPath.AROUTER_JUMP_ACTIVITY)
public class ARouterJumpActivity extends AppCompatActivity {
    @Autowired
    public String name;
    @Autowired
    int age;

    // 通过name来映射URL中的不同参数
    @Autowired(name = "girl")
    boolean boy;

    // 支持解析自定义对象，URL中使用json传递
    @Autowired
    TestObj obj;
    @Autowired
    TestParcel parcel;

    // 使用 withObject 传递 List 和 Map 的实现了
    // Serializable 接口的实现类(ArrayList/HashMap)
    // 的时候，接收该对象的地方不能标注具体的实现类类型
    // 应仅标注为 List 或 Map，否则会影响序列化中类型
    // 的判断, 其他类似情况需要同样处理
    @Autowired
    List<TestParcel> list;
    @Autowired
    Map<String, List<TestParcel>> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arouter_jump);

        ARouter.getInstance().inject(this);

        // ARouter会自动对字段进行赋值，无需主动获取
        Log.d("param", name + age + boy);
//        Log.d("param", obj.toString() + list.toString() + map.toString());
        getData();
    }

    private void getData() {
        TestParcel testParcel = parcel;
        TestObj testObj = obj;
        List<TestParcel> objList = list;
        Map<String, List<TestParcel>> listMap = map;
    }

    public void arouterJumpAct(View view) {
        ARouter.getInstance().build(Constant.SERVICE_IMPL_ACT)
                .withString("name", "hello service")
                .navigation();
    }
}
