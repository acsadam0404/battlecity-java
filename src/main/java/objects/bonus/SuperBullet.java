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
	public SuperBullet(Vector2 pos) {
		super(pos);
	}

	@Override
	protected Map<String, Animation> initClassAnimations() {
		Map<String, Animation> anims = new HashMap<String, Animation>();

		Animation base = AnimationFactory.createAnimation(this, "animations\\bonus\\superbullet.xml", 200);
		base.setContainer(this);
		base.init();
		anims.put("base", base);

		return anims;
	}

	@Override
	public void applyEffect(Player player) {
		LocalPlayer localPlayer = (LocalPlayer) player;
		localPlayer.getTank().setSuperBullet(true);
	}

	@Override
	public void removeEffect(Player player) {
		LocalPlayer localPlayer = (LocalPlayer) player;
		localPlayer.getTank().setSuperBullet(false);
	}

	@Override
	protected int getBonusTime() {
		return 15000;
	}

}
