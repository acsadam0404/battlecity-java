package components.audio;

import game.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AudioHandler {
	List<AudioData> datas = new ArrayList<AudioData>();
	Map<String, AudioPlayer> audioPlayers = new HashMap<String, AudioPlayer>();

	public List<AudioData> getDatas() {
		return datas;
	}

	public AudioHandler(AudioData data) {
		datas.add(data);
	}

	public void playSound(String name) {
		if (Config.AUDIO_ON) {
			AudioPlayer ap = new AudioPlayer(name);
			audioPlayers.put(name, ap);
			Thread playerThread = new Thread(ap);
			playerThread.start();
		}
	}

	public void addData(AudioData data) {
		datas.add(data);
	}

	private class AudioPlayer implements Runnable {
		String name = "";

		public AudioPlayer(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			for (int i = 0; i < datas.size(); i++) {
				datas.get(i).playSound(name);
			}
		}

		@Override
		public String toString() {
			return "sound: " + name;
		}
	}

	public void stop() {
		for (AudioData data : datas) {
			data.stop();
		}
	}
}