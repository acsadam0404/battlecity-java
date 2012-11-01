package objects.tiles;

import objects.EInitException;

/**
 * 
 * @author Ács Ádám
 *
 */
public final class TileFactory {
	private TileFactory() {
		super(); /* private constructor */
	}

	public static Tile getTile(int tileNumber, int posX, int posY) throws ENoSuchTileException, EInitException {
		Tile tile = null;
		
		switch (tileNumber) {
			case 0:
				return null;
			case 1:
				tile = new WallTile(posX, posY);
				break;
			case 2:
				tile = new ConcreteWallTile(posX, posY);
				break;
			case 3:
				tile = new GrassTile(posX, posY);
				break;
			case 9: break;
			case 4:
				tile = new WaterTile(posX, posY);
				break;
			default:
				throw new ENoSuchTileException();
		}
//		TILES.put(Integer.valueOf(0), null);
//		TILES.put(Integer.valueOf(1), ImageIO.read(new File("images\\fal1.png")));
//		TILES.put(Integer.valueOf(2), ImageIO.read(new File("images\\fal2.png")));
//		TILES.put(Integer.valueOf(3), ImageIO.read(new File("images\\fu.png")));
//		TILES.put(Integer.valueOf(4), ImageIO.read(new File("images\\viz.png")));
//		TILES.put(Integer.valueOf(9), ImageIO.read(new File("images\\egg.png")));
		if (tile != null) { 
			tile.init();
		}
		
		return tile;
	}
}
