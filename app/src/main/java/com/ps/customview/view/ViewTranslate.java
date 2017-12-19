package com.ps.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by PengSong on 17/12/5.
 */

public class ViewTranslate extends View {
    private Context mContext;
    public ViewTranslate(Context context) {
        this(context,null);
    }

    public ViewTranslate(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ViewTranslate(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);


        canvas.rotate(60,200,150);
        canvas.translate(100,100);
        canvas.drawColor(Color.YELLOW);
        canvas.drawRect(0,0,400,300,paint);
    }
}
