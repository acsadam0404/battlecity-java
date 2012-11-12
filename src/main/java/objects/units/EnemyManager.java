package objects.units;

import game.Config;
import game.IResetable;
import game.Registry;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import map.TileMap;
import math.Vector2;
import objects.*;
import objects.units.tanks.*;
import objects.units.tanks.Tank.State;
import player.AIPlayer;
import player.LocalPlayer;

import components.collision.BoundingPart;
import components.collision.Collision;

/**
 * Az ellenségeket irányítja, hozza létre és irányítja.
 * 
 * @author Ács Ádám
 *
 */
public class EnemyManager implements IGameLoop, IResetable  {
	private AIPlayer enemy;
	private LocalPlayer player;
	
	private int killedEnemies;
	
	/**
	 * Létrehozza az objektumot és beállítja a játékost, amire céloznia kell.
	 * @param player
	 */
	public EnemyManager(LocalPlayer player) {
		this.player = player;
		enemy = new AIPlayer(player);
	}

	/**
	 * Beolvassa a képeket.
	 */
	@Override
	public void init() {
		enemy.init();
	}

	/**
	 * Frissíti az AI-t, lényegében itt határozzuk meg az AI mûködését.
	 */
	@Override
	public void update(long gameTime) {
		if (Config.AI_ON) {
			List<Sprite> enemies = Registry.singleton().getEnemyTankRegistry();
			for (int i = enemies.size() - 1; i >= 0; i--) {
				EnemyTank tank = (EnemyTank) enemies.get(i);
				if (tank.getState() == State.DEAD) {
					killedEnemies++;
				}
			}
			
			Random rnd = new Random();
			int rndInt = rnd.nextInt(101); /* rnd % */

			boolean spawn = true;

			Vector2 spawnPoint = TileMap.singleton().getRandomSpawnPoint();
			Rectangle spawnRect = new Rectangle(spawnPoint.getX(), spawnPoint.getY(), 40, 40);

			for (Tank tank : enemy.getTanks()) {
				if (Collision.isColliding(new BoundingPart(spawnRect), tank.getBoundingPart())) {
					spawn = false;
				}
			}
			
			for (Sprite tank : Registry.singleton().getPlayerTankRegistry()) {
				if (Collision.isColliding(new BoundingPart(spawnRect), tank.getBoundingPart())) {
					spawn = false;
				}
			}
			if (rndInt < 2 && spawn == true) {
				try {
					enemy.addTank(new LightTank(spawnPoint));
				} catch (EInitException e) {
					e.printStackTrace(); 
				}
			}

			enemy.update(gameTime);
		}
	}

	/**
	 * Kirajzolja az animációt.
	 */
	@Override
	public void draw(Graphics g) {
		enemy.draw(g);
	}
	
	/**
	 * Alapértelmezett állapotok visszaállítása.
	 */
	@Override
	public void reset() {
		enemy.reset();
	}

	/**
	 * Visszaadja, az eddig megölt ellenségeket.
	 * @return
	 */
	public int getKilledEnemies() {
		return killedEnemies;
	}
	
	/**
	 * A pályán levõ összes ellenséges tankot megöli.
	 */
	public void killAllEnemies() {
		List<Sprite> enemies = Registry.singleton().getEnemyTankRegistry();
		for (int i = enemies.size() - 1; i >= 0; i--) {
			EnemyTank tank = (EnemyTank) enemies.get(i);
			tank.kill();
			killedEnemies++;
		}
	}

}
