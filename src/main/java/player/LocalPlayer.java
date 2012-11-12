package player;

import game.Config;
import game.IKeyboardControlled;
import game.input.keyboard.KeyboardInput;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import math.Vector2;
import objects.Direction;
import objects.EInitException;
import objects.units.tanks.PlayerTank;

/**
 * Lok�lis j�t�kost implement�l. Egy j�t�koshoz egy tank tartozhat egyel�re.
 * 
 * @author �cs �d�m
 *
 */
public class LocalPlayer extends Player implements IKeyboardControlled{
	private PlayerTank tank;

	/**
	 * L�trehozza a j�t�kost.
	 */
	public LocalPlayer() {
		super();
		tank = new PlayerTank(new Vector2(200, 200));
		tank.setPlayer(this);
	}

	/**
	 * Visszaadja a j�t�kos tankj�t.
	 * @return
	 */
	public PlayerTank getTank() {
		return tank;
	}

	/**
	 * Visszaadja a tank k�z�ppontj�t.
	 * @return
	 */
	public Vector2 getTankCenter() {
		return tank.getCenter();
	}

	/**
	 * Beolvassa a k�peket.
	 */
	@Override
	public void init() throws EInitException {
		tank.init();
	}

	/**
	 * Kirajzolja a tankot.
	 */
	@Override
	public void draw(Graphics g) {
		tank.draw(g);
	}

	/**
	 * Friss�ti a tankot.
	 */
	@Override
	public void update(long gameTime) {
		tank.update(gameTime);
	}

	/**
	 * Kezeli az inputot.
	 */
	@Override
	public void keyPressed(KeyboardInput keyboard) {
			if (keyboard.keyDown(KeyEvent.VK_UP)) {
				tank.move(Direction.NORTH);
			}
			else if (keyboard.keyDown(KeyEvent.VK_DOWN)) {
				tank.move(Direction.SOUTH);
			}
			else if (keyboard.keyDown(KeyEvent.VK_LEFT)) {
				tank.move(Direction.WEST);
			}
			else if (keyboard.keyDown(KeyEvent.VK_RIGHT)) {
				tank.move(Direction.EAST);
			}
			if (keyboard.keyDown(KeyEvent.VK_SPACE)) {
				tank.fire();
			}
	}

	/**
	 * Alap�rtelmezett �rt�kekre �ll�tja a j�t�kost.
	 */
	@Override
	public void reset() {
		tank.reset();
		tank.setPos(new Vector2(4 * Config.TILE_WIDTH, 12 * Config.TILE_HEIGHT));
	}
}
