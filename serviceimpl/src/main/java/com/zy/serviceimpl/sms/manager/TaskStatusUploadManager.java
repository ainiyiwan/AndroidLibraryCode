package com.zy.serviceimpl.sms.manager;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.util.Log;

import com.zy.serviceimpl.sms.bean.TaskUploadConditionBean;
import com.zy.serviceimpl.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.core.app.ActivityCompat;
import permissions.dispatcher.PermissionUtils;

public class TaskStatusUploadManager {
    private static final String TAG = TaskStatusUploadManager.class.getSimpleName();
    private OnUploadStatusListener listener;
    private TaskUploadConditionBean bean;

    public TaskStatusUploadManager(OnUploadStatusListener listener) {
        this.listener = listener;
    }

    public TaskUploadConditionBean getBean() {
        return bean;
    }

    /**
     * 每次点击打电话或者发短信，更新此实体类
     */
    public void setBean(TaskUploadConditionBean bean) {
        this.bean = bean;
    }

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
    public void queryMessageLog(Activity activity) {
        if (!PermissionUtils.hasSelfPermissions(activity, Manifest.permission.READ_SMS) || ObjectUtils.isEmpty(activity)) {
            return;
        }
        //这些参数的targetApi为19，所以单独抽取出变量
        Uri CONTENT_URI = Uri.parse("content://sms/sent");//对应SDK Telephony.Sms.Sent.CONTENT_URI
        String ADDRESS = "address";//对应SDK Telephony.Sms.ADDRESS
        String BODY = "body";//对应SDK Telephony.Sms.BODY
        String DATE = "date";//对应SDK Telephony.Sms.DATE
        String READ = "read";//对应SDK Telephony.Sms.READ
        String STATUS = "status";//对应SDK Telephony.Sms.STATUS
        String TYPE = "type";//对应SDK Telephony.Sms.TYPE

        ContentResolver resolver = activity.getContentResolver();

        /**
         * 查询条件
         * 1. 电话等于发送电话
         * 2. 短信类型为已发出
         * 3. 发送时间大于bean.currentTime(详细看注释)
         */
        String selection = ADDRESS + " = ? AND " + TYPE + " = ? AND "  + DATE + " >= ?";
        String[] selectionArgs = {bean.phone, "2", bean.currentTime + ""};
        Cursor cursor = resolver.query(CONTENT_URI, new String[]{
                ADDRESS,
                BODY,
                DATE,
                READ,
                STATUS,
                TYPE,
        }, selection, selectionArgs, "date DESC");
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    String address = cursor.getString(0);
                    String body = cursor.getString(1);
                    long date = cursor.getLong(2);
                    String time = getDateToString(date);
                    String type = cursor.getString(5);
                    Log.e(TAG, "address = " + address + " sms = " + body + " date = " + date
                            + " time = " + time);

                    if (ObjectUtils.isNotEmpty(listener)) {
                        listener.onUploadStatus(bean.taskDetailID, bean.notifyStatus);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cursor.close();  //关闭cursor，避免内存泄露
            }
        }
    }

    public long getCurTimeLong(){
        return System.currentTimeMillis();
    }

    /**
     * 时间戳转换成字符窜
     * @param milSecond
     * @return
     */
    private String getDateToString(long milSecond) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

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
    public void queryCallLog(Activity activity) {
        //  权限检查
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED || ObjectUtils.isEmpty(activity)) {
            return;
        }
        //这两个参数的targetApi为21，所以单独抽取变量，变量值和SDK一致
        String CACHED_MATCHED_NUMBER = "matched_number";
        String CACHED_FORMATTED_NUMBER = "formatted_number";

        ContentResolver resolver = activity.getContentResolver();

        /**
         * 查询条件
         * 1. 电话等于发送电话
         * 2. 通话类型为呼出
         * 3. 通话时间大于bean.currentTime(详细看注释)
         * 4. 通话时长大于0
         */
        String selection = CACHED_MATCHED_NUMBER + " = ? AND "
                + CallLog.Calls.TYPE + " = ? AND "
                + CallLog.Calls.DATE + " >= ? AND "
                + CallLog.Calls.DURATION + " >= ?";
        String[] selectionArgs = {bean.phone, CallLog.Calls.OUTGOING_TYPE + "", bean.currentTime + "", "0"};
        //获取cursor对象
        Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, new String[]{
                CACHED_FORMATTED_NUMBER,
                CACHED_MATCHED_NUMBER,
                CallLog.Calls.CACHED_NAME,
                CallLog.Calls.TYPE,
                CallLog.Calls.DATE,
                CallLog.Calls.DURATION,
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

                    if (ObjectUtils.isNotEmpty(listener)) {
                        listener.onUploadStatus(bean.taskDetailID, bean.notifyStatus);
                    }
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

    public interface OnUploadStatusListener {
        /**
         * 刷新任务状态
         * @param taskDetailID     必填，任务明细ID
         * @param notifyStatus     必填，通知状态 CALLED 已拨打电话  SENT 已发送短信
         */
        void onUploadStatus(String taskDetailID, String notifyStatus);
    }
}