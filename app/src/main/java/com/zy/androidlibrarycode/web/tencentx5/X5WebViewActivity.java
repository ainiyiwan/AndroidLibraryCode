package com.zy.androidlibrarycode.web.tencentx5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.parcel.ToastUtil;
import com.zy.androidlibrarycode.screenshot.ImageUtils;
import com.zy.androidlibrarycode.utils.CheckNetwork;
import com.zy.androidlibrarycode.utils.StatusBarUtil;
import com.zy.androidlibrarycode.utils.WebTools;
import com.zy.androidlibrarycode.web.MyJavascriptInterface;
import com.zy.androidlibrarycode.web.TestWebActivity;
import com.zy.androidlibrarycode.web.config.FullscreenHolder;
import com.zy.androidlibrarycode.web.config.MyWebChromeClient;
import com.zy.androidlibrarycode.web.config.WebProgress;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

/**
 * 使用 tencent x5 内核处理网页
 * 1、放入对应jar
 * 2、application 初始化
 * 3、gradle ndk配置
 * 4、jniLibs 配置
 * 5、添加权限 READ_PHONE_STATE
 * 6、getWindow().setFormat(PixelFormat.TRANSLUCENT);
 *
 * @author jingbin
 * link to https://github.com/youlookwhat/WebViewStudy
 */
public class X5WebViewActivity extends AppCompatActivity implements IX5WebPageView {
    public static final String TAG = X5WebViewActivity.class.getSimpleName();

