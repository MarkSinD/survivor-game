package com.gamecodeschool.survivor.GOSpec;

import android.graphics.PointF;

public class BrickTileSpec extends GameObjectSpec {
    private static final String tag = "Inert Tile";
    private static final String bitMapName = "brick";
    private static final int framesOfAnimation = 1;
    private static final float speed = 0f;
    private static final PointF size = new PointF(1f,1f);
    private static final String[] components = new String[]{
            "InanimateBlockGraphicsComponent",
            "InanimateBlockUpdateComponent"
    };


    public BrickTileSpec() {
        super(tag, bitMapName, speed, size, components, framesOfAnimation);
    }
}
