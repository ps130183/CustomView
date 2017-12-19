package com.ps.customview.menus.motion;

/**
 * Created by PengSong on 17/12/7.
 * 这个类主要是用来记录View移动动作的坐标点,
 * 通过不同的构造函数传入不同的参数来区分不同的移动轨迹
 */

public class PathPoint {

    enum Operation{
        MOVE, //起点操作
        LINE, //直线操作
        SECOND_CURVE, //二阶贝塞尔曲线
        THIRE_CURVE;  //三阶贝塞尔曲线
    }

    //终点坐标
    public float mX,mY;

    //控制点1坐标
    public float mControl0X,mControl0Y;

    //控制点2坐标
    public float mControl1X,mControl1Y;

    //操作符
    public Operation mOperation;

    /**
     * 起点MOVE/直线LINE 两种操作通过该构造函数创建
     * @param mOperation
     * @param mX
     * @param mY
     */
    public PathPoint(Operation mOperation,float mX, float mY) {
        this.mOperation = mOperation;
        this.mX = mX;
        this.mY = mY;
    }

    /**
     * 二阶贝塞尔曲线构造
     * @param mX
     * @param mY
     * @param mControl0X
     * @param mControl0Y
     */
    public PathPoint(float mX, float mY, float mControl0X, float mControl0Y) {
        this.mX = mX;
        this.mY = mY;
        this.mControl0X = mControl0X;
        this.mControl0Y = mControl0Y;
        this.mOperation = Operation.SECOND_CURVE;
    }

    /**
     * 三阶贝塞尔曲线
     * @param mX
     * @param mY
     * @param mControl0X
     * @param mControl0Y
     * @param mControl1X
     * @param mControl1Y
     */
    public PathPoint(float mX, float mY, float mControl0X, float mControl0Y, float mControl1X, float mControl1Y) {
        this.mX = mX;
        this.mY = mY;
        this.mControl0X = mControl0X;
        this.mControl0Y = mControl0Y;
        this.mControl1X = mControl1X;
        this.mControl1Y = mControl1Y;
        this.mOperation = Operation.THIRE_CURVE;
    }


    /**
     * 为了方便使用都用静态的方法来返回路径点
     */
    public static PathPoint move(float x,float y){
        return new PathPoint(Operation.MOVE,x,y);
    }

    public static PathPoint lineTo(float x,float y){
        return new PathPoint(Operation.LINE,x,y);
    }

    public static PathPoint createSecondCurve(float cX,float cY,float x,float y){
        return new PathPoint(x,y,cX,cY);
    }

    public static PathPoint createThireCurve(float c0X,float c0Y,float c1X,float c1Y,float x,float y){
        return new PathPoint(x,y,c0X,c0Y,c1X,c1Y);
    }

}
