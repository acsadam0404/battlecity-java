package objects.bullets;

import game.Registry;

import java.util.List;

import objects.Sprite;
import objects.SpriteType;
import objects.tiles.GrassTile;
import objects.tiles.WaterTile;

import components.collision.Collision;

/**
 * 
 * @author Ács Ádám
 *
 */
public class PlayerBullet extends Bullet {
	public PlayerBullet() {
		super();
	}

	@Override
	public SpriteType getSpriteType() {
		return SpriteType.PLAYER_BULLET;
	}

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
