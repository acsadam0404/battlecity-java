package testgame.states;

import java.awt.Graphics;

import math.Vector2;
import objects.EInitException;
import objects.units.tanks.PlayerTank;
import objects.units.tanks.Tank;
import testgame.AbstractGameState;
import testgame.TestGame;

import components.animation.Animation;
import components.animation.AnimationFactory;

public class Explosion extends AbstractGameState {
	Tank tank = new PlayerTank(new Vector2(200, 200));
	Animation anim;
	public Explosion(TestGame game) {
		super(game);
	}

	@Override
	public void init() throws EInitException {
		anim = AnimationFactory.createAnimation(null, "animations\\explosion.xml", 100);
		tank.setAnimation(anim);
	}
	
	@Override
	public void update(long gameTime) {
		tank.update(gameTime);
	}
	
	@Override
	public void draw(Graphics g) {
		tank.draw(g);
	}

}
