package com.gamedev.threehundred;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamedev.threehundred.entities.SpawnSystem;
import com.gamedev.threehundred.input.InputSystem;
import com.gamedev.threehundred.screens.ScreenSystem;
import com.gamedev.threehundred.screens.Screens;
import com.gamedev.threehundred.systems.EntitySystem;

public class Game300 extends Game {
	
	private AssetManager manager;
	private EntitySystem entitySys;
	private InputSystem inputSys;
	private ScreenSystem screenSys;
	private SpawnSystem spawnSys;
	private SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		entitySys = new EntitySystem();
		inputSys = new InputSystem();
		manager = new AssetManager();
		screenSys = new ScreenSystem(this);
		spawnSys = new SpawnSystem(this);
		Texture.setAssetManager(manager);
		
		screenSys.setNextScreen(Screens.LOADINGSCREEN);
		screenSys.setSavedScreen(Screens.GAMESCREEN);
	}
	
	
	@Override
	public void render () {
		screenSys.update();
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		manager.dispose();
		inputSys.dispose();
	}
	
	public AssetManager getAssetManager() {
		return manager;
	}

	public EntitySystem getEntitySystem() {
		return entitySys;
	}
	
	public InputSystem getInputSystem() {
		return inputSys;
	}
	
	public ScreenSystem getScreenSystem() {
		return screenSys;
	}
	
	public SpawnSystem getSpawnSystem() {
		return spawnSys;
	}
	
	public SpriteBatch getSpriteBatch() {
		return batch;
	}
}
