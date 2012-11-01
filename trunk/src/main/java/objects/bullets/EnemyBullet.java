package objects.bullets;

import game.Registry;
import objects.SpriteType;

import components.collision.Collision;

/**
 * 
 * @author Ács Ádám
 *
 */
public class EnemyBullet extends Bullet{

	public EnemyBullet() {
		super();
	}

	@Override
	public SpriteType getSpriteType() {
		return SpriteType.ENEMY_BULLET;
	}
	
	@Override
	public boolean isColliding() {
		boolean colliding = false;
		
		if (!Collision.intersects(this, Registry.singleton().getTileRegistry()).isEmpty()) {
			colliding = true;
		}
		if (!Collision.intersects(this, Registry.singleton().getPlayerBulletRegistry()).isEmpty()) {
			colliding = true;
		}
		if (!Collision.intersects(this, Registry.singleton().getPlayerTankRegistry()).isEmpty()) {
			colliding = true;
		}
		return colliding || super.isColliding();
	}

}
