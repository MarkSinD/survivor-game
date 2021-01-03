package com.gamecodeschool.survivor;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

public class UIController implements InputObserver {

    private float mThird;
    private boolean initialPress = false;

    UIController(GameEngineBroadcaster b, Point size){
        addObserver(b);
        mThird = size.x / 3;
    }

    void addObserver(GameEngineBroadcaster b){ b.addObserver(this);}

    @Override
    public void handleInput(MotionEvent event,
                            GameState gameState,
                            ArrayList<Rect> buttons) {

        int i = event.getActionIndex();
        int x = (int) event.getX(i);

        int eventType = event.getAction()
                & MotionEvent.ACTION_MASK;

        if (eventType == MotionEvent.ACTION_UP ||
                eventType == MotionEvent.ACTION_POINTER_UP) {

            // If game is over start a new game
            if (gameState.getGameOver() && initialPress) {

                if (x < mThird) {
                    gameState.setCurrentLevel("city");
                    gameState.startNewGame();
                } else if (x >= mThird && x < mThird * 2) {
                    gameState.setCurrentLevel("city");
                    gameState.startNewGame();
                } else if (x >= mThird * 2) {
                    gameState.setCurrentLevel("city");
                    gameState.startNewGame();
                }

            }

            initialPress = !initialPress;
        }

    }
}
