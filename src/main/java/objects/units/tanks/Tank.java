package objects.units.tanks;

import game.Config;
import game.Registry;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import math.Vector2;
import objects.EInitException;
import objects.bullets.BulletManager;
import objects.units.Unit;
import player.Player;

import components.animation.Animation;
import components.audio.AudioData;
import components.audio.AudioHandler;
import components.collision.BoundingPart;
import components.collision.Collision;

/**
 * A tankok õsosztálya.
 * @author Ács Ádám
 *
 */
public abstract class Tank extends Unit {
	protected Player player;
	protected State state = State.BASE;
	protected State prevState = State.BASE;
	protected BulletManager bulletManager;
	protected AudioHandler audioHandler;
	protected Map<State, Animation> stateAnims = new HashMap<State, Animation>();

	/**
	 * Létrehozza a tankot adott pozícióra.
	 * @param pos
	 */
	public Tank(Vector2 pos) {
		super(pos);
		bulletManager = new BulletManager(this);
	}

	/**
	 * Beolvassa a képeket, inicializálja a hang lejátszást.
	 */
	@Override
	public void init() throws EInitException {
		super.init();
		bulletManager.init();
		audioHandler = new AudioHandler(new AudioData("sounddata\\tank.txt"));
	}

	/**
	 * Visszaadja a bulletManager-t.
	 * @return
	 */
	public final BulletManager getBulletManager() {
		return bulletManager;
	}

	/**
	 * Beállítja az osztályhoz tartozó animációkat.
	 */
	@Override
	protected abstract Map<String, Animation> initClassAnimations();

	/**
	 * Visszaadja az ütközõ részeket.
	 */
	@Override
	public final BoundingPart getBoundingPart() {
		if (state.equals(State.DEAD) || state.equals(State.EXPLODING)) {
			return new BoundingPart(new Rectangle(Config.OFFSCREEN_RECT));
		}

		Rectangle rect = new Rectangle(pos.getX(), pos.getY(), size.getX(), size.getY());
		BoundingPart bPart = new BoundingPart(rect);
		return bPart;
	}

	/**
	 * Megmondja, hogy ütközhet-e a tank.
	 */
	@Override
	public boolean isCollidable() {
		return true;
	}

	/**
	 * Ütközéseket vizsgál.
	 * @return
	 */
	public abstract boolean checkCollision();

	/**
	 * Megmondja egy tankról, hogy mozgatható-e adott irányba.
	 * @param offset
	 * @return
	 */
	public boolean moveAllowed(Vector2 offset) {
		boolean moveAllowed = true;

		if (Collision.isOutOfScreen(this, offset)) {
			moveAllowed = false;
		}

		if (!Collision.intersects(this, Registry.singleton().getPlayerTankRegistry(), offset).isEmpty()) {
			moveAllowed = false;
		}

		if (!Collision.intersects(this, Registry.singleton().getTileRegistry(), offset).isEmpty()) {
			moveAllowed = false;
		}

		if (!Collision.intersects(this, Registry.singleton().getEnemyTankRegistry(), offset).isEmpty()) {
			moveAllowed = false;
		}

		return moveAllowed;
	}

	/**
	 * Beállítja a tank méreteit.
	 */
	@Override
	protected Vector2 initSize() {
		return new Vector2(Config.TILE_WIDTH, Config.TILE_HEIGHT);
	}

	/**
	 * Frissíti a tankot.
	 */
	@Override
	public void update(long gameTime) {
		super.update(gameTime);

		bulletManager.update(gameTime);

		checkCollision();
		prevState = state;
	}

	/**
	 * Visszaadja a tank aktuális állapotát.
	 * @return
	 */
	public final State getState() {
		return state;
	}

	/**
	 * Beállítja a tank aktuális állapotát.
	 * @param state
	 */
	public void setState(State state) {
		this.state = state;
		switch (state) {
		case BASE:
			setAnimation(classAnims.getAnimation("base"));
			break;
		case DEAD:
			Registry.singleton().unregister(bulletManager.getBullet());
			Registry.singleton().unregister(this);
			break;
		case EXPLODING:
			setAnimation(classAnims.getAnimation("explosion"));
			break;
		case SPAWNING:
			break;
		case WANDERING:
			break;
		default:
			break;
		}

	}

	/**
	 * Kirajzolja a tankot.
	 */
	@Override
	public void draw(Graphics g) {
		bulletManager.draw(g);
		super.draw(g);

	}

	/**
	 * Lövést kezdeményez.
	 */
	public void fire() {
		boolean fired = bulletManager.fire(getDirection());
		if (fired) {
			audioHandler.playSound("tankfire2");
		}
	}

	/**
	 * Beállítja az állapotokhoz tartozó animációkat.
	 */
	protected void initStateAnims() {
		stateAnims.put(State.BASE, classAnims.getAnimation("base"));
		stateAnims.put(State.EXPLODING, classAnims.getAnimation("exploding"));
		stateAnims.put(State.WANDERING, classAnims.getAnimation("base"));
	}

	/**
	 * A tank állapotait reprezentáli enumeráció.
	 * @author Ács
	 *
	 */
	public enum State {
		EXPLODING,
		WANDERING,
		BASE,
		SPAWNING,
		DEAD;
	}

	/**
	 * Mozgatja a tankot adott irányba.
	 */
	public void moveInDirection() {
		if (moveAllowed(Vector2.multiply(direction.getVector2(), speed))) {
			move(direction);
		}
	}

	/**
	 * Visszaadja a tankhoz tartozó játékost.
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Beállítja a tankhoz tartozó játékost.
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Alapértelmezet értékeket állít be a tankra.
	 */
	public void reset() {
		Registry.singleton().unregister(this);
		bulletManager.reset();
	}
}
