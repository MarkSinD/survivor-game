package com.gamecodeschool.survivor;

import android.graphics.PointF;
import android.graphics.Rect;


// Готов
// Хранит такие параметры как разрешение экрана телефона, колличество пикселей на метр,
// текущее положение камеры, и соответсвенно имеет геттеры на эти данные
// Умеет расчитывать мировые координаты обьектов и находить координаты элементов в новой системе координат
// Центр которой находится на игроке

public class Camera {
    private PointF mCurrentCameraWorldCentre;
    private Rect mConvertedRect;
    private int mPixelsPerMetre;
    private int mScreenCentreX;
    private int mScreenCentreY;

    Camera( int screenXResolution, int screenYResolution){
        mScreenCentreX = screenXResolution/2;
        mScreenCentreY = screenYResolution/2;

        final int pixelsPerMetreToResolutionRatio = 25;
        mPixelsPerMetre = screenXResolution/ pixelsPerMetreToResolutionRatio;

        mConvertedRect = new Rect();
        mCurrentCameraWorldCentre = new PointF();
    }

    int getPixelsPerMetreY(){ return mPixelsPerMetre; }

    int getYCentre(){ return mScreenCentreY;}

    float getCameraWorldCentreY(){ return mCurrentCameraWorldCentre.y; }

    void setWorldCentre(PointF worldCentre){
        mCurrentCameraWorldCentre.x = worldCentre.x+3;
        mCurrentCameraWorldCentre.y = worldCentre.y+2;
    }

    int getPixelsPerMetre(){ return mPixelsPerMetre; }

    Rect worldToScreen(float objectX, float objectY, float objectWidth, float objectHeight){
        int left = (int) (mScreenCentreX - (mCurrentCameraWorldCentre.x - objectX) * mPixelsPerMetre);
        int top = (int)( mScreenCentreY - (mCurrentCameraWorldCentre.y - objectY)* mPixelsPerMetre);

        int right = (int) (left + (objectWidth * mPixelsPerMetre));
        int bottom = (int) (top + (objectHeight * mPixelsPerMetre));
        mConvertedRect.set(left, top, right, bottom);
        return mConvertedRect;
    }
}
