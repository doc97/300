package com.gamedev.threehundred.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.gamedev.threehundred.entities.Player;

public class PlayerInput extends InputAdapter {

	private Player player;
	private int moveRight = Keys.D;
	private int moveLeft = Keys.A;
	private int keysDownCount;
	
	public PlayerInput(Player player) {
		this.player = player;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == moveRight)
			player.acceleration.set(2, 0);
		else if (keycode == moveLeft)
			player.acceleration.set(-2, 0);
		else
			return false;
		
		keysDownCount++;
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == moveRight)
			player.acceleration.set(-2, 0);
		else if (keycode == moveLeft)
			player.acceleration.set(2, 0);
		else
			return false;
		
		keysDownCount--;
		if (keysDownCount == 0) {
			player.acceleration.set(0, 0);
			player.velocity.set(0, 0);
		}
		return true;
	}
}
