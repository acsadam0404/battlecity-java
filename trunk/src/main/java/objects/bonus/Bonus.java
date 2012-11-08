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
 * 
 * @author Ács Ádám
 *
 */
public abstract class Bonus extends Sprite {
	protected Player player;
	protected boolean collided;
	protected boolean pending;

	protected AbstractAction removeAction = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			removeEffect(player);
			timer.stop();
		}
	};

	protected Timer timer = new Timer(getBonusTime(), removeAction);

	public Bonus(Vector2 pos) {
		super(pos);
		passableByBullet = true;
	}

	protected abstract int getBonusTime();

	@Override
	public SpriteType getSpriteType() {
		return SpriteType.BONUS;
	}

	@Override
	protected Vector2 initSize() {
		return new Vector2(Config.TILE_WIDTH, Config.TILE_HEIGHT);
	}

	@Override
	public BoundingPart getBoundingPart() {
		return new BoundingPart(new Rectangle(pos.getX(), pos.getY(), size.getX(), size.getY()));
	}

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

	@Override
	public void init() throws EInitException {
		super.init();
	}

	@Override
	public void update(long gameTime) {
		if (!pending) {
			checkCollision();
			super.update(gameTime);
		}
	}

	@Override
	public void draw(Graphics g) {
		if (!pending) {
			super.draw(g);
		}
	}

	public abstract void applyEffect(Player player);

	public abstract void removeEffect(Player player);

	public boolean isCollided() {
		return collided;
	}

	public void pending() {
		pending = true;
	}
}
