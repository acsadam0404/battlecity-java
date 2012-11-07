package map;

import game.Config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import math.Vector2;
import objects.EInitException;
import objects.tiles.ENoSuchTileException;
import objects.tiles.Tile;
import objects.tiles.TileFactory;

/**
 * @author Ács Ádám
 * beolvas egy tilemapot egy adott txt fájlból, hogy aztán átadhassuk a map
 * konstruktorának
 * 
 */
public class MapData {
	private Tile[][] tileMap;
	private List<Vector2> spawnPoints = new ArrayList<Vector2>();

	private int mapWidth;
	private int mapHeight;

	public MapData(String fileName) throws ENoSuchTileException, EInitException {
		readFromFile(fileName);
	}

	private void readFromFile(String fileName) throws ENoSuchTileException, EInitException {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(fileName));

			mapWidth = sc.nextInt();
			mapHeight = sc.nextInt();

			tileMap = new Tile[mapWidth][mapHeight];

			for (int j = 0; j < mapHeight; j++) {
				for (int i = 0; i < mapWidth && sc.hasNextInt(); i++) {

					int tileNum = sc.nextInt();
					tileMap[i][j] = TileFactory.getTile(tileNum, i * Config.TILE_WIDTH, j * Config.TILE_HEIGHT);

				}
			}

			while (sc.hasNextInt()) {
				int a = sc.nextInt() * Config.TILE_WIDTH;
				int b = sc.nextInt() * Config.TILE_HEIGHT;
				spawnPoints.add(new Vector2(a, b));
			}

		} catch (IOException ioEx) {
			throw new ENoSuchTileException(); /* XXX ez elrejti az ioEx-et, pedig nemkéne*/
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}

	Tile getTile(int i, int j) {
		return tileMap[i][j];
	}

	int getMapHeight() {
		return mapHeight;
	}

	int getMapWidth() {
		return mapWidth;
	}

	public Vector2 getRandomSpawnPoint() {
		Random rnd = new Random();
		return spawnPoints.get(rnd.nextInt(spawnPoints.size()));
	}
}
