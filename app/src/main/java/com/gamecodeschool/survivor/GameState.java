package com.gamecodeschool.survivor;

import android.content.Context;
import android.content.SharedPreferences;


// Готов

// Содержит состяния игры, состояние игрового потока
// Может начинать игру с метода, описанного в GameEngine
// Содержит лучшие результаты времени прохождения уровней
// Ведет подсчет текущих монет и текущего времени
// Сореждит состояние текущего уровня
// Умеет останавливать игровой поток и имеет еще кучу геттеров типа текущего времени игры
public class GameState {


    private static volatile boolean mThreadRunning = false;
    private static volatile boolean mPaused = true;
    private static volatile boolean mGamerOver = true;
    private static volatile boolean mDrawing = false;

    private EngineController mEngineController;

    private int mFastestUnderground;
    private int mFastestMountains;
    private int mFastestCity;
    private long mStartTimeMillis;

    private int mCoinsAvailable;
    private int mCoinsCollected;

    private SharedPreferences.Editor mEditor;

    private String mCurrentLevel;

    GameState(EngineController gs, Context context){
        mEngineController = gs;
        SharedPreferences prefs = context.getSharedPreferences("HiScore",
                Context.MODE_PRIVATE);
        mEditor = prefs.edit();
        mFastestUnderground = prefs.getInt("fastest_underground_time", 1000);
        mFastestUnderground = prefs.getInt("fastest_mountains_time", 1000);
        mFastestCity = prefs.getInt("fastest_city_time", 1000);
    }

    void coinCollected(){ mCoinsCollected++; }
    int getCoinsRemaing(){ return mCoinsAvailable - mCoinsCollected; }
    void coinAddedToLevel(){ mCoinsAvailable++; }
    void resetCoins(){ mCoinsAvailable = 0; mCoinsCollected = 0; }
    void setCurrentLevel( String level){ mCurrentLevel = level; }
    String getCurrentLevel(){ return mCurrentLevel; }
    void objectiveReached(){ endGame(); }
    int getFastestUnderground(){ return mFastestUnderground; }
    int getFastestMountains(){ return mFastestMountains; }
    int getFactestCity(){ return mFastestCity; }

    void startNewGame(){
        stopEverything();
        mEngineController.startNewLevel();
        startEverything();
        mStartTimeMillis = System.currentTimeMillis();

    }
    int getCurrentTime(){
        long MILLIS_IN_SECOND = 1000;
        return (int)((System.currentTimeMillis() - mStartTimeMillis) / MILLIS_IN_SECOND);
    }

    void death(){
        stopEverything();
        SoundEngine.playPlayerBurn();

    }

    

    private void endGame(){

        stopEverything();
        int totalTime = ( (mCoinsAvailable - mCoinsCollected) * 10 + getCurrentTime());

        switch ( mCurrentLevel ){
            case "underground":
                if( totalTime < mFastestUnderground){
                    mFastestUnderground = totalTime;

                    mEditor.putInt("fastest_underground_time", mFastestUnderground);
                    mEditor.commit();
                }
                break;
            case "city":
                if( totalTime < mFastestCity){
                    mFastestCity = totalTime;
                    mEditor.putInt("fastest_city_time", mFastestCity);
                    mEditor.commit();
                }
                break;
            case "mountains":
                if(totalTime < mFastestMountains){
                    mFastestMountains = totalTime;
                    mEditor.putInt("fastest_mountains_time", mFastestMountains);
                    mEditor.commit();
                }
                break;
        }
    }


    void stopEverything(){
        mPaused = true;
        mGamerOver = true;
        mDrawing = false;
    }

    private void startEverything(){
        mPaused = false;
        mGamerOver = false;
        mDrawing = true;
    }

    void stopThread(){ mThreadRunning = false; }
    boolean getThreadRunning(){ return mThreadRunning; }
    void startThread(){ mThreadRunning = true; }
    boolean getDrawing(){ return mDrawing; }
    boolean getPaused(){ return mPaused; }
    boolean getGameOver(){ return mGamerOver; }

}
