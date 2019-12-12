package com.zy.androidlibrarycode.databinding;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.databinding.model.Goods;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class TestActivity extends AppCompatActivity {
    private final String TAG = TestActivity.class.getSimpleName();

    private ActivityTestBinding activityTestBinding;
    private Goods goods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTestBinding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        goods = new Goods("code", "hi", 24, "https://www.baidu.com/img/bd_logo1.png");
        activityTestBinding.setGoods(goods);
        activityTestBinding.setGoodsHandler(new Goodshandler());
//        Uri uri = Uri.parse("android.resource://com.zy.androidlibrarycode/mipmap/ic_launcher");
//        activityTestBinding.setUserInfo(User.builder().drawableRes(uri).build());

//        Glide.with(this)
//                .load(R.mipmap.ic_launcher)
//                .into(activityTestBinding.image);

        goods.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (propertyId == com.zy.androidlibrarycode.BR.name) {
                    Log.e(TAG, "BR.name");
                } else if (propertyId == com.zy.androidlibrarycode.BR.details) {
                    Log.e(TAG, "BR.details");
                } else if (propertyId == com.zy.androidlibrarycode.BR._all) {
                    Log.e(TAG, "BR._all");
                } else {
                    Log.e(TAG, "未知");
                }
            }
        });
//        TestActivityPermissionsDispatcher
    }

    public class Goodshandler {
        public void changeGoodsName() {
            goods.setName("code" + new Random().nextInt(100));
            goods.setPrice(new Random().nextInt(100));
        }

        public void changeGoodsDetails() {
            goods.setDetails("hi" + new Random().nextInt(100));
            goods.setPrice(new Random().nextInt(100));
        }


//        作者：叶志陈
//        链接：https://juejin.im/post/5b02cf8c6fb9a07aa632146d
//        来源：掘金
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }

    @NeedsPermission({Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS})
    public void getContract() {
        Toast.makeText(this, "获得了权限", Toast.LENGTH_SHORT).show();
    }

}
