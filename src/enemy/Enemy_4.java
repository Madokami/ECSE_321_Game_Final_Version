package enemy;

import animation.ImageSequence;
import game.Game;

/**
* <b>Description:</b>
* <br>
* New type of enemy is defined with corresponding attributes such as speed and abilites
* <br>Enemy spawns at specific coordinates on grid map
* <br>Enemy is unique and displayed with images corresponding to its animation
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public abstract class Enemy_4 extends Enemy{
	
	protected ImageSequence attack,open;
	/**
	 * defines new enemy
	 * @param x coordinate X
	 * @param y coordinate Y
	 * @param game instance of Game
	 */	
	public Enemy_4(int x, int y, Game game) {
		super(x, y, game);
		abi1Cd=300+rand.nextInt(30);
		this.setHp(180);
		this.setSpeed(14);
		this.setCollisionDamage(25);
		this.setExp(90);
	}
	
	@Override
	public void useAbility1(){
		//change location
	}
	
}

	
