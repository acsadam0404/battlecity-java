package game;

import game.states.*;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

import objects.EInitException;

/**
 * Az oszt�ly a j�t�k magja, implement�lja a j�t�kciklust �s innen vez�relhet� az eg�sz j�t�k. 
 * State-ekkel dolgozik a k�perny�kezel�s megk�nny�t�se �rdek�ben.
 * 
 * @author �cs �d�m
 *
 */
public final class BattleCity extends Game implements KeyListener {
	private AbstractGameState playState;
	private AbstractGameState exitState;
	private AbstractGameState menuState;
	private AbstractGameState optionsState;

	private AbstractGameState state;
	
	private List<KeyEvent> keys = new ArrayList<>();

	public BattleCity(Program program) {
		super(program);
		addKeyListener(this);
		requestFocus();
		setLayout(null);
		setIgnoreRepaint(true);
		
		optionsState = new OptionsState(this);
		exitState = new ExitState(this);
		playState = new PlayState(this);
		menuState = new MenuState(this);
	}

	public AbstractGameState getPlayState() {
		return playState;
	}

	public AbstractGameState getExitState() {
		return exitState;
	}

	public AbstractGameState getMenuState() {
		return menuState;
	}

	public AbstractGameState getOptionsState() {
		return optionsState;
	}

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
			menuState.init();
			exitState.init();
			optionsState.init();
		} catch (EInitException initEx) {
			initEx.printStackTrace();
			/* IMPROVE hiba eset�n valami hibak�perny�re lehetne menni */
		}
		
		setState(optionsState);
	}

	@Override
	public void update(long gameTime) {
		if (state != null) {
			state.update(gameTime);
		}
	}

	@Override
	public void draw(Graphics g) {
		if (state != null) {
			state.draw(g);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		/* nincs sz�ks�g r� */
	}

	@Override
	public void keyPressed(KeyEvent e) {
		boolean addKey = true;
		
		for (KeyEvent key : keys) {
			if (key.getKeyCode() == e.getKeyCode()) {
				addKey = false;
			}
		}
		
		if (addKey) {
			keys.add(e);
		}
		state.keyPressed(keys);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for (int i = keys.size() - 1; i >= 0; i--) {
			if (keys.get(i).getKeyCode() == e.getKeyCode()) {
				keys.remove(i);
			}
		}
	}

	public void exit() {
		program.exit();
	}

}
