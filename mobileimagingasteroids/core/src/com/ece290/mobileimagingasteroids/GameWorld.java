package com.ece290.mobileimagingasteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.ece290.mobileimagingasteroids.controls.TouchGestureListener;
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

    private float runTime;
    private float asteroidSpawnTime;

    private float ASTEROID_ARRIVAL_RATE = 0.2f;
    private float ASTEROID_MAX = 20;

    private int deadTime = 1000;

    private boolean isGameOver;
    private boolean hasCrashed = false;

    private Sound crashSound = Gdx.audio.newSound(Gdx.files.internal("crash_sound.mp3"));

    public GameWorld(int width, int height)
    {
        this.mWidth = width;
        this.mHeight = height;
        asteroids = new ArrayList<Asteroid>();
        asteroids.add(new Asteroid(200, 200, 50, 50, 40, 40));

        shots = new ArrayList<Shot>();
        shots.add(new Shot(50, 50, 20, 20));

        asteroidSpawnTime = (float) NegativeExponentialCalculator.calculate(ASTEROID_ARRIVAL_RATE);

        //asteroids.add(new Asteroid(200,200,50,50,40,40));

        mShip = new Ship(mWidth/10,mHeight/10, mWidth/2, mHeight/2);
        mShip.setVelocityY(-30);

        TouchGestureListener.addListenser(new ControlsListener() {
            @Override
            public void onRotationUpdate(int rotationUpdate) {
                mShip.setRotationUpdate(rotationUpdate);

            }

            @Override
            public void onVelocityUpdate(float velX, float velY) {
                /*
                mShip.mVelocity = mShip.mVelocity.add(new Vector2(velX, velY).rotate(mShip.getRotation()));
                mShip.setRotationUpdate(mShip.getRotation());
                */
            }

            @Override
            public void onShoot() {
                Shot shot = mShip.shoot();
                if(shot != null)
                    shots.add(shot);
            }
        });
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

        //TODO also will need collision for shooting

        if(hasCrashed){
            deadTime-=delta;
            if(deadTime<=0){
                hasCrashed = false;
                deadTime = 1000;
            }
        }

        for (Shot s : shots) {
            for (Asteroid a : asteroids) {
                if (Intersector.overlapConvexPolygons(s.getPolygon(), a.getPolygon())) {
                    //System.out.println("BULLET HIT");
                }
            }
        }

        for(Asteroid a : asteroids)
        {
            if(Intersector.overlapConvexPolygons(mShip.getPolygon(), a.getPolygon()))
            {
                if(!hasCrashed){
                    System.out.println("SHIP HIT");
                    crashSound.play();
                    hasCrashed = true;
                }
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
