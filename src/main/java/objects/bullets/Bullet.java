package objects.bullets;

import game.Config;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import math.Vector2;
import objects.*;

import components.animation.Animation;
import components.animation.AnimationFactory;
import components.collision.BoundingPart;
import components.collision.Collision;

/**
 * 
 * @author Ács ádám
 *
 */
public abstract class Bullet extends Sprite  {
	protected Direction direction;
	protected float speed = 2f;
	protected boolean colliding = false;
	protected boolean superBullet = false;

	public Bullet() {
		super(Config.OFFSCREEN);
	}
	
	@Override
	public void init() throws EInitException {
		super.init();
	}

	@Override
	public void update(long gameTime) {
		pos = pos.add(Vector2.multiply(direction.getVector2(), speed));
		
		super.update(gameTime);
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}

	@Override
	public Vector2 getPos() {
		return pos;
	}
	
	public void fire(Direction direction) {
		this.direction = direction;
	}

	@Override
	protected Vector2 initSize() {
		return new Vector2(Config.BULLET_WIDTH, Config.BULLET_HEIGHT);
	}

	@Override
	protected Map<String, Animation> initClassAnimations() {
		Map<String, Animation> anims = new HashMap<>();
		anims.put("base", AnimationFactory.createAnimation(this, "animations\\bullet.xml", 1000));
		return anims;
	}


	@Override
	public BoundingPart getBoundingPart() {
		return new BoundingPart(new Rectangle(pos.getX(), pos.getY(), size.getX(), size.getY()));
	}

	public boolean isColliding() {
		return Collision.isOutOfScreen(this);
	}

	public void reset() {
		setPos(Config.OFFSCREEN);
		colliding = false;
	}
	
	@Override
	public boolean isCollidable() {
		return true;
	}

	public final boolean isSuperBullet() {
		return superBullet;
	}

	public final void setSuperBullet(boolean superBullet) {
		this.superBullet = superBullet;
	}

}
