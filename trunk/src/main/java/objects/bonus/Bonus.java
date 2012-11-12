package objects.bonus;

import game.Config;
import game.Registry;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Timer;

import math.Vector2;
import objects.EInitException;
import objects.Sprite;
import objects.SpriteType;
import objects.units.tanks.Tank;
import player.LocalPlayer;
import player.Player;

import components.collision.BoundingPart;
import components.collision.Collision;

/**
 * A b�nusz oszt�ly a j�t�kos �ltal felszedhet� b�nuszok �soszt�lya, implement�lja az �ltal�nos m�veleteket.
 * @author �cs �d�m
 *
 */
public abstract class Bonus extends Sprite {
	protected Player player;
	protected boolean collided;
	protected boolean pending;

	/**
	 * Az effekteket leszedi a j�t�kosr�l �s meg�ll�tja a timer-t
	 */
	protected AbstractAction removeAction = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			removeEffect(player);
			timer.stop();
		}
	};

	protected Timer timer = new Timer(getBonusTime(), removeAction);

	/**
	 * L�trehozza a b�nuszt egy megadott poz�ci�ra.
	 * @param pos
	 */
	public Bonus(Vector2 pos) {
		super(pos);
		passableByBullet = true;
	}

	/**
	 * Visszaadja, hogy mennyi ideig hat a b�nusz.
	 * @return
	 */
	protected abstract int getBonusTime();

	/**
	 * Visszaadja a sprite t�pust.
	 */
	@Override
	public SpriteType getSpriteType() {
		return SpriteType.BONUS;
	}

	/**
	 * Visszaadja a b�nusz m�ret�t.
	 */
	@Override
	protected Vector2 initSize() {
		return new Vector2(Config.TILE_WIDTH, Config.TILE_HEIGHT);
	}

	/**
	 * Visszaadja az �tk�z� r�szt.
	 */
	@Override
	public BoundingPart getBoundingPart() {
		return new BoundingPart(new Rectangle(pos.getX(), pos.getY(), size.getX(), size.getY()));
	}

	/**
	 * Ellen�rzi az �tk�z�seket.
	 */
	protected void checkCollision() {
		List<Sprite> collidingTanks = Collision.intersects(this, Registry.singleton().getPlayerTankRegistry());
		if (!collidingTanks.isEmpty()) {
			for (Sprite sprite : collidingTanks) {
				player = ((Tank) sprite).getPlayer();
				if (player instanceof LocalPlayer) {
					applyEffect(player);
					timer.start();
					collided = true;
				}
			}

			Registry.singleton().unregister(this);
		}
	}

	/**
	 * Beolvassa a k�peket a f�jlrendszerb�l.
	 */
	@Override
	public void init() throws EInitException {
		super.init();
	}

	/**
	 * Friss�ti a b�nuszt.
	 */
	@Override
	public void update(long gameTime) {
		if (!pending) {
			checkCollision();
			super.update(gameTime);
		}
	}

	/**
	 * Kirajzolja az objektumot.
	 */
	@Override
	public void draw(Graphics g) {
		if (!pending) {
			super.draw(g);
		}
	}

	/**
	 * A b�nusz effektj�t r�tessz�k a player-re.
	 * @param player
	 */
	public abstract void applyEffect(Player player);

	/**
	 * A b�nusz effektje itt sz�nik meg.
	 * @param player
	 */
	public abstract void removeEffect(Player player);

	/**
	 * Megmondja egy b�nuszr�l, hogy felszedt�k-e.
	 * @return
	 */
	public boolean isCollided() {
		return collided;
	}

	/**
	 * A b�nusz �tmeneti �llapotba t�tele.
	 */
	public void pending() {
		pending = true;
	}
}
