package game;

import gui.GLabel;

import java.awt.Color;
import java.awt.Graphics;

import objects.IDrawable;
import objects.units.EnemyManager;
import player.LocalPlayer;

/**
 * A score kezelést implementálja, felelõs a rajzolásért, a kilõtt ellenségek utána pontok nyilvántartásáért 
 * és a játékosok HP-jának kiírásáért.
 * 
 * 
 * @author Ács Ádám
 *
 */
public class ScoreHandler implements IDrawable {
	private final LocalPlayer player1;
	private final LocalPlayer player2;
	private final EnemyManager enemy;
	private GLabel scoreString;
	private GLabel player1HpString;
	private GLabel player2HpString;
	
	private GLabel score;
	private GLabel player1Hp;
	private GLabel player2Hp;
	
	/**
	 * Az objektumnak mindenképpen tudnia kell egy playerrõl és az EnemyManager-rõl. A player2 lehet null is.
	 * @param player1
	 * @param player2
	 * @param enemy
	 */
	public ScoreHandler(LocalPlayer player1, LocalPlayer player2, EnemyManager enemy) {
		this.player1 = player1;
		this.player2 = player2;
		this.enemy = enemy;
		
		scoreString = new GLabel("Score:", 430, 200);
		scoreString.setColor(Color.YELLOW);
		scoreString.setFontSize(20);
		
		player1HpString = new GLabel("P1 HP:", 430, 300);
		player1HpString.setColor(Color.YELLOW);
		player1HpString.setFontSize(20);
		
		player2HpString = new GLabel(player2 != null ? String.valueOf(player2.getTank().getMaxHealth()) : "", 430, 400);
		player2HpString.setColor(Color.YELLOW);
		player2HpString.setFontSize(20);
		
		player1Hp = new GLabel(String.valueOf(player1.getTank().getMaxHealth()), 500, 300);
		player1Hp.setColor(Color.RED);
		player1Hp.setFontSize(20);
		
		player2Hp = new GLabel(player2 != null ? "P2 HP:" : "", 500, 400);
		player2Hp.setColor(Color.RED);
		player2Hp.setFontSize(20);
		
		score = new GLabel(String.valueOf(enemy.getKilledEnemies()), 500, 200);
		score.setColor(Color.RED);
		score.setFontSize(20);
	}

	/**
	 * A score kirajzolása.
	 */
	@Override
	public void draw(Graphics g) {
		player1Hp.setString(String.valueOf(player1.getTank().getHealth()));
		score.setString(String.valueOf(enemy.getKilledEnemies()));
		
		scoreString.draw(g);
		player1HpString.draw(g);
		player2HpString.draw(g);
		
		player2Hp.draw(g);
		player1Hp.draw(g);
		score.draw(g);
	}
	
}
