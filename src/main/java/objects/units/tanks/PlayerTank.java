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
 * 
 * @author Ács Ádám
 *
 */
public class PlayerTank extends Tank implements IResetable{
	private AffineTransform at;
	
	public PlayerTank(Vector2 pos) {
		super(pos);
		maxHealth = 1;
		health = maxHealth;
		speed = 4f;
	}

	@Override
	public void init() throws EInitException {
		super.init();
	}

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

			if (!checkCollisionForMove(offset)) {
				pos = pos.add(offset);
			}
			else if (!checkCollisionForMove(offset.normalize())) {
				pos = pos.add(offset.normalize());
			}
		}
	}

	@Override
	public void update(long gameTime) {
		super.update(gameTime);

	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}

	@Override
	protected Map<String, Animation> initClassAnimations() {
		Map<String, Animation> anims = new HashMap<>();
		
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

	@Override
	public SpriteType getSpriteType() {
		return SpriteType.PLAYER_TANK;
	}

	@Override
	public boolean checkCollision() {
		if (!Collision.intersects(this, Registry.singleton().getEnemyBulletRegistry()).isEmpty()) {
			health--;
			setState(State.EXPLODING);
		}
		
		return true;
	}
	
	public void setSuperBullet(boolean superBullet) {
		bulletManager.getBullet().setSuperBullet(superBullet);
	}

	@Override
	public void reset() {
		setState(State.BASE);
		setHealth(maxHealth);
		setAnimation(classAnims.getAnimation("base"));
	}

}
