package com.gamedev.threehundred.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.gamedev.threehundred.entities.Player;

public class PlayerInput extends InputAdapter {

	private Player player;
	private int moveRight = Keys.D;
	private int moveLeft = Keys.A;
	
	public PlayerInput(Player player) {
		this.player = player;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == moveRight)
			player.velocity.set(10, 0);
		else if (keycode == moveLeft)
			player.velocity.set(-10, 0);
		else
			return false;
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == moveRight && player.velocity.x > 0)
			player.velocity.set(0, 0);
		if (keycode == moveLeft && player.velocity.x < 0)
			player.velocity.set(0, 0);
		else
			return false;
		
		return true;
	}
}
