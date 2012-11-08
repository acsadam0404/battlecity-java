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

	public Tank(Vector2 pos) {
		super(pos);
		bulletManager = new BulletManager(this);
	}

	@Override
	public void init() throws EInitException {
		super.init();
		bulletManager.init();
		audioHandler = new AudioHandler(new AudioData("sounddata\\tank.txt"));
	}

	public final BulletManager getBulletManager() {
		return bulletManager;
	}

	@Override
	protected abstract Map<String, Animation> initClassAnimations();

	@Override
	public final BoundingPart getBoundingPart() {
		if (state.equals(State.DEAD) || state.equals(State.EXPLODING)) {
			return new BoundingPart(new Rectangle(Config.OFFSCREEN_RECT));
		}

		Rectangle rect = new Rectangle(pos.getX(), pos.getY(), size.getX(), size.getY());
		BoundingPart bPart = new BoundingPart(rect);
		return bPart;
	}

	@Override
	public boolean isCollidable() {
		return true;
	}

	public abstract boolean checkCollision();

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

	@Override
	protected Vector2 initSize() {
		return new Vector2(Config.TILE_WIDTH, Config.TILE_HEIGHT);
	}

	@Override
	public void update(long gameTime) {
		super.update(gameTime);

		bulletManager.update(gameTime);

		checkCollision();
		prevState = state;
	}

	public final State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
		switch (state) {
		case BASE:
			setAnimation(classAnims.getAnimation("base"));
			break;
		case DEAD:
			break;
		case EXPLODING:
			setAnimation(classAnims.getAnimation("explosion"));
			break;
		case SPAWNING:
			break;
		case WANDERING:
			break;
		}

	}

	@Override
	public void draw(Graphics g) {
		bulletManager.draw(g);
		super.draw(g);

	}

	public void fire() {
		boolean fired = bulletManager.fire(getDirection());
		if (fired) {
			audioHandler.playSound("tankfire2");
		}
	}

	protected void initStateAnims() {
		stateAnims.put(State.BASE, classAnims.getAnimation("base"));
		stateAnims.put(State.EXPLODING, classAnims.getAnimation("exploding"));
		stateAnims.put(State.WANDERING, classAnims.getAnimation("base"));
	}

	public enum State {
		EXPLODING,
		WANDERING,
		BASE,
		SPAWNING,
		DEAD;
	}

	public void moveInDirection() {
		if (moveAllowed(Vector2.multiply(direction.getVector2(), speed))) {
			move(direction);
		}
	}

	public Player getPlayer() {
		return player;
	}

	/**
	 * XXX lehet konstruktorba kéne megadni, mer így félkész objektum van csak
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
}
