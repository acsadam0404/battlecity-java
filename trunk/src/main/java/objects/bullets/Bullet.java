package objects.bullets;

import game.Config;
import game.Registry;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import math.Vector2;
import objects.Direction;
import objects.EInitException;
import objects.Sprite;

import components.animation.Animation;
import components.animation.AnimationFactory;
import components.collision.BoundingPart;
import components.collision.Collision;

/**
 * A l�ved�kek �soszt�lya. A l�ved�k lehet superbullet, �gy a betonfalat is �tt�ri. A l�ved�kek �tmennek a v�z f�l�tt, bele�tk�znek a falba, �s �tmennek a f� alatt.
 * A l�ved�kek konstans sebess�ggel haladnak a kil�v�s id�pontj�ban meghat�rozott ir�nyba.
 * @author �cs �d�m
 *
 */
public abstract class Bullet extends Sprite {
	protected Direction direction;
	protected float speed = 5f;
	protected boolean colliding = false;
	protected boolean superBullet = false;

	/**
	 * L�trehozza a l�ved�ket a k�perny�n k�v�lre.
	 */
	public Bullet() {
		super(Config.OFFSCREEN);
	}

	/**
	 * Beolvassa a l�ved�k k�p�t.
	 */
	@Override
	public void init() throws EInitException {
		super.init();
	}

	/**
	 * Friss�ti a l�ved�ket.
	 */
	@Override
	public void update(long gameTime) {
		pos = pos.add(Vector2.multiply(direction.getVector2(), speed));

		super.update(gameTime);
	}

	/**
	 * Kirazjolja a l�ved�ket.
	 */
	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}

	/**
	 * Visszaadja a l�ved�k poz�ci�j�t.
	 */
	@Override
	public Vector2 getPos() {
		return pos;
	}

	/**
	 * Kil�vi a l�ved�ket adott ir�nyba.
	 * @param direction
	 */
	public void fire(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Be�ll�tja a l�ved�k m�ret�t.
	 */
	@Override
	protected Vector2 initSize() {
		return new Vector2(Config.BULLET_WIDTH, Config.BULLET_HEIGHT);
	}

	/**
	 * Be�ll�tja a l�ved�k anim�ci�it.
	 */
	@Override
	protected Map<String, Animation> initClassAnimations() {
		Map<String, Animation> anims = new HashMap<String, Animation>();
		anims.put("base", AnimationFactory.createAnimation(this, "animations\\bullet.xml", 1000));
		return anims;
	}

	/**
	 * Visszaadja az �tk�z� r�szeket.
	 */
	@Override
	public BoundingPart getBoundingPart() {
		/* -4 ek az�rt kellenek, mert a k�p kisebb mint a boundingpart */ /* FIXME tesztelni */
		return new BoundingPart(new Rectangle(pos.getX() - 4, pos.getY() - 4, size.getX(), size.getY()));
	}

	/**
	 * Megmondja egy l�ved�kr�l, hogy �tk�zik-e.
	 * @return
	 */
	public boolean isColliding() {
		List<Sprite> intersectingRectangles = Collision.intersects(this, Registry.singleton().getTileRegistry());
		for (Sprite tile : intersectingRectangles) {
			if (!tile.isPassableByBullet()) {
				colliding = true;
			}
		}
		
		if (Collision.isOutOfScreen(this)) {
			colliding = true;
		}
		return colliding;
		
		
	}

	/**
	 * Be�ll�tja a l�ved�ket az alap�rtelmezett �rt�kekre.
	 */
	public void reset() {
		setPos(Config.OFFSCREEN);
		colliding = false;
	}

	/**
	 * Megmondja egy l�ved�kr�l, hogy �tk�zhet-e.
	 */
	@Override
	public boolean isCollidable() {
		return true;
	}

	/**
	 * Megmondja egy l�ved�kr�l, hogy superbullet-e.
	 * @return
	 */
	public final boolean isSuperBullet() {
		return superBullet;
	}

	/**
	 * Be�ll�tja a l�ved�k superbullet mivoltj�t.
	 * @param superBullet
	 */
	public final void setSuperBullet(boolean superBullet) {
		this.superBullet = superBullet;
	}

}
