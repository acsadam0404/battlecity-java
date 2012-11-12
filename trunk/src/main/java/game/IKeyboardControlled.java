package game;

import game.input.keyboard.KeyboardInput;

/**
 * Az implement�l� objektumokat lehet ir�ny�tani a billenty�zettel.
 * @author �cs �d�m
 *
 */
public interface IKeyboardControlled {
	/**
	 * megmondja, hogy keypress-kor milyen logika �rv�nyes�l
	 * @param keyboard
	 */
	public void keyPressed(KeyboardInput keyboard);
}
