package enemy;

import animation.ImageSequence;
import game.Game;


/**
* <b>Description:</b>
* <br>
* New type of enemy boss is defined with corresponding attributes such as speed and abilities
* <br>Boss spawns at specific coordinates on grid map
* <br>Boss is unique and displayed with images corresponding to its animation
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class Enemy_Boss_2 extends Enemy{
	/**
	 * defines new enemy boss
	* @param x coordinate X
	 * @param y coordinate Y
	 * @param game instance of Game
	 */
	public Enemy_Boss_2(int x, int y, Game game) {
		super(x, y, game);
		run=new ImageSequence("/image/spriteSheet/actors/enemy/boss_2/run",12);
		stand=new ImageSequence("/image/spriteSheet/actors/enemy/boss_2/stand",8);	
		damage=new ImageSequence("/image/spriteSheet/actors/enemy/boss_2/damage",4);
		sequence.startSequence(stand);
		this.setHp(270);
		this.setSpeed(8);
		this.setCollisionDamage(50);
		this.setExp(200);
		
		ultyCd=150;
	}

	public void useUltimate() {
		if(chargeAtPlayer(40,20)){
			ultyTimer=0;
		}
		
	}

	@Override
	public void useAbility1() {
		// TODO Auto-generated method stub
		
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
