package com.ps.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/**
 * Created by PengSong on 17/12/4.
 */

public class HoneycombView extends View {

    private static String TAG = "HoneycombView";

    private Context mContext;

    //屏幕的中心点X/Y
    private float centerX = 0;
    private float centerY = 0;

    private Paint mPaint;

    //半径
    private int mRadius = 0;

    public HoneycombView(Context context) {
        this(context, null);
    }

    public HoneycombView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HoneycombView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    /**
     * 初始化
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(dip2px(2));

        mRadius = dip2px(40);
        Log.d(TAG,"mradius = " + mRadius);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //让控件wrap_content 生效
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(mRadius * 2,mRadius * 2);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mRadius * 2,heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,mRadius * 2);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        centerX = getWidth() / 2;
        centerY = getHeight() / 2;

        Log.d(TAG, "centerX = " + centerX + "   centerY = " + centerY);

//        mPaint.setColor(Color.YELLOW);
//        canvas.drawRect(0,0,getWidth() / 2,getHeight() / 2, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0,60,getWidth(),60, mPaint);

        canvas.rotate(30,mRadius,mRadius);
        drawHexagon(canvas, mPaint, Color.RED, mRadius,mRadius,mRadius,60);

//        drawHexagon(canvas, mPaint, Color.BLUE, mRadius,mRadius * 3,mRadius,-60);
//        drawHexagon(canvas, mPaint, Color.BLACK, mRadius,mRadius * 2,mRadius * 3,0);
//        drawHexagon(canvas, mPaint, Color.GREEN, mRadius,centerX,centerY,0);
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

    /**
     * 绘制彩色多边形或星形
     *
     * @param canvas Canvas画布
     * @param paint  Paint画笔
     * @param color  颜色
     * @param radius 外接圆半径
     * @param centerX 中心点X
     * @param centerY 中心点Y
     */
    private void drawHexagon(Canvas canvas, Paint paint, @ColorInt int color, float radius,float centerX,float centerY,int rotate) {

//        canvas.rotate(rotate);
//        if (paint == null) {
//            paint = new Paint();
//        } else {
//            paint.reset();
//
//        }
        Path path = new Path();

        float startX = 0;
        float startY = 0;
        for (int i = 0; i < 6; i++) {
            startX = radius * cos(360 / 6 * i + rotate) + centerX;
            startY = radius * sin(360 / 6 * i  + rotate) + centerY;
            if (i == 0) {
                path.moveTo(startX, startY);
            } else {
                path.lineTo(startX, startY);
            }
            Log.d(TAG, "radius = " + radius + "  X" + i + " = " + startX + "  Y" + i + " = " + startY);
        }

        path.close();
        paint.setColor(color);
        canvas.drawPath(path, paint);
        Log.d(TAG,"----------------------------------------------------------------------------------------------");

    }

    /**
     * Math.sin的参数为弧度，使用起来不方便，重新封装一个根据角度求sin的方法
     *
     * @param num 角度
     * @return
     */
    float sin(int num) {
        return (float) Math.sin(num * Math.PI / 180);
    }

    /**
     * 与sin同理
     */
    float cos(int num) {
        return (float) Math.cos(num * Math.PI / 180);
    }

//    private int getDefaultSize(int size,int measureSpec){
//        return 0;
//    }
}


