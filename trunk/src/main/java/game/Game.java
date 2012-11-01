package game;


import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import objects.IGameLoop;

/**
 * A j�t�kciklust implement�lja az oszt�ly, fixed length timestamp alapj�n. Az ebb�l sz�rmaz� oszt�lyoknak k�telez� implement�lni az
 * update(), draw() �s init() f�ggv�nyeket. Legjobb esetben csak egy oszt�ly sz�rmazik bel�le, az lesz a j�t�k alaposzt�lya.
 * 
 * @author �cs �d�m
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

		/* j�t�kciklus */
		while (running) {
			gameTime = System.currentTimeMillis() - currentTime;
			currentTime += gameTime;

			gameUpdate(gameTime);
			repaint();

			try {
				Thread.sleep(20);
			} catch (InterruptedException ex) {
				/* nem t�rt�nhet */
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Ez felel a j�t�k inicializ�l�s��rt. A j�t�kciklus el�tt h�v�dik meg. Itt lehet rescourceket bet�lteni, 
	 * oszt�lyok be�ll�tani, alap�rtelmez�seket megadni.
	 */
	@Override
	public abstract void init();

	/**
	 * A j�t�kciklus friss�t� f�ggv�nye, ide lehet a logik�t illeszteni 
	 */
	@Override
	public abstract void update(long gameTime);

	/**
	 * A j�t�kciklus rajzol� f�ggv�nye, itt lehet rajzolni. 
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
