package com.ece290.mobileimagingasteroids.gameobject;

import com.badlogic.gdx.math.Polygon;

/**
 * Created by ingeborgoftedal on 02.03.15.
 */
public class Bullet extends GameObject{
    public Bullet(int width, int height, float positionX, float positionY, float velocityX, float velocityY){
        super(width, height, positionX, positionY, velocityX, velocityY);

    }
    protected Polygon getPolygonInternal(){
        return null;
    }
}
