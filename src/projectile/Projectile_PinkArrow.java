package projectile;

import player.Player;
import system.GameSystem;
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
public class Projectile_PinkArrow extends Projectile{

	/**
	 * defines pink arrow projectile
	 * @param x coordinate X
	 * @param y coordinate Y
	 * @param game instance of Game
	 */
	public Projectile_PinkArrow(int x, int y, Game game,GameObject o) {
		super(x, y, game,o);
		flyRight = new ImageSequence("/image/projectiles/pinkArrowRight", 5);
		flyDown = new ImageSequence("/image/projectiles/pinkArrowDown", 5);
		flyRight.scale(0.3);
		flyDown.scale(1.3);
		/*
		flyRight.setY(flyRight.getY()-GameSystem.GRID_SIZE-GameSystem.GRID_SIZE/5);
		flyDown.setX(flyRight.getX()-GameSystem.GRID_SIZE-GameSystem.GRID_SIZE/5);
		*/
		setStartingAnimation();
		flySpeed=40;
		
		this.setStartingVelocity(flySpeed);
		setDamage(((Player)owner).bombStrength/2);
		
		int i = rand.nextInt(4);
		String url = "/sound/effects/frost_arrow";
		url=url.concat(Integer.toString(i)).concat(".wav");
		System.out.println(url);
		GameSystem.playSound(url);
	}
	/**
	 * uses current status to determine next iteration
	 */
	public void tick(){
		super.tick();
		setCollisionToImageSize();
		removeIfOutSideScreen();
		//this.setCollisionToImageSize();
	}

}
