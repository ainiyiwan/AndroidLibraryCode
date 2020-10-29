package com.zy.androidlibrarycode.supercomponent.container.delegate;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.supercomponent.container.SuperFrameLayout;
import com.zy.androidlibrarycode.supercomponent.container.SuperLinearLayout;
import com.zy.androidlibrarycode.supercomponent.container.SuperRelativeLayout;
import com.zy.androidlibrarycode.supercomponent.container.util.ScreenUtils;
import com.zy.androidlibrarycode.supercomponent.container.util.SysOSUtils;

import androidx.annotation.NonNull;

/**
 * 容器的特性代理在
 * <p>
 * Created by waylenw on 2019-11-13.
 */
public class ContainerDelegate implements Rounded, AspectRatioed {

    private static int statusBarHeight;

    private final View mView;

    private final SuperMethod mCallback;

    // 自适应系统栏
    private boolean mFitSystemBar;

    private boolean mChecked;
    private boolean mAutoCheck = true;

    /**
     * ratio
     */
    private float mAspectRadio = 0;
    // 是否默认先填充宽度，否则填充高度
    private boolean mFillWidth = true;
    private RectF mBounds = new RectF();

    /**
     * corner
     */
    private CornerType mCornerType = CornerType.ALL;
    private boolean mClipPadding = true;
    private int mRadius;
    private float[] mRadiuses = new float[8];
    private Path mClipPath = new Path();
    //是否采用bitmap的方式绘制圆角,没有锯齿，依据情况谨慎使用
    private boolean mIsClipMethodBitmap = false;
    //采用bitmap方式绘制圆角相关变量
    private Bitmap bitmapOriginal;
    private Bitmap bitmapFrame;
    private Paint bitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    /**
     * 描边相关
     */
    private int borderColor;
    private int borderWidth;
    private Paint borderPaint;
    private Path borderPath = new Path();

    public ContainerDelegate(SuperMethod superMethod) {
        mCallback = superMethod;
        mView = mCallback.getView();
    }

    @Override
    public void setCorner(int radius) {
        this.mRadius = radius;
    }

    @Override
    public void setCorner(int radius, CornerType type) {
        this.mRadius = radius;
        this.mCornerType = type;
    }

    public void setFillWidth(boolean fillWidth) {
        this.mFillWidth = fillWidth;
    }

    @Override
    public void setAspectRatio(AspectRatio ratio) {
        this.mAspectRadio = ratio.getRatio();
    }

    @Override
    public boolean setAspectRatio(float ratio) {
        if (ratio != this.mAspectRadio) {
            this.mAspectRadio = ratio;
            return true;
        }
        return false;
    }

    @Override
    public float getAspectRatio() {
        return this.mAspectRadio;
    }


