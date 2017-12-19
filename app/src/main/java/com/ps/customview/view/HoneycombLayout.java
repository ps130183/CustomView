package com.ps.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by PengSong on 17/12/5.
 */

public class HoneycombLayout extends ViewGroup {

    private static String TAG = "HoneycombLayout";
    private Context mContext;
    private Paint mPaint;

    private int totalWidth = 0;

    public HoneycombLayout(Context context) {
        this(context,null);
    }

    public HoneycombLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HoneycombLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init(){
        int count = getChildCount();
        Log.d(TAG,"the child size is  " + count);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.YELLOW);
        mPaint.setStrokeWidth(dip2px(2));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        Log.d(TAG,"the child size is  " + count);
        for (int i = 0; i < count; i++){
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        Log.d(TAG,"the child size is  " + count);
        double windowWidth = getWidth();
        Log.d(TAG,"this windowWidth is " + windowWidth);
        double curHeight = 0;
        double preHeight = 0;
        int lineWidth = 0; //行宽
        for (int i = 0; i < count; i++){
            View child = getChildAt(i);
            if (child == null){

            } else {
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();


                totalWidth += childWidth;
                curHeight = childHeight * Math.floor(totalWidth/windowWidth);

                if (preHeight < curHeight){
                    lineWidth = 0;
                    child.layout(lineWidth, (int) curHeight,lineWidth + childWidth,(int) curHeight + childHeight);
                } else {
                    child.layout(lineWidth, (int) curHeight,lineWidth + childWidth,(int) curHeight + childHeight);
                    lineWidth += childWidth;
                }


                preHeight = curHeight;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();

        canvas.drawRect(0,0,width,height,mPaint);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    private int px2dip(float pxValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
