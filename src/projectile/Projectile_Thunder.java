package projectile;

import animation.ImageSequence;
import game.Game;
import gameObject.GameObject;

/**
* <b>Description:</b>
* <br>
* Defines individual projectile
* <br>Requires input of coordinates, and outputs direction and speed of projectile
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class Projectile_Thunder extends Projectile{

	/**
	 * defines thunder projectile
	* @param x coordinate X
	 * @param y coordinate Y
	 * @param game instance of Game
	 */
	public Projectile_Thunder(int x, int y, Game game, GameObject o) {
		super(x, y, game, o);
		flyRight = new ImageSequence("/image/projectiles/sparkRight", 5);
		flyDown = new ImageSequence("/image/projectiles/sparkDown", 5);
		
		setStartingAnimation();
		flySpeed=30;
		damage = 30;
		this.setStartingVelocity(flySpeed);
	}

}
