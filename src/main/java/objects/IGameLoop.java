package objects;

import java.awt.Graphics;

/**
 * A játékban résztvevõ Objectek interfésze, az init függvényt és a játékciklust deklarálja.
 * 
 * @author Ács Ádám
 * 2012.06.28.
 */
public interface IGameLoop extends IDrawable {
	/**
	 * fájlból olvasás
	 * @throws EInitException
	 */
	public void init() throws EInitException;

	/**
	 * játékciklus frissítés mûvelete
	 * @param gameTime
	 */
	public void update(long gameTime);

	/**
	 * játékciklus rajzolás mûvelete
	 */
	@Override
	public void draw(Graphics g);
}
