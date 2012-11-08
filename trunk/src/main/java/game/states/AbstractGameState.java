package game.states;

import game.BattleCity;
import game.IKeyboardControlled;
import game.input.keyboard.KeyboardInput;

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
	}

	@Override
	public void init() throws EInitException {
	}

	public void onSet() {
	}

	public void onExit() {
	}
	
	@Override
	public void draw(Graphics g) {
	}
	
	@Override
	public void keyPressed(KeyboardInput keyboard) {
		
	}
}
