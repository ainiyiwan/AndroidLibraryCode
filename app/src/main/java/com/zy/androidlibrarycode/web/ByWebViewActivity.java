package com.zy.androidlibrarycode.web;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.databinding.util.ImageUtil;
import com.zy.androidlibrarycode.parcel.ToastUtil;
import com.zy.androidlibrarycode.screenshot.ImageUtils;
import com.zy.androidlibrarycode.utils.StatusBarUtil;
import com.zy.androidlibrarycode.utils.WebTools;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import me.jingbin.web.ByWebView;
import me.jingbin.web.OnByWebChromeCallback;
import me.jingbin.web.OnByWebClientCallback;

/**
 * 网页可以处理:
 * 点击相应控件：
 * - 拨打电话、发送短信、发送邮件
 * - 上传图片(版本兼容)
 * - 全屏播放网络视频
 * - 进度条显示
 * - 返回网页上一层、显示网页标题
 * JS交互部分：
 * - 前端代码嵌入js(缺乏灵活性)
 * - 网页自带js跳转
 * 被作为第三方浏览器打开
 *
 * @author jingbin
 * link to https://github.com/youlookwhat/ByWebView
 */
public class ByWebViewActivity extends AppCompatActivity {
    public static final String TAG = ByWebViewActivity.class.getSimpleName();

    // 网页链接
    private String mUrl;
    private String mTitle;
    private WebView webView;
    private ByWebView byWebView;
    private TextView tvGunTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //第一步
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WebView.enableSlowWholeDocumentDraw();
        }

        setContentView(R.layout.activity_by_webview);
        getIntentData();
        initTitle();
        getDataFromBrowser(getIntent());
    }

    private void getIntentData() {
        mUrl = getIntent().getStringExtra("mUrl");
        mTitle = getIntent().getStringExtra("mTitle");
    }

    private void initTitle() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.colorPrimary), 0);
        initToolBar();
        LinearLayout container = findViewById(R.id.ll_container);
        byWebView = ByWebView
                .with(this)
                .setWebParent(container, new LinearLayout.LayoutParams(-1, -1))
                .useWebProgress(ContextCompat.getColor(this, R.color.coloRed))
                .setOnByWebChromeCallback(onByWebChromeCallback)
                .setOnByWebClientCallback(onByWebClientCallback)
                .addJavascriptInterface("injectedObject", new MyJavascriptInterface(this))
                .loadUrl(mUrl);
        webView = byWebView.getWebView();
        // 与js交互
