package game.input.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * keyboard polling implement�ci�. 
 */
public class KeyboardInput implements KeyListener {
	private static final int KEY_COUNT = 256;

	private enum KeyState {
		RELEASED, // Not down
		PRESSED, // Down, but not the first time
		ONCE
		// Down for the first time
	}

	// Current state of the keyboard
	private boolean[] currentKeys = null;

	// Polled keyboard state
	private KeyState[] keys = null;

	/**
	 * l�trehozza a billenty�zetet �s be�ll�tja az alap�rtelmezett �llapotot
	 */
	public KeyboardInput() {
		currentKeys = new boolean[KEY_COUNT];
		keys = new KeyState[KEY_COUNT];
		for (int i = 0; i < KEY_COUNT; ++i) {
			keys[i] = KeyState.RELEASED;
		}
	}

	/**
	 * polling met�dus, ez k�ri le a lenyomott gombokat a rendszert�l.
	 */
	public synchronized void poll() {
		for (int i = 0; i < KEY_COUNT; ++i) {
			// Set the key state 
			if (currentKeys[i]) {
				// If the key is down now, but was not
				// down last frame, set it to ONCE,
				// otherwise, set it to PRESSED
				if (keys[i] == KeyState.RELEASED)
					keys[i] = KeyState.ONCE;
				else
					keys[i] = KeyState.PRESSED;
			} else {
				keys[i] = KeyState.RELEASED;
			}
		}
	}

	/**
	 * megmondja egy gombr�l, hogy le van-e nyomva
	 * @param keyCode
	 * @return
	 */
	public boolean keyDown(int keyCode) {
		return keys[keyCode] == KeyState.ONCE ||
				keys[keyCode] == KeyState.PRESSED;
	}

	/**
	 * megmondja egy gombr�l, hogy le lett e nyomva. Lenyomva tartott gombokat csak egyszer sz�mol.
	 * @param keyCode
	 * @return
	 */
	public boolean keyDownOnce(int keyCode) {
		return keys[keyCode] == KeyState.ONCE;
	}

	/**
	 * megmondja egy gombr�l hogy megnyomt�k-e
	 */
	@Override
	public synchronized void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode >= 0 && keyCode < KEY_COUNT) {
			currentKeys[keyCode] = true;
		}
	}

	/**
	 * megmondja egy gombr�l, hogy felengedt�k-e
	 */
	@Override
	public synchronized void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode >= 0 && keyCode < KEY_COUNT) {
			currentKeys[keyCode] = false;
		}
	}

	/**
	 * nincs sz�ks�g r�.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// Not needed
	}
}