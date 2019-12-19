package com.zy.serviceimpl.sms.bean;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V3.7.0
 * 描    述：
 * ================================================
 */
public class TaskUploadConditionBean {
    public String taskDetailID;//任务明细ID
    public String notifyStatus;//通知状态 CALLED 已拨打电话  SENT 已发送短信
    public String messageStatus;//发送短信状态 Y 已发送  N 未发送
    public String phoneStatus;// 拨打手机状态 Y 已拨打  N 未拨打
    public String phone;//手机号
    public String msgContent;//短信模块
    /**
     * 当前时间
     * 点击发送短信或者拨打电话时赋值，在查询本地数据库记录的时候，
     * 要判断查询到记录的时间是否大于这个时间，如果大于这个时间说明是最新发送的短信或者拨打的电话
     */
    public long currentTime;

    private TaskUploadConditionBean(Builder builder) {
        this.taskDetailID = builder.taskDetailID;
        this.notifyStatus = builder.notifyStatus;
        this.messageStatus = builder.messageStatus;
        this.phoneStatus = builder.phoneStatus;
        this.phone = builder.phone;
        this.msgContent = builder.msgContent;
        this.currentTime = builder.currentTime;
    }

    public static final class Builder {
        String taskDetailID;
        String notifyStatus;
        String messageStatus;
        String phoneStatus;
        String msgContent;
        String phone;
        long currentTime;

        public Builder() {
        }
        
        public Builder taskDetailID(String taskDetailID) {
            this.taskDetailID = taskDetailID;
            return this;
        }

        public Builder notifyStatus(String notifyStatus) {
            this.notifyStatus = notifyStatus;
            return this;
        }

        public Builder messageStatus(String messageStatus) {
            this.messageStatus = messageStatus;
            return this;
        }

        public Builder phoneStatus(String phoneStatus) {
            this.phoneStatus = phoneStatus;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder msgContent(String msgContent) {
            this.msgContent = msgContent;
            return this;
        }

        public Builder currentTime(long currentTime) {
            this.currentTime = currentTime;
            return this;
        }

        public TaskUploadConditionBean build() {
            return new TaskUploadConditionBean(this);
        }
    }
}
