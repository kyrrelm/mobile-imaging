package com.ece290.mobileimagingasteroids.gameobject;

import com.badlogic.gdx.math.Polygon;
import com.ece290.mobileimagingasteroids.AssetLoader;

/**
 * Created by ethan_000 on 3/2/2015.
 */
public class Asteroid extends GameObject {

    public Asteroid(int width, int height, float positionX, float positionY, float velocityX, float velocityY)
    {
        super (width, height, positionX, positionY, velocityX, velocityY);
    }

    @Override
    protected Polygon getPolygonInternal() {
        //TODO fix polygon


        Polygon p = new Polygon(new float[]{
                (1f/3f)*mWidth,(7f/8f)*mHeight,
                (7f/12f)*mWidth,(11f/32f)*mHeight,
                (19f/20f)*mWidth,(17f/32f)*mHeight,
                (2f/3f)*mWidth,(7f/8f)*mHeight});
        p.setOrigin(mWidth/2, mHeight/2);
        return p;
    }
}
