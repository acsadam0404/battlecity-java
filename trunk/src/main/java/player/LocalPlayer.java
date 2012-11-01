package player;

import game.IKeyboardControlled;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.List;

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

	public LocalPlayer() {
		super();
		tank = new PlayerTank(new Vector2(200, 200));
		tank.setPlayer(this);
	}

	public PlayerTank getTank() {
		return tank;
	}

	public Vector2 getTankCenter() {
		return tank.getCenter();
	}

	@Override
	public void init() throws EInitException {
		tank.init();
	}

	@Override
	public void draw(Graphics g) {
		tank.draw(g);
	}

	@Override
	public void update(long gameTime) {
		tank.update(gameTime);
	}

	@Override
	public void keyPressed(List<KeyEvent> keys) {
		for (KeyEvent e : keys) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				tank.move(Direction.NORTH);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				tank.move(Direction.SOUTH);
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				tank.move(Direction.WEST);
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				tank.move(Direction.EAST);
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				tank.fire();
			}
		}
	}

	@Override
	public void reset() {
		tank.reset();
		tank.setPos(new Vector2(200, 200));
	}
}
