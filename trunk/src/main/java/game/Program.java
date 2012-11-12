package game;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * A main met�dust tartalmaz� oszt�ly. Ez a program kiindul�si pontja. Be�ll�tja
 * az parancssori param�tereket, elind�tja a j�t�kot �s l�that�v� teszi.
 * JFrame-b�l sz�rmazik, teh�t a f� container is ez az oszt�ly.
 * 
 * @author �cs �d�m
 * @version 2012.08.09
 */
public class Program extends JFrame {
	private static final String NOSOUND_ARG = "-nosound";
	static BattleCity game;

	public Program() {

	}

	/**
	 * A program bel�p�si pontja. Egyel�re nem t�mogatja a parancssori
	 * param�tereket.
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
		// el�gg�.. szerintem appletk�nt emiatt nem lehetne megjelen�teni*/
		program.setVisible(true);
		program.addKeyListener(game.getKeyboardInput());
		program.setLayout(null);
		program.setResizable(false);
		program.setTitle("Battle City");

	}

	/**
	 * kil�p a programb�l.
	 */
	public void exit() {
		System.exit(0);
	}
}
