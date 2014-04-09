package bricks;

import powerUps.PowerUps_DamageUp;
import powerUps.PowerUps_HpUp;
import powerUps.PowerUps_SpeedUp;
import system.GameSystem;
import game.Game;
import gameObject.GameObject;

/**
* <b>Description:</b>
* <br><br>
* Displays brick image as an obstacle in game map
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public abstract class Brick extends GameObject{

	/**
	 * Displays brick image at specific coordinates in game map
	 * <br>Acquires brick image and fits the image at specific size based on grid map conditions
	 * 
	 * <b>Inputs:</b>
	 * <b>x</b>,<b>y</b> - coordinates of brick image
	 * <b>game</b> - Game object
	 */
	public Brick(int x, int y, Game game) {
		super(x, y, game);
		hp=30;
		setImage(GameSystem.loader.loadImage("/image/stage/stage1/brick1.png"));
		setImageWidth(GameSystem.GRID_SIZE);
		setImageHeight(GameSystem.GRID_SIZE);
		//this.renderYShift=-GameSystem.GRID_SIZE*1/4;
		/*
		ss=SpriteData.bricks;
		ssWidth=GameSystem.GRID_SIZE;
		ssHeight=GameSystem.GRID_SIZE;
		imageWidth=GameSystem.GRID_SIZE;
		imageHeight=GameSystem.GRID_SIZE;
		super.image=ss.grabImage(1,1,32,32);
		*/
	}
	
	/**
	 * Checks current conditions and defines next process
	 */
	public void tick(){
		super.tick();
		if(hp<=0){
			int type = getRand().nextInt(6);
			game.getController().removeEntity(this);
			if(type==3){
				game.getController().addEntity(new PowerUps_SpeedUp(this.getxGridNearest(),getyGridNearest(),game));
			}
			else if(type==4){
				game.getController().addEntity(new PowerUps_DamageUp(this.getxGridNearest(),getyGridNearest(),game));
			}
			else if(type==5){
				game.getController().addEntity(new PowerUps_HpUp(this.getxGridNearest(),getyGridNearest(),game));
			}
		}
	}
	
	/**
	 * Removes brick image
	 * <br>ex. fire from the bomb reaches and destroys brick
	 */
	public void remove(){
		game.getController().removeEntity(this);
	}
}
