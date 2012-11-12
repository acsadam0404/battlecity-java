package objects;

import game.Registry;

import java.awt.Graphics;
import java.util.Map;

import math.Vector2;

import components.animation.*;
import components.collision.ICollidable;

/**
 * a kirajzolható objektumok õsosztálya. Megvalósítja az animáció kezelést
 * @author Ács Ádám
 *
 */
public abstract class Sprite implements IGameLoop, IAnimatable, ICollidable {
	protected ClassAnimations classAnims;
	
	protected Vector2 pos;
	protected Vector2 size;
	protected Animation anim;
	protected boolean useAnimation = true;
	protected boolean collidable = true;
	protected boolean passableByBullet = false;
	
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
	 * Létrehoz egy sprite-ot a megadott pozícióra.
	 * @param pos
	 */
	public Sprite(Vector2 pos) {
		this.pos = pos;
	}
	
	
	/**
	 * registry ezt használja
	 * @return
	 */
	public abstract SpriteType getSpriteType();
	
	
	/**
	 * Frissíti az animációt.
	 */
	@Override
	public void update(long gameTime) {
		/* szükséges a null check, mert nem minden leszármazott fogja használni az animot */
		if (anim != null) {
			anim.update(gameTime);
		}
	}
	
	/**
	 * Kirajzolja az animációt.
	 */
	@Override
	public void draw(Graphics g) {
		/* szükséges a null check, mert nem minden leszármazott fogja használni az animot */
		if (anim != null) {
			anim.draw(g);
		}
	}
	
	/**
	 * Beolvassa az animációt és eltárolja.
	 */
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
	
	/**
	 * Visszaadja a sprite középpontját.
	 * @return
	 */
	public Vector2 getCenter() {
		return new Vector2(pos.getX() + size.getX() / 2, pos.getY() + size.getY() / 2);
	}
	
	/**
	 * Beállítja a sprite méretét.
	 * @return
	 */
	protected abstract Vector2 initSize();

	/**
	 * Beállítja az osztályhoz tartozó animációkat.
	 * @return
	 */
	protected abstract Map<String, Animation> initClassAnimations();
	
	/**
	 * Visszaadja a sprite pozícióját.
	 */
	@Override
	public Vector2 getPos() {
		return pos;
	}
	
	/**
	 * Visszaadja a sprite méretét.
	 * @return
	 */
	public final Vector2 getSize() {
		return size;
	}

	/**
	 * Beállítja a sprite pozícióját.
	 * @param pos
	 */
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
	
	/**
	 * Beállítja az aktuális animációt.
	 * @param anim
	 */
	public void setAnimation(Animation anim) {
		this.anim = anim;
		if (anim != null) {
			anim.setContainer(this);
		}
	}

	/**
	 * Megmondja az objektumról, hogy ütközhet-e.
	 */
	@Override
	public boolean isCollidable() {
		return collidable;
	}

	/**
	 * Megmondja az objektumról, hogy átmehet-e rajta a golyó.
	 */
	@Override
	public boolean isPassableByBullet() {
		return passableByBullet;
	}
}
