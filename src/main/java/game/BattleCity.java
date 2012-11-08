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
	
	public BattleCity(Program program) {
		super(program);
		addKeyListener(keyboard);
		requestFocus();
		setLayout(null);
		setIgnoreRepaint(true);

		optionsState = new OptionsState(this);
		playState = new PlayState(this);
	}

	public AbstractGameState getPlayState() {
		return playState;
	}

	public AbstractGameState getOptionsState() {
		return optionsState;
	}

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

	@Override
	public void update(long gameTime) {
		if (state != null) {
			keyboard.poll();
			state.keyPressed(keyboard);
			state.update(gameTime);
		}
	}

	@Override
	public void draw(Graphics g) {
		if (state != null) {
			state.draw(g);
		}
	}
	
	
//
//	@Override
//	public void keyTyped(KeyEvent e) {
//		/* nincs szükség rá */
//	}
//
//	@Override
//	public void keyPressed(KeyEvent e) {
//		boolean addKey = true;
//
//		for (KeyEvent key : keys) {
//			if (key.getKeyCode() == e.getKeyCode()) {
//				addKey = false;
//			}
//		}
//
//		if (addKey) {
//			keys.add(e);
//		}
//		state.keyPressed(keys);
//
//	}
//
//	@Override
//	public void keyReleased(KeyEvent e) {
//		for (int i = keys.size() - 1; i >= 0; i--) {
//			if (keys.get(i).getKeyCode() == e.getKeyCode()) {
//				keys.remove(i);
//			}
//		}
//	}

	public void exit() {
		program.exit();
	}

	public KeyListener getKeyboardInput() {
		return keyboard;
	}

}
