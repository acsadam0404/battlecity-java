package objects.tiles;

import game.Config;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import objects.EInitException;
import objects.SpriteType;

import components.collision.BoundingPart;

/**
 * 
 * @author Ács Ádám
 *
 */
public class WallTile extends Tile {
	protected Map<Rectangle, BufferedImage> images = new HashMap<>();
	protected Rectangle topRight;
	protected Rectangle bottomRight;
	protected Rectangle topLeft;
	protected Rectangle bottomLeft;
	protected BufferedImage wholeImage;

	public WallTile(int posX, int posY) {
		super(posX, posY);
		
		/* beállítom a négy négyzetet amibõl a tile-ok összeálnak */
		int newSizeX = size.getX() / 2;
		int newSizeY = size.getY() / 2;
		bottomLeft = new Rectangle(posX, posY + newSizeY, newSizeX, newSizeY);
		topLeft = new Rectangle(posX, posY, newSizeX, newSizeY);
		bottomRight = new Rectangle(posX + newSizeX, posY + newSizeY, newSizeX, newSizeY);
		topRight = new Rectangle(posX + newSizeX, posY, newSizeX, newSizeY);
	}

	@Override
	public SpriteType getSpriteType() {
		return SpriteType.WALL_TILE;
	}

	@Override
	public void init() throws EInitException {
		useAnimation = false;
		super.init();


		try {
			wholeImage = ImageIO.read(new File("images\\fal1.png"));

			reset();
		} catch (IOException ioEx) {
			throw new EInitException(ioEx);
		}
	}

	@Override
	public void update(long gameTime) {
		super.update(gameTime);
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		Iterator<Entry<Rectangle, BufferedImage>> imgs = images.entrySet().iterator();
		while (imgs.hasNext()) {
			Entry<Rectangle, BufferedImage> entry = imgs.next();
			g.drawImage(entry.getValue(), entry.getKey().x, entry.getKey().y, null);
		}
	}

	@Override
	public List<Rectangle> checkCollision() {
		List<Rectangle> collidingRects = super.checkCollision();

		for (Rectangle rect : collidingRects) {
			images.remove(rect);
		}

		return collidingRects;
	}

	@Override
	public int getLayerNumber() {
		return 1;
	}

	@Override
	public void reset() {
		List<Rectangle> rects = new ArrayList<>();
		rects.add(bottomLeft);
		rects.add(bottomRight);
		rects.add(topLeft);
		rects.add(topRight);

		boundingPart = new BoundingPart(rects);

		images.put(topLeft, wholeImage.getSubimage(0, 0, Config.TILE_WIDTH / 2, Config.TILE_HEIGHT / 2));
		images.put(topRight, wholeImage.getSubimage(Config.TILE_WIDTH / 2, 0, Config.TILE_WIDTH / 2, Config.TILE_HEIGHT / 2));
		images.put(bottomLeft, wholeImage.getSubimage(0, Config.TILE_HEIGHT / 2, Config.TILE_WIDTH / 2, Config.TILE_HEIGHT / 2));
		images.put(bottomRight, wholeImage.getSubimage(Config.TILE_WIDTH / 2, Config.TILE_HEIGHT / 2, Config.TILE_WIDTH / 2, Config.TILE_HEIGHT / 2));
	}
}
