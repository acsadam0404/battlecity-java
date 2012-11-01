package game;


import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import objects.IGameLoop;

/**
 * A játékciklust implementálja az osztály, fixed length timestamp alapján. Az ebbõl származó osztályoknak kötelezõ implementálni az
 * update(), draw() és init() függvényeket. Legjobb esetben csak egy osztály származik belõle, az lesz a játék alaposztálya.
 * 
 * @author Ács Ádám
 * @version 2012.08.09
 */
public abstract class Game extends JPanel implements Runnable, IGameLoop {
	private volatile boolean running = false;
	private volatile boolean gameOver = false;
	protected Program program;

	protected Graphics bufferGraphics;
	protected Image offscreen;

	protected Game(Program program) {
		this.program = program;
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(Config.FRAME_WIDTH, Config.FRAME_HEIGHT));
		setSize(new Dimension(Config.FRAME_WIDTH, Config.FRAME_HEIGHT));
		initBuffering();

	}

	private void initBuffering() {
		offscreen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		bufferGraphics = offscreen.getGraphics();
	}

	public void stopGame() {
		running = false;
	}

	@Override
	public void addNotify() {
		super.addNotify();
	
	}

	/**
	 *  update, render, sleep 
	 */
	@Override
	public void run() {
		running = true;

		long startingTime = System.currentTimeMillis();
		long currentTime = startingTime;
		long gameTime = 0;

		/* játékciklus */
		while (running) {
			gameTime = System.currentTimeMillis() - currentTime;
			currentTime += gameTime;

			gameUpdate(gameTime);
			repaint();

			try {
				Thread.sleep(20);
			} catch (InterruptedException ex) {
				/* nem történhet */
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Ez felel a játék inicializálásáért. A játékciklus elõtt hívódik meg. Itt lehet rescourceket betölteni, 
	 * osztályok beállítani, alapértelmezéseket megadni.
	 */
	@Override
	public abstract void init();

	/**
	 * A játékciklus frissítõ függvénye, ide lehet a logikát illeszteni 
	 */
	@Override
	public abstract void update(long gameTime);

	/**
	 * A játékciklus rajzoló függvénye, itt lehet rajzolni. 
	 */
	@Override
	public abstract void draw(Graphics g);

	private void gameUpdate(long gameTime) {
		update(gameTime);
	}

	@Override
	public void paint(Graphics g) {
		bufferGraphics.clearRect(0, 0, getWidth(), getHeight());
		draw(bufferGraphics);
		g.drawImage(offscreen, 0, 0, this);

	}

	public boolean isGameEnd() {
		return !running;
	}

}
