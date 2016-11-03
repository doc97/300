package com.gamedev.threehundred.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamedev.threehundred.Game300;

public class Projectile extends Entity {

	public Projectile(Game300 game) {
		super(game);
	}
	
	@Override
	public void update(float delta) {
		position.add(velocity.cpy().scl(delta * 100));
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(texture, position.x, position.y, texture.getWidth() / 4.0f, texture.getHeight() / 4.0f);
	}
}
