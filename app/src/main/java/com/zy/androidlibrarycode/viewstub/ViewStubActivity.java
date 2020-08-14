package com.zy.androidlibrarycode.viewstub;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.bitmap.test.MyBitmapUtil;
import com.zy.serviceimpl.util.ObjectUtils;

import androidx.appcompat.app.AppCompatActivity;

public class ViewStubActivity extends AppCompatActivity {
    private ViewStub viewStub;
    private ScrollView inflated;
    private LinearLayout linearLayout;
    private ImageView imageView;
    private StubInflateView stubInflateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub);
        viewStub = findViewById(R.id.stub);
        imageView = findViewById(R.id.iamge);
    }

    public void getViewStubPic(View view) {
        if (ObjectUtils.isEmpty(inflated)) {
            inflated = (ScrollView) viewStub.inflate();
            linearLayout = inflated.findViewById(R.id.layout_container);
            stubInflateView = new StubInflateView(this);
        }
    }

    public void delView(View view) {
        if (ObjectUtils.isNotEmpty(linearLayout)) {
            linearLayout.removeAllViews();
        }
    }

    public void capPicAndShow(View view) {
        Bitmap bitmap = MyBitmapUtil.getViewGroupBitmap(linearLayout);
        imageView.setImageBitmap(bitmap);
    }

    public void addView(View view) {
        linearLayout.addView(stubInflateView);
    }
}
