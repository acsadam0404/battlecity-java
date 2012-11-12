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
 * Az oszt�ly a j�t�k magja, implement�lja a j�t�kciklust �s innen vez�relhet� az eg�sz j�t�k. 
 * State-ekkel dolgozik a k�perny�kezel�s megk�nny�t�se �rdek�ben.
 * 
 * @author �cs �d�m
 *
 */
public final class BattleCity extends Game {
	private AbstractGameState playState;
	private AbstractGameState optionsState;

	private AbstractGameState state;

	private KeyboardInput keyboard = new KeyboardInput();
	
	/**
	 * l�trehozza a j�t�kot �s be�ll�tja a fontosabb param�tereit.
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
	 * visszaadja a j�t�k k�perny�t
	 * @return
	 */
	public AbstractGameState getPlayState() {
		return playState;
	}

	/**
	 * visszaadja a men� k�perny�t
	 * @return
	 */
	public AbstractGameState getOptionsState() {
		return optionsState;
	}

	/**
	 * Be�ll�tja a state-t (k�perny�t) �s elv�gzi a sz�ks�ges l�p�seket, hogy haszn�lhat� legyen.
	 * @param state
	 */
	public void setState(AbstractGameState state) {
		if (this.state != null) {
			this.state.onExit();
		}
		this.removeAll(); /* leszedi az �sszes componenst */
		state.onSet();
		this.state = state;

	}

	/**
	 * Minden state init-j�t megh�vja, �gy a j�t�k elej�n bet�lt�dik minden. El�sz�r a loading state lesz megjelen�tve
	 * a v�g�n pedig �tadja a vez�rl�st a men� state-nek. Innent�l a statek m�r �n�ll�an tudnak gondoskodni magukr�l.
	 */
	@Override
	public void init() {
		try {
			playState.init();
			optionsState.init();
		} catch (EInitException initEx) {
			initEx.printStackTrace();
			/* IMPROVE hiba eset�n valami hibak�perny�re lehetne menni */
		}

		setState(optionsState);
	}

	/**
	 * a j�t�kciklus friss�t�s r�sze. friss�ti az �llapotokat
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
	 * a j�t�kciklus rajzol�sa. kirajzolja az aktu�lis k�perny�t.
	 */
	@Override
	public void draw(Graphics g) {
		if (state != null) {
			state.draw(g);
		}
	}

	/**
	 * kil�p a programb�l
	 */
	public void exit() {
		program.exit();
	}

	/**
	 * visszaadja a billenty�zetet figyel� objektumot
	 * @return
	 */
	public KeyListener getKeyboardInput() {
		return keyboard;
	}

}
