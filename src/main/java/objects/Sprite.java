package objects;

import game.Registry;

import java.awt.Graphics;
import java.util.Map;

import math.Vector2;

import components.animation.*;
import components.collision.ICollidable;

/**
 * superclassa a kirajzolható cuccoknak
 * @author Ács Ádám
 *
 */
public abstract class Sprite implements IGameLoop, IAnimatable, ICollidable {
	protected Vector2 pos;
	protected Vector2 size;
	protected Animation anim;
	protected boolean useAnimation = true;
	protected boolean collidable = true;
	protected ClassAnimations classAnims;
	
	protected static class ClassAnimations {
		private Map<String, Animation> animations;
		protected ClassAnimations(Map<String, Animation> animations) {
			this.animations = animations;
		}
		
		public Animation getAnimation(String name) {
			if (animations.containsKey(name)) {
				return animations.get(name);
			}
			
			throw new ENoAnimationFoundException(name);
		}
	}
	
	/**
	 * registry ezt használja
	 * @return
	 */
	public abstract SpriteType getSpriteType();
	
	public Sprite(Vector2 pos) {
		this.pos = pos;
	}
	
	@Override
	public void update(long gameTime) {
		/* szükséges a null check, mert nem minden leszármazott fogja használni az animot */
		if (anim != null) {
			anim.update(gameTime);
		}
	}
	
	@Override
	public void draw(Graphics g) {
		/* szükséges a null check, mert nem minden leszármazott fogja használni az animot */
		if (anim != null) {
			anim.draw(g);
		}
	}
	
	@Override
	public void init() throws EInitException {
		if (useAnimation) {
			/* csak egyszer szeretnénk fájlból olvasgatni */
			if (classAnims == null) {
				classAnims = new ClassAnimations(initClassAnimations());
			}
			
			setAnimation(classAnims.getAnimation("base"));
		}
		size = initSize();
		Registry.singleton().register(this);
	}
	
	public Vector2 getCenter() {
		return new Vector2(pos.getX() + size.getX() / 2, pos.getY() + size.getY() / 2);
	}
	
	protected abstract Vector2 initSize();

	protected abstract Map<String, Animation> initClassAnimations();
	
	@Override
	public Vector2 getPos() {
		return pos;
	}
	
	public final Vector2 getSize() {
		return size;
	}

	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
	
	public void setAnimation(Animation anim) {
		this.anim = anim;
		if (anim != null) {
			anim.setContainer(this);
		}
	}

	@Override
	public boolean isCollidable() {
		return collidable;
	}
}
