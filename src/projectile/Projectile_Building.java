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
public class Projectile_Building extends Projectile{
	private int duration=500;
	private int counter;
	/**
	 * defines building projectile
	 * @param coordinates, game, game object
	 */
	public Projectile_Building(int x, int y, Game game, GameObject o) {
		super(x, y, game, o);
		flyDown=new ImageSequence("/image/projectiles/building", 1);
		flyDown.setWidth(5*GameSystem.GRID_SIZE);
		flyDown.setHeight(4*GameSystem.GRID_SIZE);
		collisionWidth=5*GameSystem.GRID_SIZE;
		collisionHeight=4*GameSystem.GRID_SIZE;
		
		
		int vx = rand.nextInt(3)+3;
		int vy = rand.nextInt(3)+3;
		
		int dir = rand.nextInt(2);
		int px;
		int py;
		if(dir==0){
			px = -collisionWidth;
			py = -collisionHeight;
		}
		else{
			px=GameSystem.GAME_WIDTH;
			py=GameSystem.GAME_HEIGHT;
			vx=-vx;
			vy=-vy;
		}
		setXPosition(px);
		setYPosition(py);
		
		setVelX(vx);
		setVelY(vy);
		pAnimate.startSequence(flyDown);
		this.hp=200;
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
