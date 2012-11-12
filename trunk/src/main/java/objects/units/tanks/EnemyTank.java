package objects.units.tanks;

import game.Registry;
import math.Vector2;

import components.collision.Collision;

/**
 * Az ellens�ges tankok �soszt�lya. Az ellens�ges tankok l�ved�kei nem hatnak egym�sra, �s mindegyik a player-t pr�b�lja kil�ni.
 * @author �cs �d�m
 *
 */
public abstract class EnemyTank extends Tank {
	/**
	 * L�trehozza az objektumot adott poz�ci�ra.
	 * @param pos
	 */
	public EnemyTank(Vector2 pos) {
		super(pos);
	}
	
	/**
	 * Vizsg�lja az �tk�z�seket.
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
	 * Meg�li a tankot.
	 */
	public void kill() {
		setState(State.EXPLODING);
		Registry.singleton().unregister(this);
	}
}
