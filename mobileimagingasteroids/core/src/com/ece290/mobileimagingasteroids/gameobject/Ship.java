package com.ece290.mobileimagingasteroids.gameobject;

import com.badlogic.gdx.math.Polygon;

/**
 * Created by ethan_000 on 2/17/2015.
 */
public class Ship extends GameObject {

    public Ship(int width, int height, float positionX, float positionY)
    {
        super (width, height, positionX, positionY);
    }

    public Bullet shoot() {
        //TODO
    }

    @Override
    protected Polygon getPolygonInternal() {
        Polygon p = new Polygon(new float[]{
                (1f/3f)*mWidth,(7f/8f)*mHeight,
                (7f/12f)*mWidth,(11f/32f)*mHeight,
                (19f/20f)*mWidth,(17f/32f)*mHeight,
                (2f/3f)*mWidth,(7f/8f)*mHeight});
        p.setOrigin(mWidth/2, mHeight/2);
        return p;
    }
}
