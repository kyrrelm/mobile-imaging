package com.ece290.mobileimagingasteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ece290.mobileimagingasteroids.controls.TouchGestureListener;
import com.ece290.mobileimagingasteroids.gameobject.Asteroid;
import com.ece290.mobileimagingasteroids.gameobject.Shot;
import com.ece290.mobileimagingasteroids.gameobject.GameObject;
import com.ece290.mobileimagingasteroids.gameobject.Ship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ethan_000 on 2/15/2015.
 */
public class GameWorld {
    private int mWidth, mHeight;
    private Ship mShip;

    private List<Asteroid> asteroids;
    private List<Shot> shots = new ArrayList<Shot>();

    private int score;

    private float runTime;
    private float asteroidSpawnTime;

    private float ASTEROID_ARRIVAL_RATE = 0.2f;
    private float ASTEROID_MAX = 20;

    private int deadTime = 300;

    private boolean isGameOver;
    private boolean hasCrashed = false;

    private Sound crashSound = Gdx.audio.newSound(Gdx.files.internal("crash_sound.mp3"));

    public GameWorld(int width, int height)
    {
        this.mWidth = width;
        this.mHeight = height;
        asteroids = new ArrayList<Asteroid>();
        asteroids.add(new Asteroid(200, 200, 50, 50, 40, 40));

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

        if(mShip.isDead()) {
            return;
        }

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
                deadTime = 300;
            }
        }

        List<Asteroid> astrCopy = asteroids;
        Iterator<Shot> shotItr = shots.iterator();
        if (shotItr.hasNext()) {
            Shot currShot = shotItr.next();
            for (int i = 0; i < astrCopy.size(); i++) {
                Asteroid currAsteroid = asteroids.get(i);

                if (Intersector.overlapConvexPolygons(currShot.getPolygon(), currAsteroid.getPolygon())) {
                    System.out.println("BULLET HIT");

                    shots.remove(currShot);

                    if (currAsteroid.getWidth() > mWidth / 12 && currAsteroid.getHeight() > mHeight / 12) {
                        asteroids.add(new Asteroid(Math.round(currAsteroid.getWidth() / 2),
                                Math.round(currAsteroid.getHeight() / 2),
                                currAsteroid.getX(),
                                currAsteroid.getY(),
                                currAsteroid.getVelocityX() + MathUtils.random(5, 20),
                                currAsteroid.getVelocityY() + MathUtils.random(5, 20)));

                        asteroids.add(new Asteroid(Math.round(currAsteroid.getWidth() / 2),
                                Math.round(currAsteroid.getHeight() / 2),
                                currAsteroid.getX(),
                                currAsteroid.getY(),
                                currAsteroid.getVelocityX() + MathUtils.random(5, 20),
                                currAsteroid.getVelocityY() + MathUtils.random(5, 20)));
                        asteroids.remove(i);
                    }
                    asteroids.remove(i);
                }
            }
        }

        Iterator<Shot> itr = shots.iterator();
        if (itr.hasNext()) {
            Shot currentShot = itr.next();
            if (!isGameObjectInScreenBounds(currentShot)){
                shots.remove(currentShot);
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
