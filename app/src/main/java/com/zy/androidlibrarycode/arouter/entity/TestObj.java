package com.zy.androidlibrarycode.arouter.entity;

import java.io.Serializable;

/**
 * ================================================
 * 作    者：zhangyang
 * 创建日期：2019/11/7
 * 描    述：
 * ================================================
 */
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
public class TestObj implements Serializable {
    private static final long serialVersionUID = 631383362485697332L;

    private String data;

    public TestObj(String data) {
        this.data = data;
    }

    public TestObj() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
