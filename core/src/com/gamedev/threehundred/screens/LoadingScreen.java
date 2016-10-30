package com.gamedev.threehundred.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.gamedev.threehundred.Game300;
import com.gamedev.threehundred.entities.Player;

public class LoadingScreen extends ScreenAdapter {

	private Game300 game;
	
	public LoadingScreen(Game300 game) {
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		boolean assetsDone = game.getAssetManager().update();
		Gdx.app.log("LoadingScreen", "Loading " + (game.getAssetManager().getProgress() * 100) + "%");
		if (assetsDone) {
			loadGame();
			game.getScreenSystem().setNextScreen(game.getScreenSystem().getSavedScreen());
			game.getScreenSystem().setSavedScreen(null);
		}
	}
	
	private void loadGame() {
		Texture playerTexture = game.getAssetManager().get("badlogic.jpg", Texture.class);
		if (playerTexture == null) return;
		Player player = new Player(playerTexture);
		player.setPosition(100, 0);
		game.getEntitySystem().addEntity(player);
	}
}
