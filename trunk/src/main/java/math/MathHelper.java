package math;

import java.awt.geom.AffineTransform;

/**
 * 
 * @author Ács Ádám
 * 2012.07.15.
 */
public final class MathHelper {
	private MathHelper() {
		/* private ctor */
	}
	
	public static int clamp(int val, int min, int max) {
		if (val < min) {
			return min;
		} else if (val > max) {
			return max;
		}
		
		return val;
	}

	/**
	 * FIXME rotationt vektorból kell számolni
	 * @param vector2
	 * @param imageCenterX
	 * @param imageCenterY
	 * @return
	 */
	public static AffineTransform getRotation(Vector2 vec, double imageCenterX, double imageCenterY) {
		int v = Vector2.multiply(Vector2.UP, vec);
		double cosDeg = v / (Vector2.UP.length() * vec.length());
		
		AffineTransform at = new AffineTransform();
		at.rotate(Math.acos(cosDeg), imageCenterX, imageCenterY);
		
		return at;
	}
}
