import java.io.*;
import javax.sound.sampled.*;

/**
 * Adds sound to the game
 * @author Steven Childrey
 *
 */
public class Sound {

	private File sound;
	private String soundString;
	private AudioInputStream audioStream;
	private AudioFormat format;
	private DataLine.Info info;
	private Clip clip;
	
	
	
	/**
	 * Plays the sound
	 * @param fileName
	 */
	public Sound(String fileName){
		
		soundString = fileName;
		
		try{
			sound = new File(soundString);
			audioStream = AudioSystem.getAudioInputStream(sound);
			format = audioStream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(audioStream);
			clip.start();
		}catch (Exception e){
			//e.printStackTrace();
		}
		
		
		
	}
	
}
