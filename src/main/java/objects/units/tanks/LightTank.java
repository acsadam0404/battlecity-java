package objects.units.tanks;

import java.util.HashMap;
import java.util.Map;

import math.Vector2;
import objects.Direction;
import objects.EInitException;
import objects.SpriteType;

import components.animation.Animation;
import components.animation.AnimationFactory;

/**
 * Egy ellens�gse tank t�pus kev�s �lettel �s nagy sebess�ggel.
 * @author �cs �d�m
 *
 */
public class LightTank extends EnemyTank {
	/**
	 * L�trehozza az objektumot adott poz�ci�ra.
	 * @param pos
	 */
	public LightTank(Vector2 pos) {
		super(pos);
		maxHealth = 1;
		health = maxHealth;
		speed = 2f;
	}

	/**
	 * Mozgatja az objektumot adott ir�nyban.
	 */
	@Override
	public void move(Direction dir) {
		Vector2 offset = Vector2.multiply(dir.getVector2(), speed);

		if (moveAllowed(offset)) {
			pos = pos.add(offset);
		}
	}

	/**
	 * Be�ll�tja az oszt�lyhoz tartoz� anim�ci�kat.
	 */
	@Override
	protected Map<String, Animation> initClassAnimations() {
		Map<String, Animation> anims = new HashMap<String, Animation>();
		Animation explosion = AnimationFactory.createAnimation(this, "animations\\explosion.xml", 100, 1);
		explosion.setContainer(this);
		anims.put("explosion", explosion);

		Animation base = AnimationFactory.createAnimation(this, "animations\\lighttank.xml", 100);
		base.setContainer(this);
		anims.put("base", base);

		return anims;
	}

	/**
	 * Friss�ti az objektumot az �llapot�nak megfelel�en.
	 */
	@Override
	public void update(long gameTime) {
		super.update(gameTime);

		switch (state) {
		case BASE:
			break;
		case DEAD:
			break;
		case EXPLODING:
			if (!anim.isRunning() && !bulletManager.isFired()) {
				setState(State.DEAD);
			}
			break;
		case WANDERING:
			break;
		case SPAWNING:
			break;
		default:
			break;
		}
	}

	/**
	 * Beolvassa a k�peket.
	 */
	@Override
	public void init() throws EInitException {
		super.init();
	}

	/**
	 * Visszaadja a sprite t�pus�t.
	 */
	@Override
	public SpriteType getSpriteType() {
		return SpriteType.ENEMY_TANK;
	}

}
