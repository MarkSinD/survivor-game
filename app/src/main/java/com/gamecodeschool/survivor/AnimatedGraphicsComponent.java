package com.gamecodeschool.survivor;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;

import com.gamecodeschool.survivor.GOSpec.GameHeroObjectSpec;
import com.gamecodeschool.survivor.GOSpec.GameObjectSpec;

public class AnimatedGraphicsComponent implements GraphicsComponent {


    private String mBitmapName;
    private AnimatorEngine mAnimatorEngine;
    private Rect mSectionToDraw;

    @Override
    public void initialize(Context c, GameObjectSpec spec, PointF objectSize, int pixelsPerMetre) {

        GameHeroObjectSpec hSpec = (GameHeroObjectSpec)spec;
        mBitmapName = hSpec.getNameOfBitmap(0);


        mAnimatorEngine = new AnimatorEngine( objectSize.y, objectSize.x, hSpec.getFramesBitmap(0), pixelsPerMetre);


        float totalWidth = objectSize.x * hSpec.getFramesBitmap(0);





        BitmapStore.addBitmap( c , mBitmapName,
                new PointF(totalWidth, objectSize.y),
                pixelsPerMetre, true);

        mSectionToDraw = mAnimatorEngine.getCurrentFrame(System.currentTimeMillis());
    }

    @Override
    public void draw(Canvas canvas, Paint paint, Transform t, Camera cam) {



        if(t.headingRight() || t.headingLeft() || t.getSpeed() == 0){
            mSectionToDraw = mAnimatorEngine.getCurrentFrame(System.currentTimeMillis());
        }
        Rect screenCoorfinates = cam.worldToScreen(t.getLocation().x, t.getLocation().y,
                t.getSize().x, t.getSize().y);

        if( t.getFacingRight()){
            canvas.drawBitmap(BitmapStore.getBitmap(mBitmapName), mSectionToDraw, screenCoorfinates, paint);
        }
        else{
            canvas.drawBitmap(BitmapStore.getBitmapReversed(mBitmapName),
                    mSectionToDraw, screenCoorfinates, paint);
        }


    }
}
