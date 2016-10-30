package com.gamedev.threehundred.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	public Vector2 position = new Vector2();
	public Vector2 velocity = new Vector2();
	protected Texture texture;
	
	public abstract void update(float delta);
	public abstract void render(SpriteBatch batch);
}
