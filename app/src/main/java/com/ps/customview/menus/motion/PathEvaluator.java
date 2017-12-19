package com.ps.customview.menus.motion;

import android.animation.TypeEvaluator;

/**
 * Created by PengSong on 17/12/7.
 * 曲线移动估值器
 */

public class PathEvaluator implements TypeEvaluator<PathPoint> {


    @Override
    public PathPoint evaluate(float t, PathPoint startPoint, PathPoint endPoint) {
        float x, y;
        float oneMiunsT = 1 - t;

        /**
         * 三阶贝塞尔曲线
         * B(t) = P0 * (1-t)^3 + 3 * P1 * t * (1-t)^2 + 3 * P2 * t^2 * (1-t) + P3 * t^3, t ∈ [0,1]
         * t 曲线长度比例
         * P0 起始点
         * P1 控制点1
         * P2 控制点2
         * P3 终止点
         */
        if (endPoint.mOperation == PathPoint.Operation.THIRE_CURVE) {
            x = startPoint.mX*oneMiunsT*oneMiunsT*oneMiunsT+3*endPoint.mControl0X*t*oneMiunsT*oneMiunsT+3*endPoint.mControl1X*t*t*oneMiunsT+endPoint.mX*t*t*t;
            y = startPoint.mY*oneMiunsT*oneMiunsT*oneMiunsT+3*endPoint.mControl0Y*t*oneMiunsT*oneMiunsT+3*endPoint.mControl1Y*t*t*oneMiunsT+endPoint.mY*t*t*t;

            /**
             * 二阶贝塞尔曲线
             * B(t) = (1 - t)^2 * P0 + 2t * (1 - t) * P1 + t^2 * P2, t ∈ [0,1]
             * t 曲线长度比例
             * p0 起始点
             * P1 控制点
             * P2 终止点
             */
        }else if(endPoint.mOperation == PathPoint.Operation.SECOND_CURVE){
            x = oneMiunsT*oneMiunsT*startPoint.mX+2*t*oneMiunsT*endPoint.mControl0X+t*t*endPoint.mX;
            y = oneMiunsT*oneMiunsT*startPoint.mY+2*t*oneMiunsT*endPoint.mControl0Y+t*t*endPoint.mY;
            //直线
        }else if (endPoint.mOperation == PathPoint.Operation.LINE) {
            //x起始点+t*起始点和终点的距离
            x = startPoint.mX + t * (endPoint.mX - startPoint.mX);
            y = startPoint.mY + t * (endPoint.mY - startPoint.mY);
        } else {
            x = endPoint.mX;
            y = endPoint.mY;
        }
        return PathPoint.move(x,y);
    }
}
