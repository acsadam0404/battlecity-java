package game;

import game.input.keyboard.KeyboardInput;
import game.states.AbstractGameState;
import game.states.OptionsState;
import game.states.PlayState;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import objects.EInitException;

/**
 * Az osztály a játék magja, implementálja a játékciklust és innen vezérelhetõ az egész játék. 
 * State-ekkel dolgozik a képernyõkezelés megkönnyítése érdekében.
 * 
 * @author Ács Ádám
 *
 */
public final class BattleCity extends Game {
	private AbstractGameState playState;
	private AbstractGameState optionsState;

	private AbstractGameState state;

	private KeyboardInput keyboard = new KeyboardInput();
	
	/**
	 * létrehozza a játékot és beállítja a fontosabb paramétereit.
	 * @param program
	 */
	public BattleCity(Program program) {
		super(program);
		addKeyListener(keyboard);
		requestFocus();
		setLayout(null);
		setIgnoreRepaint(true);

		optionsState = new OptionsState(this);
		playState = new PlayState(this);
	}

	/**
	 * visszaadja a játék képernyõt
	 * @return
	 */
	public AbstractGameState getPlayState() {
		return playState;
	}

	/**
	 * visszaadja a menü képernyõt
	 * @return
	 */
	public AbstractGameState getOptionsState() {
		return optionsState;
	}

	/**
	 * Beállítja a state-t (képernyõt) és elvégzi a szükséges lépéseket, hogy használható legyen.
	 * @param state
	 */
	public void setState(AbstractGameState state) {
		if (this.state != null) {
			this.state.onExit();
		}
		this.removeAll(); /* leszedi az összes componenst */
		state.onSet();
		this.state = state;

	}

	/**
	 * Minden state init-jét meghívja, így a játék elején betöltõdik minden. Elõször a loading state lesz megjelenítve
	 * a végén pedig átadja a vezérlést a menü state-nek. Innentõl a statek már önállóan tudnak gondoskodni magukról.
	 */
	@Override
	public void init() {
		try {
			playState.init();
			optionsState.init();
		} catch (EInitException initEx) {
			initEx.printStackTrace();
			/* IMPROVE hiba esetén valami hibaképernyõre lehetne menni */
		}

		setState(optionsState);
	}

	/**
	 * a játékciklus frissítés része. frissíti az állapotokat
	 */
	@Override
	public void update(long gameTime) {
		if (state != null) {
			keyboard.poll();
			state.keyPressed(keyboard);
			state.update(gameTime);
		}
	}

	/**
	 * a játékciklus rajzolása. kirajzolja az aktuális képernyõt.
	 */
	@Override
	public void draw(Graphics g) {
		if (state != null) {
			state.draw(g);
		}
	}

	/**
	 * kilép a programból
	 */
	public void exit() {
		program.exit();
	}

	/**
	 * visszaadja a billentyûzetet figyelõ objektumot
	 * @return
	 */
	public KeyListener getKeyboardInput() {
		return keyboard;
	}

}
