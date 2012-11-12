package objects.bullets;

import game.Config;

import java.awt.Graphics;

import math.Vector2;
import objects.*;
import objects.units.tanks.*;


/**
 * A l�ved�kek kezel�s�r�l gondoskodik. Minden tankhoz egy l�ved�k tartozik, ez�rt csak azt kell kezelnie.
 *  Megval�s�tja a kil�v�st �s a l�ved�kkel v�gezhet� egy�b m�veleteket.
 * @author �cs �d�m
 *
 */
public class BulletManager implements IGameLoop {
	private Bullet bullet;
	private boolean fired;
	private Tank tank;
	
	/**
	 * L�trehozza az objektumot �s be�ll�tja a hozz�rendelt tankot.
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
	 * A l�ved�k kil�v�se direction ir�nyba.
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
	 * Friss�ti az objektumot.
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
	 * Beolvassa a bullet-hez tartoz� k�peket.
	 */
	@Override
	public void init() throws EInitException {
		bullet.init();
	}

	/**
	 * Kirajzolja a l�ved�ket.
	 */
	@Override
	public void draw(Graphics g) {
		if (fired) {
			bullet.draw(g);
		}
	}
	
	/**
	 * Visszaadja a l�ved�ket.
	 * @return
	 */
	public Bullet getBullet() {
		return bullet;
	}

	/**
	 * Megmondja egy bullet-r�l, hogy kil�tt�k-e.
	 * @return
	 */
	public boolean isFired() {
		return fired;
	}

	/**
	 * Alap�rtelmezettre �ll�tja a l�ved�ket.
	 */
	public void reset() {
		bullet.reset();
	}
}
