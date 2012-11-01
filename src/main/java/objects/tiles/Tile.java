package objects.tiles;

import game.Config;
import game.IResetable;
import game.Registry;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.*;

import math.Vector2;
import objects.EInitException;
import objects.Sprite;

import components.animation.Animation;
import components.collision.BoundingPart;
import components.collision.Collision;

/**
 * Az osztály a térképen elõforduló akadályok alapjaként szolgál. 
 * Az akadályok megjelennek a térképen (animálódhatnak), a többi objektum ütközhet velük.
 * 
 * @author Ács Ádám
 * 2012.07.22.
 */
public abstract class Tile extends Sprite implements IResetable {
	protected BoundingPart boundingPart;
	protected List<Rectangle> collidingRects = new ArrayList<>();

	public Tile(int posX, int posY) {
		super(new Vector2(posX, posY));
		this.pos = new Vector2(posX, posY);
		this.size = new Vector2(Config.TILE_WIDTH, Config.TILE_HEIGHT);
	}
	
	/**
	 * Hányadik rétegen van a sprite. Ez hasznos, hogy egymást elfedõ tile-okat használhassunk.
	 * 
	 * IMPROVE ehelyett egy globális spritemanager kellene, ami kirajzol minden sprite-ot a megfelelõ rétegre.
	 */
	public abstract int getLayerNumber();

	@Override
	public void init() throws EInitException {
		boundingPart = new BoundingPart(new Rectangle(pos.getX(), pos.getY(), size.getX(), size.getY()));
		super.init();
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}

	@Override
	public String toString() {
		return "tile: " + getPos().toString();
	}

	@Override
	public void update(long gameTime) {
		for (int i =  collidingRects.size() - 1; i >= 0; i--) {
			Rectangle rect =  collidingRects.get(i);
			boundingPart.removeRect(rect);
		}
		collidingRects.clear();

		super.update(gameTime);
		checkCollision();
	}


	public List<Rectangle> checkCollision() {
		if (isCollidable()) {
			List<Rectangle> rects = boundingPart.getRects();
			for (int i = rects.size() - 1; i >= 0; i--) {
				Rectangle rect = rects.get(i);
				if (isCollidable() && !Collision.intersects(rect, Registry.singleton().getPlayerBulletRegistry()).isEmpty() ||
						isCollidable() && !Collision.intersects(rect, Registry.singleton().getEnemyBulletRegistry()).isEmpty()) {
					collidingRects.add(rect);
					
				}
			}
		}
		
		return collidingRects;
	}

	@Override
	public BoundingPart getBoundingPart() {
		if (isCollidable()) {
			return boundingPart;
		}

		return new BoundingPart(Config.OFFSCREEN_RECT);
	}

	@Override
	protected Vector2 initSize() {
		return new Vector2(Config.TILE_WIDTH, Config.TILE_HEIGHT);
	}
	

	@Override
	protected Map<String, Animation> initClassAnimations() {
		return null;
	}

	/**
	 * Ha a boundingPart-ot másképp használja egy alosztály, felül kell ezt írnia a normális viselkedéshez.
	 */
	@Override
	public void reset() {
		boundingPart = new BoundingPart(new Rectangle(pos.getX(), pos.getY(), size.getX(), size.getY()));
	}
}
