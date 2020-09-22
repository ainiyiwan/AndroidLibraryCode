package com.zy.androidlibrarycode.aes;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.utils.AESUtils;

import androidx.appcompat.app.AppCompatActivity;

public class AesActivity extends AppCompatActivity {
    public static final String TAG = AesActivity.class.getSimpleName();

    private String str = "hello";
    public static final String PLATFORM_KEY = "pagodabaiguoyuan";
    public String token = "813A4C5F54785ACDEE98E4F9ABBCE0CCF040E887D7EFD30E2D23BB8385FCE750";
    private String mBusinessEncryptionKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aes);
    }

    public void decrypt(View view) {
        // 解密后内容为如下结构 "9|9151dd8a7fd7|NaN" ，需要拆分取得真正的用户密钥
        try {
            String businessKeyContent = AESUtils.decrypt(PLATFORM_KEY, token);
            if (businessKeyContent.matches(".+?|.+?|.+?")) {
                mBusinessEncryptionKey = businessKeyContent.split("\\|")[1];
                Log.d(TAG, "generateBusinessKey: " + mBusinessEncryptionKey);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void encrypt(View view) {
        try {
            String encrypt = AESUtils.encrypt(mBusinessEncryptionKey, str);
            Log.d(TAG, "encrypt: " + encrypt);
            String decrypt = AESUtils.decrypt(mBusinessEncryptionKey, encrypt);
            Log.d(TAG, "decrypt: " + decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
