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
	public EnemyKiller(Vector2 pos) {
		super(pos);
	}

	@Override
	protected int getBonusTime() {
		return 0;
	}

	@Override
	public void applyEffect(Player player) {
		List<Sprite> enemies = Registry.singleton().getEnemyTankRegistry();
		for (int i = enemies.size() - 1; i >= 0; i--) {
			EnemyTank tank = (EnemyTank) enemies.get(i);
			tank.kill();
		}
	}

	@Override
	public void removeEffect(Player player) {
		/* nincs szükség rá */
	}

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
