package com.gamecodeschool.survivor;

import android.content.Context;
import android.graphics.PointF;

import com.gamecodeschool.survivor.GOSpec.GameHeroObjectSpec;
import com.gamecodeschool.survivor.GOSpec.GameObjectSpec;

public class GameObjectFactory {

    private Context mContext;
    private GameEngine mGameEngineReference;
    private int mPixelsPerMetre;

    GameObjectFactory( Context context, GameEngine gameEngine, int pixelsPerMetre){
        mContext = context;
        mGameEngineReference = gameEngine;
        mPixelsPerMetre = pixelsPerMetre;
    }


    GameObject create(GameObjectSpec spec, PointF location ){
        GameObject object = new GameObject();

        int mNumComponents = spec.getComponents().length;
        object.setTag(spec.getTag());

        switch( object.getTag() ){
            case "Background":
                // Code coming soon
                object.setTransform(
                        new BackgroundTransform(
                                spec.getSpeed(),
                                spec.getSize().x,
                                spec.getSize().y,
                                location));
                break;


            case "Player":
                GameHeroObjectSpec ghosSpec = (GameHeroObjectSpec)spec;
                // Code coming soon
                object.setTransform(
                        new PlayerTransform(ghosSpec.getSpeed(),
                                spec.getSize().x,
                                spec.getSize().y,
                                location));
                break;

            default:
                object.setTransform(new Transform(spec.getSpeed(),
                        spec.getSize().x,
                        spec.getSize().y,
                        location));
                break;

        }

        for( int i = 0; i < mNumComponents; i++ ){
            switch( spec.getComponents()[i]){

                case "PlayerInputComponent":
                    // Code coming soon
                    object.setPlayerInputTransform(
                            new PlayerInputComponent(
                                    mGameEngineReference));
                    break;
                case "AnimatedGraphicsComponent":
                    // Code coming soon
                    object.setGraphics(
                            new AnimatedGraphicsComponent(),
                            mContext, spec, spec.getSize(),
                            mPixelsPerMetre);
                    break;
                case "AnimatedHeroGraphicsComponent":
                    // Code coming soon
                    object.setGraphics(
                            new AnimatedHeroGraphicsComponent(),
                            mContext, spec, spec.getSize(),
                            mPixelsPerMetre);
                    break;
                case "PlayerUpdateComponent":
                    // Code coming soon
                    object.setMovement(new PlayerUpdateComponent());
                    break;
                case "InanimateBlockGraphicsComponent":
                    object.setGraphics(new
                                    InanimateBlockGraphicsComponent(),
                            mContext, spec, spec.getSize(),
                            mPixelsPerMetre);
                    break;
                case "InanimateBlockUpdateComponent":
                    object.setMovement(new
                            InanimateBlockUpdateComponent());
                    break;
                case "MovableBlockUpdateComponent":
                    // Code coming soon
                    object.setMovement(new
                            MovableBlockUpdateComponent());
                    break;
                case "DecorativeBlockUpdateComponent":
                    object.setMovement(new
                            DecorativeBlockUpdateComponent());
                    break;
                case "BackgroundGraphicsComponent":
                    // Code coming soon
                    object.setGraphics(
                            new BackgroundGraphicsComponent(),
                            mContext, spec, spec.getSize(),
                            mPixelsPerMetre);
                    break;
                case "BackgroundUpdateComponent":
                    // Code coming soon
                    object.setMovement(new BackgroundUpdateComponent());
                    break;

                default:
                    // Error unidentified component
                    break;
            }
        }

        return object;
    }
}
