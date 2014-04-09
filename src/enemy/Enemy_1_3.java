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
public class Enemy_1_3 extends Enemy{
	/**
	 * defines new enemy
	 * @panam coordinates, game object
	 * @return enemy
	 */
	public Enemy_1_3(int x, int y, Game game) {
		super(x, y, game);
		run=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_1_3/run",9);
		stand=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_1_3/stand",9);	
		sequence.startSequence(stand);
		ultyCd=180;

		
		this.setHp(100);
		this.setSpeed(10);
		this.setCollisionDamage(25);
		this.setExp(75);

	}

	@Override
	/**
	 * defines enemy attributes
	 */
	public void useUltimate() {
		String dir = ai.isValidStraightLine(controller.getWallArray(), this.getxGridNearest(),getyGridNearest(),Game.getPlayer().getxGridNearest(),Game.getPlayer().getyGridNearest());
		if(dir!="stop"){
			if(dir=="right"){
				controller.addEntity(new Enemy_1_2(getxGridNearest()-1,getyGridNearest(),game));
			}
			else if(dir=="left"){
				controller.addEntity(new Enemy_1_2(getxGridNearest()+1,getyGridNearest(),game));
			}
			else if(dir=="up"){
				controller.addEntity(new Enemy_1_2(getxGridNearest(),getyGridNearest()+1,game));
			}
			else if(dir=="down"){
				controller.addEntity(new Enemy_1_2(getxGridNearest(),getyGridNearest()-1,game));
			}
			game.increaseEnemyCount();
			this.ultyTimer=0;
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
