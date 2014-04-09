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
public class Enemy_5_2 extends Enemy_4{
	/**
	 * defines new enemy
	 * @panam coordinates, game object
	 * @return enemy
	 */
	public Enemy_5_2(int x, int y, Game game) {
		super(x, y, game);
		run=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_5_2/run",7);
		stand=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_5_2/stand",8);	
		damage=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_5_2/damage",4);
		sequence.startSequence(stand);
		ultyCd=150;
		this.setHp(300);
		this.setSpeed(10);
		this.setCollisionDamage(35);
		this.setExp(100);
	}

	@Override
	/**
	 * defines enemy attributes
	 */
	public void useUltimate() {
		if(chargeAtPlayer(30,20)){
			ultyTimer=0;
		}
		
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
