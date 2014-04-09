package enemy;

import projectile.Projectile_Scissors;
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
public class Enemy_1_1 extends Enemy{
	private ImageSequence attack;
	
	/**
	 * defines new enemy
	 * @panam coordinates, game object
	 * @return enemy
	 */
	public Enemy_1_1(int x, int y, Game game) {
		super(x, y, game);
		
		attack = new ImageSequence("/image/spriteSheet/actors/enemy/enemy_1_1/attack",8);
		attack.setAnimationSpeed(0.5);
		run=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_1_1/run",8);
		stand=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_1_1/stand",8);
		//stand=new ImageSequence("/image/spriteSheet/actors/enemy/boss_6/stand",10);
		damage=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_1_1/damage",4);		
		sequence.startSequence(stand);
		ultyCd=60;
		
		
		this.setHp(70);
		this.setSpeed(8);
		this.setCollisionDamage(15);
		this.setExp(50);
		
	}

	@Override
	/**
	 * defines enemy attributes
	 */
	public void useUltimate() {
		String dir = ai.isValidStraightLine(controller.wallArray, Game.getPlayer().getxGridNearest(), Game.getPlayer().getyGridNearest(), getxGridNearest(), getyGridNearest());
		if(dir!="stop"){
			moveToDirection(dir);
			setVelX(0);
			setVelY(0);
			sequence.startSequence(attack, stand);
			controller.addEntity(new Projectile_Scissors(getxGridNearest(),getyGridNearest(),game,this));
			ultyTimer=0;
		}
	}
	@Override
	public void useAbility1() {
		
		
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
