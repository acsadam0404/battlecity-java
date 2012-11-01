package testgame;

import game.Program;

import java.awt.Dimension;

import javax.swing.JFrame;

public class TestMain extends JFrame{
	static TestGame game;

	public TestMain() {

	}

	/**
	 * A program belépési pontja. Egyelõre nem támogatja a parancssori paramétereket.
	 * @param args
	 */
	public static void main(String args[]) {
		Program program = new Program();
		game = new TestGame(program);
		game.init();

		new Thread(game).start();
		program.getContentPane().add(game);
		program.setPreferredSize(new Dimension(500, 500));
		program.setSize(new Dimension(500, 500));
		program.setDefaultCloseOperation(EXIT_ON_CLOSE);
		program.setVisible(true);
		program.addKeyListener(game);
		program.setLayout(null);

	}

	public void exit() {
		System.exit(0);
	}
}
