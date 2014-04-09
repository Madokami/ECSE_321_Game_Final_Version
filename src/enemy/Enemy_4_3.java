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
public class Enemy_4_3 extends Enemy_4{
	/**
	 * defines new enemy
	 * @panam coordinates, game object
	 * @return enemy
	 */
	public Enemy_4_3(int x, int y, Game game) {
		super(x, y, game);
		run=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_4_3/run",6);
		stand=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_4_3/stand",6);	
		attack=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_4_3/attack",9);	
		open=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_4_3/open",4);	
		sequence.startSequence(stand);
		canMove=false;
	}

	@Override
	/**
	 * defines enemy attributes
	 */
	public void useUltimate() {
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
