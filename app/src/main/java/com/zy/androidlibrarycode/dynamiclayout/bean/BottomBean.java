package com.zy.androidlibrarycode.dynamiclayout.bean;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V3.7.0
 * 描    述：商品金额>包装费>运费>免运费>优惠券>门店特价>满减>满折>积分
 * ================================================
 */
public class BottomBean implements Comparable<BottomBean> {
    public String leftText;
    public String rightText;
    public int position;//该数据在布局中哪个位置

    public BottomBean(String leftText, String rightText, int position) {
        this.leftText = leftText;
        this.rightText = rightText;
        this.position = position;
    }

    @Override
    public int compareTo(BottomBean o) {
        return position - o.position;
    }

    @Override
    public String toString() {
        return "BottomBean{" +
                "leftText='" + leftText + '\'' +
                ", rightText='" + rightText + '\'' +
                ", position=" + position +
                '}';
    }
}
