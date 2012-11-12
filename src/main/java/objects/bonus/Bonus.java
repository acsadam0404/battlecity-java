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
 * A bónusz osztály a játékos által felszedhetõ bónuszok õsosztálya, implementálja az általános mûveleteket.
 * @author Ács Ádám
 *
 */
public abstract class Bonus extends Sprite {
	protected Player player;
	protected boolean collided;
	protected boolean pending;

	/**
	 * Az effekteket leszedi a játékosról és megállítja a timer-t
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
	 * Létrehozza a bónuszt egy megadott pozícióra.
	 * @param pos
	 */
	public Bonus(Vector2 pos) {
		super(pos);
		passableByBullet = true;
	}

	/**
	 * Visszaadja, hogy mennyi ideig hat a bónusz.
	 * @return
	 */
	protected abstract int getBonusTime();

	/**
	 * Visszaadja a sprite típust.
	 */
	@Override
	public SpriteType getSpriteType() {
		return SpriteType.BONUS;
	}

	/**
	 * Visszaadja a bónusz méretét.
	 */
	@Override
	protected Vector2 initSize() {
		return new Vector2(Config.TILE_WIDTH, Config.TILE_HEIGHT);
	}

	/**
	 * Visszaadja az ütközõ részt.
	 */
	@Override
	public BoundingPart getBoundingPart() {
		return new BoundingPart(new Rectangle(pos.getX(), pos.getY(), size.getX(), size.getY()));
	}

	/**
	 * Ellenõrzi az ütközéseket.
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
	 * Beolvassa a képeket a fájlrendszerbõl.
	 */
	@Override
	public void init() throws EInitException {
		super.init();
	}

	/**
	 * Frissíti a bónuszt.
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
	 * A bónusz effektjét rátesszük a player-re.
	 * @param player
	 */
	public abstract void applyEffect(Player player);

	/**
	 * A bónusz effektje itt szûnik meg.
	 * @param player
	 */
	public abstract void removeEffect(Player player);

	/**
	 * Megmondja egy bónuszról, hogy felszedték-e.
	 * @return
	 */
	public boolean isCollided() {
		return collided;
	}

	/**
	 * A bónusz átmeneti állapotba tétele.
	 */
	public void pending() {
		pending = true;
	}
}
