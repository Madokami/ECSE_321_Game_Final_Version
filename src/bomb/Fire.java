package bomb;

import data.SpriteData;
import enemy.Enemy;
import game.Game;
import gameObject.GameObject;

import java.util.LinkedList;

import player.Player;
import projectile.Projectile;
import system.Physics;

/**
* <b>Description:</b>
* <br>Produces the fire that is emitted from explosion
* <br>Displays where the explosion has reached
* <br>Decreases enemy and character health
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class Fire extends GameObject{
int strength;
private int counter;
	
	/**
	 * <b>Inputs:</b>
	 * <br><b>x</b>,<b>y</b> - coordinates of fire
	 * <br><b>game</b> - Game object
	 * <br><b>Strength</b> - magnitude of damage inflicted by 
	 * @panam coordinates, game object, strength
	 * @return projectile
	 */
	public Fire(int x, int y, Game game,int Strength) {
		super(x,y,game);
		
		this.setImageWidth(collisionWidth);
		this.setImageHeight(collisionHeight);
		
		setSs(SpriteData.bricks);
		setImage(getSs().grabImage(5, 15, 32, 32));
		this.strength = Strength;
		counter=0;
	}
	
	/**
	 * Determines the attributes of fire based on current conditions
	 */
	public void tick(){
		counter++;
		if(counter>5){
			remove();
		}
		
		LinkedList<Player> playerHit = Physics.hitPlayer(this, game.getController().getPlayerList());
		if(playerHit!=null){
			for(int i=0;i<playerHit.size();i++){
				applyDamage(strength,15,playerHit.get(i));
			}
		}
		
		LinkedList<Enemy> enemies=Physics.collision(this, game.getEnemyList());
		for(int i=0;i<enemies.size();i++){
			applyDamage(strength,10,enemies.get(i));
		}
		
		int wallHit=Physics.hitWall(this, game.getBrickList());
		if(wallHit!=-1){
			applyDamage(strength,10,game.getBrickList().get(wallHit));
		}
		int bombHit=Physics.hitBomb(this, game.getBombList());
		if(bombHit!=-1){
			applyDamage(strength,10,game.getBombList().get(bombHit));
		}
		LinkedList<Projectile> projTemp = Physics.projectileHit(this, controller.getProjectileList());
		for(int i = 0;i<projTemp.size();i++){
			applyDamage(strength,15,projTemp.get(i));
		}
		
	}

	
	
	public int getStrength(){
		return strength;
	}
	public void remove(){
		game.getController().removeFire(this);
	}
}
