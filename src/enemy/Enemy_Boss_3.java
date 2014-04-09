package enemy;

import projectile.Projectile_Thunder;
import animation.ImageSequence;
import game.Game;

/**
* <b>Description:</b>
* <br>
* New type of enemy boss is defined with corresponding attributes such as speed and abilites
* <br>Booss spawns at specific coordinates on grid map
* <br>Boss is unique and displayed with images corresponding to its animation
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class Enemy_Boss_3 extends Enemy{
	protected ImageSequence attack;
	protected ImageSequence summon;
	/**
	 * defines new enemy boss
	 * @panam coordinates, game object
	 * @return enemy
	 */
	public Enemy_Boss_3(int x, int y, Game game) {
		super(x, y, game);
		stand=new ImageSequence("/image/spriteSheet/actors/enemy/boss_3/stand",8);	
		damage=new ImageSequence("/image/spriteSheet/actors/enemy/boss_3/shuffle",8);
		attack=new ImageSequence("/image/spriteSheet/actors/enemy/boss_3/thunder",8);
		summon=new ImageSequence("/image/spriteSheet/actors/enemy/boss_3/tsukaima",8);
		sequence.startSequence(stand);
		
		this.setHp(400);
		this.setSpeed(8);
		this.setCollisionDamage(40);
		this.setExp(300);
		
		ultyCd=90;
		abi2Cd=500;
	}

	@Override
	/**
	 * defines boss attributes
	 */
	public void useUltimate() {
		//laser attack
		String dir = ai.isValidStraightLine(controller.wallArray, Game.getPlayer().getxGridNearest(), Game.getPlayer().getyGridNearest(), getxGridNearest(), getyGridNearest());
		if(dir!="stop"){
			moveToDirection(dir);
			setVelX(0);
			setVelY(0);
			sequence.startSequence(attack, stand);
			controller.addEntity(new Projectile_Thunder(getxGridNearest(),getyGridNearest(),game,this));
			ultyTimer=0;
		}
	}

	@Override
	public void useAbility1() {
		//summon stuff
	}

	@Override
	public void useAbility2() {
		int temp = rand.nextInt(2);
		if(temp==0){
			controller.addEntity(new Enemy_3_1(getxGridNearest(),getyGridNearest(),game));
		}
		else{
			controller.addEntity(new Enemy_3_2(getxGridNearest(),getyGridNearest(),game));
		}
		game.increaseEnemyCount();
		
	}

	@Override
	public void useAbility3() {
		// TODO Auto-generated method stub
		
	}

}
