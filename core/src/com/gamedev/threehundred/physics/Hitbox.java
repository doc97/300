package com.gamedev.threehundred.physics;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.gamedev.threehundred.entities.Entity;

public class Hitbox {

	private List<Hitbox> children = new ArrayList<>();
	private Rectangle box;
	private Polygon polygon;
	private Vector2 position;
	private Entity owner;
	
	public Hitbox(Vector2 position, Rectangle box, Entity owner) {
		this.owner = owner;
		this.box = box;
		this.position = position;
		polygon = new Polygon(new float[] { 0, 0, box.width, 0, box.width, box.height, 0, box.height});
	}
	
	public void addChild(Hitbox child) {
		children.add(child);
	}
	
	public void addPosition(Vector2 add) {
		this.position.add(position);
		for (Hitbox child : children) {
			child.addPosition(add);
		}
	}
	
	public void addPosition(float x, float y) {
		this.position.add(x, y);
		for (Hitbox child : children) {
			child.addPosition(x, y);
		}
	}
	
	public void rotate(float degrees) {
		polygon.rotate(degrees);
	}
	
	public float getWidth() {
		return box.width;
	}
	
	public float getHeight() {
		return box.height;
	}
	
	public Polygon getPolygon() {
		return polygon;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public Entity getOwner() {
		return owner;
	}
}
