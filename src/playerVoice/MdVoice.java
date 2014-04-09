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
public class MdVoice implements PlayerVoice{
	Random rand;
	public MdVoice(){
		rand=new Random();
	}
	public void playDeathSound(){
		int x = rand.nextInt(3);
		String url = "/sound/mdDeath";
		url=url.concat(Integer.toString(x)).concat(".wav");
		GameSystem.musicPlayer.playVoice(url);
	}
	public void playUltimateSound(){
		
	}
	public void playLevelUpSound(){
		GameSystem.musicPlayer.playVoice("/sound/mdLevel.wav");
	}
	public void playItemFoundSound(){
		GameSystem.musicPlayer.playVoice("/sound/mdItem.wav");
	}
	public void playDamagedMediumSound(){
		GameSystem.musicPlayer.playVoice("/sound/mdDamage0.wav");
	}
	public void playDamagedHeavySound(){
		GameSystem.musicPlayer.playVoice("/sound/mdDamage1.wav");
	}
	public void playSoulGemDarkSound(){
		GameSystem.musicPlayer.playVoice("/sound/mdSoul1.wav");
	}
	public void playCdSound() {
		GameSystem.playVoice("/sound/mdCd.wav");
	}
	
}
