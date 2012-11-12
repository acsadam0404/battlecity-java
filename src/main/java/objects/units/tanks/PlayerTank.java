package objects.units.tanks;

import game.IResetable;
import game.Registry;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;

import math.Vector2;
import objects.Direction;
import objects.EInitException;
import objects.SpriteType;

import components.animation.Animation;
import components.animation.AnimationFactory;
import components.collision.Collision;

/**
 * A j�t�kos tankj�t megval�s�t� oszt�ly.
 * @author �cs �d�m
 *
 */
public class PlayerTank extends Tank implements IResetable {
	private AffineTransform at;

	/**
	 * L�trehozza az objektumot adott poz�ci�ra, be�ll�tja az �rt�keit.
	 * @param pos
	 */
	public PlayerTank(Vector2 pos) {
		super(pos);
		maxHealth = 1;
		health = maxHealth;
		speed = 4f;
	}

	/**
	 * Beolvassa a k�peket.
	 */
	@Override
	public void init() throws EInitException {
		super.init();
	}

	/**
	 * Mozgatja a tankot adott ir�nyba.
	 */
	@Override
	public void move(Direction dir) {
		if (anim != null) {
			direction = dir;
			audioHandler.playSound("tankmove");

			if (dir == Direction.EAST) {
				anim.rotate(Math.toRadians(90d));
			}
			if (dir == Direction.WEST) {
				anim.rotate(Math.toRadians(270d));
			}
			if (dir == Direction.NORTH) {
				anim.rotate(Math.toRadians(0d));
			}
			if (dir == Direction.SOUTH) {
				anim.rotate(Math.toRadians(180d));
			}

			Vector2 offset = Vector2.multiply(dir.getVector2(), speed);

			if (moveAllowed(offset)) {
				pos = pos.add(offset);
			} else if (moveAllowed(offset.normalize())) {
				pos = pos.add(offset.normalize());
			}
		}
	}

	/**
	 * Friss�ti a tankot.
	 */
	@Override
	public void update(long gameTime) {
		super.update(gameTime);

	}

	/**
	 * Kirajzolja a tankot.
	 */
	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}

	/**
	 * Be�ll�tja az oszt�lyhoz tartoz� anim�ci�kat.
	 */
	@Override
	protected Map<String, Animation> initClassAnimations() {
		Map<String, Animation> anims = new HashMap<String, Animation>();

		Animation base = AnimationFactory.createAnimation(this, "animations\\playertank.xml", 200);
		base.setContainer(this);
		base.init();
		anims.put("base", base);

		Animation exploding = AnimationFactory.createAnimation(this, "animations\\explosion.xml", 50, 1);
		exploding.setContainer(this);
		exploding.init();
		anims.put("explosion", exploding);

//		Animation spawn = AnimationFactory.createAnimation(this, "animations\\spawn.xml", 50, 1);
//		spawn.setContainer(this);
//		spawn.init();
//		anims.put("spawn", spawn);

		return anims;
	}

	/**
	 * Visszaadja a sprite t�pus�t.
	 */
	@Override
	public SpriteType getSpriteType() {
		return SpriteType.PLAYER_TANK;
	}

	/**
	 * Megvizsg�lja az �tk�z�seket.
	 */
	@Override
	public boolean checkCollision() {
		if (!Collision.intersects(this, Registry.singleton().getEnemyBulletRegistry()).isEmpty()) {
			health--;
			setState(State.EXPLODING);
		}

		return true;
	}

	/**
	 * Be�ll�tja a tankra a superbullet-et.
	 * @param superBullet
	 */
	public void setSuperBullet(boolean superBullet) {
		bulletManager.getBullet().setSuperBullet(superBullet);
	}

	/**
	 * Vissza�ll�tja a tankot alap�rtelmezett �rt�keire.
	 */
	@Override
	public void reset() {
		setState(State.BASE);
		setHealth(maxHealth);
		setAnimation(classAnims.getAnimation("base"));
	}

}
