package com.gamedev.threehundred;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamedev.threehundred.screens.ScreenSystem;
import com.gamedev.threehundred.screens.Screens;

public class Game300 extends Game {
	
	private AssetManager manager;
	private ScreenSystem screenSys;
	private SpriteBatch batch;
	
	@Override
	public void create () {
		screenSys = new ScreenSystem(this);
		screenSys.initialize();
		screenSys.setNextScreen(Screens.LOADINGSCREEN);
		screenSys.setSavedScreen(Screens.GAMESCREEN);
		
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("badlogic.jpg", Texture.class);
		Texture.setAssetManager(manager);
	}

	@Override
	public void render () {
		screenSys.update();
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
	
	public AssetManager getAssetManager() {
		return manager;
	}
	
	public ScreenSystem getScreenSystem() {
		return screenSys;
	}
	
	public SpriteBatch getSpriteBatch() {
		return batch;
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
}
