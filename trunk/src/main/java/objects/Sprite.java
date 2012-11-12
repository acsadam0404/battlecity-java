package objects;

import game.Registry;

import java.awt.Graphics;
import java.util.Map;

import math.Vector2;

import components.animation.*;
import components.collision.ICollidable;

/**
 * a kirajzolhat� objektumok �soszt�lya. Megval�s�tja az anim�ci� kezel�st
 * @author �cs �d�m
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
	 * L�trehoz egy sprite-ot a megadott poz�ci�ra.
	 * @param pos
	 */
	public Sprite(Vector2 pos) {
		this.pos = pos;
	}
	
	
	/**
	 * registry ezt haszn�lja
	 * @return
	 */
	public abstract SpriteType getSpriteType();
	
	
	/**
	 * Friss�ti az anim�ci�t.
	 */
	@Override
	public void update(long gameTime) {
		/* sz�ks�ges a null check, mert nem minden lesz�rmazott fogja haszn�lni az animot */
		if (anim != null) {
			anim.update(gameTime);
		}
	}
	
	/**
	 * Kirajzolja az anim�ci�t.
	 */
	@Override
	public void draw(Graphics g) {
		/* sz�ks�ges a null check, mert nem minden lesz�rmazott fogja haszn�lni az animot */
		if (anim != null) {
			anim.draw(g);
		}
	}
	
	/**
	 * Beolvassa az anim�ci�t �s elt�rolja.
	 */
	@Override
	public void init() throws EInitException {
		if (useAnimation) {
			/* csak egyszer szeretn�nk f�jlb�l olvasgatni */
			if (classAnims == null) {
				classAnims = new ClassAnimations(initClassAnimations());
			}
			
			setAnimation(classAnims.getAnimation("base"));
		}
		size = initSize();
		Registry.singleton().register(this);
	}
	
	/**
	 * Visszaadja a sprite k�z�ppontj�t.
	 * @return
	 */
	public Vector2 getCenter() {
		return new Vector2(pos.getX() + size.getX() / 2, pos.getY() + size.getY() / 2);
	}
	
	/**
	 * Be�ll�tja a sprite m�ret�t.
	 * @return
	 */
	protected abstract Vector2 initSize();

	/**
	 * Be�ll�tja az oszt�lyhoz tartoz� anim�ci�kat.
	 * @return
	 */
	protected abstract Map<String, Animation> initClassAnimations();
	
	/**
	 * Visszaadja a sprite poz�ci�j�t.
	 */
	@Override
	public Vector2 getPos() {
		return pos;
	}
	
	/**
	 * Visszaadja a sprite m�ret�t.
	 * @return
	 */
	public final Vector2 getSize() {
		return size;
	}

	/**
	 * Be�ll�tja a sprite poz�ci�j�t.
	 * @param pos
	 */
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
	
	/**
	 * Be�ll�tja az aktu�lis anim�ci�t.
	 * @param anim
	 */
	public void setAnimation(Animation anim) {
		this.anim = anim;
		if (anim != null) {
			anim.setContainer(this);
		}
	}

	/**
	 * Megmondja az objektumr�l, hogy �tk�zhet-e.
	 */
	@Override
	public boolean isCollidable() {
		return collidable;
	}

	/**
	 * Megmondja az objektumr�l, hogy �tmehet-e rajta a goly�.
	 */
	@Override
	public boolean isPassableByBullet() {
		return passableByBullet;
	}
}
