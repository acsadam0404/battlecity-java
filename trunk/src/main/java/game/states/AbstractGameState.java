package game.states;

import game.BattleCity;
import game.IKeyboardControlled;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.List;

import objects.EInitException;
import objects.IGameLoop;

/**
 * A képernyõk õsosztálya.
 * @author Ács Ádám
 *
 */
public abstract class AbstractGameState implements IGameLoop, IKeyboardControlled {
	protected BattleCity game;

	public AbstractGameState(BattleCity game) {
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

	public void onExit() {
		return;
	}
	
	@Override
	public void keyPressed(List<KeyEvent> keys) {
		return;
	}
	
	@Override
	public void draw(Graphics g) {
		return;
	}
}
