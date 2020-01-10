package com.zy.androidlibrarycode.parcel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zy.androidlibrarycode.R;

import androidx.appcompat.app.AppCompatActivity;

public class ParcelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel);
    }

    public void jump(View view) {
//        TestPacelWithError testPacelWithError = new TestPacelWithError();
//        testPacelWithError.name = "zhang";
//        testPacelWithError.age = 1;
//        Intent mIntent = new Intent(ParcelActivity.this, ParcelGetActivity.class);
//        mIntent.putExtra("parcel", testPacelWithError);
//        startActivity(mIntent);

        OrderType orderType = OrderType.getOrderTypeByCode("TIMELY");
        Intent mIntent = new Intent(ParcelActivity.this, ParcelGetActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("parcel", orderType);
        mIntent.putExtras(bundle);
        startActivity(mIntent);
    }
}
