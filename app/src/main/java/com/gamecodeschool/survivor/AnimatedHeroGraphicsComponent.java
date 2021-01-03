package com.gamecodeschool.survivor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;

import com.gamecodeschool.survivor.GOSpec.GameHeroObjectSpec;
import com.gamecodeschool.survivor.GOSpec.GameObjectSpec;

import java.util.ArrayList;

public class AnimatedHeroGraphicsComponent implements GraphicsComponent {



    private Rect mSectionToDraw;
    boolean mStart = true;

    private ArrayList<String> mBitmapMoving = new ArrayList<String>();
    private ArrayList<AnimatorEngine> mAnimatorEngineMoving = new ArrayList<AnimatorEngine>();
    final private int RUN_FORWARD = 0;
    final private int RUN_REVERSED = 1;
    final private int JST_FORWARD  =2;
    final private int JST_REVERSED = 3;
    final private int ATT_FORWARD = 4;
    final private int ATT_REVERSED = 5;
    final private int JFL_JUMP_FALL = 6;
    final private int JFL_JUMP_FALL_REVERSED = 7;


    @Override
    public void initialize(Context c, GameObjectSpec spec, PointF objectSize, int pixelsPerMetre) {

        // Сейчас мы проинициализируем спрайды игрока, предназначенные для различнх действий игрока
        GameHeroObjectSpec hSpec = (GameHeroObjectSpec)spec;

        // Цикл необходим для последовательной инициализации всех спрайтов
        // Нужно выгрузить данные из специцикации и используя эти данные загрузить картинки в хранилище
        Log.e("Size ", hSpec.getSizeOfMoving() + " ");
        for(int i =0; i < hSpec.getSizeOfMoving(); i++) {

            Log.e("I = ", i + " ");


            mBitmapMoving.add(hSpec.getNameOfBitmap(i));
            mAnimatorEngineMoving.add(new AnimatorEngine(objectSize.y, objectSize.x, hSpec.getFramesBitmap(i), pixelsPerMetre));
            float totalWidth = objectSize.x * hSpec.getFramesBitmap(i);
            Log.e("totalWidth ",  totalWidth+ ".  hSpec.getFramesBitmap(i) = " + hSpec.getFramesBitmap(i) + " .mBitmapMoving.get(i)" + mBitmapMoving.get(i));
            BitmapStore.addBitmap(c, mBitmapMoving.get(i), new PointF(totalWidth, objectSize.y), pixelsPerMetre, false);



        }

        // Старт времени после заполнения хранилища
        mSectionToDraw = mAnimatorEngineMoving.get(0).getCurrentFrame(System.currentTimeMillis());
    }


    void drawRun(Canvas canvas, Paint paint, Transform transform, Camera cam){
        PlayerTransform playerTransform = (PlayerTransform) transform;
        Rect screenCoorfinates = cam.worldToScreen(transform.getLocation().x, transform.getLocation().y, transform.getSize().x, transform.getSize().y);


        if(!transform.stopingFlag())
            mSectionToDraw = mAnimatorEngineMoving.get(RUN_FORWARD).getCurrentFrame(System.currentTimeMillis());



        if (transform.headingRight())
            canvas.drawBitmap(BitmapStore.getBitmap(mBitmapMoving.get(RUN_FORWARD)), mSectionToDraw, screenCoorfinates, paint);

         else if( transform.headingLeft() )
             canvas.drawBitmap(BitmapStore.getBitmap(mBitmapMoving.get(RUN_REVERSED)), mSectionToDraw, screenCoorfinates, paint);

    }

    void drawAttack(Canvas canvas, Paint paint, Transform transform, Camera cam){
        PlayerTransform playerTransform = (PlayerTransform) transform;
        Rect screenCoorfinates = cam.worldToScreen(transform.getLocation().x, transform.getLocation().y, transform.getSize().x, transform.getSize().y);

        mSectionToDraw = mAnimatorEngineMoving.get(ATT_FORWARD).getCurrentFrame(System.currentTimeMillis());

        if(mStart){
            mAnimatorEngineMoving.get(ATT_FORWARD).setStartNonStop(System.currentTimeMillis());
            mStart = false;
        }

        if( transform.headingRight())
            canvas.drawBitmap(BitmapStore.getBitmap(mBitmapMoving.get(ATT_FORWARD)), mSectionToDraw, screenCoorfinates, paint);

        else
            canvas.drawBitmap(BitmapStore.getBitmap(mBitmapMoving.get(ATT_REVERSED)), mSectionToDraw, screenCoorfinates, paint);

        if(mAnimatorEngineMoving.get(ATT_FORWARD).getFinishAnimation()){
            playerTransform.handlingAttack();
            mStart = true;
        }
    }

