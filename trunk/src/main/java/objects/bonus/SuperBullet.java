package objects.bonus;

import java.util.HashMap;
import java.util.Map;

import math.Vector2;
import player.LocalPlayer;
import player.Player;

import components.animation.Animation;
import components.animation.AnimationFactory;

/**
 * A bónusz felvételekor képes leszel átlõni a betonfalat
 * @author Ács Ádám
 *
 */
public class SuperBullet extends Bonus {
	/**
	 * Létrehozza a bónuszt a megadott pozícióra.
	 * @param pos
	 */
	public SuperBullet(Vector2 pos) {
		super(pos);
	}

	/**
	 * Beállítja az osztály animációit.
	 */
	@Override
	protected Map<String, Animation> initClassAnimations() {
		Map<String, Animation> anims = new HashMap<String, Animation>();

		Animation base = AnimationFactory.createAnimation(this, "animations\\bonus\\superbullet.xml", 200);
		base.setContainer(this);
		base.init();
		anims.put("base", base);

		return anims;
	}

	/**
	 * Superbullet-et ad a játékosnak.
	 */
	@Override
	public void applyEffect(Player player) {
		LocalPlayer localPlayer = (LocalPlayer) player;
		localPlayer.getTank().setSuperBullet(true);
	}

	/**
	 * A játékosnak többé nem lesz superbullet-je.
	 */
	@Override
	public void removeEffect(Player player) {
		LocalPlayer localPlayer = (LocalPlayer) player;
		localPlayer.getTank().setSuperBullet(false);
	}

	/**
	 * Visszaadja a bónusz hatóidejét.
	 */
	@Override
	protected int getBonusTime() {
		return 15000;
	}

}
