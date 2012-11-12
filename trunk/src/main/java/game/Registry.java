package game;

import java.util.ArrayList;
import java.util.List;

import objects.Sprite;
import objects.SpriteType;

/**
 * Egy k�zponti registry, amibe minden Sprite regisztr�lja mag�t. Lehet�v� teszi
 * a Sprite-ok glob�lis el�r�s�t. Singleton minta az esetleges k�s�bbi
 * szerializ�ci� miatt.
 * 
 * @author �cs �d�m
 * 
 */
public class Registry {
	private static Registry singleton = new Registry();

	private Registry() {
		/* singleton */
	}

	private List<Sprite> playerTankRegistry = new ArrayList<Sprite>();
	private List<Sprite> playerBulletRegistry = new ArrayList<Sprite>();
	private List<Sprite> enemyTankRegistry = new ArrayList<Sprite>();
	private List<Sprite> enemyBulletRegistry = new ArrayList<Sprite>();
	private List<Sprite> bonusRegistry = new ArrayList<Sprite>();
	private List<Sprite> tileRegistry = new ArrayList<Sprite>();
	private List<Sprite> defaultRegistry = new ArrayList<Sprite>();

	/**
	 * singleton minta
	 * 
	 * @return
	 */
	public static Registry singleton() {
		return singleton;
	}

	/**
	 * a Sprite-okat beregisztr�ljuk a registry-be �s ut�na glob�lisan
	 * el�rhet�ek
	 * 
	 * @param obj
	 */
	public void register(Sprite obj) {
		getRegistryForType(obj.getSpriteType()).add(obj);
	}

	/**
	 * a regisztr�lt sprite-okat kivessz�k a registry-b�l
	 * 
	 * @param obj
	 */
	public void unregister(Sprite obj) {
		getRegistryForType(obj.getSpriteType()).remove(obj);
	}

	/**
	 * visszaadja a t�pushoz haszn�lt registry-t
	 * 
	 * @param spriteType
	 * @return
	 */
	private List<Sprite> getRegistryForType(SpriteType spriteType) {
		List<Sprite> registry = null;
		switch (spriteType) {
		case BONUS:
			registry = bonusRegistry;
			break;
		case ENEMY_BULLET:
			registry = enemyBulletRegistry;
			break;
		case ENEMY_TANK:
			registry = enemyTankRegistry;
			break;
		case PLAYER_BULLET:
			registry = playerBulletRegistry;
			break;
		case PLAYER_TANK:
			registry = playerTankRegistry;
			break;
		case TILE:
			registry = tileRegistry;
			break;
		case EGG_TILE:
			registry = tileRegistry;
			break;
		case CONCRETEWALL_TILE:
			registry = tileRegistry;
			break;
		case WALL_TILE:
			registry = tileRegistry;
			break;
		case GRASS_TILE:
			registry = tileRegistry;
			break;
		case WATER_TILE:
			registry = tileRegistry;
			break;
		default:
			registry = defaultRegistry;
			break;
		}

		return registry;
	}

	/**
	 * visszaadja a j�t�kos tankok registry-j�t
	 * 
	 * @return
	 */
	public List<Sprite> getPlayerTankRegistry() {
		return playerTankRegistry;
	}

	/**
	 * visszaadja a j�t�kos goly�jainak registry-j�t
	 * 
	 * @return
	 */
	public List<Sprite> getPlayerBulletRegistry() {
		return playerBulletRegistry;
	}

	/**
	 * visszaadja az ellens�ges tankok registry-j�t
	 * 
	 * @return
	 */
	public List<Sprite> getEnemyTankRegistry() {
		return enemyTankRegistry;
	}

	/**
	 * visszaadja az ellens�gek goly�inak registry-j�t
	 * 
	 * @return
	 */
	public List<Sprite> getEnemyBulletRegistry() {
		return enemyBulletRegistry;
	}

	/**
	 * visszaadja a b�nuszok registry-j�t
	 * 
	 * @return
	 */
	public List<Sprite> getBonusRegistry() {
		return bonusRegistry;
	}

	/**
	 * visszaadja a tile-ok registry-j�t
	 * 
	 * @return
	 */
	public List<Sprite> getTileRegistry() {
		return tileRegistry;
	}

	/**
	 * visszaadja az alap�rtelmezett registry-t
	 * @return
	 */
	public List<Sprite> getDefaultRegistry() {
		return defaultRegistry;
	}
}
