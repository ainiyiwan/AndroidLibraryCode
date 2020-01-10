package com.zy.androidlibrarycode.parcel;

import android.os.Bundle;

import com.zy.androidlibrarycode.R;

import androidx.appcompat.app.AppCompatActivity;

public class ParcelGetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_get);

//        TestPacelWithError testPacelWithError = getIntent().getParcelableExtra("parcel");
//
//        Log.i("log",testPacelWithError.name + " ==================== " + testPacelWithError.age);
        Bundle bundle = getIntent().getExtras();
        OrderType orderType = bundle.getParcelable("parcel");
    }
}
