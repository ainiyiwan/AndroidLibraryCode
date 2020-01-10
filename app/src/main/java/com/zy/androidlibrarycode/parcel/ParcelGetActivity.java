package com.zy.androidlibrarycode.parcel;

import android.os.Bundle;
import android.os.HandlerThread;
import android.widget.Button;

import com.zy.androidlibrarycode.R;

import androidx.appcompat.app.AppCompatActivity;

public class ParcelGetActivity extends AppCompatActivity {

    private HandlerThread handlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_get);

//        TestPacelWithError testPacelWithError = getIntent().getParcelableExtra("parcel");
//
//        Log.i("log",testPacelWithError.name + " ==================== " + testPacelWithError.age);
        Bundle bundle = getIntent().getExtras();
        OrderType orderType = bundle.getParcelable("parcel");

        Button button = findViewById(R.id.btn);
        button.postDelayed(new Runnable() {
            @Override
            public void run() {
                ToastUtil.toast(ParcelGetActivity.this, "我是3秒后toast");
            }
        }, 5000);

        handlerThread = new HandlerThread("name");
        handlerThread.start();
    }
}
