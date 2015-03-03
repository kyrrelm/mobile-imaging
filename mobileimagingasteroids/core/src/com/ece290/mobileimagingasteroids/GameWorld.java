package com.ece290.mobileimagingasteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.ece290.mobileimagingasteroids.gameobject.Asteroid;
import com.ece290.mobileimagingasteroids.gameobject.Ship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ethan_000 on 2/15/2015.
 */
public class GameWorld {

    private Rectangle rect = new Rectangle(0, 0, 320, 240);
    private int mWidth, mHeight;
    private Ship mShip;
    private List<Asteroid> asteroids;

    private int lives;
    private int score;

    private boolean isGameOver;

    public GameWorld(int width, int height)
    {
        this.mWidth = width;
        this.mHeight = height;
        asteroids = new ArrayList<Asteroid>();
        asteroids.add(new Asteroid(200,200,50,50,40,40));

        mShip = new Ship(mWidth/15,mHeight/15, mWidth/2, mHeight/2);
        mShip.setAccelerationY(2);
        mShip.setAccelerationY(1);
    }
    public void update(float delta) {
        //Gdx.app.log("GameWorld", "update");
        rect.x += 4;
        if (rect.x > Gdx.graphics.getWidth())
            rect.x = 0;
        mShip.setX(mShip.getX()+4);
        if (mShip.getX() > Gdx.graphics.getWidth())
            mShip.setX(0);
        for(Asteroid a : asteroids)
        {
            if (a.getX() > Gdx.graphics.getWidth())
                a.setX(0);
            if (a.getX() > Gdx.graphics.getHeight())
                a.setY(0);
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

    public Rectangle getRect() {return rect;}
    public Ship getShip(){return mShip;}
    public List<Asteroid> getAsteroids(){return asteroids;}
}
