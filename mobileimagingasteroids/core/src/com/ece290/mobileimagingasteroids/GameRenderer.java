package com.ece290.mobileimagingasteroids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.ece290.mobileimagingasteroids.controls.TouchGestureListener;
import com.ece290.mobileimagingasteroids.gameobject.Asteroid;
import com.ece290.mobileimagingasteroids.gameobject.Ship;
import com.ece290.mobileimagingasteroids.gameobject.Shot;

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

        GestureDetector gd = new GestureDetector(new TouchGestureListener());
        Gdx.input.setInputProcessor(gd);
    }
    public void render() {
        //Gdx.app.log("GameRenderer", "render");

        //Gdx.gl.glClearColor(1.0f, 1.0f, .5f, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mSpriteBatch.begin();
        AssetLoader.bgSprite.draw(mSpriteBatch);
        //mSpriteBatch.draw(AssetLoader.shipTexture, world.getShip().getX(), world.getShip().getY());
        Ship ship = world.getShip();
        mSpriteBatch.draw(AssetLoader.shipSprite, ship.getX(), ship.getY(),ship.getWidth()/2,ship.getHeight()/2,ship.getWidth(),ship.getHeight(),1,1,ship.getRotation());
        //Gdx.app.log("GameRnderer", "rotation:"+ship.getRotation());


        for (Asteroid a : world.getAsteroids())
        {
            mSpriteBatch.draw(AssetLoader.asteroidSprite, a.getX(), a.getY(),a.getWidth()/2,a.getHeight()/2,a.getWidth(),a.getHeight(),1,1,a.getRotation());
        }


        mSpriteBatch.end();


        /*shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(87 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);
        shapeRenderer.rect(world.getRect().x, world.getRect().y, world.getRect().width, world.getRect().height);
        shapeRenderer.end();*/

        //Draw Polygons
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.CYAN);
        shapeRenderer.polygon(ship.getPolygon().getTransformedVertices());
        for(Asteroid a : world.getAsteroids())
            shapeRenderer.polygon(a.getPolygon().getTransformedVertices());

        for(Shot s: world.getShots())
            shapeRenderer.polygon(s.getPolygon().getTransformedVertices());

        shapeRenderer.end();

    }
}
