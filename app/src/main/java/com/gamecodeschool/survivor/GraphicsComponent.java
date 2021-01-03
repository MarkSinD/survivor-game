package com.gamecodeschool.survivor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.gamecodeschool.survivor.GOSpec.GameObjectSpec;

public interface GraphicsComponent {

    void initialize(Context c, GameObjectSpec spec, PointF objectSize, int pixelsPerMetre);
    void draw(Canvas canvas, Paint paint, Transform t, Camera cam);
}
