package game;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * A main metódust tartalmazó osztály. Ez a program kiindulási pontja. Beállítja
 * az parancssori paramétereket, elindítja a játékot és láthatóvá teszi.
 * JFrame-bõl származik, tehát a fõ container is ez az osztály.
 * 
 * @author Ács Ádám
 * @version 2012.08.09
 */
public class Program extends JFrame {
	private static final String NOSOUND_ARG = "-nosound";
	static BattleCity game;

	public Program() {

	}

	/**
	 * A program belépési pontja. Egyelõre nem támogatja a parancssori
	 * paramétereket.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		if (args.length > 0) {
			if (NOSOUND_ARG.equalsIgnoreCase(args[0])) {
				Config.AUDIO_ON = false;
			}
		}

		Program program = new Program();

		game = new BattleCity(program);
		game.init();

		new Thread(game).start();
		program.getContentPane().add(game);
		program.setPreferredSize(new Dimension(Config.FRAME_WIDTH,
				Config.FRAME_HEIGHT));
		program.setSize(new Dimension(Config.FRAME_WIDTH, Config.FRAME_HEIGHT));
		program.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// program.setUndecorated(true); /* FIXME ha bekapcsolom van baj
		// eléggé.. szerintem appletként emiatt nem lehetne megjeleníteni*/
		program.setVisible(true);
		program.addKeyListener(game.getKeyboardInput());
		program.setLayout(null);
		program.setResizable(false);
		program.setTitle("Battle City");

	}

	/**
	 * kilép a programból.
	 */
	public void exit() {
		System.exit(0);
	}
}
