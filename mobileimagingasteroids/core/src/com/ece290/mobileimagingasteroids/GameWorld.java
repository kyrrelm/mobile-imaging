package com.ece290.mobileimagingasteroids;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.ece290.mobileimagingasteroids.gameobject.Asteroid;
<<<<<<< HEAD
import com.ece290.mobileimagingasteroids.gameobject.Shot;
=======
import com.ece290.mobileimagingasteroids.gameobject.GameObject;
>>>>>>> 96a96246e663b478f077135368cd4ef80a1b1e49
import com.ece290.mobileimagingasteroids.gameobject.Ship;

import java.util.ArrayList;

/**
 * Created by ethan_000 on 2/15/2015.
 */
public class GameWorld {
    private Rectangle rect = new Rectangle(0, 0, 320, 240);

    private int mWidth, mHeight;
    private Ship mShip;

    private ArrayList<Asteroid> asteroidList;
    private ArrayList<Shot> bulletList;

    private int lives;
    private int score;

    private boolean isGameOver;

    public GameWorld(int width, int height)
    {
        this.mWidth = width;
        this.mHeight = height;
        mShip = new Ship(mWidth/15,mHeight/15, mWidth/2, mHeight/2);
<<<<<<< HEAD
        mShip.setAccelerationY(2);
        mShip.setAccelerationY(1);
=======
        mShip.setVelocityY(-30);
>>>>>>> 96a96246e663b478f077135368cd4ef80a1b1e49
    }
    public void update(float delta) {
        Gdx.app.log("GameWorld", "update");
        rect.x += 4;
        if (rect.x > Gdx.graphics.getWidth())
            rect.x = 0;
<<<<<<< HEAD
        mShip.setX(mShip.getX()+4);
        if (mShip.getX() > Gdx.graphics.getWidth())
            mShip.setX(0);

    }
    public Rectangle getRect() {
        return rect;
    }
=======
        mShip.update(delta);
        mShip.setRotationUpdate(5);
        resetGameObjectInScreenBounds(mShip);
        for(Asteroid a : asteroids)
        {
            resetGameObjectInScreenBounds(a);
            a.setRotationUpdate(10);
            a.update(delta);
        }

        //TODO also will need collision for shooting

        for(Asteroid a : asteroids)
        {
            if(Intersector.overlapConvexPolygons(mShip.getPolygon(), a.getPolygon()));
            {
                //TODO game over
            }
        }

    }

    private void resetGameObjectInScreenBounds(GameObject o)
    {
        if (o.getX() > Gdx.graphics.getWidth())
            o.setX(0);
        if (o.getX() < 0 )
            o.setX(Gdx.graphics.getWidth());
        if (o.getY() > Gdx.graphics.getHeight())
            o.setY(0);
        if (o.getY() < 0 )
            o.setY(Gdx.graphics.getHeight());
    }

    public Rectangle getRect() {return rect;}
>>>>>>> 96a96246e663b478f077135368cd4ef80a1b1e49
    public Ship getShip(){return mShip;}
}
