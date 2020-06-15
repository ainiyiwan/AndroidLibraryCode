package com.zy.androidlibrarycode.exception;

import android.os.Bundle;

import com.zy.androidlibrarycode.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class ConcurrentModificationExceptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concurrent_modification_exception);

        doException();
    }

    private void doException() {
        ArrayList<String> list=new ArrayList<String>();
        list.add("111");
        list.add("222");
        list.add("333");

//        for (int i = 0; i < list.size(); i++) {
//            if (i == list.size() - 1) {
//                list.remove(list.get(i));
//            }
//        }

        for (String s : list) {
            System.out.println(s);
            if (s.equals("333")) {
                list.remove(s);
            }
        }

        System.out.println(list);
    }
}
