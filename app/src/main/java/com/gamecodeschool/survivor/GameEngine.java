package com.gamecodeschool.survivor;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.concurrent.CopyOnWriteArrayList;

public class GameEngine extends SurfaceView
        implements Runnable,
        EngineController,
        GameEngineBroadcaster {


    private Thread mThread = null;
    private long mFPS;

    private GameState mGameState;
    UIController mUIController;

    HUD mHUD;
    Renderer mRenderer;

    LevelManager mLevelManager;
    PhysicsEngine mPhysicsEngine;


    private CopyOnWriteArrayList<InputObserver>
            inputObservers = new CopyOnWriteArrayList<>();




    public GameEngine(Context context, Point size){
        super(context);

        BitmapStore bs = BitmapStore.getInstance(context);
        SoundEngine se = SoundEngine.getInstance(context);

        mHUD = new HUD(context, size);
        mUIController = new UIController(this, size);
        mGameState = new GameState(this, context);
        mRenderer = new Renderer(this, size);
        mLevelManager = new LevelManager(context, this, mRenderer.getPixelsMetre());
        mPhysicsEngine = new PhysicsEngine();

    }


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        for (InputObserver o : inputObservers) {
            o.handleInput(motionEvent, mGameState, mHUD.getControls());
        }
        return true;
    }


    @Override
    public void run() {

        while (mGameState.getThreadRunning()){

            long frameStartTime = System.currentTimeMillis();

            if(!mGameState.getPaused()){

                mPhysicsEngine.update(mFPS, mLevelManager.getGameObjects(), mGameState);
            }

            mRenderer.draw(mLevelManager.getGameObjects(), mGameState, mHUD);

            long timeThisFrame = System.currentTimeMillis()
                    - frameStartTime;

            if (timeThisFrame >= 1) {
                final int MILLIS_IN_SECOND = 1000;
                mFPS = MILLIS_IN_SECOND / timeThisFrame;
            }
        }

    }

    @Override
    public void startNewLevel() {
        BitmapStore.clearStore();
        inputObservers.clear();
        mUIController.addObserver(this);
        mLevelManager.setCurrentLevel(mGameState.getCurrentLevel());
        mLevelManager.buildGameObjects(mGameState);
    }

    public void stopThread() {
        mGameState.stopEverything();
        mGameState.stopThread();

        try {
            mThread.join();
        } catch (InterruptedException e) {
            Log.e("Exception ", "stopThread()" + e.getMessage());
        }

    }

    public void startThread(){
        mGameState.startThread();;
        mThread = new Thread(this);
        mThread.start();
    }

    @Override
    public void addObserver(InputObserver o) {
        inputObservers.add(o);
    }
}
