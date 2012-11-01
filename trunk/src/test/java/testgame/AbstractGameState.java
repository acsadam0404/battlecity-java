package testgame;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import objects.EInitException;
import objects.IGameLoop;

public abstract class AbstractGameState implements IGameLoop {
	protected TestGame game;

	public AbstractGameState(TestGame game) {
		this.game = game;
	}

	@Override
	public void update(long gameTime) {
		return;
	}

	@Override
	public void init() throws EInitException {
		return;
	}

	public void onSet() {
		return;
	}

	public void keyPressed(KeyEvent e) {
		return;
	}
	
	@Override
	public void draw(Graphics g) {
		return;
	}
}
