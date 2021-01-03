package com.gamecodeschool.survivor.GOSpec;


import android.graphics.PointF;

public abstract class GameHeroObjectSpec extends GameObjectSpec {

    private String[] mBitmaps;
    private int[] mCountFramesBitmaps;


    GameHeroObjectSpec(String tag, float speed, PointF size,
                       String[] components, String[] bitmaps, int[] countFramesBitmaps) {

        super(tag, null, speed, size, components, 0);

        mBitmaps = bitmaps;
        mCountFramesBitmaps = countFramesBitmaps;

    }

    public String getNameOfBitmap(int indexOfBitmap){ return mBitmaps[indexOfBitmap]; }
    public int getFramesBitmap(int indexOfBitmap) { return mCountFramesBitmaps[indexOfBitmap]; }
    public int getSizeOfMoving(){ return mBitmaps.length;}
}
