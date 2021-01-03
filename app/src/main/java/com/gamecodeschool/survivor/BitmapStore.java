package com.gamecodeschool.survivor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;

import java.util.HashMap;
import java.util.Map;


// Готов
//
// Содержит 2 HashMap, оба под картинки
// 1 под левосторонние, 2 под правосторонние
// Умеет добавлять и чистить хранилища

public class BitmapStore {

    private static Map<String, Bitmap> mBitmapsMap;
    private static Map<String, Bitmap> mBitmapsReservedMap;
    private static BitmapStore mOurInstance;


    static BitmapStore getInstance(Context context){
        mOurInstance = new BitmapStore(context);
        return mOurInstance;
    }

    private BitmapStore(Context c){
        mBitmapsMap = new HashMap();
        mBitmapsReservedMap = new HashMap();

        addBitmap(c, "death_visible", new PointF(1,1), 128, true);
    }

    static Bitmap getBitmap(String bitmapName){
        if( mBitmapsMap.containsKey(bitmapName)){
            return mBitmapsMap.get(bitmapName);
        }
        else{
            return  mBitmapsMap.get("death_visible");
        }
    }

    static Bitmap getBitmapReversed( String bitmapName){
        if( mBitmapsReservedMap.containsKey(bitmapName)){
            return mBitmapsReservedMap.get(bitmapName);
        }
        else{
            return mBitmapsReservedMap.get("death_visible");
        }
    }

    static void addBitmap(Context c,
                          String bitmapName,
                          PointF objectSize,
                          int pixelsPerMetre,
                          boolean needReversed){
        Bitmap bitmap;
        Bitmap bitmapReversed;

        int resID = c.getResources().getIdentifier( bitmapName, "drawable", c.getPackageName());

        bitmap = BitmapFactory.decodeResource(c.getResources(), resID);

        bitmap = Bitmap.createScaledBitmap(bitmap,
                (int) objectSize.x * pixelsPerMetre,
                (int) objectSize.y * pixelsPerMetre, false);

        mBitmapsMap.put(bitmapName, bitmap);


        if(needReversed){
            Matrix matrix = new Matrix();
            matrix.setScale(-1,1);
            bitmapReversed = Bitmap.createBitmap(bitmap, 0,0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            mBitmapsReservedMap.put(bitmapName, bitmapReversed);
        }
    }

    static void clearStore(){
        mBitmapsMap.clear();
        mBitmapsReservedMap.clear();
    }
}
