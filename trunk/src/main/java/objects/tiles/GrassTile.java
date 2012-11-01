package objects.tiles;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import objects.EInitException;
import objects.SpriteType;

import components.animation.Animation;
import components.animation.AnimationFactory;

/**
 * 
 * @author Ács Ádám
 *
 */
public class GrassTile extends Tile {
	public GrassTile(int posX, int posY) {
		super(posX, posY);
		collidable = false;
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
		return SpriteType.GRASS_TILE;
	}
	
	@Override
	protected Map<String, Animation> initClassAnimations() {
		Map<String, Animation> anims = new HashMap<>();
		anims.put("base", AnimationFactory.createAnimation(this, "animations\\grass.xml", 200));
		return anims;
	}

	@Override
	public int getLayerNumber() {
		return 2;
	}
}
