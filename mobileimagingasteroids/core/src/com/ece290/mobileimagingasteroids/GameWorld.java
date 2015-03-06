package com.ece290.mobileimagingasteroids;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.ece290.mobileimagingasteroids.gameobject.Asteroid;
import com.ece290.mobileimagingasteroids.gameobject.Shot;
import com.ece290.mobileimagingasteroids.gameobject.GameObject;
import com.ece290.mobileimagingasteroids.gameobject.Ship;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ethan_000 on 2/15/2015.
 */
public class GameWorld {
    private int mWidth, mHeight;
    private Ship mShip;

    private List<Asteroid> asteroids;
    private List<Shot> shots;

    private int lives;
    private int score;

    private int temp = 100;
    private float runTime;
    private float asteroidSpawnTime;

    private float ASTEROID_ARRIVAL_RATE = 0.2f;
    private float ASTEROID_MAX = 20;

    private boolean isGameOver;

    public GameWorld(int width, int height)
    {
        this.mWidth = width;
        this.mHeight = height;
        asteroids = new ArrayList<Asteroid>();
        asteroids.add(new Asteroid(200, 200, 50, 50, 40, 40));

        shots = new ArrayList<Shot>();
        shots.add(new Shot(50,50,20,20));

        asteroidSpawnTime = (float) NegativeExponentialCalculator.calculate(ASTEROID_ARRIVAL_RATE);

        //asteroids.add(new Asteroid(200,200,50,50,40,40));

        mShip = new Ship(mWidth/10,mHeight/10, mWidth/2, mHeight/2);
        mShip.setVelocityY(-30);
    }
    public void update(float delta) {
        //Gdx.app.log("GameWorld", "update");
        runTime += delta;

        if(runTime > asteroidSpawnTime)
        {
            if(asteroids.size() < ASTEROID_MAX) {
                asteroids.add(new Asteroid(mWidth / 4, mHeight / 4));
            }
            asteroidSpawnTime = runTime + (float) NegativeExponentialCalculator.calculate(ASTEROID_ARRIVAL_RATE);
        }

        mShip.update(delta);
        mShip.setRotationUpdate(5);
        resetGameObjectInScreenBounds(mShip);
        for(Asteroid a : asteroids)
        {
            resetGameObjectInScreenBounds(a);
            a.setRotationUpdate(10);
            a.update(delta);
        }

        for(Shot s : shots) {
            s.update(delta);
        }

        temp--;
        if(temp < 0) {
            shots.add(mShip.shoot());
            temp = 100;
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

    private boolean isGameObjectInScreenBounds(GameObject o)
    {
        if (o.getX() > Gdx.graphics.getWidth())
            return false;
        if (o.getX() < 0 )
            return false;
        if (o.getY() > Gdx.graphics.getHeight())
            return false;
        if (o.getY() < 0 )
            return false;
        return true;
    }

    public Ship getShip(){return mShip;}
    public List<Asteroid> getAsteroids(){return asteroids;}
    public List<Shot> getShots() {return shots;}
}
