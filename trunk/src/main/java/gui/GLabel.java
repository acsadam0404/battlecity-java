package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import math.Vector2;
import objects.IDrawable;

/**
 * ezzel kilehet rajzolni egy stringet a képernyõre.
 * 
 * @author Ács Ádám
 * 2012.07.12.
 */
public class GLabel implements IDrawable {
	private Graphics graphics; /* ez csak a draw-ban van inicializálva, ezért dobhat nullpointert, ha elõbb hivatkozunk rá */
	private String text;
	private Vector2 pos;
	private Color color = Color.WHITE; 
	private Font font = new Font("Arial Bold", Font.PLAIN, 10); /* alapértelmezett font */

	public GLabel(String str, int posX, int posY) {
		this(str, new Vector2(posX, posY));
	}

	public GLabel(String text) {
		this(text, Vector2.ZERO);
	}

	public GLabel(String str, Vector2 pos) {
		this.text = str;
		this.pos = pos;
	}

	public void setPosition(Vector2 pos) {
		this.pos = pos;
	}

	public void setPosition(int x, int y) {
		setPosition(new Vector2(x, y));
	}

	public void setString(String str) {
		this.text = str;
	}

	public void append(String toAppend) {
		text += toAppend;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public void setFontSize(int size) {
		String currentName = font.getName();
		int currentStyle = font.getStyle();

		font = new Font(currentName, currentStyle, size);
	}

	@Override
	public void draw(Graphics g) {
		/* ne az átadottat használja, csak egy másolatát, így a változtatások nem maradnak meg */
		if (graphics == null) {
			graphics = g.create();
		}

		graphics.setColor(color);
		graphics.setFont(font);

		graphics.drawString(text, pos.getX(), pos.getY());
	}
}
