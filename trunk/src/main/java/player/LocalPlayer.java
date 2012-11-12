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
 * Lokális játékost implementál. Egy játékoshoz egy tank tartozhat egyelõre.
 * 
 * @author Ács Ádám
 *
 */
public class LocalPlayer extends Player implements IKeyboardControlled{
	private PlayerTank tank;

	/**
	 * Létrehozza a játékost.
	 */
	public LocalPlayer() {
		super();
		tank = new PlayerTank(new Vector2(200, 200));
		tank.setPlayer(this);
	}

	/**
	 * Visszaadja a játékos tankját.
	 * @return
	 */
	public PlayerTank getTank() {
		return tank;
	}

	/**
	 * Visszaadja a tank középpontját.
	 * @return
	 */
	public Vector2 getTankCenter() {
		return tank.getCenter();
	}

	/**
	 * Beolvassa a képeket.
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
	 * Frissíti a tankot.
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
	 * Alapértelmezett értékekre állítja a játékost.
	 */
	@Override
	public void reset() {
		tank.reset();
		tank.setPos(new Vector2(4 * Config.TILE_WIDTH, 12 * Config.TILE_HEIGHT));
	}
}
