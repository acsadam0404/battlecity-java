package objects;

import math.Vector2;

/**
 * Mivel a tankok csak diszkrét irányban mozoghatnak, ez az osztály eltárolja ezeket az irányokat.
 * 
 * @author Ács Ádám
 * 2012.08.06.
 */
public enum Direction {
	NORTH(new Vector2(0, -1)), 
	SOUTH(new Vector2(0, 1)),
	EAST(new Vector2(1, 0)),
	WEST(new Vector2(-1, 0)), 
	ZERO(Vector2.ZERO);
	
	private Vector2 dir;
	private Direction(Vector2 dir) {
		this.dir = dir;
	}

	public Vector2 getVector2() {
		return dir;
	}
}
