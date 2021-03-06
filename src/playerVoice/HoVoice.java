package playerVoice;

import java.util.Random;

import system.GameSystem;

/**
* <b>Description:</b>
* <br>
* Responsible for playing different sounds
* <br>ex. LevelUpSound, DeathSound
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class HoVoice implements PlayerVoice{
	Random rand;
	public HoVoice(){
		rand=new Random();
	}
	public void playDeathSound(){
		int x = rand.nextInt(3);
		String url = "/sound/hoDeath";
		url=url.concat(Integer.toString(x)).concat(".wav");
		GameSystem.musicPlayer.playVoice(url);
	}
	public void playUltimateSound(){
		/*int x = rand.nextInt(3);
		String url = "/sound/hoUlt";
		url=url.concat(Integer.toString(x)).concat(".wav");
		System.out.println(url);
		GameSystem.musicPlayer.playVoice(url);
		*/
		GameSystem.playVoice("/sound/hoUlt.wav");
	}
	public void playLevelUpSound(){
		GameSystem.musicPlayer.playVoice("/sound/hoLevel0.wav");
	}
	public void playItemFoundSound(){
		GameSystem.musicPlayer.playVoice("/sound/hoItem.wav");
	}
	public void playDamagedMediumSound(){
		GameSystem.musicPlayer.playVoice("/sound/hoDamage0.wav");
	}
	public void playDamagedHeavySound(){
		GameSystem.musicPlayer.playVoice("/sound/hoDamage1.wav");
	}
	public void playSoulGemDarkSound(){
		GameSystem.musicPlayer.playVoice("/sound/hoSoul1.wav");
	}
	@Override
	public void playCdSound() {
		GameSystem.playVoice("/sound/hoCd.wav");
	}
}
