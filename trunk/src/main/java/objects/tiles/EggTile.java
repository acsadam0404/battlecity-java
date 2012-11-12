package objects.tiles;

import java.util.HashMap;
import java.util.Map;

import objects.SpriteType;

import components.animation.Animation;
import components.animation.AnimationFactory;

/**
 * Egy tojás, a klasszikus sas helyett, amit a playernek meg kell védenie.
 * @author Ács Ádám
 *
 */
public class EggTile extends Tile {
	/**
	 * Létrehozza az objektumot az adott pozíción.
	 * @param posX
	 * @param posY
	 */
	public EggTile(int posX, int posY) {
		super(posX, posY);
		collidable = true;
	}

	/**
	 * Visszaadja a sprite típusát.
	 */
	@Override
	public SpriteType getSpriteType() {
		return SpriteType.EGG_TILE;
	}

	/**
	 * Beállítja az osztályhoz tartozó animációkat.
	 */
	@Override
	protected Map<String, Animation> initClassAnimations() {
		Map<String, Animation> anims = new HashMap<String, Animation>();
		anims.put("base", AnimationFactory.createAnimation(this, "animations\\egg.xml", 200));
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
