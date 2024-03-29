package objects.tiles;

import game.Registry;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import objects.EInitException;
import objects.Sprite;
import objects.SpriteType;
import objects.bullets.Bullet;

import components.collision.Collision;

/**
 * Betonfal, amin csak a superbullet k�pes �tt�rni.
 * @author �cs �d�m
 *
 */
public class ConcreteWallTile extends WallTile {
	/**
	 * L�trehozza az objektumot adott poz�ci�ra.
	 * @param posX
	 * @param posY
	 */
	public ConcreteWallTile(int posX, int posY) {
		super(posX, posY);
	}

	/**
	 * Visszaadja a sprite t�pus�t.
	 */
	@Override
	public SpriteType getSpriteType() {
		return SpriteType.CONCRETEWALL_TILE;
	}

	/**
	 * Beolvassa a k�peket.
	 */
	@Override
	public void init() throws EInitException {
		useAnimation = false;
		super.init();
		try {
			wholeImage = ImageIO.read(new File("images\\fal2.png"));

		} catch (IOException ioEx) {
			throw new EInitException(ioEx);
		}
	}

	/**
	 * Ellen�rzi az �tk�z�seket.
	 */
	@Override
	public List<Rectangle> checkCollision() {
		if (isCollidable()) {
			List<Rectangle> rects = boundingPart.getRects();
			for (int i = rects.size() - 1; i >= 0; i--) {
				Rectangle rect = rects.get(i);
				if (isCollidable() && (!Collision.intersects(rect, Registry.singleton().getPlayerBulletRegistry()).isEmpty() ||
						!Collision.intersects(rect, Registry.singleton().getEnemyBulletRegistry()).isEmpty())) {

					/* csak a super bulletek �rthatnak a betonfalnak */
					List<Sprite> enemyBullets = Registry.singleton().getEnemyBulletRegistry();
					List<Sprite> playerBullets = Registry.singleton().getPlayerBulletRegistry();

					for (Sprite sprite : playerBullets) {
						if (((Bullet) sprite).isSuperBullet()) {
							collidingRects.add(rect);
						}
					}

					for (Sprite sprite : enemyBullets) {
						if (((Bullet) sprite).isSuperBullet()) {
							collidingRects.add(rect);
						}
					}
				}
			}
		}

		for (Rectangle rect : collidingRects) {
			images.remove(rect);
		}

		return collidingRects;
	}

}
