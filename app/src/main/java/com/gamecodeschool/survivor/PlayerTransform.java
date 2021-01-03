package com.gamecodeschool.survivor;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.ArrayList;

// Готов
//
// Является дополнением с обычному Transform
// Имеет массив прямоугольников( частей тела героя )
// Которые проверяются каждый раз на пересечение с другими обьектами
// Имеет флаги состояния "в прыжке", "касания головой", "приземлился"

public class PlayerTransform extends Transform {
    private ArrayList<RectF> mColliders;
    private final float TENTH = .1f;
    private final float HALF = .5f;
    private final float THIRD = .3f;
    private final float FIFTH = .2f;
    private final float FEET_PROTRUSION = 1.2f;

    private RectF mHeadRectF = new RectF();
    private RectF mRightRectF = new RectF();
    private RectF mFeetRectF = new RectF();
    private RectF mLeftRectF = new RectF();

    private boolean mJumpTriggered = false;
    private boolean mBumpedHeadTriggered = false;
    private boolean mAttackTriggered = false;

    private boolean mGrounded;


    PlayerTransform(float speed, float objectWidth, float objectHeight, PointF startingLocation) {
        super(speed, objectWidth, objectHeight, startingLocation);

        mColliders = new ArrayList<RectF>();
        mColliders.add(mFeetRectF);
        mColliders.add(mHeadRectF);
        mColliders.add(mRightRectF);
        mColliders.add(mLeftRectF);
    }

    public ArrayList<RectF> getColliders(){
        updateColliders();
        return mColliders;
    }

    public void updateColliders(){
        PointF location = getLocation();
        float objectHeight = getSize().y;
        float objectWidth = getSize().x;
        float bodyPartsPudding = getSize().x/20;

        // Feet
        mColliders.get(0).left = location.x + 0.424f*objectWidth;
        mColliders.get(0).top = location.y + objectHeight - bodyPartsPudding;
        mColliders.get(0).right = location.x  +0.576f * objectWidth;
        mColliders.get(0).bottom = location.y  + (objectHeight * 1.1f);

        // Head
        mColliders.get(1).left = location.x + 0.424f * objectWidth;
        mColliders.get(1).top = location.y  + 0.448f*objectHeight;
        mColliders.get(1).right = location.x + 0.576f*objectWidth;
        mColliders.get(1).bottom = location.y + 0.5695f * objectHeight;

        // Right
        mColliders.get(2).left = location.x + 0.576f*objectWidth;
        mColliders.get(2).top = location.y  + 0.448f*objectHeight + bodyPartsPudding;
        mColliders.get(2).right = location.x + 0.624f*objectWidth;
        mColliders.get(2).bottom = location.y + objectHeight - bodyPartsPudding;

        // Left
        mColliders.get(3).left = location.x + 0.376f* objectWidth;
        mColliders.get(3).top = location.y  + 0.448f*objectHeight + bodyPartsPudding;
        mColliders.get(3).right = location.x + 0.424f * objectWidth;
        mColliders.get(3).bottom = location.y + objectHeight - bodyPartsPudding;

    }


    void triggerJump() {
        mJumpTriggered = true;
    }
    void handlingJump() {
        mJumpTriggered = false;
    }
    boolean jumpTriggered() {
        return mJumpTriggered;
    }


    void triggerBumpedHead() {
        mBumpedHeadTriggered = true;
    }
    void handlingBumpedHead() {
        mBumpedHeadTriggered = false;
    }
    boolean bumpedHead() {
        return mBumpedHeadTriggered;
    }

    void setNotGrounded(){
        mGrounded=false;
    }
    void notGrounded() {
        mGrounded = false;
    }
    void grounded() {
        mGrounded = true;
    }
    boolean isGrounded() {
        return mGrounded;
    }

    void triggerAttack() {
        mAttackTriggered = true;
    }
    void handlingAttack(){
        mAttackTriggered = false;
    }
    boolean attackTriggered(){
        return  mAttackTriggered;
    }


}
