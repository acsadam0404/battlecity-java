package objects.bonus;

import game.Config;
import game.IResetable;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Timer;

import math.Vector2;
import objects.EInitException;
import objects.IGameLoop;

/**
 * L�trehozza random helyen a b�nuszokat �s egy id� ut�n el is t�nteni �ket
 * @author �cs �d�m
 *
 */
public final class BonusManager implements IGameLoop, IResetable {
	private AbstractAction removeBonusAction = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			inactivateBonus();
			stopTimer();
		}
	};

	private Timer timer = new Timer(10000, removeBonusAction);
	private List<Bonus> bonuses = new ArrayList<Bonus>();
	private Bonus active;

	/**
	 * L�trehozza a BonusManager-t.
	 */
	public BonusManager() {
		super();
	}

	/**
	 * Le�ll�tja a timer-t.
	 */
	public void stopTimer() {
		timer.stop();
	}

	/**
	 * Inaktiv�lja az akt�v b�nuszt.
	 */
	public void inactivateBonus() {
		active = null;
	}

	/**
	 * Beolvassa a k�peket.
	 */
	@Override
	public void init() throws EInitException {
		Bonus superBullet = new SuperBullet(Config.OFFSCREEN);
		superBullet.init();

		Bonus enemyKiller = new EnemyKiller(Config.OFFSCREEN);
		enemyKiller.init();

		bonuses.add(superBullet);
		bonuses.add(enemyKiller);
	}

	/**
	 * Friss�ti a b�nusz �llapot�t.
	 */
	@Override
	public void update(long gameTime) {
		Random rnd = new Random();
		int rndInt = rnd.nextInt(101); /* rnd % */
		int rndBonus = rnd.nextInt(bonuses.size());

		if (rndInt < 10 && active == null) {
			Vector2 rndPos = new Vector2(rnd.nextInt(Config.SCREEN_WIDTH - Config.SCREEN_OFFSET_X - Config.TILE_WIDTH),
					rnd.nextInt(Config.SCREEN_HEIGHT - Config.SCREEN_OFFSET_Y - Config.TILE_HEIGHT));
			active = bonuses.get(rndBonus);
			active.setPos(rndPos);
			timer.start();
		}

		if (active != null) {
			active.update(gameTime);
			if (active.isCollided()) {
				active.pending();
			}
		}
	}

	/**
	 * Kirajzolja a b�nuszt.
	 */
	@Override
	public void draw(Graphics g) {
		if (active != null) {
			active.draw(g);
		}
	}

	/**
	 * Alap�rtelmezett �rt�kre �ll�tja a b�nuszt.
	 */
	@Override
	public void reset() {
		active = null;
	}
}
