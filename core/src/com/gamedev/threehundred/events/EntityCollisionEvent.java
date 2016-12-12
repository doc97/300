package com.gamedev.threehundred.events;

import com.gamedev.threehundred.entities.Entity;

public class EntityCollisionEvent implements Event {

	private final Entity e1;
	private final Entity e2;
	
	public EntityCollisionEvent(Entity e1, Entity e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public Entity getEntityOne() {
		return e1;
	}
	
	public Entity getEntityTwo() {
		return e2;
	}
}
