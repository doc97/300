package com.gamedev.threehundred.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.gamedev.threehundred.Game300;
import com.gamedev.threehundred.entities.Player;
import com.gamedev.threehundred.entities.Soldier;
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
		AssetManager assets = game.getAssetManager();
		if (!assets.isLoaded("Background.png", Texture.class))
			assets.load("Background.png", Texture.class);
		if (!assets.isLoaded("Arrow.png", Texture.class))
			assets.load("Arrow.png", Texture.class);
		if (!assets.isLoaded("Hero.png", Texture.class))
			assets.load("Hero.png", Texture.class);
		if (!assets.isLoaded("Soldier.png", Texture.class))
			assets.load("Soldier.png", Texture.class);
	}
	
	private void loadGame() {
		initializePlayer();
		initializeSoldiers();
		initializeSpawnSystem();
		initializeInputListeners();
	}

	private void initializePlayer() {
		Texture playerTexture = game.getAssetManager().get("Hero.png", Texture.class);
		if (playerTexture == null)
			throw new RuntimeException("Player texture not loaded");
		Player player = new Player(game, playerTexture);
		float size = 80;
		player.maxVelocity = 8;
		player.position.set(Gdx.graphics.getWidth() / 2, size / 2 + 180);
		player.hitbox = new Hitbox(player.position, new Rectangle(-size / 2, -size / 2, size, size), player);
		player.setImmune(true);
		game.getEntitySystem().addEntity(player);
		game.getPhysicsSystem().addObject(player.hitbox);
	}
	
	private void initializeSoldiers() {
		Texture soldierTexture = game.getAssetManager().get("Soldier.png");
		if (soldierTexture == null)
			throw new RuntimeException("Soldier texture not loaded");
		int count = 8;
		float size = 64;
		float totalWidth = Gdx.graphics.getWidth();
		float spawnAreaWidth = totalWidth * 4 / 5f;
		float spaceBetween = (spawnAreaWidth - (count * size)) / (count - 1);
		float start = (totalWidth - spawnAreaWidth) / 2;
		for (int i = 0; i < count; i++) {
			Soldier soldier = new Soldier(game, soldierTexture);
			soldier.position.set(start + i * (size + spaceBetween), 40);
			soldier.hitbox = new Hitbox(soldier.position, new Rectangle(-size / 2, -size / 2, size, size), soldier);
			game.getEntitySystem().addEntity(soldier);
			game.getPhysicsSystem().addObject(soldier.hitbox);
		}
	}
	
	private void initializeSpawnSystem() {
		game.getSpawnSystem().setProjectileSpeed(3);
		game.getSpawnSystem().setSpawnDelay(2f);
		
		List<Vector2> spawners = new ArrayList<Vector2>();
		spawners.add(new Vector2(0, Gdx.graphics.getHeight()));
		spawners.add(new Vector2(Gdx.graphics.getWidth() * 1 / 4, Gdx.graphics.getHeight()));
		spawners.add(new Vector2(Gdx.graphics.getWidth() * 2 / 4, Gdx.graphics.getHeight()));
		spawners.add(new Vector2(Gdx.graphics.getWidth() * 3 / 4, Gdx.graphics.getHeight()));
		spawners.add(new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		game.getSpawnSystem().setSpawners(spawners);
		
		List<Vector2> targets = new ArrayList<Vector2>();
		for (Soldier soldier : game.getEntitySystem().getSoldiers()) {
			targets.add(soldier.position.cpy().add(
					soldier.hitbox.getWidth() / 2,
					soldier.hitbox.getHeight() / 2));
		}
		
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
