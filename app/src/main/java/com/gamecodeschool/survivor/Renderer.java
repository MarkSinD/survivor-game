package com.gamecodeschool.survivor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class Renderer {
    private Canvas mCanvas;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;

    private Camera mCamera;

    Renderer(SurfaceView sh, Point screenSize){
        mSurfaceHolder = sh.getHolder();
        mPaint = new Paint();
        mCamera = new Camera(screenSize.x, screenSize.y);
    }

    int getPixelsMetre(){
        return mCamera.getPixelsPerMetre();
    }

    void draw(ArrayList<GameObject> objects, GameState gs,  HUD hud) {
        if (mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.drawColor(Color.argb(255, 0, 0, 0));

            if (gs.getDrawing()) {
                // Set the player as the center of the camera
                mCamera.setWorldCentre(
                        objects.get(LevelManager.PLAYER_INDEX).getTransform().getLocation());

                for (GameObject object : objects) {
                    if (object.checkActive()) {
                        object.draw(mCanvas, mPaint, mCamera);
                    }
                }
            }
            hud.draw(mCanvas, mPaint, gs);

            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }
}
