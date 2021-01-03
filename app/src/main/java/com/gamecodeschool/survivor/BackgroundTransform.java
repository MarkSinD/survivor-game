package com.gamecodeschool.survivor;

import android.graphics.PointF;

public class BackgroundTransform extends Transform {


    private float xClip;
    private boolean reversedFirst = false;


    BackgroundTransform(float speed, float objectWidth, float objectHeight, PointF startingLocation) {

        super(speed, objectWidth, objectHeight, startingLocation);
    }



    boolean getReversedFirst(){
        return reversedFirst;
    }
    void flipReversedFirst(){
        reversedFirst=!reversedFirst;
    }

    float getXClip(){
        return  xClip;
    }

    void setXClip( float newXClip){
        xClip = newXClip;
    }

}
