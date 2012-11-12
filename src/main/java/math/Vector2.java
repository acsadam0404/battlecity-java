package math;

import java.awt.Point;
import java.awt.geom.Point2D;
/*FIXME most összevissza van minden. Static immutable metódusok meg mutable objektum szintû void metódusok kellenének */

/**
 * 
 * intek helyett floattal kéne dolgoznia és akkor szebben meg lehetne csinálni a metódusokat, pontosabbak lennének
 * @author Ács Ádám
 * 2012.07.15.
 */
public class Vector2 {
	private int x;
	private int y;

	public static final Vector2 ZERO = new Vector2(0, 0);
	public static final Vector2 UP = new Vector2(0, -1);

	/**
	 * beállítja a vektor pozícióját
	 * @param x
	 * @param y
	 */
	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * beállítja a vektor x pozícióját
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * beállítja a vektor y pozícióját
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * kivon egy vektort az eredetibõl
	 * @param other
	 * @return
	 */
	public Vector2 subtract(Vector2 other) {
		int newX = x - other.getX();
		int newY = y - other.getY();

		return new Vector2(newX, newY);
	}

	/**
	 * hozzáad egy vektort az eredetihez
	 * @param other
	 * @return
	 */
	public Vector2 add(Vector2 other) {
		int newX = x + other.getX();
		int newY = y + other.getY();

		return new Vector2(newX, newY);
	}

	/**
	 * a vektor string reprezentációja
	 */
	@Override
	public String toString() {
		return "x=" + x + ", y=" + y;
	}

	/**
	 * visszaadja az x pozíciót 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * visszaadja az y pozíciót
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * hashcode a rendezhetõséghez
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/**
	 * két vektort össze kell tudnunk hasonlítani
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector2 other = (Vector2) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	/**
	 * kiszámítja két vektor között a távolságot
	 * @param gridLocation
	 * @param gridLocation2
	 * @return
	 */
	public static float distance(Vector2 gridLocation, Vector2 gridLocation2) {
		return (float) Point2D.distance(gridLocation.getX(), gridLocation.getY(), gridLocation2.getX(), gridLocation2.getY());
	}
	
	/**
	 * kiszámítja a vektor hosszát
	 * @return
	 */
	public double length() {
		return Math.sqrt(Math.pow(x, 2)+ Math.pow(y, 2));
	}

	/**
	 * normalizálja a vektort
	 * @return
	 */
	public Vector2 normalize() {
		double length = Math.sqrt((double) x * x + y * y);
		double newX = x / length;
		double newY = y / length;

		int returnX = 0;
		int returnY = 0;
		if (newX < 0d) {
			returnX = -1;
		} else if (newX > 0d) {
			returnX = 1;
		}
		if (newY < 0d) {
			returnY = -1;
		} else if (newY > 0d) {
			returnY = 1;
		}

		return new Vector2(returnX, returnY);

	}

	/**
	 * megszorozza a vektort egy skalárral
	 * @param speed
	 * @return
	 */
	public Vector2 multiplyBy(float speed) {
		this.x *= speed;
		this.y *= speed;
		
		return this;
	}
	
	/**
	 * két vektort összeszoroz és az eredményvektort visszaadja
	 * @param v
	 * @param f
	 * @return
	 */
	public static Vector2 multiply(Vector2 v, float f) {
		return new Vector2((int)(v.getX() * f), (int)(v.getY() * f));
	}

	/**
	 * elosztja kettõvel a vektort 
	 * @return
	 */
	public Vector2 divideBy2() {
		return multiplyBy(0.5f);
	}
	
	/**
	 * elosztja a vektort egy skalárral
	 * @param vec
	 * @param num
	 * @return
	 */
	public static Vector2 divide(Vector2 vec, float num) {
		return multiply(vec, 1 / num);
	}

	/**
	 * összead két vektort és az eredményt visszaadja
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static Vector2 add(Vector2 v1, Vector2 v2) {
		return new Vector2(v1.getX() + v2.getX(), v1.getY() + v2.getY());
	}

	/**
	 * Point-á konvertál egy vektort
	 * @param v
	 * @return
	 */
	public static Point toPoint(Vector2 v) {
		return new Point(v.getX(), v.getY());
	}

	/**
	 * összeszoroz két vektort és az eredményt visszaadja
	 * @param vec1
	 * @param vec2
	 * @return
	 */
	public static int multiply(Vector2 vec1, Vector2 vec2) {
		int product = vec1.x * vec2.x + vec1.y * vec2.y;
		return product;
	}
}
