package com.zy.androidlibrarycode.closepermission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.ExplainReasonCallback;
import com.permissionx.guolindev.callback.ForwardToSettingsCallback;
import com.permissionx.guolindev.callback.RequestCallback;
import com.permissionx.guolindev.request.ExplainScope;
import com.permissionx.guolindev.request.ForwardScope;
import com.zy.androidlibrarycode.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ClosePermissionActivity extends AppCompatActivity {
    public static final String Bundle_State1 = "bundle_state1";
    public static final String Bundle_State2 = "bundle_state2";

    public static final String SP_State1 = "sp_state1";
    public static final String SP_State2 = "sp_state2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_permission);
        LogUtils.e(PermissionConst.TAG, "ClosePermissionActivity onCreate savedInstanceState = " + savedInstanceState);
        if (savedInstanceState != null) {
            LogUtils.e(PermissionConst.TAG, "ClosePermissionActivity onCreate savedInstanceState = "+ "\n"
                    + savedInstanceState.getString(Bundle_State1) + "\n" +  savedInstanceState.getString(Bundle_State2));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.e(PermissionConst.TAG, "ClosePermissionActivity onDestroy");
    }

    public void requestPhonePer(View view) {
        PermissionX.init(this)
                .permissions(Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA)
                .explainReasonBeforeRequest()
                .onExplainRequestReason(new ExplainReasonCallback() {
                    @Override
                    public void onExplainReason(ExplainScope scope, List<String> deniedList) {
                        scope.showRequestReasonDialog(deniedList, "即将重新申请的权限是程序必须依赖的权限", "我已明白", "取消");
                    }
                })
                .onForwardToSettings(new ForwardToSettingsCallback() {
                    @Override
                    public void onForwardToSettings(ForwardScope scope, List<String> deniedList) {
                        scope.showForwardToSettingsDialog(deniedList, "您需要去应用程序设置当中手动开启权限", "我已明白", "取消");
                    }
                })
                .request(new RequestCallback() {
                    @Override
                    public void onResult(boolean allGranted, List<String> grantedList, List<String> deniedList) {
                        if (allGranted) {
                            call();
                        } else {
                            Toast.makeText(ClosePermissionActivity.this, "您拒绝了拨打电话权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //方式一 使用bundle存储，官方推荐，但是有大小限制
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.e(PermissionConst.TAG, "ClosePermissionActivity onSaveInstanceState outState = " + outState);
        outState.putString(Bundle_State1, "bundle1");
        outState.putString(Bundle_State2, "bundle2");
    }

    private void call() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//        } else {
//        }
//    }
}
