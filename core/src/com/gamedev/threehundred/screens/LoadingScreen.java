package com.gamedev.threehundred.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.gamedev.threehundred.Game300;

public class LoadingScreen extends ScreenAdapter {

	private Game300 game;
	
	public LoadingScreen(Game300 game) {
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		boolean hasFinished = game.getAssetManager().update();
		Gdx.app.log("LoadingScreen", "Loading " + (game.getAssetManager().getProgress() * 100) + "%");
		if (hasFinished) {
			game.getScreenSystem().setNextScreen(game.getScreenSystem().getSavedScreen());
			game.getScreenSystem().setSavedScreen(null);
		}
	}
}
