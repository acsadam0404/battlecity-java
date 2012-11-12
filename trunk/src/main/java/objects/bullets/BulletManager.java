package objects.bullets;

import game.Config;

import java.awt.Graphics;

import math.Vector2;
import objects.*;
import objects.units.tanks.*;


/**
 * A lövedékek kezelésérõl gondoskodik. Minden tankhoz egy lövedék tartozik, ezért csak azt kell kezelnie.
 *  Megvalósítja a kilövést és a lövedékkel végezhetõ egyéb mûveleteket.
 * @author Ács Ádám
 *
 */
public class BulletManager implements IGameLoop {
	private Bullet bullet;
	private boolean fired;
	private Tank tank;
	
	/**
	 * Létrehozza az objektumot és beállítja a hozzárendelt tankot.
	 * @param tank
	 */
	public BulletManager(Tank tank) {
		this.tank = tank;
		if (tank instanceof PlayerTank) {
			bullet = new PlayerBullet();
		}
		else if (tank instanceof EnemyTank) {
			bullet = new EnemyBullet();
		}
	}
	
	/**
	 * A lövedék kilövése direction irányba.
	 * @param direction
	 * @return
	 */
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
	
	/**
	 * Frissíti az objektumot.
	 */
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

	/**
	 * Beolvassa a bullet-hez tartozó képeket.
	 */
	@Override
	public void init() throws EInitException {
		bullet.init();
	}

	/**
	 * Kirajzolja a lövedéket.
	 */
	@Override
	public void draw(Graphics g) {
		if (fired) {
			bullet.draw(g);
		}
	}
	
	/**
	 * Visszaadja a lövedéket.
	 * @return
	 */
	public Bullet getBullet() {
		return bullet;
	}

	/**
	 * Megmondja egy bullet-rõl, hogy kilõtték-e.
	 * @return
	 */
	public boolean isFired() {
		return fired;
	}

	/**
	 * Alapértelmezettre állítja a lövedéket.
	 */
	public void reset() {
		bullet.reset();
	}
}
