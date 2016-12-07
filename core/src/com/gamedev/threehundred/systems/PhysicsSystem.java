package com.gamedev.threehundred.systems;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.gamedev.threehundred.Game300;
import com.gamedev.threehundred.physics.Hitbox;

public class PhysicsSystem {

	private Game300 game;
	private List<Hitbox> objects = new ArrayList<>();
	private List<Hitbox> toBeRemoved = new ArrayList<>();
	
	public PhysicsSystem(Game300 game) {
		this.game = game;
	}
	
	public void addObject(Hitbox hitbox) {
		objects.add(hitbox);
	}
	
	public void preUpdate() {
		for (int i = 0; i < objects.size(); i++) {
			Hitbox hitbox1 = objects.get(i);
			Polygon p1 = hitbox1.getPolygon();
			p1.setPosition(hitbox1.getOwner().position.x, hitbox1.getOwner().position.y);
			for (int j = i + 1; j < objects.size(); j++) {
				Hitbox hitbox2 = objects.get(j);
				Polygon p2 = hitbox2.getPolygon();
				p2.setPosition(hitbox2.getOwner().position.x, hitbox2.getOwner().position.y);
				if (isColliding(p1, p2)) {
					if (!hitbox1.getOwner().isImmune())
						toBeRemoved.add(objects.get(i));
					if (!hitbox2.getOwner().isImmune())
						toBeRemoved.add(objects.get(j));
				}
			}
		}
	}
	
	public void update(float delta) {
		for (Hitbox box : toBeRemoved) {
			game.getEntitySystem().removeEntity(box.getOwner());
			objects.remove(box);
		}
		toBeRemoved.clear();
	}
	
	private boolean isColliding(Polygon p1, Polygon p2) {
		return Intersector.overlapConvexPolygons(p1, p2);
	}
}
