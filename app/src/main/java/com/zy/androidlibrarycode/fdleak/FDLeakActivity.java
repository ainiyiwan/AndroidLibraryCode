package com.zy.androidlibrarycode.fdleak;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import com.zy.androidlibrarycode.R;

import androidx.appcompat.app.AppCompatActivity;

public class FDLeakActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());

//        作者：钢铁鸭
//        链接：https://juejin.im/post/5cbd50f36fb9a032086dcc9f
//        来源：掘金
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdleak);
    }

    public void jump(View view) {
        for (int i = 0; i < 200000; i++) {
            Toast.makeText(this, "" + i, Toast.LENGTH_SHORT).show();
        }
//        startActivity(new Intent(this, FDLeakSecondActivity.class));
    }
}
