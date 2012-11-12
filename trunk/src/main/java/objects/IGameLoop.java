package objects;

import java.awt.Graphics;

/**
 * A j�t�kban r�sztvev� Objectek interf�sze, az init f�ggv�nyt �s a j�t�kciklust deklar�lja.
 * 
 * @author �cs �d�m
 * 2012.06.28.
 */
public interface IGameLoop extends IDrawable {
	/**
	 * f�jlb�l olvas�s
	 * @throws EInitException
	 */
	public void init() throws EInitException;

	/**
	 * j�t�kciklus friss�t�s m�velete
	 * @param gameTime
	 */
	public void update(long gameTime);

	/**
	 * j�t�kciklus rajzol�s m�velete
	 */
	@Override
	public void draw(Graphics g);
}
