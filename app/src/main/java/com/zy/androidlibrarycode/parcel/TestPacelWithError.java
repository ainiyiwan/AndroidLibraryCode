package com.zy.androidlibrarycode.parcel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V3.7.0
 * 描    述：
 * ================================================
 */
public class TestPacelWithError implements Parcelable {

    public String name;
    public int age;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.age);
    }

    public TestPacelWithError() {
    }

    protected TestPacelWithError(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
    }

//    public static final Creator<TestPacelWithError> CREATOR = new Creator<TestPacelWithError>() {
//        @Override
//        public TestPacelWithError createFromParcel(Parcel source) {
//            return new TestPacelWithError(source);
//        }
//
//        @Override
//        public TestPacelWithError[] newArray(int size) {
//            return new TestPacelWithError[size];
//        }
//    };
}
