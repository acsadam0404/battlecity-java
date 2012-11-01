package objects.tiles;

import java.util.HashMap;
import java.util.Map;

import objects.SpriteType;

import components.animation.Animation;
import components.animation.AnimationFactory;

/**
 * 
 * @author Ács Ádám
 *
 */
public class EggTile extends Tile {
	public EggTile(int posX, int posY) {
		super(posX, posY);
		collidable = true;
	}

	@Override
	public SpriteType getSpriteType() {
		return SpriteType.EGG_TILE;
	}
	
	@Override
	protected Map<String, Animation> initClassAnimations() {
		Map<String, Animation> anims = new HashMap<>();
		anims.put("base", AnimationFactory.createAnimation(this, "animations\\egg.xml", 200));
		return anims;
	}

	@Override
	public int getLayerNumber() {
		return 1;
	}
}
