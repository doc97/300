package com.gamedev.threehundred;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamedev.threehundred.screens.ScreenSystem;
import com.gamedev.threehundred.screens.Screens;
import com.gamedev.threehundred.systems.EntitySystem;

public class Game300 extends Game {
	
	private AssetManager manager;
	private EntitySystem entitySys;
	private ScreenSystem screenSys;
	private SpriteBatch batch;
	
	@Override
	public void create () {
		loadEngine();
		loadAssets();
	}
	
	private void loadEngine() {
		screenSys = new ScreenSystem(this);
		entitySys = new EntitySystem();
		batch = new SpriteBatch();
		manager = new AssetManager();
		Texture.setAssetManager(manager);
		
		screenSys.setNextScreen(Screens.LOADINGSCREEN);
		screenSys.setSavedScreen(Screens.GAMESCREEN);
	}
	
	private void loadAssets() {
		manager.load("badlogic.jpg", Texture.class);
	}

	@Override
	public void render () {
		screenSys.update();
		super.render();
	}
	
	@Override
	public void resume() {
		screenSys.setSavedScreen(screenSys.getCurrentScreen());
		screenSys.setNextScreen(Screens.LOADINGSCREEN);
		
		// Only Textures need to be reloaded after a resume
		Gdx.app.log("Game300", "Reloading assets");
		if (!manager.isLoaded("badlogic.jpg", Texture.class))
			manager.load("badlogic.jpg", Texture.class);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		manager.dispose();
	}
	
	public AssetManager getAssetManager() {
		return manager;
	}

	public EntitySystem getEntitySystem() {
		return entitySys;
	}
	
	public ScreenSystem getScreenSystem() {
		return screenSys;
	}
	
	public SpriteBatch getSpriteBatch() {
		return batch;
	}
}
