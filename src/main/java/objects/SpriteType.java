package objects;

/**
 * A registry használatához a sprite-oknak meg kell tudniuk mondani magukról milyen típusúak.
 * @author Ács Ádám
 *
 */
public enum SpriteType {
	PLAYER_TANK,
	PLAYER_BULLET,
	ENEMY_TANK,
	ENEMY_BULLET,
	TILE,
	BONUS, 
	WATER_TILE, 
	WALL_TILE, 
	CONCRETEWALL_TILE,
	GRASS_TILE, 
	EGG_TILE;
}
