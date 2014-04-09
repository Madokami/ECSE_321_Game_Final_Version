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
	 * @param coordinates, game object
	 */
	public PowerUps(int x, int y, Game game) {
		super(x, y, game);
		// TODO Auto-generated constructor stub
	}
	/**
	 * applies quick return
	 */
	public void tick(){
		return;
	}
	
	public abstract void applyEffect(Player player);
	
	//removes entity
	public void remove(){
		game.getController().removeEntity(this);
	}
}
