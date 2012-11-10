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

	public static Tile getTile(int tileNumber, int posX, int posY)
			throws ENoSuchTileException, EInitException {
		Tile tile;

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
		case 9:
			tile = new EggTile(posX, posY);
			break;
		case 4:
			tile = new WaterTile(posX, posY);
			break;
		default:
			throw new ENoSuchTileException();
		}

		tile.init();

		return tile;
	}
}
