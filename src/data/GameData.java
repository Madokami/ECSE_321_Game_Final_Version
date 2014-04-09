package data;
/**
* Description:
* Update game level and player data
* @author Team 6
* @version 1.0
* @since 2014-03-27
*/

import game.Game;

import java.io.Serializable;
import java.util.LinkedList;

public class GameData implements Serializable{
	public int curLevel;
	public PlayerData pData;
	public int[] HIGH_SCORE =  new int[Game.lastStage+1];
	
	/**
	 * Update current level and player data
	 * @param game current game
	 */
	public void updateGameData(Game game){
		this.curLevel = game.getCurLevel();
		this.pData=game.getPlayerData();

	}
	
	/**
	 * Set current level and player data
	 * @param game current game
	 */
	public void loadGame(Game game) {
		game.setCurLevel(curLevel);
		game.setPlayerData(this.pData);
	}
}
