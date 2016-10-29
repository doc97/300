package com.gamedev.threehundred.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.gamedev.threehundred.Game300;

public class GameScreen extends ScreenAdapter {

	private Game300 game;
	
	public GameScreen(Game300 game) {
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.getSpriteBatch().begin();
		game.getSpriteBatch().draw(game.getAssetManager().get("badlogic.jpg", Texture.class), 0, 0);
		game.getSpriteBatch().end();
	}
}
