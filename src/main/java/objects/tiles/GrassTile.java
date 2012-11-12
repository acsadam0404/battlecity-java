package objects.tiles;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import objects.EInitException;
import objects.SpriteType;

import components.animation.Animation;
import components.animation.AnimationFactory;

/**
 * Fû, ami alatt nem látszódnak a lövedékek és a tankok. Más hatása nincs, nem befolyásolja a játékot, csak a vizualizációt.
 * @author Ács Ádám
 *
 */
public class GrassTile extends Tile {
	/**
	 * Létrehozza az objektumot a megadott pozíción.
	 * @param posX
	 * @param posY
	 */
	public GrassTile(int posX, int posY) {
		super(posX, posY);
		collidable = false;
	}

	/**
	 * Beolvassa a képeket.
	 */
	@Override
	public void init() throws EInitException {
		super.init();
	}

	/**
	 * Frissíti az objektumot.
	 */
	@Override
	public void update(long gameTime) {
		super.update(gameTime);
	}

	/**
	 * Kirajzolja az objektumot.
	 */
	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}

	/**
	 * Visszaadja a sprite típusát.
	 */
	@Override
	public SpriteType getSpriteType() {
		return SpriteType.GRASS_TILE;
	}

	/**
	 * Beállítja az osztályhoz tartozó animációkat.
	 */
	@Override
	protected Map<String, Animation> initClassAnimations() {
		Map<String, Animation> anims = new HashMap<String, Animation>();
		anims.put("base", AnimationFactory.createAnimation(this, "animations\\grass.xml", 200));
		return anims;
	}

	/**
	 * Visszaadja, hogy az objektum melyik rétegen helyezkedik el.
	 */
	@Override
	public int getLayerNumber() {
		return 2;
	}
}
