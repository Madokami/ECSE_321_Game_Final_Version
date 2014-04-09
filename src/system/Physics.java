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
	 * defines collision
	 * @panam player object, powerups list
	 * @return powerups list
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
	 * @panam game object, bomb lists
	 * @return bombs list
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
	 * @panam game object, bomb list
	 * @return behind bomb
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
	 * @panam game object, enemy list
	 * @return enemy list
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
	 * defines when character hits player
	 * @panam game object, player list
	 * @return player list
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
	 * @panam enemy, enemy list
	 * @return check if overlap of enemies
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
	 * @panam enemy source, enemy list, coordinates
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
	 * @panam game object, bomb list
	 * @return hit bomb
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
	 * @panam game object, brick list
	 * @return hit wall
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
	 * @panam game object, place holder list
	 * @return hit place holder
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
	 * @panam game object coordinates
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
	 * @panam game object, projectile list
	 * @return projectile hit list
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
