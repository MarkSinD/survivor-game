package com.gamecodeschool.survivor;

import android.graphics.PointF;
import android.graphics.RectF;



// Готов
//
// Хранит такие характеристики обьекта как
// Локация с мировых координатах, скорость, размеры, и направления
// Имеет кучу геттеров на эти свойства
// Умеет нахожить CollideRect
public class Transform {
    RectF mCollider;
    private PointF mLocation;
    private float mSpeed;
    private float mObjectHeight;
    private float mObjectWidth;
    private PointF mStartingPosition;
    private boolean mHeadingUp = false;
    private boolean mHeadingDown = false;

    private boolean mStopFlag = true;
    private boolean mFacingRight = true;
    private boolean mHeadingLeft = false;
    private boolean mHeadingRight = true;

    Transform( float speed, float objectWidth, float objectHeight, PointF startingLocation){

        mCollider = new RectF();
        mSpeed = speed;
        mObjectHeight = objectHeight;
        mObjectWidth = objectWidth;
        mLocation = startingLocation;
        mStartingPosition = new PointF( mLocation.x, mLocation.y);
    }

    public void updateCollider(){

        mCollider.left = mLocation.x +0.424f*mObjectWidth;
        mCollider.top = mLocation.y+ 0.5695f*mObjectHeight;

        mCollider.right = mLocation.x + 0.624f*mObjectWidth;
        mCollider.bottom = mLocation.y + 1.1f*mObjectHeight;
    }

    public float getHorixontalPudding(){
        return 0.424f*mObjectWidth;
    }

    public float getVerticalPudding(){
        return 0.5695f*mObjectHeight;
    }



    public RectF getCollider(){ return mCollider; }

    void headUp(){ mHeadingUp = true; mHeadingDown = false; mStopFlag = false; }

    void headDown(){ mHeadingDown = true; mHeadingUp = false; mStopFlag = false; }

    boolean headingUp(){ return mHeadingUp; }

    boolean headingDown(){ return mHeadingDown; }

    float getSpeed(){ return mSpeed; }

    PointF getLocation(){ return mLocation; }

    PointF getSize(){ return new PointF( (int) mObjectWidth, (int)mObjectHeight);}

    void headRight(){
        mHeadingRight = true;
        mHeadingLeft = false;
        mFacingRight = true;
        mStopFlag = false;
    }

    void headLeft(){
        mHeadingRight = false;
        mHeadingLeft = true;
        mFacingRight = false;
        mStopFlag = false;
    }

    boolean headingRight(){ return mHeadingRight;}

    boolean headingLeft(){ return mHeadingLeft; }

    void stopHorizontal(){
        if(mFacingRight) {
            mHeadingLeft = false;
            mHeadingRight = true;
        }
        else{
            mHeadingLeft = true;
            mHeadingRight = false;
        }
        mFacingRight = false;
        mStopFlag = true;
    }

    boolean stopingFlag(){
        return mStopFlag;
    }
    void stopMovingLeft(){ mFacingRight = false; mStopFlag = true; mHeadingLeft = true; }
    void stopMovingRight(){ mFacingRight = false; mStopFlag = true; mHeadingRight = true;}
    boolean getFacingRight(){ return mFacingRight; }
    PointF getStartingPosition(){ return mStartingPosition; }
}
