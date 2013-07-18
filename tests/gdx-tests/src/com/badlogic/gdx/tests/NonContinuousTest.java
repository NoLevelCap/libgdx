package com.badlogic.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.tests.utils.GdxTest;

public class NonContinuousTest extends GdxTest {

	OrthographicCamera cam;
	BitmapFont font;
	SpriteBatch batch;
	int clicks = 0;
	int renders = 0;
	
	public void create() {
		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		font = new BitmapFont();
		
		Gdx.graphics.setContinuousRendering(false);
		Gdx.graphics.requestRendering();
		
		Gdx.input.setInputProcessor(new InputAdapter() {
			
			@Override
			public boolean touchDown (int screenX, int screenY, int pointer, int button) {
				clicks++;
				Gdx.graphics.requestRendering();
				return true;
			}
		});
	}
	
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		font.draw(batch, "Click count: "+clicks, 200, 200);
		font.draw(batch, "Render count: "+renders, 200, 200-font.getLineHeight()-10);
		batch.end();
		renders++;
	}
	
	public void resize(int width, int height) {
		cam.setToOrtho(false);
		batch.setProjectionMatrix(cam.combined);
	}
}
