package math;

import java.awt.geom.AffineTransform;

/**
 * �ltal�nos matematikai met�dusok.
 * @author �cs �d�m
 * 2012.07.15.
 */
public final class MathHelper {
	private MathHelper() {
		/* private ctor */
	}
	
	
	/**
	 * Egy �rt�ket min �s max k�z� helyez el.
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
