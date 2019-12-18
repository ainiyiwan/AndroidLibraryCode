package com.zy.serviceimpl.sms;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zy.serviceimpl.R;
import com.zy.serviceimpl.setting.SettingPage;
import com.zy.serviceimpl.util.DeviceUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.PermissionUtils;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class SMSActivity extends AppCompatActivity {
    public static final String TAG = SMSActivity.class.getSimpleName();
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

    @NeedsPermission({Manifest.permission.READ_SMS, Manifest.permission.CAMERA})
    void showCamera() {
        if (PermissionUtils.hasSelfPermissions(this, Manifest.permission.READ_SMS)) {
            DeviceUtil.sendSms(this, "17322447154", "哈哈哈哈哈hello there哈哈哈哈哈哈");
        } else {
//            ActivityCompat.requestPermissions(this ,new String[]{Manifest.permission.READ_SMS}, 0);
            onCameraNeverAskAgain();
        }
    }

    @OnPermissionDenied({Manifest.permission.READ_SMS, Manifest.permission.CAMERA})
    void onCameraDenied() {
        Toast.makeText(this, R.string.permission_camera_denied, Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain({Manifest.permission.READ_SMS, Manifest.permission.CAMERA})
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

    @NeedsPermission({Manifest.permission.READ_CALL_LOG})
    void readCallLog() {
        DeviceUtil.callPhone(this, "10086");
    }

    @OnPermissionDenied({Manifest.permission.READ_CALL_LOG})
    void onCallLogDenied() {
        Toast.makeText(this, R.string.permission_camera_denied, Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain({Manifest.permission.READ_CALL_LOG})
    void onCallLogNeverAskAgain() {
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
            SMSActivityPermissionsDispatcher.showCameraWithPermissionCheck(this);
        } else {
            Toast.makeText(this, "设备未安装短信应用", Toast.LENGTH_SHORT).show();
        }
    }

    public void callPhone(View view) {
        if (DeviceUtil.canCallPhone(this)) {
            SMSActivityPermissionsDispatcher.readCallLogWithPermissionCheck(this);
        } else {
            Toast.makeText(this, "设备未安装电话拨号应用", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        SMSMethod.getInstance(this).unregisterReceiver();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryMessageLog();
        queryCallLog();
    }

    ////////////////////////////////////

    /**
     * 参考：http://korion.cn/2018/05/13/Android%E7%9F%AD%E4%BF%A1%E5%8F%91%E9%80%81%E4%B8%8E%E7%9B%91%E5%90%AC%E8%AF%BB%E5%8F%96/
     *
     * _id： 短信序号，如100
     * thread_id：对话的序号，如100，与同一个手机号互发的短信，其序号是相同的
     * address： 发件人地址，即手机号，如+86138138000
     * person： 发件人，如果发件人在通讯录中则为具体姓名，陌生人为null
     * date： 日期，long型，如1346988516，可以对日期显示格式进行设置
     * protocol： 协议0SMS_RPOTO短信，1MMS_PROTO彩信
     * read： 是否阅读0未读，1已读
     * status： 短信状态-1接收，0complete,64pending,128failed
     * type： 短信类型1是接收到的，2是已发出
     * body： 短信具体内容
     * service_center：短信服务中心号码编号
     */
    @TargetApi(21)
    private void queryMessageLog() {
        ContentResolver resolver = getContentResolver();
//        mCondition = Telephony.Sms.DATE + ">= ? and " + Telephony.Sms.DATE + " <= ?";
        //type： 短信类型1是接收到的，2是已发出
//        String mCondition = Telephony.Sms.ADDRESS+" = 13025166246 AND "+Telephony.Sms.TYPE+" = 2";
//         Telephony.Sms.ADDRESS + " = 17322447154";
        String selection = Telephony.Sms.ADDRESS+" = ? AND "+Telephony.Sms.TYPE+" = ?";
        String[] selectionArgs = {"17322447154", "2"};
        Cursor cursor = resolver.query(Telephony.Sms.Sent.CONTENT_URI, new String[]{
                Telephony.Sms.ADDRESS,   //
                Telephony.Sms.BODY,
                Telephony.Sms.DATE,
                Telephony.Sms.READ,
                Telephony.Sms.STATUS,
                Telephony.Sms.TYPE,
        }, selection, selectionArgs, "date DESC");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                {
                    String address = cursor.getString(0);
                    String body = cursor.getString(1);
                    long date = cursor.getLong(2);
                    String time = getDateToString(date);
                    long current = getCurTimeLong();
                    String time1= getDateToString(current);
                    String type = cursor.getString(5);
                    Log.e(SMSActivity.class.getSimpleName(), "address = " + address + " sms = " + body + " date = " + date + " time = " + time
                            + " current = " + current + " time1 = " + time1);
                }
                cursor.close();
            }
        }
    }

    public long getCurTimeLong(){
        long time=System.currentTimeMillis();
        return time;
    }

    /**
     * 时间戳转换成字符窜
     * @param milSecond
     * @return
     */
    public String getDateToString(long milSecond) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
    ////////////////////////////////////

    ////////////////////////////////////

    /**
     * 参考：https://blog.csdn.net/WS1549384743/article/details/65633292
     *
     * CallLog.Calls.CACHED_FORMATTED_NUMBER      通话记录格式化号码
     * CallLog.Calls.CACHED_MATCHED_NUMBER     通话记录为格式化号码
     * CallLog.Calls.CACHED_NAME     联系人名称
     * CallLog.Calls.TYPE    通话类型
     * CallLog.Calls.DATE    通话时间(long型)
     * CallLog.Calls.DURATION     通话时长(秒为单位)
     * CallLog.Calls.GEOCODED_LOCATION    运营商地址(如：浙江杭州)
     *
     * 通话类型
     * CallLog.Calls.INCOMING_TYPE      呼入
     * CallLog.Calls.OUTGOING_TYPE      呼出
     * CallLog.Calls.MISSED_TYPE       未接
     */
    @TargetApi(21)
    private void queryCallLog() {
        //  权限检查
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        ContentResolver resolver = getContentResolver();
        String selection = CallLog.Calls.CACHED_MATCHED_NUMBER+" = ? AND "+CallLog.Calls.TYPE+" = ?";
        String[] selectionArgs = {"10086", CallLog.Calls.OUTGOING_TYPE + ""};
        //获取cursor对象
        Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, new String[]{
                CallLog.Calls.CACHED_FORMATTED_NUMBER,
                CallLog.Calls.CACHED_MATCHED_NUMBER,
                CallLog.Calls.CACHED_NAME,
                CallLog.Calls.TYPE,
                CallLog.Calls.DATE,
                CallLog.Calls.DURATION,
                CallLog.Calls.GEOCODED_LOCATION,
        }, selection, selectionArgs, "date DESC");

        if (cursor != null) {
            Log.i(TAG, "cursor length is " + cursor.getCount());
            try {
                if (cursor.moveToFirst()) {
                    String formatted_number = cursor.getString(0);
                    String matched_number = cursor.getString(1);
                    String name = cursor.getString(2);
                    String type = getCallType(cursor.getInt(3));
                    long date = cursor.getLong(4);
                    String time = getDateToString(date);
                    long duration = cursor.getLong(5);
                    Log.e(TAG, "formatted_number = " + formatted_number + " matched_number = " + matched_number + " name = " + name + " type = "
                            + type + " time = " + time + " duration = " + duration);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cursor.close();  //关闭cursor，避免内存泄露
            }
        }
    }

    private String getCallType(int anInt) {
        switch (anInt) {
            case CallLog.Calls.INCOMING_TYPE:
                return "呼入";
            case CallLog.Calls.OUTGOING_TYPE:
                return "呼出";
            case CallLog.Calls.MISSED_TYPE:
                return "未接";
            default:
                break;
        }
        return null;
    }
    ////////////////////////////////////
}
