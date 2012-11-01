package player;

import java.awt.Graphics;
import java.util.*;

import objects.Direction;
import objects.EInitException;
import objects.units.tanks.Tank;

/**
 * 
 * @author Ács Ádám
 *
 */
public class AIPlayer extends Player {
	protected LocalPlayer player;
	protected List<Tank> tanks = new ArrayList<>();
	
	public List<Tank> getTanks() {
		return tanks;
	}

	
	public AIPlayer(LocalPlayer player) {
		super();
		this.player = player;
	}
	

	public void addTank(Tank tank) throws EInitException {
		Tank t = tank;
		t.init();
		t.setPlayer(this);
		tanks.add(t);
	}

	
	/**
	 * az ai implementációja
	 */
	@Override
	public void update(long gameTime) {
		for (int i = tanks.size() - 1; i >= 0; i--) {
			Tank tank = tanks.get(i);
			
			if (tank.getState().equals(Tank.State.DEAD)){
				getTanks().remove(tank);
				continue;
			}

			Random rnd = new Random();
			int rndInt = rnd.nextInt(101); /* rnd % */

			if (rndInt < 3) {
				tank.setDirection(Direction.SOUTH);
			}
			else if (rndInt < 6) {
				tank.setDirection(Direction.EAST);
			}
			else if (rndInt < 9) {
				tank.setDirection( Direction.WEST);
			}
			else if (rndInt < 12) {
				tank.setDirection( Direction.NORTH);
			}
			
			tank.moveInDirection();
			
			rndInt = rnd.nextInt(101); /* rnd % */
			
			if (rndInt < 10) {
				tank.fire();
			}
			
			tank.update(gameTime);
		}
	}
	
	@Override
	public void draw(Graphics g) {
		for (Tank tank : tanks) {
			tank.draw(g);
		}
	}

	@Override
	public void init() {
		
	}


	@Override
	public void reset() {
		tanks.clear();
	}
}
