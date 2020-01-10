package com.zy.androidlibrarycode.fdleak;

import android.os.Bundle;
import android.os.HandlerThread;

import com.zy.androidlibrarycode.R;

import androidx.appcompat.app.AppCompatActivity;

public class FDLeakSecondActivity extends AppCompatActivity {
    private HandlerThread handlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdleak_second);

        handlerThread = new HandlerThread("name");
        handlerThread.start();
    }
}
