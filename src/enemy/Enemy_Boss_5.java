package enemy;

import java.util.LinkedList;

import player.Player;
import projectile.Projectile_Building;
import projectile.Projectile_ThornBall;
import projectile.Projectile_bossFire;
import animation.ImageSequence;
import system.GameSystem;
import system.Physics;
import game.Game;
import gameObject.Point;

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
public class Enemy_Boss_5 extends Enemy{
	private ImageSequence open;
	/**
	 * defines new enemy boss
	 * @panam coordinates, game object
	 * @return enemy
	 */
	public Enemy_Boss_5(int x, int y, Game game) {
		super(x, y, game);
		setDontFlip(true);
		
		open=new ImageSequence("/image/spriteSheet/actors/enemy/boss_5/open",10);
		open.setWidth(4*GameSystem.GRID_SIZE);
		open.setHeight(4*GameSystem.GRID_SIZE);
		stand=new ImageSequence("/image/spriteSheet/actors/enemy/boss_5/stand",10);
		stand.setWidth(4*GameSystem.GRID_SIZE);
		stand.setHeight(4*GameSystem.GRID_SIZE);
		sequence.startSequence(stand);
		
		collisionWidth=4*GameSystem.GRID_SIZE;
		collisionHeight=4*GameSystem.GRID_SIZE;
		
		this.setSpeed(12);
		setHp(600);
		setCollisionDamage(50);
		setExp(1000);
		
		abi1Cd=60;
		abi2Cd=420;
		ultyCd=120;
		
	}
	/**
	 * deterimes next iteration based on current status
	 */
	public void tick(){
		ultyTimer++;
		if(ultyTimer>ultyCd){
			useUltimate();
		}
		abi1Timer++;
		if(abi1Timer>abi1Cd){
			useAbility1();
			abi1Timer=0;
		}
		abi2Timer++;
		if(abi2Timer>abi2Cd){
			useAbility2();
			abi2Timer=0;
		}
		
		if(hp<=0){
			remove();
		}
		sequence.tick();
		
		
		damageRenderer.tick();
		if(invincible){
			invincibleTimer++;
			if(invincibleTimer>invincibleTime){
				invincibleTimer=0;
				invincible=false;
			}
		}
		
		
		setXPosition(x+getVelX());
		setYPosition(y+getVelY());
		updatePosition();
		checkIfAtEdge();
		
		LinkedList<Player> playerHit = Physics.hitPlayer(this, game.getController().getPlayerList());
		if(playerHit!=null){
			for(int i=0;i<playerHit.size();i++){
				applyDamage(collisionDamage,30,playerHit.get(i));
			}
		}
		counter++;
		if(this.canMove){
			if(counter>(20+actionOffSet)){
				counter=0;
				actionOffSet=rand.nextInt(20);
				if(rand.nextInt(10)<8){
					if(GameSystem.isPlayerOne){
						moveRandomly();
						//chasePlayer();
					}
					
				}
				else{
					if(GameSystem.isPlayerOne){
						moveRandomly();
					}
				}
			}
		}
		int bombKicked=Physics.onTopOfBomb(this, game.getBombList());
		if(bombKicked!=-1){
			this.kickBomb();
		}
		
	
	}
	
	@Override
	/**
	 * defines boss attributes
	 */
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
		controller.addEntity(new Projectile_bossFire(getxGridNearest()+1,getyGridNearest()+3,game,this));
		controller.addEntity(new Projectile_bossFire(getxGridNearest()+1,getyGridNearest()+3,game,this));
		
	}

	@Override
	public void useAbility2() {
		controller.addEntity(new Projectile_Building(getxGridNearest(),getyGridNearest(),game,this));
		
	}

	@Override
	public void useAbility3() {
		// TODO Auto-generated method stub
		
	}
	
}
