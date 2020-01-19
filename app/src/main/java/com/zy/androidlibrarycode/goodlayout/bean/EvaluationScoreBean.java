package com.zy.androidlibrarycode.goodlayout.bean;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by wanggang on 2019/12/4
 * <p>
 * Describe: 评价评分模型
 */
public class EvaluationScoreBean implements Serializable {

    private int storeID;//门店ID
    private String goodStarAvg;// 商品评价
    private String serverStarAvg;// 门店服务评价
    private String logisticsStarAvg;//物流评价
    private String packagingStarAvg; // 包装评价
    private String overallStarAvg;// 综合评分
    private String statisticalBeginTime;//统计开始时间
    private String statisticalEndTime; // 统计结束时间

    private CityCommentAvg cityCommentAvg;

    private String orderCommentCount;//评价订单总数
    private String orderCount;// 订单数
    private String lastOrderCommentCount; // 上个周期评价订单数（当前查询是10.01-10.07，则上周期是9.23-09.30）
    private String lastOrderCount;//上个周期订单销售数

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public String getGoodStarAvg() {
        return TextUtils.isEmpty(goodStarAvg) ? "0" : goodStarAvg;
    }

    public void setGoodStarAvg(String goodStarAvg) {
        this.goodStarAvg = goodStarAvg;
    }

    public String getServerStarAvg() {
        return TextUtils.isEmpty(serverStarAvg) ? "0" : serverStarAvg;
    }

    public void setServerStarAvg(String serverStarAvg) {
        this.serverStarAvg = serverStarAvg;
    }

    public String getLogisticsStarAvg() {
        return TextUtils.isEmpty(logisticsStarAvg) ? "0" : logisticsStarAvg;
    }

    public void setLogisticsStarAvg(String logisticsStarAvg) {
        this.logisticsStarAvg = logisticsStarAvg;
    }

    public String getPackagingStarAvg() {
        return TextUtils.isEmpty(packagingStarAvg) ? "0" : packagingStarAvg;
    }

    public void setPackagingStarAvg(String packagingStarAvg) {
        this.packagingStarAvg = packagingStarAvg;
    }

    public String getOverallStarAvg() {
        return TextUtils.isEmpty(overallStarAvg) ? "0" : overallStarAvg;
    }

    public void setOverallStarAvg(String overallStarAvg) {
        this.overallStarAvg = overallStarAvg;
    }

    public String getStatisticalBeginTime() {
        return statisticalBeginTime;
    }

    public void setStatisticalBeginTime(String statisticalBeginTime) {
        this.statisticalBeginTime = statisticalBeginTime;
    }

    public String getStatisticalEndTime() {
        return statisticalEndTime;
    }

    public void setStatisticalEndTime(String statisticalEndTime) {
        this.statisticalEndTime = statisticalEndTime;
    }

    public CityCommentAvg getCityCommentAvg() {
        return cityCommentAvg==null?new CityCommentAvg():cityCommentAvg;
    }

    public void setCityCommentAvg(CityCommentAvg cityCommentAvg) {
        this.cityCommentAvg = cityCommentAvg;
    }

    public String getOrderCommentCount() {
        return orderCommentCount;
    }

    public void setOrderCommentCount(String orderCommentCount) {
        this.orderCommentCount = orderCommentCount;
    }

    public String getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }

    public String getLastOrderCommentCount() {
        return lastOrderCommentCount;
    }

    public void setLastOrderCommentCount(String lastOrderCommentCount) {
        this.lastOrderCommentCount = lastOrderCommentCount;
    }

    public String getLastOrderCount() {
        return lastOrderCount;
    }

    public void setLastOrderCount(String lastOrderCount) {
        this.lastOrderCount = lastOrderCount;
    }

    public static class CityCommentAvg implements Serializable {
        private String goodStarAvg;// 商品评价
        private String serverStarAvg;// 门店服务评价
        private String logisticsStarAvg;//物流评价
        private String packagingStarAvg;//包装评价
        private String overallStarAvg;// 综合评分

        public String getGoodStarAvg() {
            return TextUtils.isEmpty(goodStarAvg) ? "0" : goodStarAvg;
        }

        public void setGoodStarAvg(String goodStarAvg) {
            this.goodStarAvg = goodStarAvg;
        }

        public String getServerStarAvg() {
            return TextUtils.isEmpty(serverStarAvg) ? "0" : serverStarAvg;
        }

        public void setServerStarAvg(String serverStarAvg) {
            this.serverStarAvg = serverStarAvg;
        }

        public String getLogisticsStarAvg() {
            return TextUtils.isEmpty(logisticsStarAvg) ? "0" : logisticsStarAvg;
        }

        public void setLogisticsStarAvg(String logisticsStarAvg) {
            this.logisticsStarAvg = logisticsStarAvg;
        }

        public String getPackagingStarAvg() {
            return TextUtils.isEmpty(packagingStarAvg) ? "0" : packagingStarAvg;
        }

        public void setPackagingStarAvg(String packagingStarAvg) {
            this.packagingStarAvg = packagingStarAvg;
        }

        public String getOverallStarAvg() {
            return TextUtils.isEmpty(overallStarAvg) ? "0" : overallStarAvg;
        }

        public void setOverallStarAvg(String overallStarAvg) {
            this.overallStarAvg = overallStarAvg;
        }

        @Override
        public String toString() {
            return "CityCommentAvg{" +
                    "goodStarAvg='" + goodStarAvg + '\'' +
                    ", serverStarAvg='" + serverStarAvg + '\'' +
                    ", logisticsStarAvg='" + logisticsStarAvg + '\'' +
                    ", packagingStarAvg='" + packagingStarAvg + '\'' +
                    ", overallStarAvg='" + overallStarAvg + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "EvaluationScoreBean{" +
                "storeID=" + storeID +
                ", goodStarAvg='" + goodStarAvg + '\'' +
                ", serverStarAvg='" + serverStarAvg + '\'' +
                ", logisticsStarAvg='" + logisticsStarAvg + '\'' +
                ", packagingStarAvg='" + packagingStarAvg + '\'' +
                ", overallStarAvg='" + overallStarAvg + '\'' +
                ", statisticalBeginTime='" + statisticalBeginTime + '\'' +
                ", statisticalEndTime='" + statisticalEndTime + '\'' +
                ", cityCommentAvg=" + cityCommentAvg +
                ", orderCommentCount='" + orderCommentCount + '\'' +
                ", orderCount='" + orderCount + '\'' +
                ", lastOrderCommentCount='" + lastOrderCommentCount + '\'' +
                ", lastOrderCount='" + lastOrderCount + '\'' +
                '}';
    }
}
