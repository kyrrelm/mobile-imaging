package com.ece290.mobileimagingasteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by ethan_000 on 2/15/2015.
 */
public class GameWorld {
    private Rectangle rect = new Rectangle(0, 0, 320, 240);
    public void update(float delta) {
        Gdx.app.log("GameWorld", "update");
        rect.x += 4;
        if (rect.x > Gdx.graphics.getWidth())
            rect.x = 0;
    }

    public Rectangle getRect() {
        return rect;
    }
}
