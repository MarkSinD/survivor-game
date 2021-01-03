package com.gamecodeschool.survivor.GOSpec;

import android.graphics.PointF;

public class BackgroundCitySpec extends GameObjectSpec {
    private static final String tag = "Background";
    private static final String bitmapName = "bg_snow_b";
    private static final int framesAnimation = 1;
    private static final float speed = 3f;
    private static final PointF size = new PointF(100, 55);
    private static final String[] components = new String[]{
            "BackgroundGraphicsComponent",
            "BackgroundUpdateComponent"
    };

    public BackgroundCitySpec() {
        super(tag, bitmapName, speed, size, components, framesAnimation);
    }
}
