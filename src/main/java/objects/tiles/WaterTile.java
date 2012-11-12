package objects.tiles;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import objects.EInitException;
import objects.SpriteType;

import components.animation.Animation;
import components.animation.AnimationFactory;

/**
 * A v�z tile-t implement�l� oszt�ly.
 * 
 * @author �cs �d�m
 * 
 */
public class WaterTile extends Tile {
	/**
	 * L�trehozza a tile-t a megadott poz�ci�ra.
	 * 
	 * @param posX
	 * @param posY
	 */
	public WaterTile(int posX, int posY) {
		super(posX, posY);
		passableByBullet = true;
	}

	/**
	 * Beolvassa a k�peket.
	 */
	@Override
	public void init() throws EInitException {
		super.init();
	}

	/**
	 * Friss�ti a tile-t.
	 */
	@Override
	public void update(long gameTime) {
		super.update(gameTime);
	}

	/**
	 * Kirajzolja a tile-t.
	 */
	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}

	/**
	 *  Visszaadja a sprite t�pus�t.
	 */
	@Override
	public SpriteType getSpriteType() {
		return SpriteType.WATER_TILE;
	}

	/**
	 * Be�ll�tja az oszt�ly anim�ci�it.
	 */
	@Override
	protected Map<String, Animation> initClassAnimations() {
		Map<String, Animation> anims = new HashMap<String, Animation>();
		anims.put("base", AnimationFactory.createAnimation(this,
				"animations\\viz.xml", 200));
		return anims;
	}

	/**
	 * Visszaadja, hogy az objektum melyik r�tegen helyezkedik el.
	 */
	@Override
	public int getLayerNumber() {
		return 1;
	}
}
