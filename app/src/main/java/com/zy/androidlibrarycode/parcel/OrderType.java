package com.zy.androidlibrarycode.parcel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ziv
 * @date 2017/8/11
 */

public enum OrderType implements Parcelable {
    /**
     * 及时达
     */
    TIMELY("TIMELY", "及时达"),
    /**
     * 预约单
     */
    BESPEAK("BESPEAK", "预约单"),
    /**
     * 自提单
     */
    PICKUP("PICKUP", "自提单"),
    /**
     * 拼团
     */
    GROUP_PICKUP("GROUP_PICKUP", "拼团自提"),
    /**
     * 心享到家
     */
    HOME("HOME", "心享到家");

    private String typeCode;
    private String typeDesc;
    private static Map<String, OrderType> map = new HashMap<>();
    private static Map<String, OrderType> typeDescMap = new HashMap<>();

    static {
        for (OrderType orderType : OrderType.values()) {
            map.put(orderType.typeCode, orderType);
            typeDescMap.put(orderType.typeDesc, orderType);
        }
    }

    OrderType(String typeCode, String typeDesc) {
        this.typeCode = typeCode;
        this.typeDesc = typeDesc;
    }

    public static final Creator<OrderType> CREATOR = new Creator<OrderType>() {
        @Override
        public OrderType createFromParcel(Parcel in) {
            return getOrderTypeByCode(in.readString());
        }

        @Override
        public OrderType[] newArray(int size) {
            return new OrderType[size];
        }
    };

    @Override
    public String toString() {
        return typeCode;
    }

    public String getOrderTypeDesc() {
        return typeDesc;
    }

    public static OrderType getOrderTypeByCode(String typeCode) {
        return map.get(typeCode);
    }

    public static OrderType getOrderTypeByDesc(String typeDesc) {
        return typeDescMap.get(typeDesc);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(typeCode);
        dest.writeString(typeDesc);
    }
}