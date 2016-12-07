package com.gamedev.threehundred.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.gamedev.threehundred.Game300;
import com.gamedev.threehundred.physics.Hitbox;

public class SpawnSystem {

	private Game300 game;
	private Vector2[] targets;
	private Vector2[] spawners;
	private Random rng;
	private float spawnDelaySec;
	private float timer;
	private float projectileSpeed;
	
	public SpawnSystem(Game300 game) {
		this.game = game;
		rng = new Random();
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
		Vector2 velocity = target.cpy().sub(spawn).setLength(projectileSpeed);
		float angleRad = (float) (Math.atan2(velocity.y, velocity.x) - Math.PI / 2.0f);
		float angleDeg = 180.0f * angleRad / (float) Math.PI;
		
		
		Projectile projectile = new Projectile(game);
		projectile.acceleration = Vector2.Zero;
		projectile.maxVelocity = projectileSpeed;
		projectile.velocity = new Vector2(velocity.x, velocity.y);
		projectile.position = new Vector2(spawn.x, spawn.y);
		projectile.texture = game.getAssetManager().get("badlogic.jpg", Texture.class);
		projectile.rotation = angleDeg;
		Rectangle box = new Rectangle(-4, -32, 8f, 64f);
		projectile.hitbox = new Hitbox(projectile.position, box, projectile);
		projectile.hitbox.rotate(projectile.rotation);
		game.getEntitySystem().addEntity(projectile);
		game.getPhysicsSystem().addObject(projectile.hitbox);
	}
	
	private Vector2 selectSpawner() {
		int spawnerIndex = rng.nextInt(spawners.length);
		return spawners[spawnerIndex];
	}
	
	private Vector2 selectTarget() {
		int targetIndex = rng.nextInt(targets.length);
		return targets[targetIndex];
	}
	
	public void setSpawners(Vector2[] spawners) {
		this.spawners = spawners;
	}
	
	public void setTargets(Vector2[] targets) {
		this.targets = targets;
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
