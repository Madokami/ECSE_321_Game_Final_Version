package projectile;

import animation.ImageSequence;
import system.GameSystem;
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
public class Projectile_blackBeam extends Projectile{

	public int counter;
	public int duration = 20;
	/**
	 * defines blackBeam projectile
	 * @param coordinates, game, game object
	 */
	public Projectile_blackBeam(int x, int y, Game game, GameObject o) {
		super(x, y, game, o);
		flyRight = new ImageSequence("/image/projectiles/blackBeamRight", 10);
		flyDown = new ImageSequence("/image/projectiles/blackBeamDown", 10);
		
		flyRight.setWidth(1000);
		flyRight.setHeight(GameSystem.GRID_SIZE);
		flyRight.setX(flyRight.getX()+3*GameSystem.GRID_SIZE);
		flyRight.setY(flyRight.getY()+flyRight.getHeight()/2);
		flyDown.setHeight(1000);
		flyDown.setY(flyDown.getY()+GameSystem.GRID_SIZE);
		
		setStartingAnimation();
		flySpeed=0;
		this.setStartingVelocity(flySpeed);
		setImageWidth(1000);
		setImageHeight(1000);
		allignOrientationWithOwner(owner);
	}
	/**
	 * uses current status to determine next iteration
	 */
	public void tick(){
		super.tick();
		setCollisionToImageSize();
		counter++;
		if(counter>duration){
			remove();
		}
	
	}

}
