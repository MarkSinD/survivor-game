package com.gamecodeschool.survivor.GOSpec;


import android.graphics.PointF;

public class PlayerSpec extends GameHeroObjectSpec {

    // This is all the unique specifications for a player
    private static final String tag = "Player";
    private static final String bitmapName = "no using this, bitmaps contains in @bitmaps";
    private static final String[] bitmaps = new String[]{
            "run_player_run",
            "run_player_run_reversed",
            "jst_player_jump_start",
            "jst_player_jump_start_reversed",
            "att_player_attack",
            "att_player_attack_reversed",
            "jfl_player_jump_fall",
            "jfl_player_jump_fall_reversed"
    };


    private static final int[] countFramesOfBitmaps = new int[]{13, 13, 10, 10, 14, 14,1,1};
    private static final int framesOfAnimation = 14;
    private static final float speed = 10f;
    private static final PointF size = new PointF(6f, 4f);
    private static final String[] components = new String [] {
            "PlayerInputComponent",
            "AnimatedHeroGraphicsComponent",
            "PlayerUpdateComponent"};

    public PlayerSpec() {
        super(tag, speed,size,components,bitmaps,countFramesOfBitmaps);
    }
}
