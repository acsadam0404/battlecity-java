package game;

import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Az implement�l� objektumokat lehet ir�ny�tani a billenty�zettel.
 * @author �cs �d�m
 *
 */
public interface IKeyboardControlled {
	public void keyPressed(List<KeyEvent> keys);
}
