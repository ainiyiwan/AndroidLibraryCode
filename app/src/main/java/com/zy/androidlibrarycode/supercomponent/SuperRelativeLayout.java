package com.zy.androidlibrarycode.supercomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.zy.androidlibrarycode.R;
import com.zy.androidlibrarycode.supercomponent.delegate.AspectRatio;
import com.zy.androidlibrarycode.supercomponent.delegate.AspectRatioed;
import com.zy.androidlibrarycode.supercomponent.delegate.AutoCheckable;
import com.zy.androidlibrarycode.supercomponent.delegate.ContainerDelegate;
import com.zy.androidlibrarycode.supercomponent.delegate.CornerType;
import com.zy.androidlibrarycode.supercomponent.delegate.Rotating;
import com.zy.androidlibrarycode.supercomponent.delegate.Rounded;

import androidx.annotation.NonNull;


/**
 * LinearLayout组件拓展(属性拓展支持)
 * <p>
 * Created by waylenw on 2019-11-13.
 */
public class SuperRelativeLayout extends RelativeLayout
    implements AutoCheckable, AspectRatioed, Rounded, Rotating, ContainerDelegate.SuperMethod {

    private ContainerDelegate mContainerDelegate = new ContainerDelegate(this);

    private boolean mChecked;
    private boolean mAutoCheck = true;
    // 旋转之前角度
    private float preRotation = 0;
    // 时间间隔
    private int intervalTime = 100;
    // 旋转角度
    private float intervalDegree = 30.0f;
    // 是否连续旋转
    private boolean isSmooth;
    // 是否顺时针
    private boolean isClockwise = true;
    // 持续时间
    private int during = 500;
    private long preRotationTime, startTime;
    private boolean alived, started;
    private float lastRotate;

    public SuperRelativeLayout(Context context) {
        this(context, null);
    }

    public SuperRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SuperRelativeLayout);
        mAutoCheck = array.getBoolean(R.styleable.SuperRelativeLayout_srl_auto_check, mAutoCheck);
        mChecked = array.getBoolean(R.styleable.SuperRelativeLayout_srl_checked, mChecked);
        mContainerDelegate.init(array);
        array.recycle();
        setWillNotDraw(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mContainerDelegate.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @SuppressWarnings("all")
    @Override
    public void draw(Canvas canvas) {
        mContainerDelegate.draw(canvas);
        if (!started || !isShown()) return;
        if (0 == startTime) {
            startTime = System.currentTimeMillis();
        }
        long current = System.currentTimeMillis();
        if (isSmooth) {
            setRotation((!isClockwise
                ? (360 - ((current - startTime) * 1f % during) / during * 360)
                : (((current - startTime) % during) * 1f / during * 360)) + lastRotate);
        } else {
            if (0 == preRotationTime || current - preRotationTime > intervalTime) {
                setRotation(preRotation += (isClockwise ? 1 : -1) * intervalDegree);
                preRotationTime = System.currentTimeMillis();
            }
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mContainerDelegate.onDraw(canvas);
    }

    @Override
    public boolean performClick() {
        if (mAutoCheck) {
            toggle();
        }
        return super.performClick();
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        int[] states = super.onCreateDrawableState(extraSpace + 1);

        if (isChecked()) {
            mergeDrawableStates(states, CHECKED_STATE_SET);
        }

        return states;
    }

    @Override
    public void setChecked(boolean checked) {
        if (checked ^ mChecked) {
            mChecked = !mChecked;
            refreshDrawableState();
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }

    @Override
    public void setAutoCheck(boolean autoCheck) {
        this.mAutoCheck = autoCheck;
    }

    @Override
    public boolean isAutoCheck() {
        return mAutoCheck;
    }

    @Override
    public void setAspectRatio(AspectRatio ratio) {
        mContainerDelegate.setAspectRatio(ratio);
        requestLayout();
    }

    @Override
    public boolean setAspectRatio(float ratio) {
        boolean isChange;
        if (isChange = mContainerDelegate.setAspectRatio(ratio)) {
            requestLayout();
        }
        return isChange;
    }

    @Override
    public float getAspectRatio() {
        return mContainerDelegate.getAspectRatio();
    }

    @Override
    public void setCorner(int radius) {
        mContainerDelegate.setCorner(radius);
        requestLayout();
    }

    @Override
    public void setCorner(int radius, CornerType type) {
        mContainerDelegate.setCorner(radius, type);
        requestLayout();
    }

    public void setBorderColor(int borderColor) {
        mContainerDelegate.setBorderColor(borderColor);
    }

    public void setBorderWidth(int borderWidth) {
        mContainerDelegate.setBorderWidth(borderWidth);
    }


    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (VISIBLE != visibility) {
            stopRotate();
        } else {
            alived = true;
            invalidate();
        }
    }

    @Override
    public void stopRotate() {
        this.started = false;
    }

    @Override
    public void startRotate() {
        this.started = true;
        invalidate();
        startTime = 0;
        lastRotate = getRotation();
    }

    @Override
    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    @Override
    public void setIntervalDegree(float intervalDegree) {
        this.intervalDegree = intervalDegree;
    }

    @Override
    public void setDuration(int duration) {
        this.during = duration;
    }

    @Override
    public void setSmooth(boolean smooth) {
        isSmooth = smooth;
    }

    @Override
    public void setClockwise(boolean clockwise) {
        isClockwise = clockwise;
    }

    @Override
    public void resetRotate() {
        lastRotate = 0;
        setRotation(0);
    }

    @Override
    public void superDraw(Canvas canvas) {
        super.draw(canvas);
    }

    @SuppressWarnings("all")
    @Override
    public void superOnMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    @NonNull
    public View getView() {
        return this;
    }
}
