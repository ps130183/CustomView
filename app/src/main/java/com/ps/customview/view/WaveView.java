package com.ps.customview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by PengSong on 17/12/5.
 */

public class WaveView extends View {
    private Context mContext;

    private Path mPath = new Path();
    public WaveView(Context context) {
        this(context,null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

    private int mItemWaveLength = 400;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.GREEN);


        Path mPath = new Path();
        int itemY = 300;
        int halfWaveLen = mItemWaveLength / 2;
        mPath.moveTo(-mItemWaveLength + dx,itemY);
        for (int i = -mItemWaveLength / 2; i < getWidth() + mItemWaveLength; i += mItemWaveLength){
            mPath.rQuadTo(halfWaveLen/2,-100,halfWaveLen,0);
            mPath.rQuadTo(halfWaveLen/2,100,halfWaveLen,0);
        }

        mPath.lineTo(getWidth(),getHeight());
        mPath.lineTo(0,getHeight());
        mPath.close();

        canvas.drawPath(mPath,paint);

    }

    int dx = 0;
    public void startAnimator(){
        ValueAnimator animator = ValueAnimator.ofInt(0,mItemWaveLength);
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);//无限循环
        animator.setInterpolator(new LinearInterpolator());//线性插值器
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                dx = (int) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();

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
