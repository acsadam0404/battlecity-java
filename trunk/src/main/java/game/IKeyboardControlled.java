package game;

import game.input.keyboard.KeyboardInput;

/**
 * Az implementáló objektumokat lehet irányítani a billentyûzettel.
 * @author Ács Ádám
 *
 */
public interface IKeyboardControlled {
	/**
	 * megmondja, hogy keypress-kor milyen logika érvényesül
	 * @param keyboard
	 */
	public void keyPressed(KeyboardInput keyboard);
}
