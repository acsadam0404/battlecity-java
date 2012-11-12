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
 * A lövedékek õsosztálya. A lövedék lehet superbullet, így a betonfalat is áttöri. A lövedékek átmennek a víz fölött, beleütköznek a falba, és átmennek a fû alatt.
 * A lövedékek konstans sebességgel haladnak a kilövés idõpontjában meghatározott irányba.
 * @author Ács ádám
 *
 */
public abstract class Bullet extends Sprite {
	protected Direction direction;
	protected float speed = 5f;
	protected boolean colliding = false;
	protected boolean superBullet = false;

	/**
	 * Létrehozza a lövedéket a képernyõn kívülre.
	 */
	public Bullet() {
		super(Config.OFFSCREEN);
	}

	/**
	 * Beolvassa a lövedék képét.
	 */
	@Override
	public void init() throws EInitException {
		super.init();
	}

	/**
	 * Frissíti a lövedéket.
	 */
	@Override
	public void update(long gameTime) {
		pos = pos.add(Vector2.multiply(direction.getVector2(), speed));

		super.update(gameTime);
	}

	/**
	 * Kirazjolja a lövedéket.
	 */
	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}

	/**
	 * Visszaadja a lövedék pozícióját.
	 */
	@Override
	public Vector2 getPos() {
		return pos;
	}

	/**
	 * Kilövi a lövedéket adott irányba.
	 * @param direction
	 */
	public void fire(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Beállítja a lövedék méretét.
	 */
	@Override
	protected Vector2 initSize() {
		return new Vector2(Config.BULLET_WIDTH, Config.BULLET_HEIGHT);
	}

	/**
	 * Beállítja a lövedék animációit.
	 */
	@Override
	protected Map<String, Animation> initClassAnimations() {
		Map<String, Animation> anims = new HashMap<String, Animation>();
		anims.put("base", AnimationFactory.createAnimation(this, "animations\\bullet.xml", 1000));
		return anims;
	}

	/**
	 * Visszaadja az ütközõ részeket.
	 */
	@Override
	public BoundingPart getBoundingPart() {
		/* -4 ek azért kellenek, mert a kép kisebb mint a boundingpart */ /* FIXME tesztelni */
		return new BoundingPart(new Rectangle(pos.getX() - 4, pos.getY() - 4, size.getX(), size.getY()));
	}

	/**
	 * Megmondja egy lövedékrõl, hogy ütközik-e.
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
	 * Beállítja a lövedéket az alapértelmezett értékekre.
	 */
	public void reset() {
		setPos(Config.OFFSCREEN);
		colliding = false;
	}

	/**
	 * Megmondja egy lövedékrõl, hogy ütközhet-e.
	 */
	@Override
	public boolean isCollidable() {
		return true;
	}

	/**
	 * Megmondja egy lövedékrõl, hogy superbullet-e.
	 * @return
	 */
	public final boolean isSuperBullet() {
		return superBullet;
	}

	/**
	 * Beállítja a lövedék superbullet mivoltját.
	 * @param superBullet
	 */
	public final void setSuperBullet(boolean superBullet) {
		this.superBullet = superBullet;
	}

}
