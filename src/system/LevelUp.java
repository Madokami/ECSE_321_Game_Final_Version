package system;

import player.Player;
import gameObject.MovableObject.ANIMATION;
import graphicEffects.IntToImage;

/**
* <b>Description:</b>
* <br>
* Increases the level of a character based on amount of experience acquired
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class LevelUp {
	public int level;
	public double expCurrent;
	public double expRequired;
	
	/**
	 * Checks for conditions of leveling up
	 * <br>ex. the character does not level up if it dies
	 * @param player object
	 * @return check if level up
	 */
	public void checkIfLevelUp(Player p){
		if(p.getAnimation()==ANIMATION.DYING){
			return;
		}
		expCurrent = p.expCurrent;
		level = p.level;
		expRequired = level*100;
		
		if(expCurrent>=expRequired){
			p.level++;
			p.expCurrent=p.expCurrent-expRequired;
			p.BP=p.BP+5;
			p.hp=p.maxHp;
			p.levelImage=IntToImage.toImageSmall(p.level);
			p.getpVoice().playLevelUpSound();
		}
	}
	
	
}
