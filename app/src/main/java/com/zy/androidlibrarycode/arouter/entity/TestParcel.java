package com.zy.androidlibrarycode.arouter.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ================================================
 * 作    者：zhangyang
 * 创建日期：2019/11/7
 * 描    述：
 * ================================================
 */
public class TestParcel implements Parcelable {

    private String parcel;

    public TestParcel(String parcel) {
        this.parcel = parcel;
    }

    public String getParcel() {
        return parcel;
    }

    public void setParcel(String parcel) {
        this.parcel = parcel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.parcel);
    }

    public TestParcel() {
    }

    protected TestParcel(Parcel in) {
        this.parcel = in.readString();
    }

//    public static final Creator<TestParcel> CREATOR = new Creator<TestParcel>() {
//        @Override
//        public TestParcel createFromParcel(Parcel source) {
//            return new TestParcel(source);
//        }
//
//        @Override
//        public TestParcel[] newArray(int size) {
//            return new TestParcel[size];
//        }
//    };
}
