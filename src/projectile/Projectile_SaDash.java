package projectile;

import player.Player;
import animation.ImageSequence;
import game.Game;
import gameObject.GameObject;
import gameObject.MovableObject;

/**
* <b>Description:</b>
* <br>
* Defines projectile movmement with changing velocities
* <br>Requires input of coordinates, and outputs direction and speed of projectile
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class Projectile_SaDash extends Projectile{
	int duration;
	int durationTimer;
	/**
	 * defines SaDash
	 * @param x coordinate X
	 * @param y coordinate Y
	 * @param game instance of Game
	 */
	public Projectile_SaDash(int x, int y, Game game, GameObject o, int duration) {
		super(x, y, game, o);
		this.duration = duration;
		flyRight = new ImageSequence("/image/projectiles/bariaRight", 12);
		flyDown = new ImageSequence("/image/projectiles/bariaDown", 12);
		
		setStartingAnimation();
		setStartingVelocity(0);
		damage=(((Player)owner).bombStrength)/3;
	}
	/**
	 * uses currennt status to determine next iteration
	 */
	public void tick(){
		super.tick();
		durationTimer++;
		this.x=owner.getX();
		this.y=owner.getY();
		
		if(durationTimer>duration||!((MovableObject)owner).isCharging()){
			remove();
		}
	}
	
	

}
