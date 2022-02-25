package game.Audio;

import java.io.*;
import javax.sound.sampled.*;

public class Echo extends Thread {

	String filename;	// The name of the file to play
	boolean finished;	// A flag showing that the thread has finished
	
	public Echo(String fname) {
		filename = fname;
		finished = false;
	}

	public void run() {
		try {
			File file = new File(filename);
			AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			AudioFormat	format = stream.getFormat();
			SoundFilter filtered = new SoundFilter(stream);
			AudioInputStream f = new AudioInputStream(filtered,format,stream.getFrameLength());
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip)AudioSystem.getLine(info);
			clip.open(f);
			clip.start();
			Thread.sleep(100);
			while (clip.isRunning()) { Thread.sleep(100); }
			clip.close();
		}
		catch (Exception e) {	}
		finished = true;
	}
}
