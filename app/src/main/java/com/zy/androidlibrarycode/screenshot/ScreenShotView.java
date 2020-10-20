package com.zy.androidlibrarycode.screenshot;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zy.androidlibrarycode.R;

import androidx.annotation.Nullable;

public class ScreenShotView extends LinearLayout {

    private TextView mTextView;

    public ScreenShotView(Context context) {
        this(context, null);
    }

    public ScreenShotView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScreenShotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.screen_shot_view, this, true);
        mTextView = view.findViewById(R.id.tv);
    }

    public void setText(String text) {
        mTextView.setText(text);
    }
}
