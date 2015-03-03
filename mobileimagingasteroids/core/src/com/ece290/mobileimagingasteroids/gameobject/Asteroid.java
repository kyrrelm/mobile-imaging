package com.ece290.mobileimagingasteroids.gameobject;

import com.badlogic.gdx.math.Polygon;

import java.util.ArrayList;

/**
 * Created by Hallvard on 02.03.2015.
 */
public class Asteroid extends GameObject {

    private int health;

    protected Asteroid (int width, int height, float positionX, float positionY, float velocityX, float velocityY, int health) {
        super(width, height, positionX, positionY, velocityX, velocityY);

        this.health = health;
    }

    protected ArrayList<Asteroid> hit() {
        health--;

        if(health == 0) {
            destroy();
            return null;
        }

        if(splitIfHit()) {
            splitAsteroid();
        }
        return null;
    }

    private boolean splitIfHit() {
        return true;
    }

    protected ArrayList<Asteroid> splitAsteroid() {

        ArrayList children = new ArrayList<Asteroid>();

        //Just creating a new asteroid.

        return null;
    }

    private void destroy(){
        //TODO
    }

    @Override
    protected Polygon getPolygonInternal() {
        return null;
    }
}
