package com.gamecodeschool.survivor;

import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

public interface InputObserver {

    void handleInput(MotionEvent event, GameState gs, ArrayList<Rect> buttons);
}
