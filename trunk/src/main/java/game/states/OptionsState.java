package game.states;

import game.BattleCity;
import game.Config;
import game.input.keyboard.KeyboardInput;
import gui.GLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import math.MathHelper;
import math.Vector2;

import components.audio.AudioData;
import components.audio.AudioHandler;

/**
 * A menü képernyõ.
 * @author Ács Ádám
 *
 */
public class OptionsState extends AbstractGameState {
	private GLabel level;
	private AudioHandler audioHandler;
	private Selection selection;
	private Image background;

	private Image cursor;

	int currentLevel = 1;

	/**
	 * beállítja a kiválasztó alapértelmezett helyét
	 * @param game
	 */
	public OptionsState(BattleCity game) {
		super(game);
		selection = Selection.PLAYER1;
	}

	/**
	 * beolvassa a képeket és hangokat a fájlokból
	 */
	@Override
	public void init() {
		background = new ImageIcon("images\\menubg.png").getImage();
		cursor = new ImageIcon("images\\cursor.png").getImage();

		audioHandler = new AudioHandler(new AudioData("sounddata\\menu.txt"));

		level = new GLabel(Integer.toString(currentLevel), new Vector2(330, 225));
		level.setFont(new Font("Arial Bold", Font.PLAIN, 35));
		level.setColor(Color.ORANGE);
	}

	/**
	 * kirajzolja a képernyõt
	 */
	@Override
	public void draw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		level.draw(g);

		int pos = 0;
		switch (selection) {
			case PLAYER1:
				pos = 270;
				break;
			case PLAYER2:
				pos = 310;
				break;
			case EXIT:
				pos = 350;
				break;
		}

		g.drawImage(cursor, 170, pos, null);
	}

	/**
	 * frissíti a képernyõt
	 */
	@Override
	public void update(long gameTime) {
		super.update(gameTime);
	}

	/**
	 * a képernyõre érkezéskor szükséges beállítások elvégzése
	 */
	@Override
	public void onSet() {
		Config.currentLevel = 1;
	}

	/**
	 * billentyûzet kezelés implementációja
	 */
	@Override
	public void keyPressed(KeyboardInput keyboard) {
			if (keyboard.keyDownOnce(KeyEvent.VK_ESCAPE)) {
				game.exit();
			}

			if (keyboard.keyDownOnce(KeyEvent.VK_DOWN)) {
				selection = selection.getNext();
			}
			else if (keyboard.keyDownOnce(KeyEvent.VK_UP)) {
				selection = selection.getPrevious();
			}
			else if (keyboard.keyDownOnce(KeyEvent.VK_SPACE) || keyboard.keyDown(KeyEvent.VK_ENTER)) {
				switch (selection) {
					case EXIT:
						game.exit();
						break;
					case PLAYER1:
						Config.currentLevel = currentLevel;
						game.setState(game.getPlayState());
						break;
					case PLAYER2:
						break;
				}
			}

			if (keyboard.keyDownOnce(KeyEvent.VK_LEFT)) {
				currentLevel--;
				currentLevel = MathHelper.clamp(currentLevel, 1, Config.MAX_LEVEL);
			}
			else if (keyboard.keyDownOnce(KeyEvent.VK_RIGHT)) {
				currentLevel++;
				currentLevel = MathHelper.clamp(currentLevel, 1, Config.MAX_LEVEL);
			}
			level.setString(Integer.toString(currentLevel));
	}

	private enum Selection {
		PLAYER1,
		PLAYER2,
		EXIT;

		public Selection getNext() {
			return values()[(ordinal() + 1) % values().length];
		}

		public Selection getPrevious() {
			return values()[ordinal() != 0 ? (ordinal() - 1) % values().length : values().length - 1];
		}
	}
}
