package com.gamecodeschool.survivor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;

import com.gamecodeschool.survivor.GOSpec.GameObjectSpec;

public class InanimateBlockGraphicsComponent implements GraphicsComponent {

    private String mBitmapName;


    @Override
    public void initialize(Context c, GameObjectSpec spec, PointF objectSize, int pixelsPerMetre) {
        mBitmapName = spec.getBitMapName();
        BitmapStore.addBitmap(c, mBitmapName,objectSize,pixelsPerMetre,false);
    }

    @Override
    public void draw(Canvas canvas, Paint paint, Transform t, Camera cam) {
        Bitmap bitmap = BitmapStore.getBitmap(mBitmapName);

        Rect screenCoordinated = cam.worldToScreen(
                t.getLocation().x, t.getLocation().y,
                t.getSize().x, t.getSize().y);

        canvas.drawBitmap(
                bitmap, screenCoordinated.left,
                screenCoordinated.top, paint);
    }
}
