package game;

import java.util.ArrayList;
import java.util.List;

import objects.Sprite;
import objects.SpriteType;

/**
 * Egy k�zponti registry, amibe minden Sprite regisztr�lja mag�t. Lehet�v� teszi a Sprite-ok glob�lis el�r�s�t.
 * Singleton minta az esetleges k�s�bbi szerializ�ci� miatt.
 * 
 * @author �cs �d�m
 *
 */
public class Registry {
	private static Registry singleton = new Registry();
	private Registry() {
		/* singleton */
	}

	private List<Sprite> playerTankRegistry = new ArrayList<>();
	private List<Sprite> playerBulletRegistry = new ArrayList<>();
	private List<Sprite> enemyTankRegistry = new ArrayList<>();
	private List<Sprite> enemyBulletRegistry = new ArrayList<>();
	private List<Sprite> bonusRegistry = new ArrayList<>();
	private List<Sprite> tileRegistry = new ArrayList<>();
	private List<Sprite> defaultRegistry = new ArrayList<>();

	public static Registry singleton() {
		return singleton;
	}

	public void register(Sprite obj) {
		getRegistryForType(obj.getSpriteType()).add(obj);
	}

	public void unregister(Sprite obj) {
		getRegistryForType(obj.getSpriteType()).remove(obj);
	}

	/**
	 * visszaadja a t�pushoz haszn�lt registry-t
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
	
	public List<Sprite> getPlayerTankRegistry() {
		return playerTankRegistry;
	}

	public List<Sprite> getPlayerBulletRegistry() {
		return playerBulletRegistry;
	}

	public List<Sprite> getEnemyTankRegistry() {
		return enemyTankRegistry;
	}

	public List<Sprite> getEnemyBulletRegistry() {
		return enemyBulletRegistry;
	}

	public List<Sprite> getBonusRegistry() {
		return bonusRegistry;
	}

	public List<Sprite> getTileRegistry() {
		return tileRegistry;
	}

	public List<Sprite> getDefaultRegistry() {
		return defaultRegistry;
	}
}
