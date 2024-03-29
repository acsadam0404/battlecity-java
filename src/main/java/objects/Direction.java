package objects;

import math.Vector2;

/**
 * Mivel a tankok csak diszkr�t ir�nyban mozoghatnak, ez az oszt�ly elt�rolja ezeket az ir�nyokat.
 * 
 * @author �cs �d�m
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

	/**
	 * visszaadja az ir�nyhoz tartoz� vektort
	 * @return
	 */
	public Vector2 getVector2() {
		return dir;
	}
}
