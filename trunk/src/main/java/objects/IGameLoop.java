package objects;

import java.awt.Graphics;

/**
 * A játékban résztvevõ Objectek interfésze, az init függvényt és a játékciklust deklarálja.
 * 
 * @author Ács Ádám
 * 2012.06.28.
 */
public interface IGameLoop extends IDrawable {
	public void init() throws EInitException;

	public void update(long gameTime);

	@Override
	public void draw(Graphics g);
}
