package com.ps.customview.menus.motion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by PengSong on 17/12/7.
 * 该类是最终在客户端使用的,记录一系列的不同移动轨迹,
 * 使用时调用里面的方法就可以添加不同的移动轨迹最后通过getPoints()来得到所有的移动轨迹集合
 */

public class AnimatorPath {
    /**
     * 路径的集合
     */
    List<PathPoint> mPoints = new ArrayList<>();

    /**
     * 移动位置到
     * @param x
     * @param y
     */
    public void move(float x,float y){
        mPoints.add(PathPoint.move(x,y));
    }

    /**
     * 直线移动
     * @param x
     * @param y
     */
    public void lineTo(float x,float y){
        mPoints.add(PathPoint.lineTo(x,y));
    }

    /**
     * 二阶贝塞尔曲线
     * @param cX
     * @param cY
     * @param x
     * @param y
     */
    public void secondBaseCurve(float cX,float cY,float x,float y){
        mPoints.add(PathPoint.createSecondCurve(cX,cY,x,y));
    }

    /**
     * 三阶贝塞尔曲线
     * @param c0X
     * @param c0Y
     * @param c1X
     * @param c1Y
     * @param x
     * @param y
     */
    public void thireBaseCurve(float c0X,float c0Y,float c1X,float c1Y,float x,float y){
        mPoints.add(PathPoint.createThireCurve(c0X,c0Y,c1X,c1Y,x,y));
    }

    /**
     * 返回运动集合
     * @return
     */
    public Collection<PathPoint> getPoints(){
        return mPoints;
    }
}
