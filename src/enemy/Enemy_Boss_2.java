package enemy;

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
public class Enemy_Boss_2 extends Enemy{
	/**
	 * defines new enemy boss
	 * @panam coordinates, game object
	 * @return enemy
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

	@Override
	/**
	 * defines boss attributes
	 */
	public void useUltimate() {
		/*
		LinkedList<Point> points = ai.obtainRandomValidPoints(game.getWallArray(), 10);
		if(points.size()>0){
			for(int i=0;i<points.size();i++){
				controller.addEntity(new Bomb(points.get(i).getX(),points.get(i).getY(),game,50,3,50));
			}
			ultyTimer=0;
		}
		*/
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