    public void init(TypedArray array) {
        int prtCornerRadius = 0;
        int prtClipBitmap = 0;
        int prtCornerType = 0;
        int prtClipPadding = 0;
        int prtAutoCheck = 0;
        int prtChecked = 0;
        int prtAspectRatio = 0;
        int prtFitSystemBar = 0;
        if (mView instanceof SuperLinearLayout) {
            prtCornerRadius = R.styleable.SuperLinearLayout_sll_corner_radius;
            prtClipBitmap = R.styleable.SuperLinearLayout_sll_clip_bitmap;
            prtCornerType = R.styleable.SuperLinearLayout_sll_corner_type;
            prtClipPadding = R.styleable.SuperLinearLayout_sll_clip_padding;
            prtAutoCheck = R.styleable.SuperLinearLayout_sll_auto_check;
            prtChecked = R.styleable.SuperLinearLayout_sll_checked;
            prtAspectRatio = R.styleable.SuperLinearLayout_sll_aspect_ratio;
            prtFitSystemBar = R.styleable.SuperLinearLayout_sll_fit_system_bar;
        }
        if (mView instanceof SuperFrameLayout) {
            prtCornerRadius = R.styleable.SuperFrameLayout_sfl_corner_radius;
            prtClipBitmap = R.styleable.SuperFrameLayout_sfl_clip_bitmap;
            prtCornerType = R.styleable.SuperFrameLayout_sfl_corner_type;
            prtClipPadding = R.styleable.SuperFrameLayout_sfl_clip_padding;
            prtAutoCheck = R.styleable.SuperFrameLayout_sfl_auto_check;
            prtChecked = R.styleable.SuperFrameLayout_sfl_checked;
            prtAspectRatio = R.styleable.SuperFrameLayout_sfl_aspect_ratio;
            prtFitSystemBar = R.styleable.SuperFrameLayout_sfl_fit_system_bar;
        }
        if (mView instanceof SuperRelativeLayout) {
            prtCornerRadius = R.styleable.SuperRelativeLayout_srl_corner_radius;
            prtClipBitmap = R.styleable.SuperRelativeLayout_srl_clip_bitmap;
            prtCornerType = R.styleable.SuperRelativeLayout_srl_corner_type;
            prtClipPadding = R.styleable.SuperRelativeLayout_srl_clip_padding;
            prtAutoCheck = R.styleable.SuperRelativeLayout_srl_auto_check;
            prtChecked = R.styleable.SuperRelativeLayout_srl_checked;
            prtAspectRatio = R.styleable.SuperRelativeLayout_srl_aspect_ratio;
            prtFitSystemBar = R.styleable.SuperRelativeLayout_srl_fit_system_bar;
        }

        mRadius = array.getDimensionPixelSize(prtCornerRadius, mRadius);
        mIsClipMethodBitmap = array.getBoolean(prtClipBitmap, mIsClipMethodBitmap);
        int type = array.getInt(prtCornerType, 0);
        mCornerType = CornerType.values()[type];
        mClipPadding = array.getBoolean(prtClipPadding, mClipPadding);
        mChecked = array.getBoolean(prtChecked, mChecked);
        mAutoCheck = array.getBoolean(prtAutoCheck, mAutoCheck);
        mAspectRadio = array.getFloat(prtAspectRatio, mAspectRadio);
        mFitSystemBar = array.getBoolean(prtFitSystemBar, mFitSystemBar);
        borderColor = array.getColor(R.styleable.SuperLinearLayout_sll_border_color, borderColor);
        borderWidth = array.getDimensionPixelOffset(R.styleable.SuperLinearLayout_sll_border_width, borderWidth);
        resetBorderStyle();

        if (SysOSUtils.isUpperKitkat() && mFitSystemBar) {
            if (0 == statusBarHeight) {
                statusBarHeight = ScreenUtils.getStatusBarHeight(
                    mView.getContext().getApplicationContext());
            }
            mView.setPadding(mView.getPaddingLeft(), mView.getPaddingTop() + statusBarHeight,
                mView.getPaddingRight(), mView.getPaddingBottom());
        }
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int specWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        int specHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        if (this.mAspectRadio > 0) {
            int width = mFillWidth ? specWidth : (int) (specHeight * this.mAspectRadio);
            int height = mFillWidth ? (int) (width / this.mAspectRadio) : specHeight;
            widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
            mBounds.set(0, 0, width, height);
            mCallback.superOnMeasure(widthMeasureSpec, heightMeasureSpec);
        } else if (this.mAspectRadio < 0) {
            int size = this.mAspectRadio < 0 ?
                ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT;
            mCallback.getView().getLayoutParams().height = size;
            mCallback.getView().getLayoutParams().width = size;
            mCallback.superOnMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            mCallback.superOnMeasure(widthMeasureSpec, heightMeasureSpec);
            mBounds.set(0, 0, mView.getMeasuredWidth(), mView.getMeasuredHeight());
        }

        if (0 < mRadius) {
            mClipPath.reset();
            for (int i = 0; i < 8; i++) mRadiuses[i] = this.mRadius;
            switch (this.mCornerType) {
                case LEFT:
                    resetRadiuses(2, 3, 4, 5);
                    break;
                case TOP:
                    resetRadiuses(4, 5, 6, 7);
                    break;
                case RIGHT:
                    resetRadiuses(0, 1, 6, 7);
                    break;
                case BOTTOM:
                    resetRadiuses(0, 1, 2, 3);
                    break;
                case TOP_LEFT:
                    resetRadiuses(2, 3, 4, 5, 6, 7);
                    break;
                case TOP_RIGHT:
                    resetRadiuses(0, 1, 4, 5, 6, 7);
                    break;
                case BOTTOM_LEFT:
                    resetRadiuses(0, 1, 2, 3, 4, 5);
                    break;
                case BOTTOM_RIGHT:
                    resetRadiuses(0, 1, 2, 3, 6, 7);
                    break;
                case OTHER_BOTTOM_LEFT:
                    resetRadiuses(6, 7);
                    break;
                case OTHER_BOTTOM_RIGHT:
                    resetRadiuses(4, 5);
                    break;
                case OTHER_TOP_LEFT:
                    resetRadiuses(0, 1);
                    break;
                case OTHER_TOP_RIGHT:
                    resetRadiuses(2, 3);
                    break;
            }
//            mBounds.left = mBounds.left + mView.getPaddingLeft();
//            mBounds.left = mBounds.top + mView.getPaddingTop();
//            mBounds.right = mBounds.right - mView.getPaddingRight();
//            mBounds.bottom = mBounds.bottom - mView.getPaddingBottom();
            mClipPath.addRoundRect(mBounds, mRadiuses, Path.Direction.CW);
            mClipPath.close();

            borderPath.reset();
            borderPath.addRoundRect(mBounds, mRadiuses, Path.Direction.CW);
            borderPath.close();
        }
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        resetBorderStyle();
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        resetBorderStyle();
    }


