package math;

import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * FIXME most összevissza van minden. Static immutable metódusok meg mutable objektum szintû void metódusok kellenének
 * intek helyett floattal kéne dolgoznia és akkor szebben meg lehetne csinálni a metódusokat, pontosabbak lennének
 * @author Ács Ádám
 * 2012.07.15.
 */
public class Vector2 {
	private int x;
	private int y;

	public static final Vector2 ZERO = new Vector2(0, 0);
	public static final Vector2 UP = new Vector2(0, -1);

	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Vector2 subtract(Vector2 other) {
		int newX = x - other.getX();
		int newY = y - other.getY();

		return new Vector2(newX, newY);
	}

	public Vector2 add(Vector2 other) {
		int newX = x + other.getX();
		int newY = y + other.getY();

		return new Vector2(newX, newY);
	}

	@Override
	public String toString() {
		return "x=" + x + ", y=" + y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

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

	public static float distance(Vector2 gridLocation, Vector2 gridLocation2) {
		return (float) Point2D.distance(gridLocation.getX(), gridLocation.getY(), gridLocation2.getX(), gridLocation2.getY());
	}
	
	public double length() {
		return Math.sqrt(Math.pow(x, 2)+ Math.pow(y, 2));
	}

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

	public Vector2 multiplyBy(float speed) {
		this.x *= speed;
		this.y *= speed;
		
		return this;
	}
	
	public static Vector2 multiply(Vector2 v, float f) {
		return new Vector2((int)(v.getX() * f), (int)(v.getY() * f));
	}

	public Vector2 divideBy2() {
		return multiplyBy(0.5f);
	}
	
	public static Vector2 divide(Vector2 vec, float num) {
		return multiply(vec, 1 / num);
	}

	public static Vector2 add(Vector2 v1, Vector2 v2) {
		return new Vector2(v1.getX() + v2.getX(), v1.getY() + v2.getY());
	}

	public static Point toPoint(Vector2 v) {
		return new Point(v.getX(), v.getY());
	}

	public static int multiply(Vector2 vec1, Vector2 vec2) {
		int product = vec1.x * vec2.x + vec1.y * vec2.y;
		return product;
	}
}
