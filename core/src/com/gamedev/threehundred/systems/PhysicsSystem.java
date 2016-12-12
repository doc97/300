package com.gamedev.threehundred.systems;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.gamedev.threehundred.Game300;
import com.gamedev.threehundred.entities.Entity;
import com.gamedev.threehundred.events.EntityCollisionEvent;
import com.gamedev.threehundred.events.Event;
import com.gamedev.threehundred.events.EventListener;
import com.gamedev.threehundred.physics.Hitbox;

public class PhysicsSystem implements EventListener {

	private Game300 game;
	private List<Hitbox> objects = new ArrayList<>();
	
	public PhysicsSystem(Game300 game) {
		this.game = game;
	}
	
	@Override
	public void process(Event event) {
		if (event instanceof EntityCollisionEvent) {
			Entity e1 = ((EntityCollisionEvent) event).getEntityOne();
			Entity e2 = ((EntityCollisionEvent) event).getEntityTwo();
			if (!e1.isImmune())
				removeObject(e1.hitbox);
			if (!e2.isImmune())
				removeObject(e2.hitbox);
		}
	}
	
	public void addObject(Hitbox hitbox) {
		objects.add(hitbox);
	}
	
	public void removeObject(Hitbox hitbox) {
		objects.remove(hitbox);
	}
	
	public void update(float delta) {
		for (int i = 0; i < objects.size(); i++) {
			Hitbox hitbox1 = objects.get(i);
			Polygon p1 = hitbox1.getPolygon();
			p1.setPosition(hitbox1.getOwner().position.x, hitbox1.getOwner().position.y);
			for (int j = i + 1; j < objects.size(); j++) {
				Hitbox hitbox2 = objects.get(j);
				Polygon p2 = hitbox2.getPolygon();
				p2.setPosition(hitbox2.getOwner().position.x, hitbox2.getOwner().position.y);
				if (isColliding(p1, p2)) {
					EntityCollisionEvent event = new EntityCollisionEvent(hitbox1.getOwner(), hitbox2.getOwner());
					game.getEventSystem().register(event);
				}
			}
		}
	}
	
	private boolean isColliding(Polygon p1, Polygon p2) {
		return Intersector.overlapConvexPolygons(p1, p2);
	}
}
