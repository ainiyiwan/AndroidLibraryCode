package com.zy.androidlibrarycode.databinding.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V3.7.0
 * 描    述：
 * ================================================
 */
public class Goods extends BaseObservable {
    //如果是 public 修饰符，则可以直接在成员变量上方加上 @Bindable 注解
    @Bindable
    public String name;

    //如果是 private 修饰符，则在成员变量的 get 方法上添加 @Bindable 注解
    private String details;

    private float price;

    @Bindable
    public String url;

    public Goods(String name, String details, float price, String url) {
        this.name = name;
        this.details = details;
        this.price = price;
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
        //只更新本字段
        notifyPropertyChanged(com.zy.androidlibrarycode.BR.name);
    }

    @Bindable
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
        //更新所有字段
        notifyChange();
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

//    作者：叶志陈
//    链接：https://juejin.im/post/5b02cf8c6fb9a07aa632146d
//    来源：掘金
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
