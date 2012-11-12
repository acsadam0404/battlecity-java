package objects.units.tanks;

import game.Registry;
import math.Vector2;

import components.collision.Collision;

/**
 * Az ellenséges tankok õsosztálya. Az ellenséges tankok lövedékei nem hatnak egymásra, és mindegyik a player-t próbálja kilõni.
 * @author Ács Ádám
 *
 */
public abstract class EnemyTank extends Tank {
	/**
	 * Létrehozza az objektumot adott pozícióra.
	 * @param pos
	 */
	public EnemyTank(Vector2 pos) {
		super(pos);
	}
	
	/**
	 * Vizsgálja az ütközéseket.
	 */
	@Override
	public boolean checkCollision() {
		boolean colliding = false;
		if (!Collision.intersects(this, Registry.singleton().getPlayerBulletRegistry()).isEmpty()) {
			setState(State.EXPLODING);
			colliding = true;
			Registry.singleton().unregister(this);
		}
		
		return colliding;
	}

	/**
	 * Megöli a tankot.
	 */
	public void kill() {
		setState(State.EXPLODING);
		Registry.singleton().unregister(this);
	}
}
