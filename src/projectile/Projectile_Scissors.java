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
public class Projectile_Scissors extends Projectile{

	private int counter;
	private int duration = 30;
	
	/**
	 * defines scissor projectile
	 * @param coordinates, game, game object
	 * @return projectile
	 */
	public Projectile_Scissors(int x, int y, Game game, GameObject o) {
		super(x, y, game, o);
		this.damage=20;
		
		flyRight = new ImageSequence("/image/projectiles/scissorRight", 4);
		flyDown = new ImageSequence("/image/projectiles/scissorDown", 4);
		
		setStartingAnimation();
		flySpeed=15;
		
		this.setStartingVelocity(flySpeed);
	}
	/**
	 * checks current status to determine next iteration
	 */
	public void tick(){
		super.tick();
		counter++;
		if(counter>duration){
			remove();
		}
		//setCollisionToImageSize();
		removeIfOutSideScreen();
		removeIfHitWall();
		//this.setCollisionToImageSize();
	}
	@Override
	public void applyDamage(int damage,int invincTime,GameObject target){
		super.applyDamage(damage, invincTime, target);
		remove();
	}
}
