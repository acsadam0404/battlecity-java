package objects.bullets;

import java.util.List;

import game.Registry;
import objects.Sprite;
import objects.SpriteType;

import components.collision.Collision;

/**
 * Egy l�ved�k, ami a t�bbi ellens�get nem b�ntja, csak a playerre van hat�ssal.
 * 
 * @author �cs �d�m
 * 
 */
public class EnemyBullet extends Bullet {
	/**
	 * L�trehozza az objektumot.
	 */
	public EnemyBullet() {
		super();
	}

	/**
	 * Visszaadja az sprite t�pus�t.
	 */
	@Override
	public SpriteType getSpriteType() {
		return SpriteType.ENEMY_BULLET;
	}

	/**
	 * Megmondja a l�ved�kr�l, hogy �tk�zik-e.
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
