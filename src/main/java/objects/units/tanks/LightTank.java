package objects.units.tanks;

import java.util.HashMap;
import java.util.Map;

import math.Vector2;
import objects.*;

import components.animation.Animation;
import components.animation.AnimationFactory;

/**
 * 
 * @author Ács Ádám
 *
 */
public class LightTank extends EnemyTank {
	public LightTank(Vector2 pos) {
		super(pos);
		maxHealth = 1;
		health = maxHealth;
		speed = 2f;
	}


	@Override
	public void move(Direction dir) {
		Vector2 offset = Vector2.multiply(dir.getVector2(),speed);

		if (!checkCollisionForMove(offset)) {
			pos = pos.add(offset);
		}
	}

	@Override
	protected Map<String, Animation> initClassAnimations() {
		Map<String, Animation> anims = new HashMap<>();
		Animation explosion = AnimationFactory.createAnimation(this, "animations\\explosion.xml", 100, 1);
		explosion.setContainer(this);
		anims.put("explosion", explosion);
		
		Animation base = AnimationFactory.createAnimation(this, "animations\\lighttank.xml", 100);
		base.setContainer(this);
		anims.put("base", base);
		
		return anims;
	}
	
	@Override
	public void update(long gameTime) {
		super.update(gameTime);
		
		switch (state) {
			case BASE:
				break;
			case DEAD:
				break;
			case EXPLODING:
				if (!anim.isRunning()) {
					setState(State.DEAD);
				}
				break;
			case WANDERING:
				break;
			case SPAWNING:
				break;
		}
	}


	@Override
	public void init() throws EInitException {
		super.init();
	}


	@Override
	public SpriteType getSpriteType() {
		return SpriteType.ENEMY_TANK;
	}
	

}
