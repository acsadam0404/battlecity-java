package game;

import game.input.keyboard.KeyboardInput;

/**
 * Az implementáló objektumokat lehet irányítani a billentyûzettel.
 * @author Ács Ádám
 *
 */
public interface IKeyboardControlled {
	public void keyPressed(KeyboardInput keyboard);
}
