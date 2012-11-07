package components.audio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AudioData {

	private String fileName;
	private Map<String, String> sounds = new HashMap<String, String>();

	private AudioMaker audioMaker = new AudioMaker();

	public Map<String, String> getSounds() {
		return sounds;
	}

	public AudioData(String fileName) {
		this.fileName = fileName;
		init();
	}

	private final void init() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String key = br.readLine();
			String value = br.readLine();
			if (key != null && value != null) {
				while (null != key && null != value) {
					sounds.put(key, value);
					key = br.readLine();
					value = br.readLine();
				}
				br.close();
			}
		} catch (FileNotFoundException fnfEx) {
			fnfEx.printStackTrace();
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		}
	}

	protected void playSound(String name) {
		if (sounds.containsKey(name)) {
			audioMaker.playSound(sounds.get(name));
		}
	}

	protected void stop() {
		audioMaker.stop();
	}
}