package com.zy.serviceimpl.sms;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zy.serviceimpl.R;
import com.zy.serviceimpl.setting.SettingPage;
import com.zy.serviceimpl.util.DeviceUtil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class SMSActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_SETTING = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // NOTE: delegate the permission handling to generated method
        SMSActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @NeedsPermission({Manifest.permission.CAMERA})
    void showCamera() {
        Toast.makeText(this, R.string.permission_camera_allow, Toast.LENGTH_SHORT).show();
        SMSMethod.getInstance(this).SendMessage("13025166246","测试短信。。。");
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied() {
        // NOTE: Deal with a denied permission, e.g. by showing specific UI
        // or disabling certain functionality
        Toast.makeText(this, R.string.permission_camera_denied, Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNeverAskAgain() {
        new AlertDialog.Builder(this)
                .setPositiveButton("允许", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        SettingPage setting = new SettingPage(SMSActivity.this);
                        setting.start(REQUEST_CODE_SETTING);
                    }
                })
                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                    }
                })
                .setCancelable(true)
                .setMessage("给我权限啊")
                .show();
    }

    public void sendTextMessage(View view){
        SMSActivityPermissionsDispatcher.showCameraWithPermissionCheck(this);
    }
    public void sendMultipartTextMessage(View view){
        if (DeviceUtil.canSendSms(this)) {
            DeviceUtil.sendSms(this, "13025166246", "hello there");
        } else {
            Toast.makeText(this, "设备未安装短信应用", Toast.LENGTH_SHORT).show();
        }
    }

    public void callPhone(View view) {
        if (DeviceUtil.canCallPhone(this)) {
            DeviceUtil.callPhone(this, "13025166246");
        } else {
            Toast.makeText(this, "设备未安装电话拨号应用", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        SMSMethod.getInstance(this).unregisterReceiver();
        super.onDestroy();
    }

}
