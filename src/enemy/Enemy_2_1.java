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
public class Enemy_2_1 extends Enemy{
	/**
	 * defines new enemy
	 * @panam coordinates, game object
	 * @return enemy
	 */
	public Enemy_2_1(int x, int y, Game game) {
		super(x, y, game);
		run=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_2_1/run",6);
		damage=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_2_1/damage",6);	
		stand=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_2_1/stand",3);	
		sequence.startSequence(stand);
		
		ultyCd=210;
		this.setHp(70);
		this.setSpeed(15);
		this.setCollisionDamage(20);
		this.setExp(65);
		
	}

	@Override
	/**
	 * defines enemy attributes
	 */
	public void useUltimate() {
		if(chargeAtPlayer(20,20)){
			ultyTimer=350;
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
