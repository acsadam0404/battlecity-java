package game.states;

import game.BattleCity;
import game.Config;
import game.ScoreHandler;
import game.input.keyboard.KeyboardInput;
import gui.GLabel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import map.MapData;
import map.TileMap;
import objects.EInitException;
import objects.bonus.BonusManager;
import objects.tiles.ENoSuchTileException;
import objects.units.EnemyManager;
import player.LocalPlayer;

import components.audio.AudioData;
import components.audio.AudioHandler;

/**
 * A játékot irányító state, ami felelõs a TileMap, játékosok, ellenségek, score
 * kezeléséért. A játék képernyõt valósítja meg.
 * 
 * @author Ács Ádám
 * 
 */
public class PlayState extends AbstractGameState {
	private AbstractAction gameEndListener = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			time--;
		}
	};

	private Timer timer = new Timer(1000, gameEndListener);
	private GLabel timeStringLabel;
	private GLabel timeLabel;
	private GLabel gameOverYouWonLabel;
	private GLabel gameOverEnemyWonLabel;

	private int time;
	private int maxTime;

	private boolean gameOverYouWon;
	private boolean gameOverEnemyWon;
	private boolean paused;

	private LocalPlayer player;
	private EnemyManager enemyManager;
	private BonusManager bonusManager;
	private Image background;
	private AudioHandler audioHandler;
	private ScoreHandler scoreHandler;

	private Map<Integer, MapData> levels = new HashMap<Integer, MapData>();

	public PlayState(BattleCity game) {
		super(game);
		player = new LocalPlayer();
		enemyManager = new EnemyManager(player);
		bonusManager = new BonusManager();
	}

	@Override
	public void init() throws EInitException {
		background = new ImageIcon("images\\playbg.png").getImage();

		timeStringLabel = new GLabel("timer", 430, 100);
		timeStringLabel.setColor(Color.YELLOW);
		timeStringLabel.setFontSize(20);

		timeLabel = new GLabel(String.valueOf(maxTime), 480, 100);
		timeLabel.setColor(Color.RED);
		timeLabel.setFontSize(20);

		gameOverYouWonLabel = new GLabel("Nyertél!", 200, 200);
		gameOverYouWonLabel.setColor(Color.RED);
		gameOverYouWonLabel.setFontSize(20);

		gameOverEnemyWonLabel = new GLabel("Vesztettél!", 200, 200);
		gameOverEnemyWonLabel.setColor(Color.RED);
		gameOverEnemyWonLabel.setFontSize(20);

		AudioData audioData = new AudioData("sounddata\\play.txt");
		audioHandler = new AudioHandler(audioData);

		try {
			levels.put(Integer.valueOf(1), new MapData("maps\\1.txt"));
			levels.put(Integer.valueOf(2), new MapData("maps\\2.txt"));
			levels.put(Integer.valueOf(3), new MapData("maps\\3.txt"));
			levels.put(Integer.valueOf(4), new MapData("maps\\4.txt"));
			levels.put(Integer.valueOf(5), new MapData("maps\\5.txt"));
		} catch (ENoSuchTileException nstEx) {
			nstEx.printStackTrace();
		}
		player.init();
		enemyManager.init();
		bonusManager.init();
		scoreHandler = new ScoreHandler(player, null, enemyManager);
	}

	@Override
	public void update(long gameTime) {
		if (!paused) {
			if (player.getTank().isDead()) {
				gameOverEnemyWon = true;
				paused = true;
			}

			if (time <= 0) {
				gameOverYouWon = true;
				paused = true;
			}

			TileMap.singleton().update(gameTime);
			player.update(gameTime);
			enemyManager.update(gameTime);
			bonusManager.update(gameTime);
			timeLabel.setString(String.valueOf(time));
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		scoreHandler.draw(g);
		timeStringLabel.draw(g);
		timeLabel.draw(g);

		TileMap.singleton().drawFirstLayer(g);

		player.draw(g);
		enemyManager.draw(g);

		TileMap.singleton().drawSecondLayer(g);

		bonusManager.draw(g);

		if (gameOverYouWon) {
			gameOverYouWonLabel.draw(g);
		} else if (gameOverEnemyWon) {
			gameOverEnemyWonLabel.draw(g);
		}
	}

	@Override
	public void onSet() {
		TileMap.singleton().setData(
		levels.get(Integer.valueOf(Config.currentLevel)));
		audioHandler.playSound("background");
		time = Config.MAXTIME;
		timer.start();
		gameOverYouWon = false;
		gameOverEnemyWon = false;
		paused = false;
		enemyManager.reset();
		bonusManager.reset();
		player.reset();
		TileMap.singleton().reset();
	}

	@Override
	public void onExit() {
		audioHandler.stop();
		timer.stop();
	}

	@Override
	public void keyPressed(KeyboardInput keyboard) {
		if (keyboard.keyDown(KeyEvent.VK_ESCAPE)) {
			game.setState(game.getOptionsState());
		}

		if (keyboard.keyDown(KeyEvent.VK_P) && !gameOverYouWon
				&& !gameOverEnemyWon) {
			if (paused) {
				paused = false;
			} else {
				paused = true;
			}
		}

		if (!paused) {
			player.keyPressed(keyboard);
		}
	}
}
