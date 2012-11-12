package objects.tiles;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import objects.EInitException;
import objects.SpriteType;

import components.animation.Animation;
import components.animation.AnimationFactory;

/**
 * F�, ami alatt nem l�tsz�dnak a l�ved�kek �s a tankok. M�s hat�sa nincs, nem befoly�solja a j�t�kot, csak a vizualiz�ci�t.
 * @author �cs �d�m
 *
 */
public class GrassTile extends Tile {
	/**
	 * L�trehozza az objektumot a megadott poz�ci�n.
	 * @param posX
	 * @param posY
	 */
	public GrassTile(int posX, int posY) {
		super(posX, posY);
		collidable = false;
	}

	/**
	 * Beolvassa a k�peket.
	 */
	@Override
	public void init() throws EInitException {
		super.init();
	}

	/**
	 * Friss�ti az objektumot.
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
	 * Visszaadja a sprite t�pus�t.
	 */
	@Override
	public SpriteType getSpriteType() {
		return SpriteType.GRASS_TILE;
	}

	/**
	 * Be�ll�tja az oszt�lyhoz tartoz� anim�ci�kat.
	 */
	@Override
	protected Map<String, Animation> initClassAnimations() {
		Map<String, Animation> anims = new HashMap<String, Animation>();
		anims.put("base", AnimationFactory.createAnimation(this, "animations\\grass.xml", 200));
		return anims;
	}

	/**
	 * Visszaadja, hogy az objektum melyik r�tegen helyezkedik el.
	 */
	@Override
	public int getLayerNumber() {
		return 2;
	}
}
