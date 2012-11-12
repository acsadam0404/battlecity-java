package player;

import game.IResetable;

import java.awt.Graphics;

import objects.IGameLoop;

/**
 * A játékosok õsosztálya. Különbözõ játékosok lehetnek (AI által irányított, lokális, netes stb.) 
 * 
 * itt implementálható szövetség, ellenség, mivel egy fajta játékosból több is lehet
 * 
 * @author Ács Ádám
 * 2012.08.26.
 */
public abstract class Player implements IGameLoop, IResetable  {
	/**
	 * Létrehozza a játékost.
	 */
	public Player() {
		super();
	}

	/**
	 * Frissíti a játékost.
	 */
	@Override
	public abstract void update(long gameTime) ;

	/**
	 * Kirajzolja a játékost
	 */
	@Override
	public abstract void draw(Graphics g);
}
