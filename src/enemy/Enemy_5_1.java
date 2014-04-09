package enemy;



import projectile.Projectile_blackBeam;
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
public class Enemy_5_1 extends Enemy_4{
	/**
	 * defines new enemy
	 * @panam coordinates, game object
	 * @return enemy
	 */
	public Enemy_5_1(int x, int y, Game game) {
		super(x, y, game);
		run=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_5_1/run",8);
		stand=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_5_1/stand",8);	
		attack=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_5_1/attack",13);	
		attack.setAnimationSpeed(0.5);
		damage=new ImageSequence("/image/spriteSheet/actors/enemy/enemy_5_1/damage",5);
		sequence.startSequence(stand);
		ultyCd = 240;
		this.setHp(200);
		this.setSpeed(12);
		this.setCollisionDamage(30);
		this.setExp(100);
	}


	@Override
	public void useAbility2() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void useAbility3() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void useUltimate() {
		String dir = ai.isValidStraightLine(controller.wallArray, Game.getPlayer().getxGridNearest(), Game.getPlayer().getyGridNearest(), getxGridNearest(), getyGridNearest());
		if(dir!="stop"){
			moveToDirection(dir);
			setVelX(0);
			setVelY(0);
			sequence.startSequence(attack, stand);
			controller.addEntity(new Projectile_blackBeam(getxGridNearest(),getyGridNearest(),game,this));
			ultyTimer=0;
		}
		
	}

}
