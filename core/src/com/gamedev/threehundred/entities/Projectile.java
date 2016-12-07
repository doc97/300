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
		batch.draw(texture,								// Texture
				position.x,								// Position X (centered)
				position.y,								// Position Y (centered)
				0,										// Origin X
				0,										// Origin Y
				hitbox.getWidth(), hitbox.getHeight(),	// Width, height
				1, 1,									// Scale X & Y
				rotation,								// Rotation in degrees
				0, 0,									// The whole texture
				texture.getWidth(),
				texture.getHeight(),
				false, false							// Flip X & Y
				);
	}
}
