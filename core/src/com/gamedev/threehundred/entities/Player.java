package com.gamedev.threehundred.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamedev.threehundred.Game300;

public class Player extends Entity {
	
	public Player(Game300 game, Texture texture) {
		super(game);
		this.texture = texture;
	}
	
	public void update(float delta) {
		velocity.add(acceleration).limit(maxVelocity);
		position.add(velocity);
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(texture, position.x, position.y);
	}
}
