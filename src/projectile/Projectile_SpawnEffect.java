package projectile;

import animation.ImageSequence;
import game.Game;
import gameObject.GameObject;
import gameObject.GameObject.ORIENTATION;


/**
* <b>Description:</b>
* <br>
* Defines individual projectile
* <br>Requires input of coordinates, and outputs direction and speed of projectile
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class Projectile_SpawnEffect extends Projectile{
	private int counter;
	private int duration = 15;
	/**
	 * defines spawn effect projectile
	 * @param coordinates, game, game object
	 */
	public Projectile_SpawnEffect(int x, int y, Game game, GameObject o) {
		super(x, y, game, o);
		flyRight = new ImageSequence("/image/projectiles/effectRing", 6);
		flyDown = new ImageSequence("/image/projectiles/effectRing", 6);
		this.orientation=ORIENTATION.RIGHT;
		setStartingAnimation();
		this.damage=0;
		invincibleDuration=30;
	}
	/**
	 * uses current status to determine next iteration
	 */
	public void tick(){
		super.tick();
		counter++;
		if(counter>duration){
			remove();
		}
		
	}
	
	
	
}
