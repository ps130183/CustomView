package com.ps.customview.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

import com.ps.customview.view.BaseView;

/**
 * Created by PengSong on 17/12/8.
 */

public class PinwheelView extends BaseView {

    /**
     * 扇叶的高度和宽度
     */
    private int mLeafHeight = 180;
    private int mLeafWidth = mLeafHeight / 2;

    /**
     * 旋转角度
     */
    private int mDegree = 0;


    public PinwheelView(Context context) {
        super(context);
    }

    public PinwheelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PinwheelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init(AttributeSet attrs, int defStyleAttr) {

    }

    @Override
    protected int getDefaultWidth() {
        return (mLeafHeight + 20) * 2;
    }

    @Override
    protected int getDefaultHeight() {
        return (mLeafHeight + 20) * 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth = getWidth();
        mHeight = getHeight();

        int centerX = (int) (mWidth / 2);
        int centerY = (int) (mHeight / 2);

        canvas.rotate(mDegree,centerX,centerY);

        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        canvasLeaf(canvas,centerX,centerY,Color.RED,mPaint,0);
        canvasLeaf(canvas,centerX,centerY,Color.BLUE,mPaint,90);
        canvasLeaf(canvas,centerX,centerY,Color.BLUE,mPaint,180);
        canvasLeaf(canvas,centerX,centerY,Color.RED,mPaint,270);

    }

    private void canvasLeaf(Canvas canvas,int cX,int cY,int color,Paint paint,float degree){
        canvas.rotate(degree,cX,cY);
        if (paint == null){
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        } else {
            paint.setColor(color);
        }
        Path tPath = new Path();
        tPath.moveTo(cX,cY);
        tPath.rQuadTo(-mLeafWidth,-mLeafHeight/2,0,-mLeafHeight);
        tPath.close();

        canvas.drawPath(tPath,paint);
    }




    public void startAnimator(){
        ObjectAnimator animator = ObjectAnimator.ofInt(this,"mDegree",0,360);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                postInvalidate();
            }
        });
        animator.start();
    }

    public void setMDegree(int mDegree) {
        this.mDegree = mDegree;
    }
}

