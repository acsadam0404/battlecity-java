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
 * A tile-ok kirajzol�s��rt �s friss�t�s��rt felel�s oszt�ly. Egy MapData nev� objektummal dolgozik, ami egy map reprezent�ci�j�t jelenti.
 * 
 * @author �cs �d�m
 * @version 2012.07.22.
 */
public final class TileMap implements IGameLoop, IResetable {
	private static final TileMap SINGLETON = new TileMap();
	private MapData data;
	
	/**
	 * be�ll�tja a mapot
	 * @param mapData
	 */
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
	
	/**
	 * singleton minta
	 * @return
	 */
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

	/**
	 * friss�ti a tile-okat
	 */
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
	
	/**
	 * kirajzolja az els� r�tegen l�v� tile-okat
	 * @param g
	 */
	public void drawFirstLayer(Graphics g) {
		for (int j = 0; j < data.getMapWidth(); j++) {
			for (int i = 0; i < data.getMapHeight(); i++) {
				if (data.getTile(j, i) != null && data.getTile(j, i).getLayerNumber() == 1) {
					data.getTile(j, i).draw(g);
				}
			}
		}
	}
	
	/**
	 * kirajzolja a m�sodik r�tegen l�v� tile-okat
	 * @param g
	 */
	public void drawSecondLayer(Graphics g) {
		for (int j = 0; j < data.getMapWidth(); j++) {
			for (int i = 0; i < data.getMapHeight(); i++) {
				if (data.getTile(j, i) != null && data.getTile(j, i).getLayerNumber() == 2) {
					data.getTile(j, i).draw(g);
				}
			}
		}
	}
	
	/**
	 * visszaadja az i, j helyen l�v� tile-t
	 * @param i
	 * @param j
	 * @return
	 */
	public Tile getTile(int i, int j) {
		return data.getTile(i, j);
	}
	
	/**
	 * visszaadja a pixelhez tartoz� poz�ci�t
	 * @param pixelPos
	 * @return
	 */
	public Vector2 getSquareAtPixel(Vector2 pixelPos) {
		return new Vector2(pixelPos.getX() / Config.TILE_WIDTH, pixelPos.getY() / Config.TILE_HEIGHT);
	}
	
	/**
	 * visszaadja a pixelhez tartoz� tile-t
	 * @param pixelPos
	 * @return
	 */
	public Tile getTileAtPixel(Vector2 pixelPos) {
		Vector2 square = getSquareAtPixel(pixelPos);
		return getTile(square.getX(), square.getY());
	}
	
	/**
	 * visszaadja a map sz�less�g�t
	 * @return
	 */
	public int getWidth() {
		return data.getMapWidth();
	}
	
	/**
	 * visszaadja a map magass�g�t
	 * @return
	 */
	public  int getHeight() {
		return data.getMapHeight();
	}
	
	/**
	 * megmondja egy poz�ci�n l�v� tile-r�l, hogy �tk�zhet-e
	 * @param pos
	 * @return
	 */
	public  boolean isCollidable(Vector2 pos) {
		return getTile(pos.getX(), pos.getY()).isCollidable();
	}
	
	/**
	 * megmondja egy pixelhez tartoz� tile-r�l, hogy �tk�zhet-e
	 * @param pixelPos
	 * @return
	 */
	public  boolean isCollidableByPixel(Vector2 pixelPos) {
		Vector2 pos = getSquareAtPixel(pixelPos);
		return isCollidable(pos);
	}

	/**
	 * egy poz�ci�t pixel poz�ci�ra konvert�l
	 * @param square
	 * @return
	 */
	public  Vector2 asPixelPos(Vector2 square) {
		return new Vector2(square.getX() * Config.TILE_WIDTH, square.getY() * Config.TILE_HEIGHT);
	}

	/**
	 * visszaad egy v�letlenszer� spawn pontot a mapba nmegadottak k�z�l
	 * @return
	 */
	public  Vector2 getRandomSpawnPoint() {
		return data.getRandomSpawnPoint();
	}

	/**
	 * a tile-okat vissza�ll�tja az alap�rtelmezettre
	 */
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
	 * Az oszt�ly nem v�gez semmilyen IO m�veletet, ez�rt ez nincs haszn�lva.
	 */
	@Override
	public void init() throws EInitException {
		/* nincs haszn�lva */
	}

	/**
	 * Mivel a Tile-okat k�l�n layereken kell kirazjolni, ez a draw met�dus nem el�g. 
	 * Ez�rt ez nincs implement�lva, helyette a drawFirstLayer �s drawSecondLayer met�dusokat kell haszn�lni.
	 */
	@Override
	public void draw(Graphics g) {
		/* nincs haszn�lva */
	}
}
