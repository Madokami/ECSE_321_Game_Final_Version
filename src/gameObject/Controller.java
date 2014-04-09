package gameObject;

import enemy.Enemy;
import game.Game;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import bomb.Bomb;
import bomb.Fire;
import bricks.Brick;
import bricks.HitableBrick;
import bricks.PlaceHolder;
import player.Player;
import powerUps.PowerUps;
import projectile.Projectile;
import system.GameSystem;

/**
* <b>Description:</b>
* <br>
* Contains the controllers of the gaming system
* <br>
* Creates players, describes explosions, renders graphics etc.
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class Controller{
	public Game game;
	
	public LinkedList<GameObject> everything = new LinkedList<GameObject>();
	
	private LinkedList<Player> player = new LinkedList<Player>();
	private LinkedList<Bomb> bombList= new LinkedList<Bomb>();
	private LinkedList<Enemy> enemyList= new LinkedList<Enemy>();
	private LinkedList<PowerUps> powerupList = new LinkedList<PowerUps>();
	private LinkedList<Projectile> projectiles = new LinkedList<Projectile>();
	public LinkedList<Fire> fireList = new LinkedList<Fire>();
	public LinkedList<HitableBrick> brickList = new LinkedList<HitableBrick>();
	public LinkedList<PlaceHolder> placeHolderList = new LinkedList<PlaceHolder>();
	public boolean[][] wallArray;
	public boolean[][] bombArray;
	
	Random r = new Random();
	
	/**
	 * Creates a instance of Controller which is used to add and delete all GameObjects
	 * @param game the instance of Game to associate with
	 */
	
	public Controller(Game game){
		this.game=game;
		wallArray = new boolean[GameSystem.GRIDW+1][GameSystem.GRIDH+1];
		bombArray = new boolean[GameSystem.GRIDW+1][GameSystem.GRIDH+1];
	}
	
	/**
	 * Different objects are implemented based on existing conditions
	 */
	public void tick(){
		for(int i=0;i<player.size();i++){
			player.get(i).tick();
		}
		for(int i=0;i<bombList.size();i++){
			bombList.get(i).tick();
		}
		for(int i=0;i<enemyList.size();i++){
			enemyList.get(i).tick();
		}
		for(int i=0;i<powerupList.size();i++){
			powerupList.get(i).tick();
		}
		for(int i=0;i<projectiles.size();i++){
			projectiles.get(i).tick();
		}
		
		for(int i=0;i<fireList.size();i++){
			fireList.get(i).tick();
		}
		for(int i=0;i<brickList.size();i++){
			brickList.get(i).tick();
		}
		for(int i=0;i<placeHolderList.size();i++){
			placeHolderList.get(i).tick();
		}
	}
	
	/**
	 * Renders each graphic by running through loops based on list sizes
	 * @param g current graphic
	 */
	public void render(Graphics g){
		
		for(int i=0;i<powerupList.size();i++){
			powerupList.get(i).render(g);
		}
		for(int i=0;i<bombList.size();i++){
			bombList.get(i).render(g);
		}
		
		
		for(int i=0;i<fireList.size();i++){
			fireList.get(i).render(g);
		}
		
		game.renderStageObjects(g);
		
		for(int i=0;i<brickList.size();i++){
			brickList.get(i).render(g);
		}
		for(int i=0;i<enemyList.size();i++){
			enemyList.get(i).render(g);
		}
		for(int i=0;i<player.size();i++){
			player.get(i).render(g);
		}
		for(int i=0;i<projectiles.size();i++){
			projectiles.get(i).render(g);
		}
		//renders damage as image dealt to object. damage must be above the images at all time
		for(int i=0;i<player.size();i++){
			player.get(i).renderDamage(g);
		}
		for(int i=0;i<enemyList.size();i++){
			enemyList.get(i).renderDamage(g);
		}
		for(int i=0;i<projectiles.size();i++){
			projectiles.get(i).renderDamage(g);
		}
		
		for(int i=0;i<brickList.size();i++){
			brickList.get(i).renderDamage(g);
		}
	}
	public void createPlayer(Player o){
		player.add(o);
		this.everything.add(o);
	}
	public void addEntity(PowerUps o){
		powerupList.add(o);
	}
	public void removeEntity(PowerUps o){
		powerupList.remove(o);
	}
	public void addEntity(Projectile o){
		projectiles.add(o);
		this.everything.add(o);
	}
	public void removeEntity(Projectile o){
		projectiles.remove(o);
		this.everything.remove(o);
	}
	/**
	 * adds bomb entity
	 * @param o bomb
	 */
	public void addEntity(Bomb o){
		bombList.add(o);
		this.everything.add(o);
		bombArray[o.getxGridNearest()][o.getyGridNearest()]=true;
		
	}
	/**
	 * remove bomb entity
	 * @param o bomb
	 */
	public void removeEntity(Bomb o){
		bombList.remove(o);
		this.everything.remove(o);
		bombArray[o.xStarting][o.yStarting]=false;
		
	}
	public boolean[][] getBombArray(){
		return this.bombArray;
	}
	public boolean[][] getWallArray(){
		return this.wallArray;
	}
	public void addEntity(Enemy o){
		enemyList.add(o);
		this.everything.add(o);
	}
	public void removeEntity(Enemy o){
		enemyList.remove(o);
		this.everything.remove(o);
	}
	public LinkedList<Bomb> getBList(){
		return bombList;
	}
	public LinkedList<Enemy> getEList(){
		return enemyList;
	}
	public LinkedList<PowerUps> getPList(){
		return powerupList;
	}
	public LinkedList<Projectile> getProjectileList(){
		return projectiles;
	}
	public Player getPlayer(){
		return player.get(player.size()-1);
	}
	public LinkedList<Player> getPlayerList(){
		return this.player;
	}
	
	public void removePlayer(Player o){
		player.remove(o);
		this.everything.remove(o);
	}
	
	public void removeFire(Fire fire){
		fireList.remove(fire);
	}
	public void addEntity(HitableBrick o){
		brickList.add(o);
		wallArray[o.getxGridNearest()][o.getyGridNearest()]=true;
	}
	public void removeEntity(Brick o){
		wallArray[o.getxGridNearest()][o.getyGridNearest()]=false;
		brickList.remove(o);
	}
	public LinkedList<HitableBrick> getBrickList(){
		return brickList;
	}
	public void addEntity(PlaceHolder o){
		placeHolderList.add(o);
		wallArray[o.getxGridNearest()][o.getyGridNearest()]=true;
	}
	public void removeEntity(PlaceHolder o){
		wallArray[o.getxGridNearest()][o.getyGridNearest()]=false;
		placeHolderList.remove(o);
	}
	public LinkedList<PlaceHolder> getPlaceHolderList(){
		return placeHolderList;
	}
	
	/**
	 * Responsible for detailing attributes of the explosion such as size and damaging effect
	 * <br><br>
	 * <b>Inputs:</b>
	 * <br><b>x</b>,<b>y</b> - coordinates of fire
	 * <br><b>size</b> - size of explosion
	 * <br><b>strength</b> - magnitude of damage inflicted to enemy and character from explosion
	 * @param x X location of center of explosion
	 * @param y Y location of center of explosion
	 * @param size the radius of explosion
	 * @param strength how much damage the explosion does
	 */
	public void createExplosion(int x, int y, int size,int strength){
		GameSystem.musicPlayer.playExplosion();
		Game.explosionPlayed=true;
		fireList.add(new Fire(x,y,game,strength));
		for(int i=1;i<=size;i++){
			if(x-i<1)
				break;
			if(wallArray[x-i][y]==true){
				fireList.add(new Fire(x-i,y,game,strength));
				break;
			}
			fireList.add(new Fire(x-i,y,game,strength));
			
		}
		for(int i=1;i<=size;i++){
			if(x+i>GameSystem.GRIDW)
				break;
			if(wallArray[x+i][y]==true){
				fireList.add(new Fire(x+i,y,game,strength));
				break;
			}
			fireList.add(new Fire(x+i,y,game,strength));
		}
		for(int j=1;j<=size;j++){
			if(y+j>GameSystem.GRIDH)
				break;
			if(wallArray[x][y+j]==true){
				fireList.add(new Fire(x,y+j,game,strength));
				break;
			}
			fireList.add(new Fire(x,y+j,game,strength));
		}
		for(int j=1;j<=size;j++){
			if(y-j<1)
				break;
			if(wallArray[x][y-j]==true){
				fireList.add(new Fire(x,y-j,game,strength));
				break;
			}
			fireList.add(new Fire(x,y-j,game,strength));
		}
	}
}
