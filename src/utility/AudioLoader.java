package utility;
/**
* Description:
* Load sound audio
* @author Team 6
* @version 1.0
* @since 2014-03-27
*/

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioLoader {
	AudioInputStream a;
	/*
	public void loadAudio(String path,Clip c){
		try {
			a = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path));
			try {
				c.open(a);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	/**
	 * Load a audio clip
	 * @param path source path of audio file
	 * @return a Clip type variable which is the audio clip obtain from the audio source
	 */
	public Clip newClip(String path){
		Clip c = null;
		try {
			try {
				c=AudioSystem.getClip();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			AudioInputStream in = AudioSystem.getAudioInputStream(getClass().getResource(path));
			try {
				c.open(in);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
}
