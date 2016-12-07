package com.gamedev.threehundred.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.gamedev.threehundred.Game300;
import com.gamedev.threehundred.entities.Player;
import com.gamedev.threehundred.input.Inputs;
import com.gamedev.threehundred.input.PlayerInput;
import com.gamedev.threehundred.physics.Hitbox;

public class LoadingScreen extends ScreenAdapter {

	private Game300 game;
	
	public LoadingScreen(Game300 game) {
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		boolean assetsDone = game.getAssetManager().update();
		float progress = game.getAssetManager().getProgress() * 100;
		Gdx.app.log("LoadingScreen", "Loading " + progress+ "%");
		if (!assetsDone) return;
		
		loadGame();
		Screens saved = game.getScreenSystem().getSavedScreen();
		game.getScreenSystem().setNextScreen(saved);
		game.getScreenSystem().setSavedScreen(null);
	}

	@Override
	public void show() {
		loadAssets();
	}
	
	private void loadAssets() {
		if (!game.getAssetManager().isLoaded("badlogic.jpg", Texture.class))
			game.getAssetManager().load("badlogic.jpg", Texture.class);
	}
	
	private void loadGame() {
		initializePlayer();
		initializeSpawnSystem();
		initializeInputListeners();
	}

	private void initializePlayer() {
		Texture playerTexture = game.getAssetManager().get("badlogic.jpg", Texture.class);
		if (playerTexture == null)
			throw new RuntimeException("Player texture not loaded");
		Player player = new Player(game, playerTexture);
		float size = 120;
		player.maxVelocity = 8;
		player.position.set(100, size / 2);
		player.hitbox = new Hitbox(player.position, new Rectangle(-size / 2, -size / 2, size, size), player);
		player.setImmune(true);
		game.getEntitySystem().addEntity(player);
		game.getPhysicsSystem().addObject(player.hitbox);
	}
	
	private void initializeSpawnSystem() {
		game.getSpawnSystem().setProjectileSpeed(2);
		game.getSpawnSystem().setSpawnDelay(2f);
		
		Vector2[] spawners = new Vector2[] {
			new Vector2(0, Gdx.graphics.getHeight()),
			new Vector2(Gdx.graphics.getWidth() * 1 / 4, Gdx.graphics.getHeight()),
			new Vector2(Gdx.graphics.getWidth() * 2 / 4, Gdx.graphics.getHeight()),
			new Vector2(Gdx.graphics.getWidth() * 3 / 4, Gdx.graphics.getHeight()),
			new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())
		};
		game.getSpawnSystem().setSpawners(spawners);
		
		Vector2[] targets = new Vector2[] {
			new Vector2(0, 0),
			new Vector2(Gdx.graphics.getWidth() / 2, 0),
			new Vector2(Gdx.graphics.getWidth(), 0)
		};
		game.getSpawnSystem().setTargets(targets);
	}
	
	private void initializeInputListeners() {
		Player player = game.getEntitySystem().getPlayer();
		if (player == null)
			throw new IllegalStateException("Player must be initialized before its listener");
		PlayerInput pInput = new PlayerInput(player);
		game.getInputSystem().register(Inputs.PLAYER, pInput, false);
	}
}
