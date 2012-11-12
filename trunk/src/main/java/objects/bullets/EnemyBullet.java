package objects.bullets;

import java.util.List;

import game.Registry;
import objects.Sprite;
import objects.SpriteType;

import components.collision.Collision;

/**
 * Egy lövedék, ami a többi ellenséget nem bántja, csak a playerre van hatással.
 * 
 * @author Ács Ádám
 * 
 */
public class EnemyBullet extends Bullet {
	/**
	 * Létrehozza az objektumot.
	 */
	public EnemyBullet() {
		super();
	}

	/**
	 * Visszaadja az sprite típusát.
	 */
	@Override
	public SpriteType getSpriteType() {
		return SpriteType.ENEMY_BULLET;
	}

	/**
	 * Megmondja a lövedékrõl, hogy ütközik-e.
	 */
	@Override
	public boolean isColliding() {
		boolean colliding = false;

		if (!Collision.intersects(this,
				Registry.singleton().getPlayerBulletRegistry()).isEmpty()) {
			colliding = true;
		}
		if (!Collision.intersects(this,
				Registry.singleton().getPlayerTankRegistry()).isEmpty()) {
			colliding = true;
		}
		return colliding || super.isColliding();
	}

}
