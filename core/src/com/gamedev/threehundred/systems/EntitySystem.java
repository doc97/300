package com.gamedev.threehundred.systems;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamedev.threehundred.entities.Entity;
import com.gamedev.threehundred.entities.Player;
import com.gamedev.threehundred.entities.Projectile;
import com.gamedev.threehundred.entities.Soldier;

public class EntitySystem {

	private Player player;
	private List<Soldier> soldiers = new ArrayList<>();
	private List<Projectile> projectiles = new ArrayList<>();
	private List<Entity> entities = new ArrayList<>();
	
	public void addEntity(Entity entity) {
		if (entity instanceof Player)
			player = (Player) entity;
		else if (entity instanceof Projectile)
			projectiles.add((Projectile) entity);
		else if (entity instanceof Soldier)
			soldiers.add((Soldier) entity);
		entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		if (entity instanceof Projectile)
			projectiles.remove((Projectile) entity);
		else if (entity instanceof Soldier)
			soldiers.remove((Soldier) entity);
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
	
	public final List<Soldier> getSoldiers() {
		return new ArrayList<>(soldiers);
	}
	
	public final List<Projectile> getProjectiles() {
		return new ArrayList<>(projectiles);
	}

	public int getTotalEntityCount() {
		return entities.size();
	}
	
	public int getProjectileCount() {
		return projectiles.size();
	}
	
	public int getSoldierCount() {
		return soldiers.size();
	}
	
	public Player getPlayer() {
		return player;
	}
}
