package objects.bullets;

import game.Config;

import java.awt.Graphics;

import math.Vector2;
import objects.*;
import objects.units.tanks.*;

/**
 * 
 * @author Ács Ádám
 *
 */
public class BulletManager implements IGameLoop {
	private Bullet bullet;
	private boolean fired;
	private Tank tank;
	
	public BulletManager(Tank tank) {
		this.tank = tank;
		if (tank instanceof PlayerTank) {
			bullet = new PlayerBullet();
		}
		else if (tank instanceof EnemyTank) {
			bullet = new EnemyBullet();
		}
	}
	
	public boolean fire(Direction direction) {
		if (!fired) {
			switch (direction) {
				case EAST:
					bullet.setPos(Vector2.add(tank.getPos(), new Vector2(Config.TILE_WIDTH - Config.BULLET_WIDTH, Config.TILE_HEIGHT / 2))); 
					break;
				case NORTH:
					bullet.setPos(Vector2.add(tank.getPos(), new Vector2(Config.TILE_WIDTH / 2 - Config.BULLET_WIDTH / 2 , 0))); 
					break;
				case SOUTH:
					bullet.setPos(Vector2.add(tank.getPos(), new Vector2(Config.TILE_WIDTH / 2 - Config.BULLET_WIDTH / 2, Config.TILE_HEIGHT - Config.BULLET_HEIGHT))); 
					break;
				case WEST:
					bullet.setPos(Vector2.add(tank.getPos(), new Vector2(0, Config.TILE_HEIGHT / 2))); 
					break;
				case ZERO:
					bullet.setPos(Vector2.add(tank.getPos(), new Vector2(Config.TILE_WIDTH / 2, Config.TILE_HEIGHT / 2))); 
					break;
			default:
				break;
			}
			
			fired = true;
			bullet.fire(direction);
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public void update(long gameTime) {
		if (fired) {
			if (bullet.isColliding()) {  
				fired = false;
				bullet.reset();
			}
			bullet.update(gameTime);
		}
	}

	@Override
	public void init() throws EInitException {
		bullet.init();
	}

	@Override
	public void draw(Graphics g) {
		if (fired) {
			bullet.draw(g);
		}
	}
	
	public Bullet getBullet() {
		return bullet;
	}

	public boolean isFired() {
		return fired;
	}

	public void reset() {
		bullet.reset();
	}
}
