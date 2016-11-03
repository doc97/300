package com.gamedev.threehundred.systems;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamedev.threehundred.entities.Entity;
import com.gamedev.threehundred.entities.Player;

public class EntitySystem {

	private Player player;
	private List<Entity> entities = new ArrayList<>();
	
	public void addEntity(Entity entity) {
		if (entity instanceof Player)
			player = (Player) entity;
		entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}
	
	public void update(float delta) {
		for (Entity entity : entities)
			entity.update(delta);
	}
	
	public void render(SpriteBatch batch) {
		for (Entity entity : entities)
			entity.render(batch);
	}

	public int getEntityCount() {
		return entities.size();
	}
	
	public Player getPlayer() {
		return player;
	}
}
