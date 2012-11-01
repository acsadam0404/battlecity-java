package map;

import game.Config;
import game.IResetable;
import game.Registry;

import java.awt.Graphics;

import math.Vector2;
import objects.EInitException;
import objects.IGameLoop;
import objects.tiles.Tile;

/**
 * A tile-ok kirajzolásáért és frissítéséért felelõs osztály. Egy MapData nevû objektummal dolgozik, ami egy map reprezentációját jelenti.
 * 
 * @author Ács Ádám
 * @version 2012.07.22.
 */
public final class TileMap implements IGameLoop, IResetable {
	private static final TileMap SINGLETON = new TileMap();
	private MapData data;
	
	public void setData(MapData mapData) {
		data = mapData;
		unregisterTiles();
		registerTiles();
	}

	private void registerTiles() {
		Registry.singleton().getTileRegistry().clear();
		for (int j = 0; j < data.getMapWidth(); j++) {
			for (int i = 0; i < data.getMapHeight(); i++) {
				if (data.getTile(j, i) != null) {
					Registry.singleton().register(data.getTile(j, i));
				}
			}
		}
	}
	
	public static final TileMap singleton() {
		return SINGLETON;
	}
	
	private void unregisterTiles() {
		Registry.singleton().getTileRegistry().clear();
		for (int j = 0; j < data.getMapWidth(); j++) {
			for (int i = 0; i < data.getMapHeight(); i++) {
				if (data.getTile(j, i) != null) {
					Registry.singleton().unregister(data.getTile(j, i));
				}
			}
		}
	}


	@Override
	public void update(long gameTime) {
		for (int j = 0; j < data.getMapWidth(); j++) {
			for (int i = 0; i < data.getMapHeight(); i++) {
				if (data.getTile(j, i) != null) {
					data.getTile(j, i).update(gameTime);
				}
			}
		}
	}
	
	public void drawFirstLayer(Graphics g) {
		for (int j = 0; j < data.getMapWidth(); j++) {
			for (int i = 0; i < data.getMapHeight(); i++) {
				if (data.getTile(j, i) != null && data.getTile(j, i).getLayerNumber() == 1) {
					data.getTile(j, i).draw(g);
				}
			}
		}
	}
	
	public void drawSecondLayer(Graphics g) {
		for (int j = 0; j < data.getMapWidth(); j++) {
			for (int i = 0; i < data.getMapHeight(); i++) {
				if (data.getTile(j, i) != null && data.getTile(j, i).getLayerNumber() == 2) {
					data.getTile(j, i).draw(g);
				}
			}
		}
	}
	
	public Tile getTile(int i, int j) {
		return data.getTile(i, j);
	}
	
	public Vector2 getSquareAtPixel(Vector2 pixelPos) {
		return new Vector2(pixelPos.getX() / Config.TILE_WIDTH, pixelPos.getY() / Config.TILE_HEIGHT);
	}
	
	public Tile getTileAtPixel(Vector2 pixelPos) {
		Vector2 square = getSquareAtPixel(pixelPos);
		return getTile(square.getX(), square.getY());
	}
	
	public int getWidth() {
		return data.getMapWidth();
	}
	
	public  int getHeight() {
		return data.getMapHeight();
	}
	
	public  boolean isCollidable(Vector2 pos) {
		return getTile(pos.getX(), pos.getY()).isCollidable();
	}
	
	public  boolean isCollidableByPixel(Vector2 pixelPos) {
		Vector2 pos = getSquareAtPixel(pixelPos);
		return isCollidable(pos);
	}

	public  Vector2 asPixelPos(Vector2 square) {
		return new Vector2(square.getX() * Config.TILE_WIDTH, square.getY() * Config.TILE_HEIGHT);
	}

	public  Vector2 getRandomSpawnPoint() {
		return data.getRandomSpawnPoint();
	}

	@Override
	public void reset() {
		for (int j = 0; j < data.getMapWidth(); j++) {
			for (int i = 0; i < data.getMapHeight(); i++) {
				if (data.getTile(j, i) != null) {
					data.getTile(j, i).reset();
				}
			}
		}
	}

	/**
	 * Az osztály nem végez semmilyen IO mûveletet, ezért ez nincs használva.
	 */
	@Override
	public void init() throws EInitException {
		/* nincs használva */
	}

	/**
	 * Mivel a Tile-okat külön layereken kell kirazjolni, ez a draw metódus nem elég. 
	 * Ezért ez nincs implementálva, helyette a drawFirstLayer és drawSecondLayer metódusokat kell használni.
	 */
	@Override
	public void draw(Graphics g) {
		/* nincs használva */
	}
}
