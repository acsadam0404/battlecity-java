package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import math.Vector2;
import objects.IDrawable;

/**
 * ezzel kilehet rajzolni egy stringet a képernyõre.
 * 
 * @author Ács Ádám 2012.07.12.
 */
public class GLabel implements IDrawable {
	private Graphics graphics; /*
								 * ez csak a draw-ban van inicializálva, ezért
								 * dobhat nullpointert, ha elõbb hivatkozunk rá
								 */
	private String text;
	private Vector2 pos;
	private Color color = Color.WHITE;
	private Font font = new Font("Arial Bold", Font.PLAIN, 10); /*
																 * alapértelmezett
																 * font
																 */

	/**
	 * beállítja a megadott szöveget megadott helyre
	 * 
	 * @param str
	 * @param posX
	 * @param posY
	 */
	public GLabel(String str, int posX, int posY) {
		this(str, new Vector2(posX, posY));
	}

	/**
	 * beállítja a szöveget a 0, 0 pozícióra
	 * 
	 * @param text
	 */
	public GLabel(String text) {
		this(text, Vector2.ZERO);
	}

	/**
	 * beállítja a megadott szöveget a megadott helyre
	 * 
	 * @param str
	 * @param pos
	 */
	public GLabel(String str, Vector2 pos) {
		this.text = str;
		this.pos = pos;
	}

	/**
	 * beállítja a szöveg pozícióját
	 * 
	 * @param pos
	 */
	public void setPosition(Vector2 pos) {
		this.pos = pos;
	}

	/**
	 * beállítja a szöveg pozícióját
	 * 
	 * @param pos
	 */
	public void setPosition(int x, int y) {
		setPosition(new Vector2(x, y));
	}

	/**
	 * beállítja a kirajzolandó szöveget
	 * 
	 * @param str
	 */
	public void setString(String str) {
		this.text = str;
	}

	/**
	 * hozzáfûz a szöveghez egy másikat
	 * 
	 * @param toAppend
	 */
	public void append(String toAppend) {
		text += toAppend;
	}

	/**
	 * beûállítja a szöveg színét
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * beállítja a betûtípust
	 * 
	 * @param font
	 */
	public void setFont(Font font) {
		this.font = font;
	}

	/**
	 * beállítja a betûméretet
	 * 
	 * @param size
	 */
	public void setFontSize(int size) {
		String currentName = font.getName();
		int currentStyle = font.getStyle();

		font = new Font(currentName, currentStyle, size);
	}

	/**
	 * kirajzolja a szöveget a képernyõre
	 */
	@Override
	public void draw(Graphics g) {
		/*
		 * ne az átadottat használja, csak egy másolatát, így a változtatások
		 * nem maradnak meg
		 */
		if (graphics == null) {
			graphics = g.create();
		}

		graphics.setColor(color);
		graphics.setFont(font);

		graphics.drawString(text, pos.getX(), pos.getY());
	}
}
