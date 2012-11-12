package objects.tiles;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import objects.EInitException;
import objects.SpriteType;

import components.animation.Animation;
import components.animation.AnimationFactory;

/**
 * A víz tile-t implementáló osztály.
 * 
 * @author Ács Ádám
 * 
 */
public class WaterTile extends Tile {
	/**
	 * Létrehozza a tile-t a megadott pozícióra.
	 * 
	 * @param posX
	 * @param posY
	 */
	public WaterTile(int posX, int posY) {
		super(posX, posY);
		passableByBullet = true;
	}

	/**
	 * Beolvassa a képeket.
	 */
	@Override
	public void init() throws EInitException {
		super.init();
	}

	/**
	 * Frissíti a tile-t.
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
	 *  Visszaadja a sprite típusát.
	 */
	@Override
	public SpriteType getSpriteType() {
		return SpriteType.WATER_TILE;
	}

	/**
	 * Beállítja az osztály animációit.
	 */
	@Override
	protected Map<String, Animation> initClassAnimations() {
		Map<String, Animation> anims = new HashMap<String, Animation>();
		anims.put("base", AnimationFactory.createAnimation(this,
				"animations\\viz.xml", 200));
		return anims;
	}

	/**
	 * Visszaadja, hogy az objektum melyik rétegen helyezkedik el.
	 */
	@Override
	public int getLayerNumber() {
		return 1;
	}
}
