package com.ece290.mobileimagingasteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by ethan_000 on 2/17/2015.
 */
public class AssetLoader{
    public static Texture bgTexture;
    public static Sprite bgSprite;
    public static Texture shipTexture;
    public static Sprite shipSprite;

    public static void load()
    {
        bgTexture = new Texture(Gdx.files.internal("bg.png"));
        bgSprite = new Sprite(bgTexture, 0, 0, bgTexture.getWidth(), bgTexture.getHeight());
        bgSprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        shipTexture = new Texture(Gdx.files.internal("ship1.png"));
        shipSprite = new Sprite(shipTexture);
        shipSprite.flip(false,true);
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        bgTexture.dispose();
        shipTexture.dispose();
    }
}
