package com.ps.customview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by PengSong on 17/12/8.
 */

public abstract class BaseView extends View {

    protected Context mContext;
    //屏幕的宽高
    protected float mWidth,mHeight;

    public BaseView(Context context) {
        this(context,null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(attrs,defStyleAttr);
    }

    public abstract void init(AttributeSet attrs,int defStyleAttr);

    /**
     * wrap_content 时 默认的宽度 和高度
     * @return
     */
    protected abstract int getDefaultWidth();
    protected abstract int getDefaultHeight();

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //让控件wrap_content 生效
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = getWidth();


        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(getDefaultWidth(),getDefaultHeight());
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(getDefaultWidth(),heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,getDefaultHeight());
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    protected int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    protected int px2dip(float pxValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
