package com.gamedev.threehundred.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.gamedev.threehundred.Game300;
import com.gamedev.threehundred.events.EntityCollisionEvent;
import com.gamedev.threehundred.events.Event;
import com.gamedev.threehundred.events.EventListener;
import com.gamedev.threehundred.physics.Hitbox;

public class SpawnSystem implements EventListener {

	private final Game300 game;
	private final List<Vector2> targets;
	private final List<Vector2> spawners;
	private final Random rng;
	private float spawnDelaySec;
	private float timer;
	private float projectileSpeed;
	
	public SpawnSystem(Game300 game) {
		this.game = game;
		targets = new ArrayList<>();
		spawners = new ArrayList<>();
		rng = new Random();
	}
	
	@Override
	public void process(Event event) {
		if (event instanceof EntityCollisionEvent) {
			Entity e1 = ((EntityCollisionEvent) event).getEntityOne();
			Entity e2 = ((EntityCollisionEvent) event).getEntityTwo();
			float e1HalfWidth = e1.hitbox.getWidth() / 2;
			float e1HalfHeight = e1.hitbox.getHeight() / 2;
			float e2HalfWidth = e2.hitbox.getWidth() / 2;
			float e2HalfHeight = e2.hitbox.getHeight() / 2;
			if (e1 instanceof Soldier || e2 instanceof Soldier) {
				removeTarget(e1.position.cpy().add(e1HalfWidth, e1HalfHeight));
				removeTarget(e2.position.cpy().add(e2HalfWidth, e2HalfHeight));
			}
		}
	}
	
	public void update(float delta) {
		timer += delta;
		if (timer >= spawnDelaySec) {
			spawnProjectile();
			timer = 0;
		}
	}
	
	public void spawnProjectile() {
		Vector2 spawn = selectSpawner();
		Vector2 target = selectTarget();
		if (spawn == null || target == null) return;
		Vector2 velocity = target.cpy().sub(spawn).setLength(projectileSpeed);
		float angleRad = (float) (Math.atan2(velocity.y, velocity.x) - Math.PI / 2.0f);
		float angleDeg = 180.0f * angleRad / (float) Math.PI;
		
		Projectile projectile = new Projectile(game);
		projectile.acceleration = Vector2.Zero;
		projectile.maxVelocity = projectileSpeed;
		projectile.velocity = new Vector2(velocity.x, velocity.y);
		projectile.position = new Vector2(spawn.x, spawn.y);
		projectile.texture = game.getAssetManager().get("Arrow.png", Texture.class);
		projectile.rotation = angleDeg;
		Rectangle box = new Rectangle(-5, -40, 10, 80);
		projectile.hitbox = new Hitbox(projectile.position, box, projectile);
		projectile.hitbox.rotate(projectile.rotation);
		game.getEntitySystem().addEntity(projectile);
		game.getPhysicsSystem().addObject(projectile.hitbox);
	}
	
	private Vector2 selectSpawner() {
		if (spawners.isEmpty()) return null;
		int spawnerIndex = rng.nextInt(spawners.size());
		return spawners.get(spawnerIndex);
	}
	
	private Vector2 selectTarget() {
		if (targets.isEmpty()) return null;
		int targetIndex = rng.nextInt(targets.size());
		return targets.get(targetIndex);
	}
	
	private void removeTarget(Vector2 position) {
		for (Iterator<Vector2> it = targets.iterator(); it.hasNext(); ) {
			Vector2 pos = it.next();
			if (pos.x == position.x && pos.y == position.y) {
				it.remove();
			}
		}
	}
	
	public void setSpawners(List<Vector2> spawners) {
		this.spawners.clear();
		this.spawners.addAll(spawners);
	}
	
	public void setTargets(List<Vector2> targets) {
		this.targets.clear();
		this.targets.addAll(targets);
	}
	
	public void setSpawnDelay(float seconds) {
		spawnDelaySec = seconds;
	}
	
	public void setProjectileSpeed(float speed) {
		projectileSpeed = speed;
	}
	
	public float getSpawnDelay() {
		return spawnDelaySec;
	}
	
	public float getProjectileSpeed() {
		return projectileSpeed;
	}
}
