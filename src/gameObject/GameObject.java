package gameObject;

import game.Game;
import graphicEffects.DamageRenderer;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import bomb.Bomb;
import system.GameSystem;
import system.Physics;

/**
* <b>Description:</b>
* <br>
* GameObject class is extended by most game objects
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public abstract class GameObject{
	//stores the absolute position of character
	//stores the absolute position of character
		public int serialNumber;
	
		protected double x;
		protected double y;
		private int xGridNearest;
		private int yGridNearest;
		protected int lastX,lastY,nextX,nextY; //used to implement moving into grids only
		public double xTemp,yTemp;
		private SpriteSheet ss;
		protected SpriteSheet ssStand;
		private Image image;
		//stores the last moved position of the character
		public double curX;
		public double curY;
		//detects hitting bricks
		private int ssX=1;
		private int ssY=1;
		private int ssWidth=32;
		private int ssHeight=32;
		private int imageWidth=getSsWidth();
		private int imageHeight=getSsHeight();
		private int frames = 3;
		
		
		protected int targetX,targetY;
		
		public boolean finishingMove;
		public boolean atEdge;
		protected Image standGif;
		
		private boolean channelling=false;
		
		
		private Random rand;
		
		// MS is how fast the sprite changes pose
		public double MS = 0.2;
		
		public Game game;
		public String direction;
		public double i=0;
		public boolean blocked;
		
		public boolean invincible = false;
		public int invincibleTimer;
		public int invincibleTime;
		
		public int collisionWidth;
		public int collisionHeight;
		public int renderXShift=0;
		public int renderYShift=0;
		
		protected DamageRenderer damageRenderer;
		protected Controller controller;
	
		
		public enum ORIENTATION{
			DOWN,
			UP,
			LEFT,
			RIGHT,
			//STAND
		};
		public ORIENTATION orientation = ORIENTATION.DOWN;
		public double hp;
		
		/**
		 * Overall GameObject
		 * <br><br>
		 * <b>Inputs:</b>
		 * <br><b>x</b>,<b>y</b> - coordinates
		 * <br><b>game</b> - Game object
		 * @param coordinates, game object
		 * @return game object
		 */
		public GameObject(int x, int y,Game game){
			setSerialNumber();
			
			setxGridNearest(x);
			setyGridNearest(y);
			lastX=x;
			lastY=y;
			nextX=x;
			nextY=y;
			this.x=(x-1)*GameSystem.GRID_SIZE;
			this.y=(y-1)*GameSystem.GRID_SIZE;
			this.game = game;
			curX=this.x;
			curY=this.y;
			direction = "stand";
			setRand(new Random());
			collisionWidth=GameSystem.GRID_SIZE;
			collisionHeight=GameSystem.GRID_SIZE;
			
			damageRenderer=new DamageRenderer(this);
			controller=game.getController();
		}
		public void tick(){
			//updates position of char
			//maps the position to the closest "grid"
			damageRenderer.tick();
			if(invincible){
				invincibleTimer++;
				if(invincibleTimer>invincibleTime){
					invincibleTimer=0;
					invincible=false;
				}
			}
		}
		

		public double getX(){
			return x;
		}
		public double getY(){
			return y;
		}
		public void setX(double x){
			this.x = x;
		}
		public void setY(double y){
			this.y=y;
		}
	
		
		public void render(Graphics g){
			g.drawImage(getImage(),(int)x+renderXShift,(int)y+renderYShift,getImageWidth(),getImageHeight(),null);
			
		}
		public void renderDamage(Graphics g){
			damageRenderer.render(g);
		}
		public Game getGame(){
			return this.game;
		}
		
		
		/**
		 * Designates bounds of specific rectangle of grid map
		 * <br><br>
		 * <b>Inputs:</b>
		 * <br><b>width</b> - width of rectangle
		 * <br><b>height</b> - height of rectangle
		 * @panam width, height
		 */
		public final Rectangle getBounds(int width, int height){
			double xCord=this.x;
			double yCord=this.y;		
			return new Rectangle((int)xCord,(int)yCord,width,height);
		}
		public void playDeathSound(){
			
		}
		/**
		 * defines invincibility
		 * @panam duration
		 */
		public void setInvincible(int time){
			invincibleTime=time;
			invincibleTimer=0;
			invincible=true;
		}
		/**
		 * defines received damage
		 * @panam damage
		 */
		public void takeDamage(int damage){
			this.hp-=damage;
			this.damageRenderer.renderDamage(damage);
		}
		
		/**
		 * Applies damage to character
		 * <br><br>
		 * <b>Inputs:</b>
		 * <br><b>value</b> - amount of damage
		 * <br><b>invincibleDuration</b> - time in which the character does not receive damage
		 * <br><b>target</b> - target object
		 * @panam value, duration, target object
		 * @return application of damage
		 */
		public void applyDamage(int value, int invincibleDuration, GameObject target){
			if(target.invincible) return;
			else{
				target.setInvincible(invincibleDuration);
				target.takeDamage(value);
			}
		}
		/**
		 * Applies damage to character
		 * <br><br>
		 * <b>Inputs:</b>
		 * <br><b>value</b> - amount of damage
		 * <br><b>invincibleDuration</b> - time in which the character does not receive damage
		 * <br><b>target</b> - target object
		 * @panam value, duration, target object
		 * @return application of damage
		 */
		public void applyDamage(int value,int randomValue, int invincibleDuration, GameObject target){
			if(target.invincible) return;
			else{
				target.setInvincible(invincibleDuration);
				if(randomValue>0){
					target.takeDamage(value+getRand().nextInt(randomValue));
				}
				else{
					target.takeDamage(value);
				}
			}
		}
		
		
		
		public double getXAbsolute(){
			return this.x;
		}
		public double getYAbsolute(){
			return this.y;
		}
		public void placeBomb(int bombStrength,int bombLength,int duration){
			if(Physics.onTopOfBomb(this, game.getBombList())!=-1){
				return;
			}
			controller.addEntity(new Bomb(this.getxGridNearest(),this.getyGridNearest(),game,bombStrength,bombLength,duration));
		}
		/**
		 * defines kick bomb
		 */
		public void kickBomb(){
			int kickedNum = Physics.behindBomb(this, game.getBombList());
			if(kickedNum!=-1){
				Bomb kickedBomb=game.getBombList().get(kickedNum);
				if(orientation==ORIENTATION.UP){
					kickedBomb.moveUp();
				}
				else if(orientation==ORIENTATION.DOWN){
					kickedBomb.moveDown();
				}
				else if(orientation==ORIENTATION.LEFT){
					kickedBomb.moveLeft();
				}
				else if(orientation==ORIENTATION.RIGHT){
					kickedBomb.moveRight();
				}
			}
		}
		public void setSerialNumber(){
			this.serialNumber=GameSystem.serialNumber;
			GameSystem.serialNumber++;
		}
		
		public void stopChannelling(){
			this.setChannelling(false);
		}
		public abstract void remove();
		
		/*
		public void sendCommand(String s){
			GameSystem.sendCommand="!"+s+","+Integer.toString(this.serialNumber)+";";
		}
		*/
		/**
		 * send command
		 * @panam string s
		 * @return sending of command
		 */
		public void sendCommand(String s){
			if(!GameSystem.LAN_TWO_PLAYER_MODE){
				return;
			}
			if(GameSystem.sendCommand!=null){
				GameSystem.sendCommand=GameSystem.sendCommand.concat("!"+s+","+Integer.toString((this.serialNumber))+";");
			}
			else{
				GameSystem.sendCommand="!"+s+","+Integer.toString(this.serialNumber)+";";
			}
			
			if(GameSystem.sendCommandSelf!=null){
				GameSystem.sendCommandSelf=GameSystem.sendCommandSelf.concat("!"+s+","+Integer.toString((this.serialNumber))+";");
			}
			else{
				GameSystem.sendCommandSelf="!"+s+","+Integer.toString(this.serialNumber)+";";
			}
		}
		/**
		 * send command to other
		 * @panam string s
		 */
		public void sendCommandToOther(String s){
			if(!GameSystem.LAN_TWO_PLAYER_MODE){
				return;
			}
			if(GameSystem.sendCommand!=null){
				GameSystem.sendCommand=GameSystem.sendCommand.concat("!"+s+","+Integer.toString((this.serialNumber))+";");
			}
			else{
				GameSystem.sendCommand="!"+s+","+Integer.toString(this.serialNumber)+";";
			}
		}
		public Image getImage() {
			return image;
		}
		public void setImage(Image image) {
			this.image = image;
		}
		public int getImageWidth() {
			return imageWidth;
		}
		public void setImageWidth(int imageWidth) {
			this.imageWidth = imageWidth;
		}
		public int getImageHeight() {
			return imageHeight;
		}
		public void setImageHeight(int imageHeight) {
			this.imageHeight = imageHeight;
		}
		public int getFrames() {
			return frames;
		}
		public void setFrames(int frames) {
			this.frames = frames;
		}
		public SpriteSheet getSs() {
			return ss;
		}
		public void setSs(SpriteSheet ss) {
			this.ss = ss;
		}
		public int getSsX() {
			return ssX;
		}
		public void setSsX(int ssX) {
			this.ssX = ssX;
		}
		public int getSsY() {
			return ssY;
		}
		public void setSsY(int ssY) {
			this.ssY = ssY;
		}
		public int getSsWidth() {
			return ssWidth;
		}
		public void setSsWidth(int ssWidth) {
			this.ssWidth = ssWidth;
		}
		public int getSsHeight() {
			return ssHeight;
		}
		public void setSsHeight(int ssHeight) {
			this.ssHeight = ssHeight;
		}
		public int getxGridNearest() {
			return xGridNearest;
		}
		public void setxGridNearest(int xGridNearest) {
			this.xGridNearest = xGridNearest;
		}
		public int getyGridNearest() {
			return yGridNearest;
		}
		public void setyGridNearest(int yGridNearest) {
			this.yGridNearest = yGridNearest;
		}
		public boolean isChannelling() {
			return channelling;
		}
		public void setChannelling(boolean channelling) {
			this.channelling = channelling;
		}
		public Random getRand() {
			return rand;
		}
		public void setRand(Random rand) {
			this.rand = rand;
		}
}
