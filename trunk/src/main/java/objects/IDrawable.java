package objects;

import java.awt.Graphics;

/**
 * olyan dolgokhoz, amik csak rajzolhat�k, de nem kell friss�teni meg ilyenek
 * @author �cs �d�m
 * 2012.07.12.
 */
public interface IDrawable {
	/**
	 * rajzol�st itt kell megval�s�tani
	 * @param g
	 */
	public void draw(Graphics g);
}
