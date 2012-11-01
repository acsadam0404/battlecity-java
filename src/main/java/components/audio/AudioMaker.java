package components.audio;

import game.Config;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class AudioMaker {
	private final int BUFFER_SIZE = 128000;
	private File soundFile;
	private AudioInputStream audioStream;
	private AudioFormat audioFormat;
	private SourceDataLine sourceLine;

	AudioMaker() {
		/* package-priv ctor */
	}

	public void playSound(String filename) {

		String strFilename = filename;

		try {
			soundFile = new File(strFilename);
			audioStream = AudioSystem.getAudioInputStream(soundFile);
			audioFormat = audioStream.getFormat();

			DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
			sourceLine = (SourceDataLine) AudioSystem.getLine(info);
			sourceLine.open(audioFormat);

			sourceLine.start();

			int nBytesRead = 0;
			byte[] abData = new byte[BUFFER_SIZE];
			while (nBytesRead != -1) {
				try {
					nBytesRead = audioStream.read(abData, 0, abData.length);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (nBytesRead >= 0) {
					sourceLine.write(abData, 0, nBytesRead);
				}
			}

			sourceLine.drain();
			sourceLine.close();
		} catch (UnsupportedAudioFileException uafEx) {
			uafEx.printStackTrace();
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		} catch (LineUnavailableException luEx) {
			luEx.printStackTrace();
		}
	}

	void stop() {
		if (Config.AUDIO_ON) {
			sourceLine.close();
		}
	}
}
