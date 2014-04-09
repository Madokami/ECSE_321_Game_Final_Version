package enemy;

import projectile.Projectile_ThornBall;
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
public class Enemy_1_2 extends Enemy{
	public int counter=0;
	public int time=1;
	/**
	 * defines new enemy
	 * @panam coordinates, game object
	 * @return enemy
	 */
	public Enemy_1_2(int x, int y, Game game) {
		super(x, y, game);
		
		run=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_1_2/run",8);
		stand=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_1_2/stand",8);	
		sequence.startSequence(stand);
		ultyCd=150;
		// TODO Auto-generated constructor stub
		
		this.setHp(50);
		this.setSpeed(12);
		this.setCollisionDamage(20);
		this.setExp(35);
	}
	

	@Override
	/**
	 * defines enemy attributes
	 */
	public void useUltimate() {
		if(ai.onTopOfPlayer(this.getxGridNearest(),getyGridNearest(),Game.getPlayer().getxGridNearest(),Game.getPlayer().getyGridNearest())){
			controller.addEntity(new Projectile_ThornBall(getxGridNearest(),getyGridNearest(),game,this));
			game.decreaseEnemyCount();
			controller.removeEntity(this);
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
