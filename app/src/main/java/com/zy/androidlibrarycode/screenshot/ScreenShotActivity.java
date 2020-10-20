package com.zy.androidlibrarycode.screenshot;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zy.androidlibrarycode.R;

import androidx.appcompat.app.AppCompatActivity;

public class ScreenShotActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private ImageView imageView;
    private ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_shot);

        imageView = findViewById(R.id.image);
        imageView1 = findViewById(R.id.image1);
        linearLayout = findViewById(R.id.ll);



        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ImageUtils.view2Bitmap(createShotView());
                imageView1.setImageBitmap(bitmap);
            }
        });
    }

    private View createShowView() {
        LinearLayout rootView = new LinearLayout(this);
        rootView.setOrientation(LinearLayout.VERTICAL);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        rootView.setBackgroundColor(getResources().getColor(android.R.color.white));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = 30;

        TextView textView = new TextView(this);
        textView.setHeight(50);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setText("hello world");

        rootView.addView(textView, params);
        return rootView;
    }

    private View createShotView() {
        ScreenShotView view = new ScreenShotView(this);
        view.setText("你好啊！！！");
        return view;
    }
}
