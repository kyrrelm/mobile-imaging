package com.ece290.mobileimagingasteroids.gameobject;


import com.badlogic.gdx.math.Polygon;

/**
 * Created by ingeborgoftedal on 06.03.15.
 */
public class Shot extends GameObject{
    public Shot(float positionX, float positionY, float velocityX, float velocityY){
        super(5, 5, positionX, positionY, velocityX, velocityY);
    }
    protected Polygon getPolygonInternal() {
        Polygon p = new Polygon(new float[]{
                0,0,
                mWidth,0,
                mWidth,mHeight,
                0, mHeight});

      /*  Polygon p = new Polygon(new float[]{
                (1f/3f)*mWidth,(7f/8f)*mHeight,
                (7f/12f)*mWidth,(11f/32f)*mHeight,
                (19f/20f)*mWidth,(17f/32f)*mHeight,
                (2f/3f)*mWidth,(7f/8f)*mHeight});
      */
        p.setOrigin(mWidth/2, mHeight/2);
        return p;
    }
}
