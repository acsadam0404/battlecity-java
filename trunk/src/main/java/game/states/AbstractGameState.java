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

	/**
	 * Egy state-nek tudnia kell a játékról, hogy a mûveleteit elérhesse.
	 * @param game
	 */
	public AbstractGameState(BattleCity game) {
		this.game = game;
	}

	/**
	 * frissíti az állapotot
	 */
	@Override
	public void update(long gameTime) {
	}

	/**
	 * fájlból beolvasásra ezt kell használni
	 */
	@Override
	public void init() throws EInitException {
	}

	/**
	 * Az állapotba lépéskor hajtódik végre.
	 */
	public void onSet() {
	}

	/**
	 * Az állapotból való kilépéskor hajtódik végre.
	 */
	public void onExit() {
	}
	
	/**
	 * kirajzolja a screen-t
	 */
	@Override
	public void draw(Graphics g) {
	}
	
	/**
	 * a billentyûzet kezelését itt kell megvalósítani
	 */
	@Override
	public void keyPressed(KeyboardInput keyboard) {
		
	}
}
