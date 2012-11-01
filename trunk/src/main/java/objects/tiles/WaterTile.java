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
	public WaterTile(int posX, int posY) {
		super(posX, posY);
	}
	
	@Override
	public void init() throws EInitException {
		super.init();
	}

	@Override
	public void update(long gameTime) {
		super.update(gameTime);
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}

	@Override
	public SpriteType getSpriteType() {
		return SpriteType.WATER_TILE;
	}
	
	@Override
	protected Map<String, Animation> initClassAnimations() {
		Map<String, Animation> anims = new HashMap<>();
		anims.put("base", AnimationFactory.createAnimation(this, "animations\\viz.xml", 200));
		return anims;
	}

	@Override
	public int getLayerNumber() {
		return 1;
	}
}
