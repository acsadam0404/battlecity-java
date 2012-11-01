package testgame;

import game.Game;
import game.Program;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import objects.EInitException;
import testgame.states.Explosion;

/**
 * a játék tesztelésére
 * 
 * @author Ács Ádám
 *
 */
public class TestGame extends Game implements KeyListener{
	AbstractGameState state = new Explosion(this);

	protected TestGame(Program program) {
		super(program);
	}

	@Override
	public void init() {
		try {
			state.init();
		} catch (EInitException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(long gameTime) {
		state.update(gameTime);
	}

	@Override
	public void draw(Graphics g) {
		state.draw(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		state.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
