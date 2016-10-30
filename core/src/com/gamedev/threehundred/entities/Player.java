package com.gamedev.threehundred.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Entity {
	
	public Player(Texture texture) {
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
