package bomb;

import data.SpriteData;
import enemy.Enemy;
import game.Game;
import gameObject.MovableObject;

import java.util.LinkedList;

import player.Player;
import system.Physics;
import animation.ImageSequence;

/**
* <b>Description:</b>
* <br><br>
* Dislayment of bomb at appropriate time and location on map grid
* <br>Bomb is designed to appear when it is dropped by character at user's command
* <br>Bomb is placed at exact coordinates of the character
* <br>Bomb should disappear when detonated
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class Bomb extends MovableObject{
	public int counter;
	public int explodeTime;
	public int strength;
	public int length;
	public int xStarting,yStarting;
	public String direction;
	public LinkedList<MovableObject> initiallyOnBomb=new LinkedList<MovableObject>();
	
	/**
	 * Responsible for acquiring bomb image from sprite sheet and detonating at specified time
	 * <br>Includes effects such as damage to enemies and player character
	 * <br><br>
	 * <b>Inputs:</b>
	 * <br><b>x</b>, <b>y</b> - coordinates for where bomb should be placed
	 * <br><b>game</b> - Game object
	 * <br><b>bombStrength</b> - the amount of health the character loses from the bomb
	 * <br><b>bombLength</b> - the size of the bomb
	 * <br><b>duration</b> - the time that the bomb is present before detonation
	 * @panam coordinates, game object; bomb strength, length, duration
	 * @return bomb
	 */
	public Bomb(int x,int y, Game game,int bombStrength,int bombLength,int duration){
		super(x,y,game);
		this.spd=30;
		stand=new ImageSequence("/image/projectiles/bomb/stand",1);
		sequence.startSequence(stand);
		
		explodeTime=duration;
		length=bombLength;
		strength=bombStrength;
		setSs(SpriteData.bomb);
		setImage(getSs().grabImage(1, 1, 32, 32));
		counter = 0;
		hp=1;
		
		xStarting=getxGridNearest();
		yStarting=getyGridNearest();
		//fireBomb();
		LinkedList<Player> playerHit = Physics.hitPlayer(this, game.getController().getPlayerList());
		if(playerHit!=null){
			for(int i=0;i<playerHit.size();i++){
				initiallyOnBomb.add(playerHit.get(i));
			}
		}
		
		
		LinkedList<Enemy> enemies=Physics.collision(this, game.getEnemyList());
		for(int i=0;i<enemies.size();i++){
			initiallyOnBomb.add(enemies.get(i));
		}
		
		initiallyOnBomb.add(this);
	}
	
	/**
	 * Incremental method that determines the next process based on the current status quo
	 */
	public void tick(){
		super.tick();
		for(int i=0;i<initiallyOnBomb.size();i++){
			if(!Physics.collide(this, initiallyOnBomb.get(i))){
				initiallyOnBomb.remove(i);
			}
		}
		
		counter++;
		if(counter>explodeTime||hp<=0){
			if(!game.isTimeStop()){
				explode();
			}
		}
	}
	
	/**
	 * The bomb's explosion effects grids within specific lengths from point of explosion
	 */
	public void explode(){
		game.getController().createExplosion((int)super.getxGridNearest(), (int)super.getyGridNearest(), length, strength);
		game.getController().removeEntity(this);
	}
	
	/**
	 * The bomb disappears after it is detonated
	 */
	public void removeFromMap(){
		boolean[][] map = game.getBombArray();
		map[xStarting][yStarting]=false;
	}
	public void remove(){
		game.getController().removeEntity(this);
	}
	@Override
	public void useUltimate() {
		// TODO Auto-generated method stub
		return;
	}
	public  void useAbility1(){
		
	}
	public  void useAbility2(){
		
	}
	public  void useAbility3(){
		
	}
}
