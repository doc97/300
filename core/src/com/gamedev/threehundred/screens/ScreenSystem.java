package com.gamedev.threehundred.screens;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.gamedev.threehundred.Game300;

/**
 * Changes screens using a Finite State Machine
 * @author doc97
 * @see Screen
 */
public class ScreenSystem {
	private Map<Screens, Screen> screens;
	private Screens current;
	private Screens next = null;
	private Screens saved = null;
	private Game300 game;
	
	public ScreenSystem(Game300 game) {
		this.game = game;
		screens = new HashMap<Screens, Screen>();
	}
	
	/**
	 * Adds screens to the FSM
	 */
	public void initialize() {
		screens.put(Screens.LOADINGSCREEN, new LoadingScreen(game));
		screens.put(Screens.GAMESCREEN, new GameScreen(game));
	}
	
	/**
	 * Checks if it should change screens. Used when one wants to change screens using a flag.
	 * Either because it is not possible because one is in another thread,
	 * or because the change should not be immediate
	 */
	public void update() {
		if(next != null) {
			enterScreen(next);
			next = null;
		}
	}
	
	/**
	 * Changes screens with immediate effect.
	 * The last screen is exited and the new one entered.
	 * @param screen The enum id, for the screen to which it should change
	 */
	private void enterScreen(Screens screen) {
		Screen s = screens.get(screen);
		if(s != null) {
			game.setScreen(s);
			current = screen;
		} else {
			Gdx.app.log("ScreenSystem", "No screen with id: " + screen.toString());
		}
	}
	
	public void exitCurrentScreen() {
		game.setScreen(null);
	}
	
	public void setNextScreen(Screens screen) {
		next = screen;
	}
	
	public void setSavedScreen(Screens screen) {
		saved = screen;
	}
	
	public Screens getCurrentScreen() {
		return current;
	}
	
	public Screens getSavedScreen() {
		return saved;
	}
	
	public void dispose() {
		for(Screen s : screens.values())
			s.dispose();
	}
}
