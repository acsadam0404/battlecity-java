package objects.units;

import java.awt.Graphics;

import math.Vector2;
import objects.*;

/**
 * Az olyan objektumok õsosztálya, amik tudnak mozogni és van hp-juk.
 * @author Ács Ádám
 * 2012.07.31.
 */
public abstract class Unit extends Sprite {
	protected int health;
	protected int maxHealth;
	protected float speed;
	protected Direction direction = Direction.NORTH;

	/**
	 * Létrehozza az objektumot a megadott pozícióra.
	 * @param pos
	 */
	public Unit(Vector2 pos) {
		super(pos);
	}
	
	/**
	 * Beolvassa a képeket.
	 */
	@Override
	public void init() throws EInitException {
		super.init();
	}
	
	/**
	 * Frissíti az objektumot.
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
	 * Hozzáad az objektumo pozíciójához egy vektort.
	 * @param toAdd
	 */
	public void addToPos(Vector2 toAdd) {
		pos = pos.add(toAdd);
	}

	/**
	 * Visszaadja a maximum életet.
	 * @return
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	/**
	 * Beállítja a maximum életet.
	 * @param maxHealth
	 */
	public void setMaxHealth(int maxHealth) {
		this.setMaxHealth(maxHealth);
	}

	/**
	 * Visszaadja a jelenlegi életet.
	 * @return
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Beállítja a jelenlegi életet.
	 * @param health
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * Megmondja az objektumról, hogy halott-e.
	 * @return
	 */
	public boolean isDead() {
		if (health <= 0) {
			return true;
		}

		return false;
	}
	
	/**
	 * Mozgatja az objektumot direction irányba.
	 * @param dir
	 */
	public void move(Direction dir) {
		
	}

	/**
	 * Beállítja a direction-t.
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
