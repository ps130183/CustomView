package com.ps.customview.menus.motion;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.ps.customview.R;

public class MotionActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private AnimatorPath path;



    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion);
        Button btnStart = findViewById(R.id.btn_start);
        fab = findViewById(R.id.floatingActionButton);
        Display display=getWindowManager().getDefaultDisplay();
        width=display.getWidth();
        height=display.getHeight();
        setPath();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimator();
            }
        });


    }


    /**
     * 设置动画路径
     * */
    public void setPath(){
        path = new AnimatorPath();
        int centerX = width / 2;
        int centerY = height / 2;
        path.move(centerX + 100,centerY);
        path.lineTo(centerX - 100,centerY);
        path.secondBaseCurve(centerX - 400, centerY / 2, centerX, centerY - 600); //订单
        path.thireBaseCurve(100,600,900,1000,200,1200);
    }


    private void startAnimator(){
        ObjectAnimator animator = ObjectAnimator.ofObject(this,"fab",new PathEvaluator(),path.getPoints().toArray());
        animator.setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());
//        animator.setInterpolator(new DecelerateInterpolator());
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(10);
        animator.start();
    }

    public void setFab(PathPoint point){
        fab.setTranslationX(point.mX);
        fab.setTranslationY(point.mY);
    }
}
