package game.Audio;

import javax.sound.sampled.*;
import java.io.File;

public class Looping extends Thread {

	String filename;	// The name of the file to play
	boolean finished;	// A flag showing that the thread has finished

	public Looping(String fname) {
		filename = fname;
		finished = false;
	}

	public void run() {
		try {
			File file = new File(filename);
			AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			AudioFormat	format = stream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip)AudioSystem.getLine(info);
			clip.open(stream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			Thread.sleep(100);
			while (clip.isRunning()) {
				Thread.sleep(100);
			}
			clip.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finished = true;
	}
}
