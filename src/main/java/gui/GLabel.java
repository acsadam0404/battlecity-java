package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import math.Vector2;
import objects.IDrawable;

/**
 * ezzel kilehet rajzolni egy stringet a k�perny�re.
 * 
 * @author �cs �d�m 2012.07.12.
 */
public class GLabel implements IDrawable {
	private Graphics graphics; /*
								 * ez csak a draw-ban van inicializ�lva, ez�rt
								 * dobhat nullpointert, ha el�bb hivatkozunk r�
								 */
	private String text;
	private Vector2 pos;
	private Color color = Color.WHITE;
	private Font font = new Font("Arial Bold", Font.PLAIN, 10); /*
																 * alap�rtelmezett
																 * font
																 */

	/**
	 * be�ll�tja a megadott sz�veget megadott helyre
	 * 
	 * @param str
	 * @param posX
	 * @param posY
	 */
	public GLabel(String str, int posX, int posY) {
		this(str, new Vector2(posX, posY));
	}

	/**
	 * be�ll�tja a sz�veget a 0, 0 poz�ci�ra
	 * 
	 * @param text
	 */
	public GLabel(String text) {
		this(text, Vector2.ZERO);
	}

	/**
	 * be�ll�tja a megadott sz�veget a megadott helyre
	 * 
	 * @param str
	 * @param pos
	 */
	public GLabel(String str, Vector2 pos) {
		this.text = str;
		this.pos = pos;
	}

	/**
	 * be�ll�tja a sz�veg poz�ci�j�t
	 * 
	 * @param pos
	 */
	public void setPosition(Vector2 pos) {
		this.pos = pos;
	}

	/**
	 * be�ll�tja a sz�veg poz�ci�j�t
	 * 
	 * @param pos
	 */
	public void setPosition(int x, int y) {
		setPosition(new Vector2(x, y));
	}

	/**
	 * be�ll�tja a kirajzoland� sz�veget
	 * 
	 * @param str
	 */
	public void setString(String str) {
		this.text = str;
	}

	/**
	 * hozz�f�z a sz�veghez egy m�sikat
	 * 
	 * @param toAppend
	 */
	public void append(String toAppend) {
		text += toAppend;
	}

	/**
	 * be��ll�tja a sz�veg sz�n�t
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * be�ll�tja a bet�t�pust
	 * 
	 * @param font
	 */
	public void setFont(Font font) {
		this.font = font;
	}

	/**
	 * be�ll�tja a bet�m�retet
	 * 
	 * @param size
	 */
	public void setFontSize(int size) {
		String currentName = font.getName();
		int currentStyle = font.getStyle();

		font = new Font(currentName, currentStyle, size);
	}

	/**
	 * kirajzolja a sz�veget a k�perny�re
	 */
	@Override
	public void draw(Graphics g) {
		/*
		 * ne az �tadottat haszn�lja, csak egy m�solat�t, �gy a v�ltoztat�sok
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
