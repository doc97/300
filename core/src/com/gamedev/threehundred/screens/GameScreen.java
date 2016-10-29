package com.gamedev.threehundred.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {

	private SpriteBatch batch;
	private Texture test;
	
	public GameScreen(SpriteBatch batch) {
		this.batch = batch;
		test = new Texture("badlogic.jpg");
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(test, 0, 0);
		batch.end();	
		System.out.println("yes");
	}
}
