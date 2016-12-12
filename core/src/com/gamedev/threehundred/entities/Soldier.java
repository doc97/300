package com.gamedev.threehundred.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamedev.threehundred.Game300;

public class Soldier extends Entity {

	public Soldier(Game300 game, Texture texture) {
		super(game);
		this.texture = texture;
	}

	public void update(float delta) {
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(texture,
				position.x,
				position.y,
				hitbox.getWidth(), hitbox.getHeight());
	}
}