//        webView.addJavascriptInterface(new MyJavascriptInterface(this), "injectedObject");
    }

    private void initToolBar() {
        // 可滚动的title 使用简单 没有渐变效果，文字两旁有阴影
        Toolbar mTitleToolBar = findViewById(R.id.title_tool_bar);
        tvGunTitle = findViewById(R.id.tv_gun_title);
        setSupportActionBar(mTitleToolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mTitleToolBar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.actionbar_more));
        tvGunTitle.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvGunTitle.setSelected(true);
            }
        }, 1900);
        tvGunTitle.setText(mTitle);
    }

    private OnByWebChromeCallback onByWebChromeCallback = new OnByWebChromeCallback() {
        @Override
        public void onReceivedTitle(String title) {
            Log.e("---title", title);
            tvGunTitle.setText(title);
        }
    };

    private OnByWebClientCallback onByWebClientCallback = new OnByWebClientCallback() {
        @Override
        public void onPageFinished(WebView view, String url) {
            Log.e(TAG, "onPageFinished = " + url);

            //方式一：只能截取当前页面
//            Bitmap bitmap = ImageUtils.view2Bitmap(view);
//            TestWebActivity.showImage(bitmap);

            //方式二：Web页面传递给App页面高度
//            Bitmap bitmap = ImageUtils.view2Bitmap(view, 5000);
//            ToastUtil.toast(ByWebViewActivity.this, "onPageFinished");
//            TestWebActivity.showImage(bitmap);

            //方式三：Web页面自身测量高度
//            Bitmap bitmap = ImageUtils.getWebViewBitmap(view);
//            ToastUtil.toast(ByWebViewActivity.this, "onPageFinished");
//            TestWebActivity.showImage(bitmap);

            //方式四：Web的getScale方法截取
            Bitmap bitmap = ImageUtils.captureWebViewLollipop(view);
            ToastUtil.toast(ByWebViewActivity.this, "onPageFinished");
            TestWebActivity.showImage(bitmap);

            //方式五：大神的博客，计算的高度不正确
//            Bitmap bitmap = ImageUtils.captureWebViewByYiFeng(view);
//            ToastUtil.toast(ByWebViewActivity.this, "onPageFinished");
//            TestWebActivity.showImage(bitmap);

            //方式六：使用低版本的截图方法 也是可行的。
//            Bitmap bitmap = ImageUtils.captureWebViewKitKat(view);
//            Log.e(TAG, "getBitmapSizeWithUnit = " + ImageUtils.getBitmapSizeWithUnit(bitmap) + " MB");
//            ToastUtil.toast(ByWebViewActivity.this, "onPageFinished");
//            TestWebActivity.showImage(bitmap);
        }

        @Override
        public boolean isOpenThirdApp(String url) {
            Log.e("---url", url);
            return WebTools.handleThirdApp(ByWebViewActivity.this, url);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_webview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// 返回键
                supportFinishAfterTransition();
                break;
            case R.id.actionbar_share:// 分享到
                String shareText = webView.getTitle() + webView.getUrl();
                WebTools.share(ByWebViewActivity.this, shareText);
                break;
            case R.id.actionbar_cope:// 复制链接
                WebTools.copy(webView.getUrl());
                Toast.makeText(this, "复制成功", Toast.LENGTH_LONG).show();
                break;
            case R.id.actionbar_open:// 打开链接
                WebTools.openLink(ByWebViewActivity.this, webView.getUrl());
                break;
            case R.id.actionbar_webview_refresh:// 刷新页面
                byWebView.reload();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 前端注入JS：
     * 这段js函数的功能就是，遍历所有的img节点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
     */
    private void loadImageClickJS() {
        byWebView.getLoadJsHolder().loadJs("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\");" +
                "for(var i=0;i<objs.length;i++)" +
                "{" +
                "objs[i].onclick=function(){window.injectedObject.imageClick(this.getAttribute(\"src\"));}" +
                "}" +
                "})()");
    }

    /**
     * 前端注入JS：
     * 遍历所有的<li>节点,将节点里的属性传递过去(属性自定义,用于页面跳转)
     */
    private void loadTextClickJS() {
        byWebView.getLoadJsHolder().loadJs("javascript:(function(){" +
                "var objs =document.getElementsByTagName(\"li\");" +
                "for(var i=0;i<objs.length;i++)" +
                "{" +
                "objs[i].onclick=function(){" +
                "window.injectedObject.textClick(this.getAttribute(\"type\"),this.getAttribute(\"item_pk\"));}" +
                "}" +
                "})()");
    }

    /**
     * 传应用内的数据给html，方便html处理
     */
    private void loadCallJS() {
        // 无参数调用
//        byWebView.loadJs("javascript:javacalljs()");
        byWebView.getLoadJsHolder().quickCallJs("javacalljs");
        // 传递参数调用
//        byWebView.loadJs("javascript:javacalljswithargs('" + "android传入到网页里的数据，有参" + "')");
        byWebView.getLoadJsHolder().quickCallJs("javacalljswithargs", "android传入到网页里的数据，有参");
    }

    /**
     * get website source code
     * 获取网页源码
     */
    private void loadWebsiteSourceCodeJS() {
        byWebView.getLoadJsHolder().loadJs("javascript:window.injectedObject.showSource(document.getElementsByTagName('html')[0].innerHTML);");
    }

    /**
     * 上传图片之后的回调
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (byWebView.getWebChromeClient() != null) {
            byWebView.getWebChromeClient().handleFileChooser(requestCode, resultCode, intent);
        }
    }

    /**
     * 使用singleTask启动模式的Activity在系统中只会存在一个实例。
     * 如果这个实例已经存在，intent就会通过onNewIntent传递到这个Activity。
     * 否则新的Activity实例被创建。
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getDataFromBrowser(intent);
    }

    /**
     * 作为三方浏览器打开传过来的值
     * Scheme: https
     * host: www.jianshu.com
     * path: /p/1cbaf784c29c
     * url = scheme + "://" + host + path;
     */
    private void getDataFromBrowser(Intent intent) {
        Uri data = intent.getData();
        if (data != null) {
            try {
                String scheme = data.getScheme();
                String host = data.getHost();
                String path = data.getPath();
                String text = "Scheme: " + scheme + "\n" + "host: " + host + "\n" + "path: " + path;
                Log.e("data", text);
                String url = scheme + "://" + host + path;
                webView.loadUrl(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (byWebView.handleKeyEvent(keyCode, event)) {
            return true;
        } else {
            finish();
            return true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        byWebView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        byWebView.onResume();
    }

    @Override
    protected void onDestroy() {
        byWebView.onDestroy();
        super.onDestroy();
    }

    /**
     * 打开网页:
     *
     * @param mContext 上下文
     * @param mUrl     要加载的网页url
     * @param mTitle   标题
     */
    public static void loadUrl(Context mContext, String mUrl, String mTitle) {
        Intent intent = new Intent(mContext, ByWebViewActivity.class);
        intent.putExtra("mUrl", mUrl);
        intent.putExtra("mTitle", mTitle == null ? "加载中..." : mTitle);
        mContext.startActivity(intent);
    }
}
