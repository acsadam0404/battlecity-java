package objects.bonus;

import java.util.HashMap;
import java.util.Map;

import math.Vector2;
import player.LocalPlayer;
import player.Player;

import components.animation.Animation;
import components.animation.AnimationFactory;

/**
 * A b�nusz felv�telekor k�pes leszel �tl�ni a betonfalat
 * @author �cs �d�m
 *
 */
public class SuperBullet extends Bonus {
	/**
	 * L�trehozza a b�nuszt a megadott poz�ci�ra.
	 * @param pos
	 */
	public SuperBullet(Vector2 pos) {
		super(pos);
	}

	/**
	 * Be�ll�tja az oszt�ly anim�ci�it.
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
	 * Superbullet-et ad a j�t�kosnak.
	 */
	@Override
	public void applyEffect(Player player) {
		LocalPlayer localPlayer = (LocalPlayer) player;
		localPlayer.getTank().setSuperBullet(true);
	}

	/**
	 * A j�t�kosnak t�bb� nem lesz superbullet-je.
	 */
	@Override
	public void removeEffect(Player player) {
		LocalPlayer localPlayer = (LocalPlayer) player;
		localPlayer.getTank().setSuperBullet(false);
	}

	/**
	 * Visszaadja a b�nusz hat�idej�t.
	 */
	@Override
	protected int getBonusTime() {
		return 15000;
	}

}
