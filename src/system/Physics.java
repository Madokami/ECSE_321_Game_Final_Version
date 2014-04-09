package system;

import enemy.Enemy;
import gameObject.GameObject;
import gameObject.GameObject.ORIENTATION;

import java.util.LinkedList;

import bomb.Bomb;
import bricks.HitableBrick;
import bricks.PlaceHolder;
import player.Player;
import powerUps.PowerUps;
import projectile.Projectile;

/**
* <b>Description:</b>
* <br>
* Defines physics of game objects
* <br>ex. collision between a character and an obstacle
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class Physics {
	/**
	 * defines collision between player and linkedList of powerups
	 * @param p player object
	 * @param powerUpList list of powerups
	 * @return the index of the powerUp collided, -1 if does not exist
	 */
	public static int collision(Player p,LinkedList<PowerUps> powerUpList){
		for(int i=0;i<powerUpList.size();i++){
			if(p.getBounds(p.collisionWidth-1,p.collisionHeight-1).intersects(powerUpList.get(i).getBounds(powerUpList.get(i).collisionWidth-1,powerUpList.get(i).collisionHeight-1))){
				return i;
			}
		}
		return -1;
	}
	/**
	 * defines when player moves onto location of bomb
	 * @return the index of the bomb collided, -1 if does not exist
	 */
	public static int onTopOfBomb(GameObject gameObject,LinkedList<Bomb> bList){
		for(int i=0;i<bList.size();i++){
			if(gameObject.getxGridNearest()==bList.get(i).getxGridNearest()&&gameObject.getyGridNearest()==bList.get(i).getyGridNearest()){
				return i;
			}
		}
		return -1;
	}
	/**
	 * defines when player is behind bomb
	 * @return the index of the bomb on top of, -1 if does not exist
	 */
	public static int behindBomb(GameObject gameObject,LinkedList<Bomb> bList){
		if(gameObject.orientation==ORIENTATION.UP){
			for(int i=0;i<bList.size();i++){
				if(gameObject.getxGridNearest()==bList.get(i).getxGridNearest()&&gameObject.getyGridNearest()==bList.get(i).getyGridNearest()+1){
					return i;
				}
			}
		}
		else if(gameObject.orientation==ORIENTATION.DOWN){
			for(int i=0;i<bList.size();i++){
				if(gameObject.getxGridNearest()==bList.get(i).getxGridNearest()&&gameObject.getyGridNearest()==bList.get(i).getyGridNearest()-1){
					return i;
				}
			}
		}
		else if(gameObject.orientation==ORIENTATION.RIGHT){
			for(int i=0;i<bList.size();i++){
				if(gameObject.getxGridNearest()==bList.get(i).getxGridNearest()-1&&gameObject.getyGridNearest()==bList.get(i).getyGridNearest()){
					return i;
				}
			}
		}
		else if(gameObject.orientation==ORIENTATION.LEFT){
			for(int i=0;i<bList.size();i++){
				if(gameObject.getxGridNearest()==bList.get(i).getxGridNearest()+1&&gameObject.getyGridNearest()==bList.get(i).getyGridNearest()){
					return i;
				}
			}
		}
		return -1;
	}
	/**
	 * defines when collision occurs
	 * @return a LinkedList of Enemy that is collided by w
	 */
	public static LinkedList<Enemy> collision(GameObject w,LinkedList<Enemy> ei){
		LinkedList<Enemy> ret= new LinkedList<Enemy>();
		for(int i=0;i<ei.size();i++){
			if(w.getBounds(w.collisionWidth-1,w.collisionHeight-1).intersects(ei.get(i).getBounds(ei.get(i).collisionWidth-1,ei.get(i).collisionHeight-1))){
				ret.add(ei.get(i));
			}
		}
		return ret;
	}
	/**
	 * defines when something hits player
	 * @return a LinkedList of Player that is collided by w
	 */
	public static LinkedList<Player> hitPlayer(GameObject w,LinkedList<Player> players){
		LinkedList<Player> ret= new LinkedList<Player>();
		for(int i=0;i<players.size();i++){
			if(w.getBounds(w.collisionWidth-1,w.collisionHeight-1).intersects(players.get(i).getBounds(players.get(i).collisionWidth-1,players.get(i).collisionHeight-1))){
				ret.add(players.get(i));
			}
		}
		return ret;
	}
	
	
	/**
	 * defines when player is in same coordinates as enemy
	 * @return true if there is overlap
	 */
	public static boolean overlapWithOtherEnemies(Enemy w,LinkedList<Enemy> ei){
		for(int i=0;i<ei.size();i++){
			Enemy tempEnemy=ei.get(i);
			if(w!=tempEnemy){
				if(w.getBounds(w.collisionWidth,w.collisionHeight).intersects(tempEnemy.getBounds(ei.get(i).collisionWidth,ei.get(i).collisionHeight))){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * defines when player is blocked
	 * @return true if is blocked
	 */
	public static boolean blockedByEnemy(Enemy source, LinkedList<Enemy> list,int targetX,int targetY){
		for(int i=0;i<list.size();i++){
			Enemy tempEnemy=list.get(i);
			if(source!=tempEnemy){
				if(list.get(i).getxGridNearest()==targetX&&list.get(i).getyGridNearest()==targetY){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * defines when player hits bomb
	 * @return index of bomb hit, -1 if does not exist
	 */
	public static int hitBomb(GameObject w,LinkedList<Bomb> list){
		for(int i=0;i<list.size();i++){
			if(w.getBounds(w.collisionWidth-1,w.collisionHeight-1).intersects(list.get(i).getBounds(GameSystem.GRID_SIZE-1,GameSystem.GRID_SIZE-1))){
				return i;
			}
		}
		return -1;
	}
	/**
	 * defines when there is a collision with the wall
	 * @return index of wall hit, -1 if does not exist
	 */
	public static int hitWall(GameObject f,LinkedList<HitableBrick> linkedList){
		for(int i=0;i<linkedList.size();i++){
			if(f.getBounds(f.collisionWidth-1, f.collisionHeight-1).intersects(linkedList.get(i).getBounds(GameSystem.GRID_SIZE-1, GameSystem.GRID_SIZE-1)))
				return i;
		}
		return -1;
	}
	/**
	 * defines place holder
	 * @param game object, place holder list
	 * @return index of placeHolder hit, -1 if does not exist
	 */
	public static int hitPlaceHolder(GameObject f,LinkedList<PlaceHolder> linkedList){
		for(int i=0;i<linkedList.size();i++){
			if(f.getBounds(f.collisionWidth-1, f.collisionHeight-1).intersects(linkedList.get(i).getBounds(GameSystem.GRID_SIZE-1, GameSystem.GRID_SIZE-1)))
				return i;
		}
		return -1;
	}
	/**
	 * defines collision
	 * @param game object coordinates
	 * @return collision
	 */
	public static boolean collide(GameObject x,GameObject y){
		if(x.getBounds(x.collisionWidth-1, x.collisionHeight-1).intersects(y.getBounds(y.collisionWidth-1,y.collisionHeight-1))){
			return true;
		}
		return false;
	}
	/**
	 * defines projectile hit
	 * @param game object, projectile list
	 * @return LinkedList of Projectile that x collided with
	 */
	public static LinkedList<Projectile> projectileHit(GameObject x,LinkedList<Projectile> list){
		LinkedList<Projectile> ret = new LinkedList<Projectile>();
		for(int i=0;i<list.size();i++){
			if(x.getBounds(x.collisionWidth-1,x.collisionHeight-1).intersects(list.get(i).getBounds(list.get(i).collisionWidth-1,list.get(i).collisionHeight-1))){
				ret.add(list.get(i));
			}
		}
		return ret;
	}
}
