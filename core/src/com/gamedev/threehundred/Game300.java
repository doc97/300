package com.gamedev.threehundred;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamedev.threehundred.screens.ScreenSystem;
import com.gamedev.threehundred.screens.Screens;

public class Game300 extends Game {
	
	private ScreenSystem screenSys;
	SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		screenSys = new ScreenSystem(this);
		screenSys.initialize(batch);
		screenSys.setScreenToEnter(Screens.GAMESCREEN);
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
}
