package com.zy.androidlibrarycode.viewstub;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.zy.androidlibrarycode.R;

import androidx.annotation.Nullable;

public class StubInflateView extends LinearLayout {

    public StubInflateView(Context context) {
        this(context, null);
    }

    public StubInflateView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StubInflateView(Context context, @Nullable AttributeSet attrs,
                           int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_stub_image,
            this, true);
    }


}
