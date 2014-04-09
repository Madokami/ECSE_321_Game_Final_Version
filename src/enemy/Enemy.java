package enemy;

import game.Game;
import gameObject.MovableObject;

import java.util.LinkedList;
import java.util.Random;

import data.SpriteData;
import player.Player;
import projectile.Projectile_SpawnEffect;
import system.Ai;
import system.GameSystem;
import system.Physics;

/**
* <b>Description:</b>
* <br>Contains the attributes and data of the enemy
* <br>Data is adjusted based on other factors such as AI and other players
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public abstract class Enemy extends MovableObject{
	
	public int score, exp;
	public Random rand;
	public int counter;
	public int collisionDamage;
	protected Ai ai;
	public int actionOffSet;
	protected boolean canMove=true;
	
	protected int ultyTimer,ultyCd,abi1Timer,abi1Cd,abi2Timer,abi2Cd;
	
	/**
	 * Defines new enemy with attributes such as exp and collisionDamage
	 * <br><br>
	 * <b>Inputs:</b>
	 * <br><b>x</b>,<b>y</b> - coordinates of enemy
	 * <br><b>game</b> - Game object
	 * @param x coordinate X in grids
	 * @param y coordinate Y in grids
	 * @param game instance of Game
	 * @return enemy
	 */
	public Enemy(int x,int y, Game game){
		super(x,y,game);
		//image = ss.grabImage(10, 1, 32, 32);
		setSsX(10);
		setSsY(1);
		setSs(SpriteData.char2);
		setImage(getSs().grabImage(getSsX(), getSsY(), getSsWidth(), getSsHeight()));
		
		score = 50;
		exp = 50;
		collisionDamage=20;
		
		counter = 0;
		rand = new Random();
		ai=new Ai();
		actionOffSet = rand.nextInt(10);
		
		controller.addEntity(new Projectile_SpawnEffect(getxGridNearest(),getyGridNearest(),game,this));
	}
	
	/**
	 * Enemies attributes are changed based on existing conditions
	 * <br>ex. twoPlayerMode
	 */
	public void tick(){
		super.tick();
		/*
		if(Physics.overlapWithOtherEnemies(this, game.getEnemyList())){
			this.moveToLastAcceptableLocation();
		}
		*/
		if(GameSystem.LAN_TWO_PLAYER_MODE){
			if(GameSystem.isPlayerOne){
				if(positionUpdateTimer>15){
					positionUpdateTimer=0;
					sendCommand("updateX"+Double.toString(x));
					sendCommand("updateY"+Double.toString(y));
				}
			}
		}
		LinkedList<Player> playerHit = Physics.hitPlayer(this, game.getController().getPlayerList());
		if(playerHit!=null){
			for(int i=0;i<playerHit.size();i++){
				applyDamage(collisionDamage,collisionDamage/4,30,playerHit.get(i));
			}
		}
		counter++;
		if(this.canMove){
			if(counter>(5+actionOffSet)){
				counter=0;
				actionOffSet=rand.nextInt(5);
				if(rand.nextInt(10)<7){
					if(GameSystem.TWO_PLAYER_MODE){
						int p1Distance = Math.abs(getxGridNearest()-Game.getPlayer().getxGridNearest())+Math.abs(getyGridNearest()-Game.getPlayer().getyGridNearest());
						int p2Distance = Math.abs(getxGridNearest()-Game.getPlayer2().getxGridNearest())+Math.abs(getyGridNearest()-Game.getPlayer2().getyGridNearest());
						if(p1Distance<p2Distance){
							chasePlayer();
						}
						else{
							chasePlayer2();
						}
					}
					else{
						if(GameSystem.isPlayerOne){
							chasePlayer();
						}
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
		/*
		if(counter>40){
			counter=0;
			moveRandomly();
		}
		*/
		
		if(hp<=0){
			remove();
		}
		/*
		if(super.x<=0)
			moveRight();
		if(super.x>=Game.WIDTH*Game.SCALE-23)
			moveLeft();
		if(super.y<=0)
			super.moveDown();
		if(super.y>=Game.HEIGHT*Game.SCALE-23)
			super.moveUp();
		if(Physics.collision(game.p, this)){
			//game.c.removeEntity(this);
		}
		*/
	
	}
	/**
	 * changes experience of player
	 * @param player object
	 * @return points of player
	 */
	public void providePoints(Player p){
		p.expCurrent+=exp;
		
	}
	
	/**
	 * Defines random enemy movement in any of the four directions: up, down, left, right
	 */
	public void moveRandomly(){
		int temp = rand.nextInt(4);
		if(temp==0){
			moveUp();
			sendCommand("moveUp");
		}
		else if(temp==1){
			moveDown();
			sendCommand("moveDown");
		}
		else if(temp==2){
			moveLeft();
			sendCommand("moveLeft");
		}
		else if(temp==3){
			moveRight();
			sendCommand("moveRight");
		}
	}
	
	/**
	 * Based on coordinates of enemy and character, the enemy moves towards the character
	 */
	public void chasePlayer(){
		String s;
		s=ai.makeStep(game.getWallArray(), Game.getPlayer().getxGridNearest(), Game.getPlayer().getyGridNearest(), lastX, lastY);
		if(s.equals("up")) {
			if(!Physics.blockedByEnemy(this, game.getEnemyList(), lastX, lastY-1)){
				this.sendCommand("moveUp");
				moveUp();
			}
		}
		else if(s.equals("down")) {
			if(!Physics.blockedByEnemy(this, game.getEnemyList(), lastX, lastY+1)){
				sendCommand("moveDown");
				moveDown();
			}
		}
		else if(s.equals("left")) {
			if(!Physics.blockedByEnemy(this, game.getEnemyList(), lastX-1, lastY)) {
				sendCommand("moveLeft");
				moveLeft();
			}
		}
		else if(s.equals("right")) {
			if(!Physics.blockedByEnemy(this, game.getEnemyList(), lastX+1, lastY-1)) {
				sendCommand("moveRight");
				moveRight();
			}
		}
		else if(s.equals("stop")) {
			moveRandomly();
		}
	}
	public void chasePlayer2(){
		String s;
		s=ai.makeStep(game.getWallArray(), Game.getPlayer2().getxGridNearest(), Game.getPlayer2().getyGridNearest(), lastX, lastY);
		if(s.equals("up")) {
			if(!Physics.blockedByEnemy(this, game.getEnemyList(), lastX, lastY-1)){
				this.sendCommand("moveUp");
				moveUp();
			}
		}
		else if(s.equals("down")) {
			if(!Physics.blockedByEnemy(this, game.getEnemyList(), lastX, lastY+1)){
				sendCommand("moveDown");
				moveDown();
			}
		}
		else if(s.equals("left")) {
			if(!Physics.blockedByEnemy(this, game.getEnemyList(), lastX-1, lastY)) {
				sendCommand("moveLeft");
				moveLeft();
			}
		}
		else if(s.equals("right")) {
			if(!Physics.blockedByEnemy(this, game.getEnemyList(), lastX+1, lastY-1)) {
				sendCommand("moveRight");
				moveRight();
			}
		}
		else if(s.equals("stop")) {
			moveRandomly();
		}
	}
	
	/**
	 * At the appropriate time and distance, the enemy will charge at the player at a specific speed and direction
	 * <br><br>
	 * <b>Inputs:</b>
	 * <br><b>speed</b> - the rate at which the enemy travels over time
	 * <br><b>duration</b> - the time that the enemy charges at the character
	 * @panam speed, duration
	 * @return charge movmenet
	 */
	public boolean chargeAtPlayer(int speed,int duration){
		String dir=ai.isValidStraightLine(game.getWallArray(), Game.getPlayer().getxGridNearest(), Game.getPlayer().getyGridNearest(), getxGridNearest(), getyGridNearest());
		if(dir.equals("stop")){
			return false;
		}
		else if(dir.equals("right")){
			moveRight();
			startCharge(speed,duration);
		}
		else if(dir.equals("left")){
			moveLeft();
			startCharge(speed,duration);
		}
		else if(dir.equals("up")){
			moveUp();
			startCharge(speed,duration);
		}
		else if(dir.equals("down")){
			moveDown();
			startCharge(speed,duration);
		}
		return true;
	}
	
	/**
	 * Enemy moves in one of the four directions: up, down, left, right
	 * @panam direction
	 * @return movement
	 */
	public void moveToDirection(String dir){
		
		if(dir.equals("right")){
			moveRight();
		}
		else if(dir.equals("left")){
			moveLeft();
		}
		else if(dir.equals("up")){
			moveUp();
		}
		else if(dir.equals("down")){
			moveDown();
		}
		return;
	}
	
	/**
	 * Removes enemy
	 */
	public void remove(){
		game.decreaseEnemyCount();
		game.getController().removeEntity(this);
		providePoints(Game.getPlayer());
		if(GameSystem.TWO_PLAYER_MODE){
			providePoints(Game.getPlayer2());
		}
		Player.SCORE+=score;
	}
	
	public abstract void useUltimate();
	public abstract void useAbility1();
	public abstract void useAbility2();
	public abstract void useAbility3();
	
	/**
	 * sets hp
	 * @panam hp
	 */
	public void setHp(double hp){
		this.hp=hp*Game.DIFFICULTY;
	}
	/**
	 * sets value
	 * @panam value
	 * @return set hp
	 */
	public void setSpeed(double value){
		this.spd=(int) (value*Game.DIFFICULTY);
	}
	/**
	 * sets collision damage
	 * @panam damage
	 * @return set damage
	 */
	public void setCollisionDamage(int value){
		this.collisionDamage=(int) (value*Game.DIFFICULTY);
	}
	/**
	 * sets experience
	 * @panam expierence
	 * @return set experience
	 */
	public void setExp(int value){
		this.exp=value;
		this.score=value;
	}
}
