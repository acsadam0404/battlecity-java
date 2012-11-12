package game.input.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * keyboard polling implementáció. 
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
	 * létrehozza a billentyûzetet és beállítja az alapértelmezett állapotot
	 */
	public KeyboardInput() {
		currentKeys = new boolean[KEY_COUNT];
		keys = new KeyState[KEY_COUNT];
		for (int i = 0; i < KEY_COUNT; ++i) {
			keys[i] = KeyState.RELEASED;
		}
	}

	/**
	 * polling metódus, ez kéri le a lenyomott gombokat a rendszertõl.
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
	 * megmondja egy gombról, hogy le van-e nyomva
	 * @param keyCode
	 * @return
	 */
	public boolean keyDown(int keyCode) {
		return keys[keyCode] == KeyState.ONCE ||
				keys[keyCode] == KeyState.PRESSED;
	}

	/**
	 * megmondja egy gombról, hogy le lett e nyomva. Lenyomva tartott gombokat csak egyszer számol.
	 * @param keyCode
	 * @return
	 */
	public boolean keyDownOnce(int keyCode) {
		return keys[keyCode] == KeyState.ONCE;
	}

	/**
	 * megmondja egy gombról hogy megnyomták-e
	 */
	@Override
	public synchronized void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode >= 0 && keyCode < KEY_COUNT) {
			currentKeys[keyCode] = true;
		}
	}

	/**
	 * megmondja egy gombról, hogy felengedték-e
	 */
	@Override
	public synchronized void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode >= 0 && keyCode < KEY_COUNT) {
			currentKeys[keyCode] = false;
		}
	}

	/**
	 * nincs szükség rá.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// Not needed
	}
}