package com.ece290.mobileimagingasteroids;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MobileImagingAsteroids extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    Texture img2;
    Texture img3;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        img2 = new Texture("bg.png");
        img3 = new Texture("hd_flame.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
        batch.draw(img2, 500,500);
        batch.draw(img3, 800,800);
		batch.end();
	}
}
