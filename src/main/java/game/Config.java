package game;

import java.awt.Rectangle;

import math.Vector2;

/**
 * Itt van minden kívülrõl jövõ config nyilvántartva.
 * 
 * IMPROVE fájlból olvasni ezeket.
 * IMPROVE ezek alapján mûködjön minden (képek skálázása fõleg)
 * 
 * @author Ács Ádám
 * 2012.07.18.
 */
public final class Config {
	public static int currentLevel = 1;
	
	public static int SCREEN_WIDTH = 416;
	public static int SCREEN_HEIGHT = 416;
	
	public static int FRAME_WIDTH = 545;
	public static int FRAME_HEIGHT = 455;

	public static int SCREEN_OFFSET_X = 0;
	public static int SCREEN_OFFSET_Y = 0;

	public static int TILE_WIDTH = 32;
	public static int TILE_HEIGHT = 32;

	public static final int BULLET_WIDTH = 8;
	public static final int BULLET_HEIGHT = 8;
	
	public static final Vector2 OFFSCREEN = new Vector2(5000, -500);
	
	public static final int MAX_LEVEL = 5;

	public static final Rectangle OFFSCREEN_RECT = new Rectangle(-1000, -1000, 0, 0);

	public static boolean AUDIO_ON = true;
	
	public static final boolean AI_ON = true;

	public static final int MAXTIME = 200;

	private Config() {
		/* private constructor */
	}
}
