package com.ece290.mobileimagingasteroids.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.ece290.mobileimagingasteroids.GameWorld;


/**
 * Screen to be displayed at endgame
 *
 * Created by Hallvard on 16.03.2015.
 */
public class EndGameScreen implements Screen {

    private GameWorld world;

    private SpriteBatch batch;
    private BitmapFont font;

    private TextButton button;
    private TextButton.TextButtonStyle textButtonStyle;
    //    private Skin skin;

    public EndGameScreen(GameWorld world) {
        this.world = world;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        //Restart game button
        textButtonStyle = new TextButton.TextButtonStyle();
   //     skin = new Skin();

        //Setting the style of the button
        BitmapFont textButtonFont = new BitmapFont();
        textButtonStyle.font = textButtonFont;
  /*      textButtonStyle.up = skin.getDrawable("up-button");
        textButtonStyle.down = skin.getDrawable("down-button");
        textButtonStyle.checked = skin.getDrawable("checked-button");
  */      button = new TextButton("Try again!", textButtonStyle);

        //Listener
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent e, Actor a) {
                Gdx.app.log("Test", "Button pressed! now reset game");

                //TODO reset game.
            }
        });
    }

    @Override
    public void render(float delta) {

        //Message
        String endGameText = "Game over/nYou managed a score of "
                   +world.getScore()+" \n Good job!\nPress the button to try again.";

        //Draw sprites using the spritebatch
        batch.begin();
        button.draw(batch, 1);
        font.setColor(1.0f,1.0f,1.0f,1.0f);
        font.draw(batch, endGameText, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        batch.end();
    }


    // ---- UNUSED (below)---- //

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
