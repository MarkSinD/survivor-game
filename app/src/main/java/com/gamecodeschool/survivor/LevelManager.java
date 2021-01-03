package com.gamecodeschool.survivor;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;

import com.gamecodeschool.survivor.GOSpec.BackgroundCitySpec;
import com.gamecodeschool.survivor.GOSpec.BrickTileSpec;
import com.gamecodeschool.survivor.GOSpec.CartTileSpec;
import com.gamecodeschool.survivor.GOSpec.CoalTileSpec;
import com.gamecodeschool.survivor.GOSpec.CollectibleObjectSpec;
import com.gamecodeschool.survivor.GOSpec.ConcreteTileSpec;
import com.gamecodeschool.survivor.GOSpec.DeadTreeTileSpec;
import com.gamecodeschool.survivor.GOSpec.FireTileSpec;
import com.gamecodeschool.survivor.GOSpec.SnowByFeetCenterTileSpec;
import com.gamecodeschool.survivor.GOSpec.InvisibleDeathTenByTenSpec;
import com.gamecodeschool.survivor.GOSpec.LamppostTileSpec;
import com.gamecodeschool.survivor.GOSpec.MoveablePlatformSpec;
import com.gamecodeschool.survivor.GOSpec.ObjectiveTileSpec;
import com.gamecodeschool.survivor.GOSpec.PlayerSpec;
import com.gamecodeschool.survivor.GOSpec.ScorchedTileSpec;
import com.gamecodeschool.survivor.GOSpec.SnowTileSpec;
import com.gamecodeschool.survivor.GOSpec.SnowyTreeTileSpec;
import com.gamecodeschool.survivor.GOSpec.StalactiteTileSpec;
import com.gamecodeschool.survivor.GOSpec.StalagmiteTileSpec;
import com.gamecodeschool.survivor.GOSpec.StonePileTileSpec;
import com.gamecodeschool.survivor.Levels.Level;
import com.gamecodeschool.survivor.Levels.LevelCity;

import java.util.ArrayList;

final class LevelManager {

    static int PLAYER_INDEX = 0;
    private ArrayList<GameObject> objects;
    private Level currentLevel;
    private GameObjectFactory factory;

    LevelManager(Context context, GameEngine ge, int pixelsPerMetre){
        objects = new ArrayList<>();
        factory = new GameObjectFactory(context, ge, pixelsPerMetre);
    }

    void setCurrentLevel( String level ){
        switch (level){

            case "city":
                currentLevel = new LevelCity();
                break;

            case "mountains":
                // Уровня не существует
                break;

        }
    }

    void buildGameObjects(GameState gs){
        // Backgrounds 1, 2, 3(City, Underground, Mountain...)
        // p = Player
        // g = Grass tile
        // o = Objective
        // m = Movable platform
        // b = Brick tile
        // c = mine Cart
        // s = Stone pile
        // l = coaL
        // n = coNcrete
        // a = lAmpost
        // r = scoRched tile
        // w = snoW tile
        // t = stalacTite
        // i = stalagmIte
        // d = Dead tree
        // e = snowy trEe
        // x = Collectable
        // z = Fire
        // y = invisible death_invisible

        gs.resetCoins();
        objects.clear();
        ArrayList<String> levelToLoad = currentLevel.getTiles();

        for(int row = 0; row < levelToLoad.size(); row++ )
        {
            for(int column = 0; column < levelToLoad.get(row).length();  column++){

                PointF coords = new PointF(column, row);

                switch (levelToLoad.get(row)
                        .charAt(column)){

                    case '1':
                        objects.add(factory.create(
                                new BackgroundCitySpec(),
                                coords));
                        break;



                    case 'p':
                        objects.add(factory.create(new
                                        PlayerSpec(),
                                coords));
                        // Remember the location of
                        // the player
                        PLAYER_INDEX = objects.size()-1;
                        break;

                    case 'g':
                        objects.add(factory.create(
                                new SnowByFeetCenterTileSpec(),
                                coords));
                        break;

                    case 'o':
                        objects.add(factory.create(
                                new ObjectiveTileSpec(),
                                coords));
                        break;

                    case 'm':
                        objects.add(factory.create(
                                new MoveablePlatformSpec(),
                                coords));
                        break;

                    case 'b':
                        objects.add(factory.create(
                                new BrickTileSpec(),
                                coords));
                        break;

                    case 'c':
                        objects.add(factory.create(
                                new CartTileSpec(),
                                coords));
                        break;

                    case 's':
                        objects.add(factory.create(
                                new StonePileTileSpec(),
                                coords));
                        break;

                    case 'l':
                        objects.add(factory.create(
                                new CoalTileSpec(),
                                coords));
                        break;

                    case 'n':
                        objects.add(factory.create(
                                new ConcreteTileSpec(),
                                coords));
                        break;

                    case 'a':
                        objects.add(factory.create(
                                new LamppostTileSpec(),
                                coords));
                        break;

                    case 'r':
                        objects.add(factory.create(
                                new ScorchedTileSpec(),
                                coords));
                        break;

                    case 'w':
                        objects.add(factory.create(
                                new SnowTileSpec(),
                                coords));
                        break;

                    case 't':
                        objects.add(factory.create(
                                new StalactiteTileSpec(),
                                coords));
                        break;

                    case 'i':
                        objects.add(factory.create(
                                new StalagmiteTileSpec(),
                                coords));
                        break;

                    case 'd':
                        objects.add(factory.create(
                                new DeadTreeTileSpec(),
                                coords));
                        break;

                    case 'e':
                        objects.add(factory.create(
                                new SnowyTreeTileSpec(),
                                coords));
                        break;

                    case 'x':
                        objects.add(factory.create(
                                new CollectibleObjectSpec(),
                                coords));
                        gs.coinAddedToLevel();
                        break;

                    case 'z':
                        objects.add(factory.create(
                                new FireTileSpec(),
                                coords));

                        break;

                    case 'y':
                        objects.add(factory.create(
                                new InvisibleDeathTenByTenSpec(),
                                coords));

                        break;


                    case '.':
                        // Nothing to see here
                        break;

                    default:
                        Log.e("Unhandled item in level",
                                "row:"+row
                                        + " column:"+column);
                        break;
                }

            }

        }

    }

    ArrayList<GameObject> getGameObjects(){
        return objects;
    }




}
