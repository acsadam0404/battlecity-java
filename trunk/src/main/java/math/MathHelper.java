package math;

import java.awt.geom.AffineTransform;

/**
 * Általános matematikai metódusok.
 * @author Ács Ádám
 * 2012.07.15.
 */
public final class MathHelper {
	private MathHelper() {
		/* private ctor */
	}
	
	
	/**
	 * Egy értéket min és max közé helyez el.
	 * Ha min < x < max -> x = x
	 * ha min > x -> x = min
	 * ha max < x -> x = max
	 * @param val
	 * @param min
	 * @param max
	 * @return
	 */
	public static int clamp(int val, int min, int max) {
		if (val < min) {
			return min;
		} else if (val > max) {
			return max;
		}
		
		return val;
	}
}
