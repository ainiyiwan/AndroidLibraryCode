package com.zy.androidlibrarycode.bitmap.test;

import android.os.Bundle;
import android.text.TextUtils;

import com.zy.androidlibrarycode.R;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class BitmapCompressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_compress);

        luban();
        compressHelper();
    }

    private void luban() {
        Luban.with(this)
//                .load(photos)
//                .ignoreBy(100)
//                .setTargetDir(getPath())
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @Override
                    public void onSuccess(File file) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                    }
                }).launch();
    }

    private void compressHelper() {
//        File newFile = new CompressHelper.Builder(this)
//                .setMaxWidth(720)  // 默认最大宽度为720
//                .setMaxHeight(960) // 默认最大高度为960
//                .setQuality(80)    // 默认压缩质量为80
//                .setFileName(yourFileName) // 设置你需要修改的文件名
//                .setCompressFormat(Bitmap.CompressFormat.JPEG) // 设置默认压缩为jpg格式
//                .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
//                        Environment.DIRECTORY_PICTURES).getAbsolutePath())
//                .build()
//                .compressToFile(oldFile);
    }
}
