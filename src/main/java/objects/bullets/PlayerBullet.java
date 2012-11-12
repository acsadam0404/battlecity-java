package objects.bullets;

import game.Registry;

import java.util.List;

import objects.Sprite;
import objects.SpriteType;
import objects.tiles.GrassTile;
import objects.tiles.WaterTile;

import components.collision.Collision;

/**
 * Egy l�ved�k amit a j�t�kos haszn�l, �s a j�t�kosra nincs hat�ssal.
 * @author �cs �d�m
 *
 */
public class PlayerBullet extends Bullet {
	/**
	 * L�trehozza a l�ved�ket.
	 */
	public PlayerBullet() {
		super();
	}

	/**
	 * Visszaadja a sprite t�pus�t.
	 */
	@Override
	public SpriteType getSpriteType() {
		return SpriteType.PLAYER_BULLET;
	}

	/**
	 * Megmondja a l�ved�kr�l, hogy �tk�zik-e.
	 */
	@Override
	public boolean isColliding() {
		boolean colliding = false;

		List<Sprite> collidingSprites = Collision.intersects(this, Registry.singleton().getTileRegistry());
		for (Sprite sp : collidingSprites) {
			if (!(sp instanceof WaterTile) && !(sp instanceof GrassTile) ) {
				colliding = true;
			}
		}
		if (!Collision.intersects(this, Registry.singleton().getEnemyBulletRegistry()).isEmpty()) {
			colliding = true;
		}
		if (!Collision.intersects(this, Registry.singleton().getEnemyTankRegistry()).isEmpty()) {
			colliding = true;
		}
		return colliding || super.isColliding();
	}

}
