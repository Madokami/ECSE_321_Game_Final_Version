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
public class Enemy_3_1 extends Enemy{
	/**
	 * defines new enemy
	* @param x coordinate X
	 * @param y coordinate Y
	 * @param game instance of Game
	 */
	public Enemy_3_1(int x, int y, Game game) {
		super(x, y, game);
		run=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_3_1/run",8);
		stand=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_3_1/stand",8);
		sequence.startSequence(stand);
		
		abi1Cd=5;
		this.setHp(150);
		this.setSpeed(12);
		this.setCollisionDamage(25);
		this.setExp(75);
	}

	@Override
	/**
	 * defines enemy attributes
	 */
	public void useUltimate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void useAbility1() {
		kickBomb();
		
	}

	@Override
	public void useAbility2() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void useAbility3() {
		// TODO Auto-generated method stub
		
	}

}
