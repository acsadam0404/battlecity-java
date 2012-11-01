package objects;

import java.awt.Graphics;

/**
 * olyan dolgokhoz, amik csak rajzolhatók, de nem kell frissíteni meg ilyenek
 * @author Ács Ádám
 * 2012.07.12.
 */
public interface IDrawable {
	public void draw(Graphics g);
}
