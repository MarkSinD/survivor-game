package com.gamecodeschool.survivor;

import android.graphics.Rect;
import android.util.Log;

public class AnimatorEngine {

    private Rect mSourceRect;
    private int mFrameCount;
    private int mCurrentFrame;
    private long mFrameTicker;
    private int mFramePeriod;
    private int mFrameWidth;
    private long mStartTime;
    private boolean mStopFlagAnimation;

    AnimatorEngine(float frameHeight, float frameWidth, int frameCount, int pixelsPerMetre){
        final int ANIM_FPS = 20;
        this.mCurrentFrame = 0;
        this.mFrameCount = frameCount;
        this.mFrameWidth = (int)frameWidth * pixelsPerMetre;

        frameHeight = frameHeight * pixelsPerMetre;

        mSourceRect = new Rect( 0, 0, this.mFrameWidth, (int)frameHeight);
        mFramePeriod = 1000 / ANIM_FPS;
        mFrameTicker = 0L;

    }

    Rect getCurrentFrame(long time){
        if( time > mFrameTicker + mFramePeriod){
            mFrameTicker = time;
            mCurrentFrame++;
            if(mCurrentFrame >= mFrameCount){
                mCurrentFrame = 0;
                mStopFlagAnimation = true;

            }
        }

        this.mSourceRect.left = mCurrentFrame * mFrameWidth;
        this.mSourceRect.right = this.mSourceRect.left + mFrameWidth;

        return mSourceRect;
    }

    public void setStartNonStop(long time){
        mStartTime = time;
        mStopFlagAnimation = false;
    }

    public boolean getFinishAnimation(){

        return mStopFlagAnimation;
    }
}
