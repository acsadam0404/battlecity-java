package math;

import java.awt.Point;
import java.awt.geom.Point2D;
/*FIXME most �sszevissza van minden. Static immutable met�dusok meg mutable objektum szint� void met�dusok kellen�nek */

/**
 * 
 * intek helyett floattal k�ne dolgoznia �s akkor szebben meg lehetne csin�lni a met�dusokat, pontosabbak lenn�nek
 * @author �cs �d�m
 * 2012.07.15.
 */
public class Vector2 {
	private int x;
	private int y;

	public static final Vector2 ZERO = new Vector2(0, 0);
	public static final Vector2 UP = new Vector2(0, -1);

	/**
	 * be�ll�tja a vektor poz�ci�j�t
	 * @param x
	 * @param y
	 */
	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * be�ll�tja a vektor x poz�ci�j�t
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * be�ll�tja a vektor y poz�ci�j�t
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * kivon egy vektort az eredetib�l
	 * @param other
	 * @return
	 */
	public Vector2 subtract(Vector2 other) {
		int newX = x - other.getX();
		int newY = y - other.getY();

		return new Vector2(newX, newY);
	}

	/**
	 * hozz�ad egy vektort az eredetihez
	 * @param other
	 * @return
	 */
	public Vector2 add(Vector2 other) {
		int newX = x + other.getX();
		int newY = y + other.getY();

		return new Vector2(newX, newY);
	}

	/**
	 * a vektor string reprezent�ci�ja
	 */
	@Override
	public String toString() {
		return "x=" + x + ", y=" + y;
	}

	/**
	 * visszaadja az x poz�ci�t 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * visszaadja az y poz�ci�t
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * hashcode a rendezhet�s�ghez
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
	 * k�t vektort �ssze kell tudnunk hasonl�tani
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
	 * kisz�m�tja k�t vektor k�z�tt a t�vols�got
	 * @param gridLocation
	 * @param gridLocation2
	 * @return
	 */
	public static float distance(Vector2 gridLocation, Vector2 gridLocation2) {
		return (float) Point2D.distance(gridLocation.getX(), gridLocation.getY(), gridLocation2.getX(), gridLocation2.getY());
	}
	
	/**
	 * kisz�m�tja a vektor hossz�t
	 * @return
	 */
	public double length() {
		return Math.sqrt(Math.pow(x, 2)+ Math.pow(y, 2));
	}

	/**
	 * normaliz�lja a vektort
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
	 * megszorozza a vektort egy skal�rral
	 * @param speed
	 * @return
	 */
	public Vector2 multiplyBy(float speed) {
		this.x *= speed;
		this.y *= speed;
		
		return this;
	}
	
	/**
	 * k�t vektort �sszeszoroz �s az eredm�nyvektort visszaadja
	 * @param v
	 * @param f
	 * @return
	 */
	public static Vector2 multiply(Vector2 v, float f) {
		return new Vector2((int)(v.getX() * f), (int)(v.getY() * f));
	}

	/**
	 * elosztja kett�vel a vektort 
	 * @return
	 */
	public Vector2 divideBy2() {
		return multiplyBy(0.5f);
	}
	
	/**
	 * elosztja a vektort egy skal�rral
	 * @param vec
	 * @param num
	 * @return
	 */
	public static Vector2 divide(Vector2 vec, float num) {
		return multiply(vec, 1 / num);
	}

	/**
	 * �sszead k�t vektort �s az eredm�nyt visszaadja
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static Vector2 add(Vector2 v1, Vector2 v2) {
		return new Vector2(v1.getX() + v2.getX(), v1.getY() + v2.getY());
	}

	/**
	 * Point-� konvert�l egy vektort
	 * @param v
	 * @return
	 */
	public static Point toPoint(Vector2 v) {
		return new Point(v.getX(), v.getY());
	}

	/**
	 * �sszeszoroz k�t vektort �s az eredm�nyt visszaadja
	 * @param vec1
	 * @param vec2
	 * @return
	 */
	public static int multiply(Vector2 vec1, Vector2 vec2) {
		int product = vec1.x * vec2.x + vec1.y * vec2.y;
		return product;
	}
}
