package com.ece290.mobileimagingasteroids.gameobject;

import com.badlogic.gdx.math.Polygon;

/**
 * Created by ethan_000 on 2/17/2015.
 */
public class Ship extends GameObject {

    private final float COOLDOWN_TIME = 3; //TODO

    private float shotCooldown = 0;

    public Ship(int width, int height, float positionX, float positionY)
    {
        super (width, height, positionX, positionY);
    }

    public void shoot(float delta) {
        if(shotCooldown > 0)
            return;

        //TODO
        shotCooldown = COOLDOWN_TIME;
    }

    @Override
    public void update(float delta)
    {
        mVelocity.add(mAcceleration.cpy().scl(delta));
        //mPosition.add(mVelocity.cpy().scl(delta));
        mPosition.add(mVelocity.cpy().scl(delta).rotate(mRotation.x));
        mRotation.add(mRotationUpdate.cpy().scl(delta));

        if(shotCooldown > 0)
            shotCooldown -= delta;
    }

    @Override
    protected Polygon getPolygonInternal() {

        Polygon p = new Polygon(new float[]{
                (1f/2f)*mWidth,(1f/8f)*mHeight,
                (7f/8f)*mWidth,(2f/3f)*mHeight,
                (1f/2f)*mWidth,(7f/8f)*mHeight,
                (1f/8f)*mWidth,(2f/3f)*mHeight});
        p.setOrigin(mWidth/2, mHeight/2);
        return p;
    }
}
