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
 * A k�perny�k �soszt�lya.
 * @author �cs �d�m
 *
 */
public abstract class AbstractGameState implements IGameLoop, IKeyboardControlled {
	protected BattleCity game;

	/**
	 * Egy state-nek tudnia kell a j�t�kr�l, hogy a m�veleteit el�rhesse.
	 * @param game
	 */
	public AbstractGameState(BattleCity game) {
		this.game = game;
	}

	/**
	 * friss�ti az �llapotot
	 */
	@Override
	public void update(long gameTime) {
	}

	/**
	 * f�jlb�l beolvas�sra ezt kell haszn�lni
	 */
	@Override
	public void init() throws EInitException {
	}

	/**
	 * Az �llapotba l�p�skor hajt�dik v�gre.
	 */
	public void onSet() {
	}

	/**
	 * Az �llapotb�l val� kil�p�skor hajt�dik v�gre.
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
	 * a billenty�zet kezel�s�t itt kell megval�s�tani
	 */
	@Override
	public void keyPressed(KeyboardInput keyboard) {
		
	}
}
