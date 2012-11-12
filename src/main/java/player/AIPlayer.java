package player;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import objects.Direction;
import objects.EInitException;
import objects.units.tanks.Tank;

/**
 * Mesters�ges intelligencia �ltal ir�ny�tott j�t�kos, ami tankokat ir�ny�t.
 * @author �cs �d�m
 *
 */
public class AIPlayer extends Player {
	protected LocalPlayer player;
	protected List<Tank> tanks = new ArrayList<Tank>();

	/**
	 * Visszaadja az AI tankjait.
	 * @return
	 */
	public List<Tank> getTanks() {
		return tanks;
	}

	/**
	 * L�trehozza az AI-t, ami az adott j�t�kos ellen van.
	 * @param player
	 */
	public AIPlayer(LocalPlayer player) {
		super();
		this.player = player;
	}

	/**
	 * Hozz�ad egy tankot az AI tankjaihoz.
	 * @param tank
	 * @throws EInitException
	 */
	public void addTank(Tank tank) throws EInitException {
		Tank t = tank;
		t.init();
		t.setPlayer(this);
		tanks.add(t);
	}

	/**
	 * az ai implement�ci�ja
	 */
	@Override
	public void update(long gameTime) {
		for (int i = tanks.size() - 1; i >= 0; i--) {
			Tank tank = tanks.get(i);

			if (tank.getState().equals(Tank.State.DEAD)) {
				tank.reset();
				getTanks().remove(tank);
				continue;
			}

			Random rnd = new Random();
			int rndInt = rnd.nextInt(101); /* rnd % */

			if (rndInt < 2) {
				tank.setDirection(Direction.SOUTH);
			} else if (rndInt < 4) {
				tank.setDirection(Direction.EAST);
			} else if (rndInt < 6) {
				tank.setDirection(Direction.WEST);
			} else if (rndInt < 8) {
				tank.setDirection(Direction.NORTH);
			}

			tank.moveInDirection();

			rndInt = rnd.nextInt(101); /* rnd % */

			if (rndInt < 10) {
				tank.fire();
			}

			tank.update(gameTime);
		}
	}

	/**
	 * Kirajzolja az AI tankjait.
	 */
	@Override
	public void draw(Graphics g) {
		for (Tank tank : tanks) {
			tank.draw(g);
		}
	}

	/**
	 * Nem haszn�lt.
	 */
	@Override
	public void init() {

	}

	/**
	 * Alap�rtelmezett �rt�kekre �ll�tja az AI-t.
	 */
	@Override
	public void reset() {
		for (Tank tank : tanks) {
			tank.reset();
		}
		tanks.clear();
	}
}
