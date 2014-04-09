package projectile;

import enemy.Enemy;
import game.Game;
import gameObject.GameObject;
import gameObject.MovableObject;
import gameObject.GameObject.ORIENTATION;

import java.util.LinkedList;
import java.util.Random;

import player.Player;
import animation.ImageSequence;
import animation.ProjectileAnimation;
import system.GameSystem;
import system.Physics;

/**
* <b>Description:</b>
* <br>
* Defines Projectile that travels across the screen
* <br>Depends on coordinates on grid map and travels with a certain speed
* <br>The projectile is capable of damaging characters
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public abstract class Projectile extends MovableObject {
	protected ImageSequence flyRight;
	protected ImageSequence flyDown;
	protected int flySpeed;
	protected ProjectileAnimation pAnimate = new ProjectileAnimation(this);
	protected int invincibleDuration;
	protected Random rand = new Random();
	
	public int damage;
	public GameObject owner;
	
	/**
	 * Creates an instance of a Projectile
	 * @param x coordinate X
	 * @param y coordinate Y
	 * @param game instance of Game
	 */
	public Projectile(int x, int y, Game game,GameObject o) {
		super(x, y, game);
		owner=o;
		allignOrientationWithOwner(owner);
		damage = 10;
		hp=5;
		invincibleDuration = 10;
		// TODO Auto-generated constructor stub
	}
	/**
	 * uses current status to determine next iteration
	 */
	public void tick(){
		if(GameSystem.TWO_PLAYER_MODE){
			if(owner==Game.getPlayer()||owner==Game.getPlayer2()){
				LinkedList<Enemy> enemies=Physics.collision(this, game.getEnemyList());
				for(int i=0;i<enemies.size();i++){
					applyDamage(damage,invincibleDuration,enemies.get(i));
				}
			}
			else{
				LinkedList<Player> playerHit = Physics.hitPlayer(this, controller.getPlayerList());
				for(int i=0;i<playerHit.size();i++){
					applyDamage(damage,invincibleDuration,playerHit.get(i));
				}
			}
		}
		else{
			if(owner==Game.getPlayer()){
				LinkedList<Enemy> enemies=Physics.collision(this, game.getEnemyList());
				for(int i=0;i<enemies.size();i++){
					applyDamage(damage,invincibleDuration,enemies.get(i));
				}
			}
			else{
				LinkedList<Player> playerHit = Physics.hitPlayer(this, controller.getPlayerList());
				for(int i=0;i<playerHit.size();i++){
					applyDamage(damage,invincibleDuration,playerHit.get(i));
				}
			}
		}
		if(hp<=0){
			remove();
		}
		x+=getVelX();
		y+=getVelY();
		pAnimate.tick();
	}
	/**
	 * removes image if moves off boundary of grid
	 */
	public void removeIfOutSideScreen(){
		if(x<=-getSsWidth()||y<=-getSsHeight()||x>=GameSystem.GAME_WIDTH||y>=GameSystem.GAME_HEIGHT){
			remove();
		}
	}
	/**
	 * removes image if there is a collision with a wall
	 */
	public void removeIfHitWall(){
		if(Physics.hitWall(this, controller.getBrickList())!=-1){
			remove();
		}
		if(Physics.hitPlaceHolder(this, controller.getPlaceHolderList())!=-1){
			remove();
		}
	}
	
	/**
	 * aligns image with owner object
	 * @param owner object
	 */
	public void allignOrientationWithOwner(GameObject owner){
		if(owner.orientation==ORIENTATION.DOWN){
			this.orientation=ORIENTATION.DOWN;
			
		}
		else if(owner.orientation==ORIENTATION.RIGHT){
			this.orientation=ORIENTATION.RIGHT;
			
		}
		else if(owner.orientation==ORIENTATION.UP){
			this.orientation=ORIENTATION.UP;
			y-=getImageHeight();
		}
		else if(owner.orientation==ORIENTATION.LEFT){
			this.orientation=ORIENTATION.LEFT;
			x-=getImageWidth();
		}
	}
	/**
	 * initializes the starting animation
	 */
	public void setStartingAnimation(){
		if(this.orientation==ORIENTATION.RIGHT||this.orientation==ORIENTATION.LEFT){
			pAnimate.startSequence(flyRight);
		}
		else{
			pAnimate.startSequence(flyDown);
		}
	}
	
	/**
	 * Defines speed of the projectile
	 * <br>distance/time
	 * @param fly speed
	 */
	public void setStartingVelocity(int flySpeed){
		if(orientation==ORIENTATION.RIGHT){
			setVelX(flySpeed);
			direction="right";
		}
		else if(orientation==ORIENTATION.LEFT){
			setVelX(-flySpeed);
			direction="left";
		}
		if(orientation==ORIENTATION.UP){
			setVelY(-flySpeed);
			direction="up";
		}
		if(orientation==ORIENTATION.DOWN){
			setVelY(flySpeed);
			direction="down";
		}
	}
	/**
	 * removes entity
	 */
	public void remove(){
		game.getController().removeEntity(this);
	}
	
	/**
	 * A specific region of the grid map corresponds to possible inflicted damage
	 */
	public void setCollisionToImageSize(){
		if(orientation==ORIENTATION.RIGHT||orientation==ORIENTATION.DOWN){
			collisionWidth=getImageWidth();
			collisionHeight=getImageHeight();
		}
		else if(orientation==ORIENTATION.LEFT){
			collisionWidth=-getImageWidth();
			collisionHeight=getImageHeight();
		}
		else if(orientation==ORIENTATION.UP){
			collisionWidth=getImageWidth();
			collisionHeight=-getImageHeight();
		}
	}
	
	public void useUltimate(){
		return;
	}
	public  void useAbility1(){
		
	}
	public  void useAbility2(){
		
	}
	public  void useAbility3(){
		
	}
	
	public void setDamage(int damage){
		this.damage=damage;
	}
}
