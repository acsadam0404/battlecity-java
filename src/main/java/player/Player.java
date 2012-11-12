package player;

import game.IResetable;

import java.awt.Graphics;

import objects.IGameLoop;

/**
 * A j�t�kosok �soszt�lya. K�l�nb�z� j�t�kosok lehetnek (AI �ltal ir�ny�tott, lok�lis, netes stb.) 
 * 
 * itt implement�lhat� sz�vets�g, ellens�g, mivel egy fajta j�t�kosb�l t�bb is lehet
 * 
 * @author �cs �d�m
 * 2012.08.26.
 */
public abstract class Player implements IGameLoop, IResetable  {
	/**
	 * L�trehozza a j�t�kost.
	 */
	public Player() {
		super();
	}

	/**
	 * Friss�ti a j�t�kost.
	 */
	@Override
	public abstract void update(long gameTime) ;

	/**
	 * Kirajzolja a j�t�kost
	 */
	@Override
	public abstract void draw(Graphics g);
}
