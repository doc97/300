package com.gamedev.threehundred.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.gamedev.threehundred.Game300;
import com.gamedev.threehundred.entities.Player;
import com.gamedev.threehundred.input.Inputs;
import com.gamedev.threehundred.input.PlayerInput;

public class LoadingScreen extends ScreenAdapter {

	private Game300 game;
	
	public LoadingScreen(Game300 game) {
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		boolean assetsDone = game.getAssetManager().update();
		Gdx.app.log("LoadingScreen", "Loading " + (game.getAssetManager().getProgress() * 100) + "%");
		if (!assetsDone) return;
		
		boolean hasLoaded = loadGame();
		if (hasLoaded) {
			game.getScreenSystem().setNextScreen(game.getScreenSystem().getSavedScreen());
			game.getScreenSystem().setSavedScreen(null);
		} else {
			Gdx.app.error("Loading Screen", "Failed to load game!");
			Gdx.app.exit();
		}
	}
	
	private boolean loadGame() {
		Texture playerTexture = game.getAssetManager().get("badlogic.jpg", Texture.class);
		if (playerTexture == null) return false;
		Player player = new Player(playerTexture);
		player.maxVelocity = 8;
		player.position.set(100, 0);
		game.getEntitySystem().addEntity(player);
		game.setPlayer(player);

		game.getInputSystem().register(Inputs.PLAYER, new PlayerInput(player), false);
		return true;
	}
}
