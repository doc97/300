package com.gamedev.threehundred.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.gamedev.threehundred.Game300;
import com.gamedev.threehundred.input.Inputs;

public class GameScreen extends ScreenAdapter {

	private Game300 game;
	
	public GameScreen(Game300 game) {
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		// Update
		game.getSpawnSystem().update(delta);
		game.getEntitySystem().update(delta);
		game.getPhysicsSystem().preUpdate();
		game.getPhysicsSystem().update(delta);
		
		// Render
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.getSpriteBatch().begin();
		game.getEntitySystem().render(game.getSpriteBatch());
		game.getSpriteBatch().end();
	}

	@Override
	public void show() {
		game.getInputSystem().add(Inputs.PLAYER);
	}

	@Override
	public void hide() {
		game.getInputSystem().remove(Inputs.PLAYER);
	}
}
