package objects.units;

import java.awt.Graphics;

import math.Vector2;
import objects.*;

/**
 * 
 * @author Ács Ádám
 * 2012.07.31.
 */
public abstract class Unit extends Sprite {
	protected int health;
	protected int maxHealth;
	protected float speed;
	protected Direction direction = Direction.NORTH;

	public Unit(Vector2 pos) {
		super(pos);
	}
	
	@Override
	public void init() throws EInitException {
		super.init();
	}
	
	@Override
	public void update(long gameTime) {
		super.update(gameTime);
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}
	
	public void addToPos(Vector2 toAdd) {
		pos = pos.add(toAdd);
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.setMaxHealth(maxHealth);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isDead() {
		if (health <= 0) {
			return true;
		}

		return false;
	}
	
	public void move(Direction dir) {
		
	}

	public final void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}
}
