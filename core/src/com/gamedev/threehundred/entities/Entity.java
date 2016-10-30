package com.gamedev.threehundred.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	protected Vector2 position = new Vector2();
	protected Vector2 velocity = new Vector2();
	protected Texture texture;
	
	public abstract void update(float delta);
	public abstract void render(SpriteBatch batch);
	
	public void setPosition(Vector2 position) {
		this.position.set(position);
	}
	
	public void setPosition(float x, float y) {
		position.set(x, y);
	}
	
	public void setVelocity(Vector2 velocity) {
		this.velocity.set(velocity);
	}
	
	public void setVelocity(float dx, float dy) {
		velocity.set(dx, dy);
	}
}
