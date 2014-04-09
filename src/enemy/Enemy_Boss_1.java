package enemy;

import java.util.LinkedList;

import projectile.Projectile_Scissors;
import projectile.Projectile_ThornBall;
import animation.ImageSequence;
import game.Game;
import gameObject.Point;

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
public class Enemy_Boss_1 extends Enemy{
	protected ImageSequence attack;
	/**
	 * defines new enemy boss
	* @param x coordinate X
	 * @param y coordinate Y
	 * @param game instance of Game
	 */
	public Enemy_Boss_1(int x, int y, Game game) {
		super(x, y, game);
		attack = new ImageSequence("/image/spriteSheet/actors/enemy/boss_1/attack",10);
		attack.setAnimationSpeed(0.5);
		run=new ImageSequence("/image/spriteSheet/actors/enemy/boss_1/run",8);
		stand=new ImageSequence("/image/spriteSheet/actors/enemy/boss_1/stand",8);
		damage=new ImageSequence("/image/spriteSheet/actors/enemy/boss_1/damage",4);		
		sequence.startSequence(stand);
		
		this.setHp(250);
		this.setSpeed(10);
		this.setCollisionDamage(40);
		this.setExp(150);
		
		ultyCd=300;
		abi1Cd=200;
		abi2Cd=600;
	}

	public void useUltimate() {
		LinkedList<Point> points = ai.obtainRandomValidPoints(game.getWallArray(), 10);
		if(points.size()>0){
			for(int i=0;i<points.size();i++){
				controller.addEntity(new Projectile_ThornBall(points.get(i).getX(),points.get(i).getY(),game,this));
			}
			ultyTimer=0;
		}
		
	}

	@Override
	public void useAbility1() {
		setVelX(0);
		setVelY(0);
		sequence.startSequence(attack, stand);
		controller.addEntity(new Projectile_Scissors(this.getxGridNearest(),this.getyGridNearest(),game,this));
		
	}

	@Override
	public void useAbility2() {
		int temp = rand.nextInt(3);
		if(temp==0){
			controller.addEntity(new Enemy_1_1(getxGridNearest(),getyGridNearest(),game));
		}
		else if(temp==1){
			controller.addEntity(new Enemy_1_2(getxGridNearest(),getyGridNearest(),game));
		}
		else{
			controller.addEntity(new Enemy_1_3(getxGridNearest(),getyGridNearest(),game));
		}
		game.increaseEnemyCount();
	}

	@Override
	public void useAbility3() {
		// TODO Auto-generated method stub
		
	}
	

}
