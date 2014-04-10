package system;
/**
* Description:
* Convert an integer to an image
* @author Team 6
* @version 1.23
* @since 2014-04-03
*/
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import utility.AudioLoader;



public class Music{

	AudioInputStream in;
    Clip music;
    Clip voice;
    Clip sound;
    Clip explosion;
    Clip sisPuellaMagica;
    
    public static int musicVolume,soundVolume,voiceVolume;
    
    AudioLoader loader;
	public Music(){
		//musicFile=new File("/bgm1.wav");
		loader = new AudioLoader();
		try {
			music = AudioSystem.getClip();
			sound = AudioSystem.getClip();
			sisPuellaMagica = AudioSystem.getClip();
			explosion = loader.newClip("/sound/expl1.wav");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		musicVolume=-10;
		
		
	}
	
	/**
	 * Play game background music
	 */
	public void playBgm(){
		if (GameSystem.mute == false){
			music=loader.newClip("/sound/bgm1.wav");
			music.loop(music.LOOP_CONTINUOUSLY);
		}
	}
	
	/**
	 * Play bomb explosion sound
	 */
	public void playExplosion(){
		explosion.start();
	}
	
	/**
	 * Play game battle music
	 */
	public void playBattleMusic(){
		music.stop();
		music.flush();
		music = loader.newClip("/sound/Delusio_summa.wav");
		music.start();
	}
	
	/**
	 * Stop music
	 */
	public void stopMusic(){
		music.stop();
	}
	
	/**
	 * resume music
	 */
	public void resumeMusic(){
		music.start();
	}
	
	/**
	 * Pause music
	 */
	public void pause(){
		try {
			music.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Play swoosh sound effect 
	 */
	public void playSwoosh() {
		sound = loader.newClip("/sound/soundEffect1.wav");
		sound.start();
	}
	
	/**
	 * Play music
	 * @param url url of the music source
	 */
	public synchronized void playMusic(String url){
		if (GameSystem.mute == false){
			music.stop();
			music = loader.newClip(url);
			setMusicVolume(musicVolume);
			music.loop(music.LOOP_CONTINUOUSLY);
		}
	}
	
	/**
	 * Play character voice
	 * @param url url of the voice source
	 */
	public void playVoice(String url){
		if(voice!=null) voice.stop();
		voice = loader.newClip(url);
		voice.start();
	}
	
	/**
	 * Play sound
	 * @param url url of the sound source
	 */
	public void playSound(String url){
		if(sound!=null) sound.stop();
		sound=loader.newClip(url);
		sound.start();
	}
	
	public void playSoundLoop(String url){
		if(sound!=null) sound.stop();
		sound=loader.newClip(url);
		sound.loop(sound.LOOP_CONTINUOUSLY);
	}
	public void stopSound(){
		if(sound!=null) sound.stop();
	}
	
	/**
	 * Reload explosion sound
	 */
	public void reloadExplosion(){
		explosion = loader.newClip("/sound/expl1.wav");
	}
	
	/**
	 * Check if a music is playing
	 * @return a boolean indicating whether a music is playing 
	 */
	public boolean musicIsPlaying(){
		if(music.isActive())
			return true;
		return false;
	}
	
	/**
	 * Set music volume
	 * @param value integer indicating the music volume
	 */
	public void setMusicVolume(int value){
		FloatControl volume = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
		volume.setValue(value);
	}
}
