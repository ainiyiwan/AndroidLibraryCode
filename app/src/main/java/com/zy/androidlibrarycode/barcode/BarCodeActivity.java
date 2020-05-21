package com.zy.androidlibrarycode.barcode;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.oned.Code128Writer;
import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.utils.ScreenUtils;

import androidx.appcompat.app.AppCompatActivity;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class BarCodeActivity extends AppCompatActivity {

    private EditText editText;
    private ImageView imageView;
    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code);
        editText = findViewById(R.id.edit_text);
        imageView = findViewById(R.id.iv);

        width = ScreenUtils.getScreenWidth(this) - ScreenUtils.dp2px(this, 60);
        height = ScreenUtils.dp2px(this, 150);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int width1 = getNewWidth(editText.getText().toString().trim());
                Bitmap bitmapBar = QRCodeEncoder.syncEncodeBarcode(editText.getText().toString().trim(),
                        width1, height, 0);
                imageView.setImageBitmap(bitmapBar);
            }
        });
    }

    /**
     * 参考OneDimensionalCodeWriter源码中对于条码边距的计算
     */
    private int getNewWidth(String contents) {
        Code128Writer code128Writer = new Code128Writer();
        boolean[] code = code128Writer.encode(contents);

        int inputWidth = code.length;
        int outputWidth = Math.max(width, inputWidth);
        int remain = outputWidth % inputWidth;
        return outputWidth - remain;
    }
}
