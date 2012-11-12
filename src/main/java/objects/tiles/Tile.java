package objects.tiles;

import game.Config;
import game.IResetable;
import game.Registry;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import math.Vector2;
import objects.EInitException;
import objects.Sprite;

import components.animation.Animation;
import components.collision.BoundingPart;
import components.collision.Collision;

/**
 * Az oszt�ly a t�rk�pen el�fordul� akad�lyok alapjak�nt szolg�l. 
 * Az akad�lyok megjelennek a t�rk�pen (anim�l�dhatnak), a t�bbi objektum �tk�zhet vel�k.
 * 
 * @author �cs �d�m
 * 2012.07.22.
 */
public abstract class Tile extends Sprite implements IResetable {
	protected BoundingPart boundingPart;
	protected List<Rectangle> collidingRects = new ArrayList<Rectangle>();

	public Tile(int posX, int posY) {
		super(new Vector2(posX, posY));
		this.size = new Vector2(Config.TILE_WIDTH, Config.TILE_HEIGHT);
	}

	/**
	 * H�nyadik r�tegen van a sprite. Ez hasznos, hogy egym�st elfed� tile-okat haszn�lhassunk.
	 * 
	 */
	public abstract int getLayerNumber();

	/**
	 * K�pek beolvas�sa �s �tk�z� r�sz be�ll�t�sa.
	 */
	@Override
	public void init() throws EInitException {
		boundingPart = new BoundingPart(new Rectangle(pos.getX(), pos.getY(), size.getX(), size.getY()));
		super.init();
	}

	/**
	 * Tile kirajzol�sa.
	 */
	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}

	/**
	 * Tile string reprezent�ci�ja.
	 */
	@Override
	public String toString() {
		return "tile: " + getPos().toString();
	}

	/**
	 * Tile friss�t�se.
	 */
	@Override
	public void update(long gameTime) {
		for (int i = collidingRects.size() - 1; i >= 0; i--) {
			Rectangle rect = collidingRects.get(i);
			boundingPart.removeRect(rect);
		}
		collidingRects.clear();

		super.update(gameTime);
		checkCollision();
	}

	/**
	 * �tk�z�sek vizsg�lata.
	 * @return
	 */
	public List<Rectangle> checkCollision() {
		if (isCollidable()) {
			List<Rectangle> rects = boundingPart.getRects();
			for (int i = rects.size() - 1; i >= 0; i--) {
				Rectangle rect = rects.get(i);
				if (isCollidable() && !Collision.intersects(rect, Registry.singleton().getPlayerBulletRegistry()).isEmpty() ||
						isCollidable() && !Collision.intersects(rect, Registry.singleton().getEnemyBulletRegistry()).isEmpty()
						&& !isPassableByBullet()) {
					collidingRects.add(rect);

				}
			}
		}

		return collidingRects;
	}

	/**
	 * Visszaadja az �tk�z� r�szt.
	 */
	@Override
	public BoundingPart getBoundingPart() {
		if (isCollidable()) {
			return boundingPart;
		}

		return new BoundingPart(Config.OFFSCREEN_RECT);
	}

	/**
	 * Be�ll�tja a tile m�ret�t.
	 */
	@Override
	protected Vector2 initSize() {
		return new Vector2(Config.TILE_WIDTH, Config.TILE_HEIGHT);
	}

	/**
	 * Be�ll�tja az oszt�lyhoz tartoz� anim�ci�kat.
	 */
	@Override
	protected Map<String, Animation> initClassAnimations() {
		return null;
	}

	/**
	 * Ha a boundingPart-ot m�sk�pp haszn�lja egy aloszt�ly, fel�l kell ezt �rnia a norm�lis viselked�shez.
	 */
	@Override
	public void reset() {
		boundingPart = new BoundingPart(new Rectangle(pos.getX(), pos.getY(), size.getX(), size.getY()));
	}
}