    void drawJumpStart(Canvas canvas, Paint paint, Transform transform, Camera cam){

        PlayerTransform playerTransform = (PlayerTransform) transform;
        Rect screenCoorfinates = cam.worldToScreen(transform.getLocation().x, transform.getLocation().y, transform.getSize().x, transform.getSize().y);

        mSectionToDraw = mAnimatorEngineMoving.get(JST_FORWARD).getCurrentFrame(System.currentTimeMillis());

        if(mStart){
            mAnimatorEngineMoving.get(JST_FORWARD).setStartNonStop(System.currentTimeMillis());
            mStart = false;
        }

        if( transform.headingRight())
            canvas.drawBitmap(BitmapStore.getBitmap(mBitmapMoving.get(JST_FORWARD)), mSectionToDraw, screenCoorfinates, paint);

        else
            canvas.drawBitmap(BitmapStore.getBitmap(mBitmapMoving.get(JST_REVERSED)), mSectionToDraw, screenCoorfinates, paint);

        if(mAnimatorEngineMoving.get(JST_FORWARD).getFinishAnimation()){
            playerTransform.handlingJump();
            mStart = true;
        }

    }

    void drawJumpFall(Canvas canvas, Paint paint, Transform transform, Camera cam){
        PlayerTransform playerTransform = (PlayerTransform) transform;
        Rect screenCoorfinates = cam.worldToScreen(transform.getLocation().x, transform.getLocation().y, transform.getSize().x, transform.getSize().y);

        mSectionToDraw = mAnimatorEngineMoving.get(JFL_JUMP_FALL).getCurrentFrame(System.currentTimeMillis());


        if( transform.headingRight())
            canvas.drawBitmap(BitmapStore.getBitmap(mBitmapMoving.get(JFL_JUMP_FALL)), mSectionToDraw, screenCoorfinates, paint);

        else
            canvas.drawBitmap(BitmapStore.getBitmap(mBitmapMoving.get(JFL_JUMP_FALL_REVERSED)), mSectionToDraw, screenCoorfinates, paint);

    }

    @Override
    public void draw(Canvas canvas, Paint paint, Transform transform, Camera cam) {
        PlayerTransform playerTransform = (PlayerTransform) transform;

        if(playerTransform.attackTriggered() )
            drawAttack(canvas, paint, transform, cam);

        else if(!playerTransform.jumpTriggered() && !playerTransform.isGrounded())
            drawJumpFall(canvas, paint, transform, cam);

        else if(playerTransform.jumpTriggered())
            drawJumpStart(canvas, paint, transform, cam);

        else
            drawRun(canvas,paint,transform,cam);



        /*
        paint.setColor(Color.RED);


        Rect rectFeet = cam.worldToScreen(playerTransform.getColliders().get(0).left, playerTransform.getColliders().get(0).top,
                1, 1);


        Log.e("rectFeet" , "x : " + rectFeet.left + ". y : " + rectFeet.top + ". plX : " + playerTransform.getLocation().x + " plY : " + playerTransform.getLocation().y);
        canvas.drawRect( rectFeet, paint);



        Rect rectHead = cam.worldToScreen(playerTransform.getColliders().get(1).left, playerTransform.getColliders().get(1).top,
                1, 1);
        canvas.drawRect( rectHead, paint);



        Rect rectRight = cam.worldToScreen(playerTransform.getColliders().get(2).left, playerTransform.getColliders().get(2).top,
                0.5f, 0.5f);
        canvas.drawRect( rectRight, paint);


        Rect rectLeft = cam.worldToScreen(playerTransform.getColliders().get(3).left, playerTransform.getColliders().get(3).top,
                0.5f, 0.5f);
        canvas.drawRect( rectLeft, paint);
        */

    }
}