    private void resetRadiuses(int... slides) {
        for (int index : slides) this.mRadiuses[index] = 0;
    }

    RectF getBounds() {
        return mBounds;
    }


    private void resetBorderStyle() {
        if (borderColor != 0 && borderWidth != 0) {
            borderPaint = new Paint();
            borderPaint.setColor(borderColor);
            borderPaint.setStrokeWidth(borderWidth);
            borderPaint.setStyle(Paint.Style.STROKE);
        }
    }

    public void draw(Canvas canvas) {
        final View view = mCallback.getView();
        if (0 < this.mRadius) {
            if (mIsClipMethodBitmap) {
                drawCornerFromBitmap(canvas);
            } else {
                Drawable background = view.getBackground();
                if (null != background && this.mClipPadding) {
                    background.setBounds(view.getPaddingLeft(), view.getPaddingTop(),
                        view.getMeasuredWidth() - view.getPaddingRight(),
                        view.getMeasuredHeight() - view.getPaddingBottom());
                }
                canvas.clipPath(this.mClipPath);
                mCallback.superDraw(canvas);
            }
        } else {
            mCallback.superDraw(canvas);
        }
    }

    public void onDraw(Canvas canvas) {
        if (borderPaint != null) {
            canvas.drawPath(mClipPath, borderPaint);
        }
    }


    /**
     * 通过bigmap绘制圆角
     */
    private void drawCornerFromBitmap(Canvas canvas) {
        final View view = mCallback.getView();
        //改用xfermode
        if (bitmapOriginal == null) {
            final int w = view.getWidth();
            final int h = view.getHeight();
            bitmapOriginal = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            bitmapFrame = makeRoundRectFrame(w, h);
        }
        //先将内部ui绘制在bitmapOriginal上
        Canvas c = new Canvas(bitmapOriginal);
        ((SuperMethod) view).superDraw(c);
        view.setLayerType(View.LAYER_TYPE_HARDWARE, bitmapPaint);
        // 利用Xfermode取交集（利用bitmapFrame作为画框来裁剪bitmapOriginal）
        canvas.drawBitmap(bitmapFrame, 0, 0, bitmapPaint);
        bitmapPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmapOriginal, 0, 0, bitmapPaint);
        bitmapPaint.setXfermode(null);
    }

    private Bitmap makeRoundRectFrame(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Path path = new Path();
        path.addRoundRect(new RectF(0, 0, w, h), mRadiuses, Path.Direction.CW);
        Paint bitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmapPaint.setColor(Color.GREEN); // 颜色随意，不要有透明度。
        c.drawPath(path, bitmapPaint);
        return bm;
    }


    /**
     * view中的super方法
     */
    public interface SuperMethod {

        @NonNull
        View getView();

        void superDraw(Canvas canvas);

        void superOnMeasure(int widthMeasureSpec, int heightMeasureSpec);
    }

}
