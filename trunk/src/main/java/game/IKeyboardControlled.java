package game;

import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Az implementáló objektumokat lehet irányítani a billentyûzettel.
 * @author Ács Ádám
 *
 */
public interface IKeyboardControlled {
	public void keyPressed(List<KeyEvent> keys);
}
