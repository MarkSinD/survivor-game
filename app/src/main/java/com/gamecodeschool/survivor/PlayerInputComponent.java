package com.gamecodeschool.survivor;

import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

public class PlayerInputComponent implements InputObserver {

    private Transform mPlayersTransform;
    private PlayerTransform mPlayersPlayerTransform;


    PlayerInputComponent( GameEngine ger){ ger.addObserver(this);}


    public void setTransform(Transform transform){
        mPlayersTransform = transform;
        mPlayersPlayerTransform = (PlayerTransform)mPlayersTransform;
    }







    @Override
    public void handleInput(MotionEvent event, GameState gs, ArrayList<Rect> buttons) {
        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);

        if(!gs.getPaused()){
            switch ( event.getAction() & MotionEvent.ACTION_MASK ){
                case MotionEvent.ACTION_UP:
                    if (buttons.get(HUD.LEFT).contains(x,y) || buttons.get(HUD.RIGHT).contains(x,y)){
                        mPlayersTransform.stopHorizontal();
                    }
                    break;

                case MotionEvent.ACTION_DOWN:
                    if(buttons.get(HUD.LEFT).contains(x,y)){
                        mPlayersTransform.headLeft();
                    }
                    else if( buttons.get(HUD.RIGHT).contains(x,y)){
                        mPlayersTransform.headRight();
                    }
                    else if( buttons.get(HUD.JUMP).contains(x,y)){
                        mPlayersPlayerTransform.triggerJump();
                    }
                    else if( buttons.get(HUD.ATTAСK).contains(x,y)){
                        mPlayersPlayerTransform.triggerAttack();
                    }
                    break;

                case MotionEvent.ACTION_POINTER_UP:
                    if( buttons.get(HUD.LEFT).contains(x,y)
                            || buttons.get(HUD.RIGHT).contains(x,y)){
                        mPlayersTransform.stopHorizontal();
                    }
                    break;

                case MotionEvent.ACTION_POINTER_DOWN:
                    if(buttons.get(HUD.LEFT).contains(x,y)){
                        mPlayersTransform.headLeft();
                    }
                    else if(buttons.get(HUD.RIGHT).contains(x,y)){
                        mPlayersTransform.headRight();
                    }
                    else if(buttons.get(HUD.JUMP).contains(x,y)){
                        mPlayersPlayerTransform.triggerJump();
                    }
                    else if(buttons.get(HUD.ATTAСK).contains(x,y)){
                        mPlayersPlayerTransform.triggerAttack();
                    }
                    break;
            }
        }
    }
}
