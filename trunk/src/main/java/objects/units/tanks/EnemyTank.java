package objects.units.tanks;

import game.Registry;
import math.Vector2;

import components.collision.Collision;

/**
 * 
 * @author Ács Ádám
 *
 */
public abstract class EnemyTank extends Tank {
	public EnemyTank(Vector2 pos) {
		super(pos);
	}
	
	@Override
	public boolean checkCollision() {
		boolean colliding = false;
		if (!Collision.intersects(this, Registry.singleton().getPlayerBulletRegistry()).isEmpty()) {
			setState(State.EXPLODING);
			colliding = true;
		}
		
		return colliding;
	}

	public void kill() {
		setState(State.EXPLODING);
		Registry.singleton().unregister(this);
	}
}
