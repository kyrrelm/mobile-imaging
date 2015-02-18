package com.ece290.mobileimagingasteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by ethan_000 on 2/15/2015.
 */
public class GameRenderer {
    private GameWorld world;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch mSpriteBatch;

    public GameRenderer(GameWorld world){
        this.world = world;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        mSpriteBatch = new SpriteBatch();
        mSpriteBatch.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }
    public void render() {
        Gdx.app.log("GameRenderer", "render");

        //Gdx.gl.glClearColor(1.0f, 1.0f, .5f, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mSpriteBatch.begin();
        AssetLoader.bgSprite.draw(mSpriteBatch);
        mSpriteBatch.end();

        // Tells shapeRenderer to begin drawing filled shapes
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Chooses RGB Color of 87, 109, 120 at full opacity
        shapeRenderer.setColor(87 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);

        // Draws the rectangle from myWorld (Using ShapeType.Filled)
        shapeRenderer.rect(world.getRect().x, world.getRect().y, world.getRect().width, world.getRect().height);

        // Tells the shapeRenderer to finish rendering
        // We MUST do this every time.
        shapeRenderer.end();
    }
}