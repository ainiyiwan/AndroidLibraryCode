package com.zy.androidlibrarycode.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zy.androidlibrarycode.IMyAidlInterface;
import com.zy.androidlibrarycode.R;

import androidx.appcompat.app.AppCompatActivity;
//https://blog.csdn.net/lmj623565791/article/details/38461079
public class AidlActivity extends AppCompatActivity {
    private IMyAidlInterface mCalcAidl;

    private ServiceConnection mServiceConn = new ServiceConnection()
    {
        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            Log.e("client", "onServiceDisconnected");
            mCalcAidl = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            Log.e("client", "onServiceConnected");
            mRemote = service;
            mCalcAidl = IMyAidlInterface.Stub.asInterface(service);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
    }

    /**
     * 点击BindService按钮时调用
     * @param view
     */
    public void bindService(View view)
    {
        Intent intent = new Intent();
        intent.setClass(this, CalcService.class);
        bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);
    }
    /**
     * 点击unBindService按钮时调用
     * @param view
     */
    public void unbindService(View view)
    {
        unbindService(mServiceConn);
    }
    /**
     * 点击12+12按钮时调用
     * @param view
     */
    public void addInvoked(View view) throws Exception
    {

        if (mCalcAidl != null)
        {
            int addRes = mCalcAidl.add(12, 12);
            Toast.makeText(this, addRes + "", Toast.LENGTH_SHORT).show();
        } else
        {
            Toast.makeText(this, "服务器被异常杀死，请重新绑定服务端", Toast.LENGTH_SHORT)
                    .show();

        }

    }
    /**
     * 点击50-12按钮时调用
     * @param view
     */
    private static final java.lang.String DESCRIPTOR = "com.zy.androidlibrarycode.IMyAidlInterface";
    static final int TRANSACTION_min = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    private android.os.IBinder mRemote;
    public void minInvoked(View view) throws Exception
    {

//        if (mCalcAidl != null)
//        {
//            int addRes = mCalcAidl.min(50, 12);
//            Toast.makeText(this, addRes + "", Toast.LENGTH_SHORT).show();
//        } else
//        {
//            Toast.makeText(this, "服务端未绑定或被异常杀死，请重新绑定服务端", Toast.LENGTH_SHORT)
//                    .show();
//
//        }

        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        int _result;
        try {
            _data.writeInterfaceToken(DESCRIPTOR);
            _data.writeInt(50);
            _data.writeInt(12);
            mRemote.transact(TRANSACTION_min, _data, _reply, 0);
            _reply.readException();
            _result = _reply.readInt();
        }
        finally {
            _reply.recycle();
            _data.recycle();
        }
        Toast.makeText(this, _result + "", Toast.LENGTH_SHORT).show();
    }

}
