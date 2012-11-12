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
 * A b�nusz felv�telekor meghal az �sszes ellens�g a p�ly�n
 * @author �cs �d�m
 *
 */
public class EnemyKiller extends Bonus {
	/**
	 * L�trehozza a b�nuszt megadott poz�ci�ra.
	 * @param pos
	 */
	public EnemyKiller(Vector2 pos) {
		super(pos);
	}

	/**
	 * Visszaadja a b�nusz hat�idej�t.
	 */
	@Override
	protected int getBonusTime() {
		return 0;
	}

	/**
	 * Meg�li az �sszes ellens�get a mapon.
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
	 * Nem sz�ks�ges, a b�nusz r�gt�n hat �s el is m�lik.
	 */
	@Override
	public void removeEffect(Player player) {
		/* nincs sz�ks�g r� */
	}

	/**
	 * Be�ll�tja a b�nusz anim�ci�it.
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
