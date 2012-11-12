package objects.bonus;

import game.Registry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import math.Vector2;
import objects.Sprite;
import objects.units.tanks.EnemyTank;
import player.Player;

import components.animation.Animation;
import components.animation.AnimationFactory;

/**
 * A bónusz felvételekor meghal az összes ellenség a pályán
 * @author Ács Ádám
 *
 */
public class EnemyKiller extends Bonus {
	/**
	 * Létrehozza a bónuszt megadott pozícióra.
	 * @param pos
	 */
	public EnemyKiller(Vector2 pos) {
		super(pos);
	}

	/**
	 * Visszaadja a bónusz hatóidejét.
	 */
	@Override
	protected int getBonusTime() {
		return 0;
	}

	/**
	 * Megöli az összes ellenséget a mapon.
	 */
	@Override
	public void applyEffect(Player player) {
		List<Sprite> enemies = Registry.singleton().getEnemyTankRegistry();
		for (int i = enemies.size() - 1; i >= 0; i--) {
			EnemyTank tank = (EnemyTank) enemies.get(i);
			tank.kill();
		}
	}

	/**
	 * Nem szükséges, a bónusz rögtön hat és el is múlik.
	 */
	@Override
	public void removeEffect(Player player) {
		/* nincs szükség rá */
	}

	/**
	 * Beállítja a bónusz animációit.
	 */
	@Override
	protected Map<String, Animation> initClassAnimations() {
		Map<String, Animation> anims = new HashMap<String, Animation>();

		Animation base = AnimationFactory.createAnimation(this, "animations\\bonus\\enemykiller.xml", 200);
		base.setContainer(this);
		base.init();
		anims.put("base", base);

		return anims;
	}
}
