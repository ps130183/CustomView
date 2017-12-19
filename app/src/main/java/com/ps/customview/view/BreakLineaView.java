package com.ps.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by PengSong on 17/12/5.
 */

public class BreakLineaView extends View {
    private Context mContext;

    private Path mPath = new Path();
    public BreakLineaView(Context context) {
        this(context,null);
    }

    public BreakLineaView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BreakLineaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

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
            setMeasuredDimension(widthSpecSize,dip2px(200));
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize,heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,dip2px(200));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setColor(Color.RED);

        Log.d("huahua","绘画中。。。。");
        canvas.drawPath(mPath,paint);
    }

    private float preX;
    private float preY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(),event.getY());
                preX = event.getX();
                preY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = (event.getX() + preX) / 2;
                float endY = (event.getY() + preY) / 2;

                mPath.quadTo(preX,preY,endX,endY);
                preX = event.getX();
                preY = event.getY();
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    /**
     * 重置
     */
    public void reset(){
        mPath.reset();
        invalidate();
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
