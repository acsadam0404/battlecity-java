package game;

import java.util.ArrayList;
import java.util.List;

import objects.Sprite;
import objects.SpriteType;

/**
 * Egy központi registry, amibe minden Sprite regisztrálja magát. Lehetõvé teszi
 * a Sprite-ok globális elérését. Singleton minta az esetleges késõbbi
 * szerializáció miatt.
 * 
 * @author Ács Ádám
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
	 * a Sprite-okat beregisztráljuk a registry-be és utána globálisan
	 * elérhetõek
	 * 
	 * @param obj
	 */
	public void register(Sprite obj) {
		getRegistryForType(obj.getSpriteType()).add(obj);
	}

	/**
	 * a regisztrált sprite-okat kivesszük a registry-bõl
	 * 
	 * @param obj
	 */
	public void unregister(Sprite obj) {
		getRegistryForType(obj.getSpriteType()).remove(obj);
	}

	/**
	 * visszaadja a típushoz használt registry-t
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
	 * visszaadja a játékos tankok registry-jét
	 * 
	 * @return
	 */
	public List<Sprite> getPlayerTankRegistry() {
		return playerTankRegistry;
	}

	/**
	 * visszaadja a játékos golyójainak registry-jét
	 * 
	 * @return
	 */
	public List<Sprite> getPlayerBulletRegistry() {
		return playerBulletRegistry;
	}

	/**
	 * visszaadja az ellenséges tankok registry-jét
	 * 
	 * @return
	 */
	public List<Sprite> getEnemyTankRegistry() {
		return enemyTankRegistry;
	}

	/**
	 * visszaadja az ellenségek golyóinak registry-jét
	 * 
	 * @return
	 */
	public List<Sprite> getEnemyBulletRegistry() {
		return enemyBulletRegistry;
	}

	/**
	 * visszaadja a bónuszok registry-jét
	 * 
	 * @return
	 */
	public List<Sprite> getBonusRegistry() {
		return bonusRegistry;
	}

	/**
	 * visszaadja a tile-ok registry-jét
	 * 
	 * @return
	 */
	public List<Sprite> getTileRegistry() {
		return tileRegistry;
	}

	/**
	 * visszaadja az alapértelmezett registry-t
	 * @return
	 */
	public List<Sprite> getDefaultRegistry() {
		return defaultRegistry;
	}
}
