package com.ece290.mobileimagingasteroids.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by ethan_000 on 2/13/2015.
 */
public class GameScreen implements Screen {

    private Game game;
    private OrthographicCamera camera;
    private float runTime;

    SpriteBatch batch;
    Texture img;
    Texture img2;



    public GameScreen(Game game)
    {
        Gdx.app.log("GameScreen", "Attached");
        this.game = game;


        /*camera = new OrthographicCamera();
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //camera.setToOrtho(true, 1920, 1080);

        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        img2 = new Texture("bg.png");*/
    }
    @Override
    public void render(float delta) {
        Gdx.app.log("GameScreen", "render");
        runTime += delta;
        //mWorld.update(delta, runTime);
        //mRenderer.render(runTime);
        Gdx.gl.glClearColor(1, .5f, .2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        /*Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.draw(img2, 500,500);
        batch.end();
*/
        //camera.update();
    }

    @Override
    public void dispose() {
        Gdx.app.log("GameScreen", "dispose");
        // TODO Auto-generated method stub
        //mRenderer.dispose();
        //mWorld.dispose();
        //game.setScreen(new GameOverScreen(game));
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide");
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume");
        // TODO Auto-generated method stub
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show");
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       /* batch.begin();
        batch.draw(img, 0, 0);
        batch.draw(img2, 500,500);
        batch.end();*/
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause");
        // TODO Auto-generated method stub

    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resize");
        // TODO Auto-generated method stub

    }
}
