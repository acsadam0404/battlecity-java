package objects.tiles;

import java.util.HashMap;
import java.util.Map;

import objects.SpriteType;

import components.animation.Animation;
import components.animation.AnimationFactory;

/**
 * Egy toj�s, a klasszikus sas helyett, amit a playernek meg kell v�denie.
 * @author �cs �d�m
 *
 */
public class EggTile extends Tile {
	/**
	 * L�trehozza az objektumot az adott poz�ci�n.
	 * @param posX
	 * @param posY
	 */
	public EggTile(int posX, int posY) {
		super(posX, posY);
		collidable = true;
	}

	/**
	 * Visszaadja a sprite t�pus�t.
	 */
	@Override
	public SpriteType getSpriteType() {
		return SpriteType.EGG_TILE;
	}

	/**
	 * Be�ll�tja az oszt�lyhoz tartoz� anim�ci�kat.
	 */
	@Override
	protected Map<String, Animation> initClassAnimations() {
		Map<String, Animation> anims = new HashMap<String, Animation>();
		anims.put("base", AnimationFactory.createAnimation(this, "animations\\egg.xml", 200));
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
