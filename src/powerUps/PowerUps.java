package powerUps;

import player.Player;
import game.Game;
import gameObject.GameObject;

/**
* <b>Description:</b>
* <br>
* Defines PowerUps class that extends GameObject
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public abstract class PowerUps extends GameObject{
	public String type;
	/**
	 * defines power up
	 * @param x coordinate X
	 * @param y coordinate Y
	 * @param game instance of Game
	 */
	public PowerUps(int x, int y, Game game) {
		super(x, y, game);
		// TODO Auto-generated constructor stub
	}
	
	public void tick(){
		return;
	}
	/**
	 * applies certain effects to the inputed player object
	 */
	public abstract void applyEffect(Player player);
	
	//removes entity
	public void remove(){
		game.getController().removeEntity(this);
	}
}