    // 进度条
    private WebProgress mProgressBar;
    private com.tencent.smtt.sdk.WebView webView;
    // 全屏时视频加载view
    private FrameLayout videoFullView;
    // 加载视频相关
    private MyX5WebChromeClient mWebChromeClient;
    // 网页链接
    private String mUrl;
    private Toolbar mTitleToolBar;
    // 可滚动的title 使用简单 没有渐变效果，文字两旁有阴影
    private TextView tvGunTitle;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //第一步
        //作者：贝聊科技
        //链接：https://juejin.im/post/6844903509310078984
        //来源：掘金
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        // 有x5内核没有生效，并且Android版本是5.0及以上时，调用enableSlowWholeDocumentDraw()方便截取长图
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            android.webkit.WebView.enableSlowWholeDocumentDraw();
        }

        setContentView(R.layout.activity_webview_x5);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getIntentData();
        initTitle();
        initWebView();
        webView.loadUrl(mUrl);
        IX5WebViewExtension extension = webView.getX5WebViewExtension();
        getDataFromBrowser(getIntent());
    }

    private void getIntentData() {
        mUrl = getIntent().getStringExtra("mUrl");
        mTitle = getIntent().getStringExtra("mTitle");
    }


    private void initTitle() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.colorPrimary), 0);
        mProgressBar = findViewById(R.id.pb_progress);
        mProgressBar.setColor(ContextCompat.getColor(this, R.color.colorPink), ContextCompat.getColor(this, R.color.colorAccent));
        mProgressBar.show();
        webView = findViewById(R.id.webview_detail);
        mTitleToolBar = findViewById(R.id.title_tool_bar);
        tvGunTitle = findViewById(R.id.tv_gun_title);
        initToolBar();
    }

    private void initToolBar() {
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
        setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_webview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// 返回键
                handleFinish();
                break;
            case R.id.actionbar_share:// 分享到
                String shareText = webView.getTitle() + webView.getUrl();
                WebTools.share(this, shareText);
                break;
            case R.id.actionbar_cope:// 复制链接
                WebTools.copy(webView.getUrl());
                Toast.makeText(this, "复制成功", Toast.LENGTH_LONG).show();
                break;
            case R.id.actionbar_open:// 打开链接
                WebTools.openLink(this, webView.getUrl());
                break;
            case R.id.actionbar_webview_refresh:// 刷新页面
                webView.reload();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void initWebView() {
        com.tencent.smtt.sdk.WebSettings ws = webView.getSettings();
        // 网页内容的宽度自适应屏幕
        ws.setLoadWithOverviewMode(true);
        ws.setUseWideViewPort(true);
        // 保存表单数据
        ws.setSaveFormData(true);
        // 是否应该支持使用其屏幕缩放控件和手势缩放
        ws.setSupportZoom(true);
        ws.setBuiltInZoomControls(true);
        ws.setDisplayZoomControls(false);
        // 启动应用缓存
        ws.setAppCacheEnabled(true);
        // 设置缓存模式
        ws.setCacheMode(com.tencent.smtt.sdk.WebSettings.LOAD_DEFAULT);
        // setDefaultZoom  api19被弃用
        // 设置此属性，可任意比例缩放。
        ws.setUseWideViewPort(true);
        // 告诉WebView启用JavaScript执行。默认的是false。
        ws.setJavaScriptEnabled(true);
        //  页面加载好以后，再放开图片
        ws.setBlockNetworkImage(false);
        // 使用localStorage则必须打开
        ws.setDomStorageEnabled(true);
        // 排版适应屏幕
        ws.setLayoutAlgorithm(com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        // WebView是否新窗口打开(加了后可能打不开网页)
        ws.setSupportMultipleWindows(true);

        // webview从5.0开始默认不允许混合模式,https中不能加载http资源,需要设置开启。MIXED_CONTENT_ALWAYS_ALLOW
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ws.setMixedContentMode(com.tencent.smtt.sdk.WebSettings.LOAD_NORMAL);
        }
        /** 设置字体默认缩放大小(改变网页字体大小,setTextSize  api14被弃用)*/
        ws.setTextZoom(100);

        mWebChromeClient = new MyX5WebChromeClient(this);
        webView.setWebChromeClient(mWebChromeClient);
        // 与js交互
        webView.addJavascriptInterface(new MyJavascriptInterface(this), "injectedObject");
        webView.setWebViewClient(new MyX5WebViewClient(this));
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return handleLongImage();
            }
        });

    }

    @Override
    public void showWebView() {
        webView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hindWebView() {
        webView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void fullViewAddView(View view) {
        FrameLayout decor = (FrameLayout) getWindow().getDecorView();
        videoFullView = new FullscreenHolder(this);
        videoFullView.addView(view);
        decor.addView(videoFullView);
    }

    @Override
    public void showVideoFullView() {
        videoFullView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hindVideoFullView() {
        videoFullView.setVisibility(View.GONE);
    }

    @Override
    public void startProgress(int newProgress) {
        mProgressBar.setWebProgress(newProgress);
    }

    public void setTitle(String mTitle) {
        tvGunTitle.setText(mTitle);
    }

    /**
     * android与js交互：
     * 前端注入js代码：不能加重复的节点，不然会覆盖
     * 前端调用js代码
     */
    @Override
    public void onPageFinished(com.tencent.smtt.sdk.WebView view, String url) {
        if (!CheckNetwork.isNetworkConnected(this)) {
            mProgressBar.hide();
        }

        //内核启动失败
        //方式一：
        Bitmap bitmap = ImageUtils.captureWebViewKitKat(view);
        ToastUtil.toast(X5WebViewActivity.this, "onPageFinished");
        Log.e(TAG, "getBitmapSizeWithUnit = " + ImageUtils.getBitmapSizeWithUnit(bitmap) + " MB");
        TestWebActivity.showImage(bitmap);

        //方法二：不可行
//        Bitmap bitmap = ImageUtils.captureWebViewLollipop(view);
//        ToastUtil.toast(X5WebViewActivity.this, "onPageFinished");
//        Log.e(TAG, "getBitmapSizeWithUnit = " + ImageUtils.getBitmapSizeWithUnit(bitmap) + " MB");
//        TestWebActivity.showImage(bitmap);
    }

    /**
     * 处理是否唤起三方app
     */
    @Override
    public boolean isOpenThirdApp(String url) {
        return WebTools.handleThirdApp(this, url);
    }

    /**
     * 4.4以上可用 evaluateJavascript 效率高
     */
    private void loadJs(String jsString) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.evaluateJavascript(jsString, null);
        } else {
            webView.loadUrl(jsString);
        }
    }

    /**
     * 全屏时按返加键执行退出全屏方法
     */
    public void hideCustomView() {
        mWebChromeClient.onHideCustomView();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public FrameLayout getVideoFullView() {
        return videoFullView;
    }

    @Override
    public View getVideoLoadingProgressView() {
        return LayoutInflater.from(this).inflate(R.layout.video_loading_progress, null);
    }

    @Override
    public void onReceivedTitle(com.tencent.smtt.sdk.WebView view, String title) {
        setTitle(title);
    }

    @Override
    public void startFileChooserForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }

    /**
     * 上传图片之后的回调
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == MyWebChromeClient.FILECHOOSER_RESULTCODE) {
            mWebChromeClient.mUploadMessage(intent, resultCode);
        } else if (requestCode == MyWebChromeClient.FILECHOOSER_RESULTCODE_FOR_ANDROID_5) {
            mWebChromeClient.mUploadMessageForAndroid5(intent, resultCode);
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

    /**
     * 直接通过三方浏览器打开时，回退到首页
     */
    public void handleFinish() {
        supportFinishAfterTransition();
    }

    /**
     * 长按图片事件处理
     */
    private boolean handleLongImage() {
        final com.tencent.smtt.sdk.WebView.HitTestResult hitTestResult = webView.getHitTestResult();
        // 如果是图片类型或者是带有图片链接的类型
        if (hitTestResult.getType() == com.tencent.smtt.sdk.WebView.HitTestResult.IMAGE_TYPE ||
                hitTestResult.getType() == com.tencent.smtt.sdk.WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
            // 弹出保存图片的对话框
            new AlertDialog.Builder(this)
                    .setItems(new String[]{"查看大图", "保存图片到相册"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String picUrl = hitTestResult.getExtra();
                            //获取图片
                            Log.e("picUrl", picUrl);
                            switch (which) {
                                case 0:
                                    break;
                                case 1:
                                    break;
                                default:
                                    break;
                            }
                        }
                    })
                    .show();
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //全屏播放退出全屏
            if (mWebChromeClient.inCustomView()) {
                hideCustomView();
                return true;

                //返回网页上一页
            } else if (webView.canGoBack()) {
                webView.goBack();
                return true;

                //退出网页
            } else {
                handleFinish();
            }
        }
        return false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
        // 支付宝网页版在打开文章详情之后,无法点击按钮下一步
        webView.resumeTimers();
        // 设置为横屏
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    protected void onDestroy() {
        if (videoFullView != null) {
            videoFullView.removeAllViews();
        }
        if (webView != null) {
            ViewGroup parent = (ViewGroup) webView.getParent();
            if (parent != null) {
                parent.removeView(webView);
            }
            webView.removeAllViews();
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.stopLoading();
            webView.setWebChromeClient(null);
            webView.setWebViewClient(null);
            webView.destroy();
            webView = null;
        }
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
        Intent intent = new Intent(mContext, X5WebViewActivity.class);
        intent.putExtra("mUrl", mUrl);
        intent.putExtra("mTitle", mTitle == null ? "加载中..." : mTitle);
        mContext.startActivity(intent);
    }
}
