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
        //TODO fix polygon for different asteroid shapes

        Polygon p = new Polygon(new float[]{
                (1f/4f)*mWidth,(1f/8f)*mHeight,
                (7f/9f)*mWidth,(1f/6f)*mHeight,
                (7f/8f)*mWidth,(7f/8f)*mHeight,
                (1f/8f)*mWidth,(7f/8f)*mHeight});
        p.setOrigin(mWidth/2, mHeight/2);
        return p;
    }
}
