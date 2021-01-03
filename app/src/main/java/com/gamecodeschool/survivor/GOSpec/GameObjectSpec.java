package com.gamecodeschool.survivor.GOSpec;

import android.graphics.PointF;

//Готов
//
// Класс является собирательным для всех spec-классов
// Содежит такие параметры как таг, имя картинки, требуемая скорость,
// размеры обьекта, компоненты, количество слайдов анимации
// + много различных геттеров
public abstract class GameObjectSpec {
    private String mTag;
    private String mBitmapName;
    private float mSpeed;
    private PointF mSize;
    private String[] mComponents;
    private int mFramesAnimation;

    GameObjectSpec( String tag,
                    String bitMapName,
                    float speed,
                    PointF size,
                    String[] components,
                    int framesAnimation){
        mTag = tag;
        mBitmapName = bitMapName;
        mSpeed = speed;
        mSize = size;
        mComponents = components;
        mFramesAnimation = framesAnimation;

    }

    public int getNumFrames(){ return mFramesAnimation; }
    public String getTag(){ return mTag; }
    public String getBitMapName(){ return mBitmapName; }
    public float getSpeed(){ return mSpeed; }
    public PointF getSize(){ return  mSize; }
    public String[] getComponents(){ return mComponents; }
}
