package game.states;

import game.BattleCity;

import java.awt.Graphics;

import objects.EInitException;

public class ExitState extends AbstractGameState {
	public ExitState(BattleCity game) {
		super(game);
	}

	@Override
	public void init() throws EInitException {
		super.init();
	}

	@Override
	public void update(long gameTime) {
		super.update(gameTime);
	}

	@Override
	public void draw(Graphics g) {}

	@Override
	public void onSet() {
		System.out.println("ExitState");
	}

}
