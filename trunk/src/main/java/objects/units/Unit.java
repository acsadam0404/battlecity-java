package objects.units;

import java.awt.Graphics;

import math.Vector2;
import objects.*;

/**
 * Az olyan objektumok �soszt�lya, amik tudnak mozogni �s van hp-juk.
 * @author �cs �d�m
 * 2012.07.31.
 */
public abstract class Unit extends Sprite {
	protected int health;
	protected int maxHealth;
	protected float speed;
	protected Direction direction = Direction.NORTH;

	/**
	 * L�trehozza az objektumot a megadott poz�ci�ra.
	 * @param pos
	 */
	public Unit(Vector2 pos) {
		super(pos);
	}
	
	/**
	 * Beolvassa a k�peket.
	 */
	@Override
	public void init() throws EInitException {
		super.init();
	}
	
	/**
	 * Friss�ti az objektumot.
	 */
	@Override
	public void update(long gameTime) {
		super.update(gameTime);
	}

	/**
	 * Kirajzolja az objektumot.
	 */
	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}
	
	/**
	 * Hozz�ad az objektumo poz�ci�j�hoz egy vektort.
	 * @param toAdd
	 */
	public void addToPos(Vector2 toAdd) {
		pos = pos.add(toAdd);
	}

	/**
	 * Visszaadja a maximum �letet.
	 * @return
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	/**
	 * Be�ll�tja a maximum �letet.
	 * @param maxHealth
	 */
	public void setMaxHealth(int maxHealth) {
		this.setMaxHealth(maxHealth);
	}

	/**
	 * Visszaadja a jelenlegi �letet.
	 * @return
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Be�ll�tja a jelenlegi �letet.
	 * @param health
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * Megmondja az objektumr�l, hogy halott-e.
	 * @return
	 */
	public boolean isDead() {
		if (health <= 0) {
			return true;
		}

		return false;
	}
	
	/**
	 * Mozgatja az objektumot direction ir�nyba.
	 * @param dir
	 */
	public void move(Direction dir) {
		
	}

	/**
	 * Be�ll�tja a direction-t.
	 * @param direction
	 */
	public final void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Visszaadja a direction-t.
	 * @return
	 */
	public Direction getDirection() {
		return direction;
	}
}
