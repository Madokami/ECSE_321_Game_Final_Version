package game;

import data.GameData;
import data.PlayerData;
import enemy.Enemy;
import gameObject.Controller;
import gameObject.GameObject.ORIENTATION;
import graphicEffects.IntToImage;
import graphicEffects.TimedEvent;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import bomb.Bomb;
import bomb.Fire;
import bricks.HitableBrick;
import bricks.PlaceHolder;
import player.Player;
import powerUps.PowerUps;
import projectile.Projectile;
import menu.Menu;
import system.GameSystem;
import system.GameSystem.STATE;
import system.GameTimer;
import utility.BufferedImageLoader;

/**
* <b>Description:</b>
* <br>
* Overall Game class
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class Game {
	public GameTimer timer;
	//private CommandHandler commandHandler;
	public static boolean TITLE_DONE;
	public static double DIFFICULTY;
	public static int TOTAL_SCORE;
	public static int HIGH_SCORE;
	
	//test make change
	//test change number 2
	private static Player player,player2;
	private BufferedImage background;
	private boolean playing;
	private boolean musicOn;
	private int duration = 3000;
	private BufferedImage hpGauge;
	private BufferedImage bottomPanel,bottomPanel2Players;

	private boolean playerIsAlive = true;
	private LinkedList<Bomb> bombList;
	private LinkedList<Enemy> enemyList;
	private LinkedList<HitableBrick> brickList;
	private LinkedList<PlaceHolder> placeHolderList;
	private LinkedList<Fire> fireList;
	private LinkedList<PowerUps> powerUpList;
	private LinkedList<Projectile> projectileList;
	private boolean[][] wallArray;
	private boolean[][] bombArray;
	
	private Controller controller; 
	public static int curLevel;
	private LevelLoader levelLoader;
	public BufferedImageLoader loader;
	
	
	public static final int lastStage=12;
	
	
	private int enemyCount;
	private boolean victory;
	
	private int shift = 428;
	
	private PlayerData playerData;
	private StageRenderer stageRenderer = new StageRenderer();
	
	
	//handles ticking problems with respect to bomb explosion sound.
	//else "time stop" ability + mass bombing leads to serious lag
	public int timePastSinceLastExplode=0;
	public static boolean explosionPlayed;
	
	
	public long renderStageStart;
	
	private boolean timeStop;
	private boolean stopTick;
	
	public TimedEvent event1; 
	public TimedEvent event2;
	
	GameSystem sys;
	BufferedImage cutIn;
	
	/**
	 * defines characters
	 */
	public enum CHARACTER{
		MADOKA,
		HOMURA,
		SAYAKA,
		MAMI,
		KYOUKO,
	};
	
	
	/**
	 * defines game states
	 */
	public static enum GameState{
		PLAY,
		WAIT,
		LOAD,
	};
	public static GameState gState = GameState.WAIT;
	public static CHARACTER cChosen = CHARACTER.HOMURA;
	public static CHARACTER cChosenP2 = null;
	
	/**
	 * defines overall game system
	 */
	public Game(GameSystem sys){
		
	
		timer = new GameTimer();
		this.sys = sys;
		playing = false;
		levelLoader = new LevelLoader(this);
		controller = new Controller(this);
		curLevel=1;
		musicOn=false;
		event1 = new TimedEvent(this);
		event2= new TimedEvent(this);
		loader = new BufferedImageLoader();
		//car = l.loadImage("/car.gif");
		cutIn = loader.loadImage("/homuraCutIn.png");
		hpGauge = loader.loadImage("/image/hpGauge.png");
		
		playerData = new PlayerData();
		playerData.loadDefaultValues();
		//gameData = new GameData();
		bottomPanel=loader.loadImage("/image/stage/bottomPanel.png");
		bottomPanel2Players=loader.loadImage("/image/stage/twoPlayerBottomLayout.png");

	}

	/**
	 * loads level
	 */
	public void loadLevel(){
		levelLoader.load();
	}
	/**
	 * Checks current conditions to determine changes in game system
	 */
	public void tick(){
		
		
		if(isWaiting()){
			if(GameSystem.LAN_TWO_PLAYER_MODE){
				if(Game.cChosenP2==null){
					System.out.println("player 2 data is still not sent");
					return;
				}
			}
			loadLevel();
			renderStageTitle(duration);
			//Game.cChosenP2=null;
			
			
		}
		if(!isWaiting()){
			if(!playerIsAlive){
				setWait();
				goToDeath();
				GameSystem.setDefaultKeyLayout();
				return;
			}
			if(victory){
				curLevel++;
				if(curLevel>lastStage){
					curLevel=1;
				}
				GameSystem.setDefaultKeyLayout();
				player.updatePlayerData();
				//gameData.updateGameData(this);
				calculateTotalScore();
				goToScore();
				saveGame();
				return;
			}
			
		}
		if(Game.gState==Game.GameState.LOAD){
			if(GameSystem.LAN_TWO_PLAYER_MODE){
				if(GameSystem.otherPlayerIsReady){
					if(Game.TITLE_DONE){
						gState=GameState.PLAY;
						playing=true;
					}
				}
				else{
					System.out.println("waiting for other player.....");
				}
			}
			else{
				if(Game.TITLE_DONE){
					gState=GameState.PLAY;
					playing=true;
				}
			}
		}
		if(Game.gState==Game.GameState.PLAY){
			timer.tick();
			event1.tick();
			event2.tick();
			checkVictoryCondition();
			if(stopTick){
				return;
			}
			if(timeStop){
				player.tick();
				if(GameSystem.TWO_PLAYER_MODE){
					player2.tick();
				}
				for(int i=0;i<this.bombList.size();i++){
					bombList.get(i).tick();
				}
				return;
			}			
			controller.tick();
		}
		if(explosionPlayed){
			if(timePastSinceLastExplode<10){
				timePastSinceLastExplode++;
				
			}
			else{
				GameSystem.musicPlayer.reloadExplosion();
				explosionPlayed=false;
				timePastSinceLastExplode=0;
			}
		}

		
	}
	/**
	 * renders stage title
	 * @param duration int
	 */
	public void renderStageTitle(int duration){
		levelLoader.renderStart(duration);
		gState=GameState.LOAD;
	}
	/**
	 * updates playing
	 */
	public void updatePlaying(){
		if(!playerIsAlive){
			playing=false;
		}
	}
	/**
	 * defines witing state
	 */
	public boolean isWaiting(){
		if(gState==GameState.WAIT){
			return true;
		}
		return false;
	}
	/**
	 * sets wait state
	 */
	public void setWait(){
		gState=GameState.WAIT;
	}
	/**
	 * Renders graphics
	 * <br>Incorporates background, maps, obstacles, characters, enemies, etc.
	 * @param g graphic object
	 */
	public void render(Graphics g){
	
			if(Game.gState==Game.GameState.LOAD){
				levelLoader.render(g);
			}
			else if(Game.gState==Game.GameState.PLAY){
				g.drawImage(background, 0, 0,GameSystem.GAME_WIDTH+10,GameSystem.GAME_HEIGHT, null);
				g.setColor(Color.WHITE);
				for(int i=0;i<GameSystem.GAME_WIDTH;i+=GameSystem.GRID_SIZE){
					g.drawLine(i, 0, i, GameSystem.GAME_HEIGHT);
				}
				for(int i=0;i<GameSystem.GAME_HEIGHT;i+=GameSystem.GRID_SIZE){
					g.drawLine(0, i, GameSystem.GAME_WIDTH, i);
				}
				
				controller.render(g);
				g.setColor(Color.DARK_GRAY);
				g.fillRect(0, GameSystem.GAME_HEIGHT, GameSystem.GAME_WIDTH+10, 106);
				event1.render(g);
				event2.render(g);
				
				if(GameSystem.TWO_PLAYER_MODE){
					renderTwoPlayerStatus(g);				
					//draws the thing at the bottom which acts as background for player stats
					g.drawImage(bottomPanel2Players, 0, GameSystem.ABSHEIGHT-148,910,160, null);
					
					renderPlayerHealth(g);
					renderPlayerMana(g);
					renderTwoPlayerExp(g);
					renderTwoPlayerSkills(g);
					renderTwoPlayerLevel(g);
					renderTwoPlayerSoulGem(g);
					
				}
				else{
					player.renderPlayerStatus(g);
					//draws the thing at the bottom which acts as background for player stats
					g.drawImage(bottomPanel, 0, GameSystem.ABSHEIGHT-148,910,160, null);
					
					renderPlayerHealth(g);
					renderPlayerMana(g);
					player.renderExp(g);;
					player.renderSkills(g);
					player.renderPlayerLevel(g);
					player.renderSoulGem(g);
				}
				
				timer.render(g);
			}
		
	}
	
	/**
	 * renders two player game
	 * @param g graphic object
	 */
	private void renderTwoPlayerSoulGem(Graphics g) {
		int soulX = GameSystem.GAME_WIDTH/2-440;
		int nameX = 110;
		int shift = 454;
		g.setFont(new Font("serif",Font.BOLD,12));
		g.setColor(Color.LIGHT_GRAY);
		g.drawString(player.name, nameX, GameSystem.ABSHEIGHT-Player.H_STATUS+3);
		g.drawImage(player.soulGemImage,soulX,GameSystem.GAME_HEIGHT-10,null);
		for(int i=0;i<player.soulGemValueImage.length;i++){
			g.drawImage(player.soulGemValueImage[i],soulX+i*20,GameSystem.GAME_HEIGHT+60,null);
		}
		
		g.drawString(player2.name, nameX+shift, GameSystem.ABSHEIGHT-Player.H_STATUS+3);
		g.drawImage(player2.soulGemImage,soulX+shift,GameSystem.GAME_HEIGHT-10,null);
		for(int i=0;i<player2.soulGemValueImage.length;i++){
			g.drawImage(player2.soulGemValueImage[i],soulX+i*20+shift,GameSystem.GAME_HEIGHT+60,null);
		}
		
	}

	/**
	 * renders two player level
	 * @param g graphic object
	 */
	private void renderTwoPlayerLevel(Graphics g) {
		int shift = 454;
		int x = 75;
		for(int i=0;i<player.levelImage.length;i++){
			g.drawImage(player.levelImage[i],x+i*12,GameSystem.GAME_HEIGHT+89,null);
		}
		for(int i=0;i<player2.levelImage.length;i++){
			g.drawImage(player2.levelImage[i],x+i*12+shift,GameSystem.GAME_HEIGHT+89,null);
		}
		
	}
	/**
	 * renders two player skills
	 * @param g graphic object
	 */
	private void renderTwoPlayerSkills(Graphics g) {
		int shift = 454;
		int spacing = 64;
		int size = 50;
		int x = 200;
		int y = GameSystem.GAME_HEIGHT+25;
		
		g.drawImage(player.skill1, x,y,size,size,null);
		g.drawImage(player.skill2, x+spacing,y,size,size,null);
		g.drawImage(player.skill3, x+2*spacing,y,size,size,null);
		g.drawImage(player.skillUlt, x+3*spacing,y,size,size,null);
		
		g.setColor(new Color(0f,0f,0f,.8f));
		
		if(player.ultyCd-player.ultyTimer>0){
			int time = (player.ultyCd-player.ultyTimer)/30;
			BufferedImage[] cd = IntToImage.toImageSmall(time);						
			g.fillRect(x+3*spacing, y, size, (player.ultyCd-player.ultyTimer)*size/player.ultyCd);
			
			
			for(int i=0;i<cd.length;i++){
				g.drawImage(cd[i], x+3*spacing+spacing/2+i*12, y+spacing/2, null);
			}
		}
		if(player.skill1Cd-player.skill1Timer>0){
			int time = (player.skill1Cd-player.skill1Timer)/30;
			BufferedImage[] cd = IntToImage.toImageSmall(time);						
			g.fillRect(x, y, size, (player.skill1Cd-player.skill1Timer)*size/player.skill1Cd);
			
			
			for(int i=0;i<cd.length;i++){
				g.drawImage(cd[i], x+spacing/2+i*12, y+spacing/2, null);
			}
		}
		if(player.skill2Cd-player.skill2Timer>0){
			int time = (player.skill2Cd-player.skill2Timer)/30;
			BufferedImage[] cd = IntToImage.toImageSmall(time);						
			g.fillRect(x+spacing, y, size, (player.skill2Cd-player.skill2Timer)*size/player.skill2Cd);
			
			
			for(int i=0;i<cd.length;i++){
				g.drawImage(cd[i], x+spacing+spacing/2+i*12, y+spacing/2, null);
			}
		}
		if(player.skill3Cd-player.skill3Timer>0){
			int time = (player.skill3Cd-player.skill3Timer)/30;
			BufferedImage[] cd = IntToImage.toImageSmall(time);						
			g.fillRect(x+2*spacing, y, size, (player.skill3Cd-player.skill3Timer)*size/player.skill3Cd);
			
			
			for(int i=0;i<cd.length;i++){
				g.drawImage(cd[i], x+2*spacing+spacing/2+i*12, y+spacing/2, null);
			}
		}
		
		g.setFont(new Font("serif",Font.BOLD,25));
		g.setColor(Color.BLUE);
		g.drawString(Integer.toString(player.skill1Cost), x, y+70);
		g.drawString(Integer.toString(player.skill2Cost), x+spacing, y+70);
		g.setColor(Color.MAGENTA);
		g.drawString(Integer.toString(player.skill3Cost), x+2*spacing, y+70);
		g.setColor(Color.BLUE);
		g.drawString(Integer.toString(player.skillUltCost), x+3*spacing, y+70);
		
		
		
		g.drawImage(player2.skill1, x+shift,y,size,size,null);
		g.drawImage(player2.skill2, x+spacing+shift,y,size,size,null);
		g.drawImage(player2.skill3, x+2*spacing+shift,y,size,size,null);
		g.drawImage(player2.skillUlt, x+3*spacing+shift,y,size,size,null);
		
		
		g.setColor(new Color(0f,0f,0f,.8f));
		
		if(player2.ultyCd-player2.ultyTimer>0){
			int time = (player2.ultyCd-player2.ultyTimer)/30;
			BufferedImage[] cd = IntToImage.toImageSmall(time);						
			g.fillRect(x+3*spacing+shift, y, size, (player2.ultyCd-player2.ultyTimer)*size/player2.ultyCd);
			
			
			for(int i=0;i<cd.length;i++){
				g.drawImage(cd[i], x+3*spacing+spacing/2+i*12+shift, y+spacing/2, null);
			}
		}
		if(player2.skill1Cd-player2.skill1Timer>0){
			int time = (player2.skill1Cd-player2.skill1Timer)/30;
			BufferedImage[] cd = IntToImage.toImageSmall(time);						
			g.fillRect(x+shift, y, size, (player2.skill1Cd-player2.skill1Timer)*size/player2.skill1Cd);
			
			
			for(int i=0;i<cd.length;i++){
				g.drawImage(cd[i], x+spacing/2+i*12+shift, y+spacing/2, null);
			}
		}
		if(player2.skill2Cd-player2.skill2Timer>0){
			int time = (player2.skill2Cd-player2.skill2Timer)/30;
			BufferedImage[] cd = IntToImage.toImageSmall(time);						
			g.fillRect(x+spacing+shift, y, size, (player2.skill2Cd-player2.skill2Timer)*size/player2.skill2Cd);
			
			
			for(int i=0;i<cd.length;i++){
				g.drawImage(cd[i], x+spacing+spacing/2+i*12+shift, y+spacing/2, null);
			}
		}
		if(player2.skill3Cd-player2.skill3Timer>0){
			int time = (player2.skill3Cd-player2.skill3Timer)/30;
			BufferedImage[] cd = IntToImage.toImageSmall(time);						
			g.fillRect(x+spacing+shift, y, size, (player2.skill3Cd-player2.skill3Timer)*size/player2.skill3Cd);
			
			
			for(int i=0;i<cd.length;i++){
				g.drawImage(cd[i], x+2*spacing+spacing/2+i*12+shift, y+spacing/2, null);
			}
		}
		
		g.setFont(new Font("serif",Font.BOLD,25));
		g.setColor(Color.BLUE);
		g.drawString(Integer.toString(player2.skill1Cost), x+shift, y+70);
		g.drawString(Integer.toString(player2.skill2Cost), x+spacing+shift, y+70);
		g.setColor(Color.MAGENTA);
		g.drawString(Integer.toString(player2.skill3Cost), x+2*spacing+shift, y+70);
		g.setColor(Color.BLUE);
		g.drawString(Integer.toString(player2.skillUltCost), x+3*spacing+shift, y+70);
		
	}
	/**
	 * renders two player experience
	 * @param g graphic object
	 */
	private void renderTwoPlayerExp(Graphics g) {
		int shift = 454;
		int x = 96;
		
		g.drawImage(player.expBar, x, GameSystem.GAME_HEIGHT+93, null);
		double ratio = player.expCurrent/player.levelUpdater.expRequired;
		g.setColor(Color.YELLOW);
		g.fillRect(x, GameSystem.GAME_HEIGHT+96, (int)(ratio*67), 4);
		
		g.drawImage(player2.expBar, x+shift, GameSystem.GAME_HEIGHT+93, null);
		double ratio2 = player2.expCurrent/player2.levelUpdater.expRequired;
		g.setColor(Color.YELLOW);
		g.fillRect(x+shift, GameSystem.GAME_HEIGHT+96, (int)(ratio2*67), 4);
		
	}
	/**
	 * renders two player status
	 * @param g graphics object
	 */
	private void renderTwoPlayerStatus(Graphics g) {
		int shift = 454;
		int x = 82;
		int y = GameSystem.ABSHEIGHT-Player.H_STATUS-6;
		
		
		if(player.soul==0){
			g.drawImage(player.despairStatus,x, y,null);
		}
		else if(player.hp/player.maxHp>0.6){
			g.drawImage(player.okStatus,x, y,null);
		}
		else if(player.hp/player.maxHp<=0.6&&player.hp/player.maxHp>0.3){
			g.drawImage(player.midDamageStatus,x, y,null);
		}
		else if(player.hp/player.maxHp<=0.3){
			g.drawImage(player.highDamageStatus,x, y,null);
		}
		
		if(player2.soul==0){
			g.drawImage(player2.despairStatus,x+shift, y,null);
		}
		else if(player2.hp/player2.maxHp>0.6){
			g.drawImage(player2.okStatus,x+shift, y,null);
		}
		else if(player2.hp/player2.maxHp<=0.6&&player2.hp/player2.maxHp>0.3){
			g.drawImage(player2.midDamageStatus,x+shift, y,null);
		}
		else if(player2.hp/player2.maxHp<=0.3){
			g.drawImage(player2.highDamageStatus,x+shift, y,null);
		}
			
	}
	public void timeStop(){
		timeStop = true;
	}
	public void stopTick(){
		stopTick = true;
	}
	public void removeTimedEvents(){
		stopTick = false;
		timeStop = false;
	}

	/**
	 * Interface between key that is pressedby user and the function that it corresponds to
	 * @param key integer
	 */
	public void keyPressed(int key) {
		if(key==KeyEvent.VK_P){
			GameSystem.state=STATE.PAUSE;
		}
		if(gState==GameState.PLAY){
			
			if(key==GameSystem.ULT){
				if(player.damaged){
					return;
				}
				if(cChosen==CHARACTER.MAMI){
					if(player.isChannelling()){
						return;
					}
				}
				player.sendCommand("ult");
				player.useUltimate();
				
			}
			else if(key==GameSystem.UTILITY){
				player.useAbility3();
			}
			else if(key==GameSystem.CONFIRM){
				player.sendCommand("placeBomb");
				player.useAbility1();
				
				
			}
			else if(key==GameSystem.CANCEL){
				player.useAbility2();
			}
			else if(key==GameSystem.RIGHT){
				player.sendCommand("moveRight");
				player.moveRight();
			}
			else if(key==GameSystem.LEFT){
				player.sendCommand("moveLeft");
				player.moveLeft();
			}
			else if(key==GameSystem.UP){
				player.sendCommand("moveUp");
				player.moveUp();
			}
			else if(key==GameSystem.DOWN){
				player.sendCommand("moveDown");
				player.moveDown();
			}
			else if(GameSystem.TWO_PLAYER_MODE){
				if(key==GameSystem.ULT2){
					if(player2.damaged){
						return;
					}
					if(cChosenP2==CHARACTER.MAMI){
						if(player2.isChannelling()){
							return;
						}
					}
					player2.useUltimate();
					
				}
				else if(key==GameSystem.UTILITY2){
					player2.useAbility3();
				}
				else if(key==GameSystem.CONFIRM2){
					player2.useAbility1();										
				}
				else if(key==GameSystem.CANCEL2){
					player2.useAbility2();
				}
				else if(key==GameSystem.RIGHT2){
					player2.moveRight();
				}
				else if(key==GameSystem.LEFT2){
					player2.moveLeft();
				}
				else if(key==GameSystem.UP2){
					player2.moveUp();
				}
				else if(key==GameSystem.DOWN2){
					player2.moveDown();
				}
			}
			
		}
		
	}
	/**
	 * defines key that is released and its effects to players movement
	 * @param key integer
	 */
	public void keyReleased(int key) {
		if(key==GameSystem.RIGHT&&player.orientation==ORIENTATION.RIGHT){			
			player.sendCommand("moveStop");
			player.moveStop();
		}
		else if(key==GameSystem.LEFT&&player.orientation==ORIENTATION.LEFT){		
			player.sendCommand("moveStop");			
			player.moveStop();
		}
		else if(key==GameSystem.UP&&player.orientation==ORIENTATION.UP){
			player.sendCommand("moveStop");
			player.moveStop();
		}
		else if(key==GameSystem.DOWN&&player.orientation==ORIENTATION.DOWN){
			player.sendCommand("moveStop");			
			player.moveStop();
		}
		else if(key==GameSystem.ULT){
			if(cChosen==CHARACTER.MAMI){
				player.stopChannelling();
				GameSystem.stopSound();
			}
		}
		else if(GameSystem.TWO_PLAYER_MODE){
			if(key==GameSystem.RIGHT2&&player2.orientation==ORIENTATION.RIGHT){			
				player2.moveStop();
			}
			else if(key==GameSystem.LEFT2&&player2.orientation==ORIENTATION.LEFT){				
				player2.moveStop();
			}
			else if(key==GameSystem.UP2&&player2.orientation==ORIENTATION.UP){
				player2.moveStop();
			}
			else if(key==GameSystem.DOWN2&&player2.orientation==ORIENTATION.DOWN){		
				player2.moveStop();
			}
			else if(key==GameSystem.ULT2){
				if(cChosenP2==CHARACTER.MAMI){
					player2.stopChannelling();
					GameSystem.stopSound();
				}
			}
		}
	
	}
	/**
	 * decreases number of enemies
	 */
	public void decreaseEnemyCount() {
		enemyCount--;
		
	}
	/**
	 * redirects to menu while changing music
	 */
	public void goToMenu(){
		GameSystem.turnOffBgm();
		GameSystem.turnOnBgm("/sound/music/title.wav");	
		Menu.mState=Menu.MENUSTATE.MAIN;
		GameSystem.state=GameSystem.STATE.MENU;
	}
	/**
	 * redirects to menu after death while changing music
	 */
	public void goToDeath(){
		GameSystem.turnOffBgm();
		GameSystem.turnOnBgm("/sound/music/death.wav");
		Menu.mState=Menu.MENUSTATE.DEATH;
		GameSystem.state=GameSystem.STATE.MENU;
	}
	/**
	 * redirects to score menu while changing music
	 */
	public void goToScore(){
		GameSystem.turnOffBgm();
		GameSystem.turnOnBgm("/sound/music/theme1.wav");	
		Menu.mState=Menu.MENUSTATE.SCORE;
		GameSystem.state=GameSystem.STATE.MENU;
	}
	/**
	 * renders the health of the player
	 * @param g graphic object
	 */
	public void renderPlayerHealth(Graphics g){
		int x = 241;
		int y = 565;
		int multX = 187;
		int multY = 567;
		int shift=454;
		int width = 645;
		int height = 15;
		//g.drawImage(hpGauge, 250, GameSystem.ABSHEIGHT-75, null);
		g.setColor(Color.GREEN);
		
		if(GameSystem.TWO_PLAYER_MODE){
			if(player.hp>=0){
				g.fillRect(multX, multY, (int) (player.hp/player.maxHp*width*.4), height);
			}
			if(player2.hp>=0){
				g.fillRect(multX+shift, multY, (int) (player2.hp/player2.maxHp*width*.4), height);
			}
			g.setColor(Color.WHITE);
			g.drawRect(multX, multY, (int) (width*.4),height);
			g.drawRect(multX+shift, multY, (int) (width*.4),height);
		}
		else{
			if(player.hp>=0){
				g.fillRect(x, y, (int) (player.hp/player.maxHp*width), height);
				g.setFont(new Font("serif",Font.BOLD,18));
				g.setColor(Color.RED);
				g.drawString(Integer.toString((int)player.hp), x+width/2, y+height);
			}
			g.setColor(Color.WHITE);
			g.drawRect(x, y, width,height);
		}
	}
	/**
	 * renders player mana
	 * @param g graphic object
	 */
	public void renderPlayerMana(Graphics g){
		int x = 241;
		int y = 581;
		int multX = 187;
		int multY = 583;
		int shift=454;
		int width = 645;
		int height = 10;
		g.setColor(Color.BLUE);
		if(GameSystem.TWO_PLAYER_MODE){
			if(player.mp>=0){
				g.fillRect(multX, multY, (int) (player.mp/player.maxMp*width*.4), height);
			}
			if(player2.mp>=0){
				g.fillRect(multX+shift, multY, (int) (player2.mp/player2.maxMp*width*.4), height);
			}
			g.setColor(Color.WHITE);
			g.drawRect(multX, multY, (int) (width*.4),height);
			g.drawRect(multX+shift, multY, (int) (width*.4),height);
		}
		else{
			g.fillRect(x, y, (int) (player.mp/player.maxMp*width), height);
			g.setFont(new Font("serif",Font.BOLD,12));
			g.setColor(Color.RED);
			g.drawString(Integer.toString((int)player.mp), x+width/2, y+height);
			
			g.setColor(Color.WHITE);
			g.drawRect(x, y,width,height);
		}
	}
	/**
	 * checks if player is victorious
	 */
	public void checkVictoryCondition(){
		if(enemyCount<=0){
			victory=true;
		}
	}
	/**
	 * saves game
	 */
	public void saveGame(){
		try
	      {
			//String path = getClass().getResource("bin/save/game.ser").toString();
			//path = URLDecoder.decode(path);
			//File newFile = new File(path);"C:/Users/Attack on Majou/workspace/Java2DGame/res/save/game.ser"
			 GameData saveData = GameSystem.GAME_DATA;
			 saveData.updateGameData(this);
			//game.p.pData.upDatePlayerData(game.p);
	         FileOutputStream fileOut = new FileOutputStream("system/save/game.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         //game.pData.upDatePlayerData(game.p);
	         out.writeObject(saveData);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in /save/game.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	/**
	 * renders stage objects
	 * @param g graphic object
	 */
	public void renderStageObjects(Graphics g){
		if(curLevel==1){
			stageRenderer.render1(g);
		}
		else if(curLevel==2){
			stageRenderer.render2(g);
		}
		else if(curLevel==3){
			stageRenderer.render3(g);
		}
		else if(curLevel==4){
			stageRenderer.render4(g);
		}
		else if(curLevel==5){
			stageRenderer.render5(g);
		}
		else if(curLevel==6){
			stageRenderer.render6(g);
		}
		else if(curLevel==7){
			stageRenderer.render7(g);
		}
		else if(curLevel==8){
			stageRenderer.render8(g);
		}
		else if(curLevel==9){
			stageRenderer.render9(g);
		}
		else if(curLevel==10){
			stageRenderer.render10(g);
		}
		else if(curLevel==11){
			stageRenderer.render11(g);
		}
		else if(curLevel==12){
			stageRenderer.render12(g);
		}
		
	}
	/**
	 * calculates the score of the player for the current stage
	 */
	public void calculateTotalScore(){
		int timeSpent = GameTimer.mGameTime*60 + GameTimer.sGameTime;
		TOTAL_SCORE = Player.SCORE*100/timeSpent;
		if(GameSystem.GAME_DATA.HIGH_SCORE[curLevel-1]<TOTAL_SCORE){
			GameSystem.GAME_DATA.HIGH_SCORE[curLevel-1]=TOTAL_SCORE;
		}
		HIGH_SCORE=GameSystem.GAME_DATA.HIGH_SCORE[curLevel-1];
	}
	//getters and setters


	public static Player getPlayer() {
		return player;
	}
	public static Player getPlayer2(){
		return player2;
	}
	public void setPlayer(Player p) {
		Game.player = p;
	}
	public void setPlayer2(Player p){
		Game.player2=p;
	}

	public BufferedImage getBackground() {
		return background;
	}

	public void setBackground(BufferedImage background) {
		this.background = background;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public boolean isMusicOn() {
		return musicOn;
	}

	public void setMusicOn(boolean musicOn) {
		this.musicOn = musicOn;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public BufferedImage getHpGauge() {
		return hpGauge;
	}

	public void setHpGauge(BufferedImage hpGauge) {
		this.hpGauge = hpGauge;
	}

	public boolean isPlayerIsAlive() {
		return playerIsAlive;
	}

	public void setPlayerIsAlive(boolean playerIsAlive) {
		this.playerIsAlive = playerIsAlive;
	}

	public LinkedList<Bomb> getBombList() {
		return bombList;
	}

	public void setBombList(LinkedList<Bomb> bombList) {
		this.bombList = bombList;
	}

	public LinkedList<Enemy> getEnemyList() {
		return enemyList;
	}

	public void setEnemyList(LinkedList<Enemy> eList) {
		this.enemyList = eList;
	}

	public LinkedList<HitableBrick> getBrickList() {
		return brickList;
	}

	public void setBrickList(LinkedList<HitableBrick> brickList) {
		this.brickList = brickList;
	}
	
	public LinkedList<PlaceHolder> getPlaceHolderList() {
		return placeHolderList;
	}

	public void setPlaceHolderList(LinkedList<PlaceHolder> list) {
		this.placeHolderList = list;
	}

	public LinkedList<Fire> getFireList() {
		return fireList;
	}

	public void setFireList(LinkedList<Fire> fireList) {
		this.fireList = fireList;
	}

	public LinkedList<PowerUps> getPowerUpList() {
		return powerUpList;
	}

	public void setPowerUpList(LinkedList<PowerUps> powerUpList) {
		this.powerUpList = powerUpList;
	}

	public LinkedList<Projectile> getProjectileList() {
		return projectileList;
	}

	public void setProjectileList(LinkedList<Projectile> projectileList) {
		this.projectileList = projectileList;
	}

	public boolean[][] getWallArray() {
		return wallArray;
	}

	public void setWallArray(boolean[][] wallArray) {
		this.wallArray = wallArray;
	}

	public boolean[][] getBombArray() {
		return bombArray;
	}

	public void setBombArray(boolean[][] bombArray) {
		this.bombArray = bombArray;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller c) {
		this.controller = c;
	}

	public int getCurLevel() {
		return curLevel;
	}

	public void setCurLevel(int curLevel) {
		Game.curLevel = curLevel;
	}

	

	public int getLastStage() {
		return lastStage;
	}

	public int getEnemyCount() {
		return enemyCount;
	}

	public void setEnemyCount(int enemyCount) {
		this.enemyCount = enemyCount;
	}
	public void increaseEnemyCount(){
		this.enemyCount++;
	}

	public boolean isVictory() {
		return victory;
	}

	public void setVictory(boolean victory) {
		this.victory = victory;
	}

	public int getShift() {
		return shift;
	}

	public void setShift(int shift) {
		this.shift = shift;
	}

	public PlayerData getPlayerData() {
		return playerData;
	}

	public void setPlayerData(PlayerData pData) {
		this.playerData = pData;
	}

	public int getTimePastSinceLastExplode() {
		return timePastSinceLastExplode;
	}

	public void setTimePastSinceLastExplode(int timePastSinceLastExplode) {
		this.timePastSinceLastExplode = timePastSinceLastExplode;
	}

	public static boolean isExplosionPlayed() {
		return explosionPlayed;
	}

	public static void setExplosionPlayed(boolean explosionPlayed) {
		Game.explosionPlayed = explosionPlayed;
	}

	public long getRenderStageStart() {
		return renderStageStart;
	}

	public void setRenderStageStart(long renderStageStart) {
		this.renderStageStart = renderStageStart;
	}

	public boolean isTimeStop() {
		return timeStop;
	}

	public void setTimeStop(boolean timeStop) {
		this.timeStop = timeStop;
	}

	public boolean isStopTick() {
		return stopTick;
	}

	public void setStopTick(boolean stopTick) {
		this.stopTick = stopTick;
	}

	public TimedEvent getEvent1() {
		return event1;
	}

	public void setEvent1(TimedEvent event1) {
		this.event1 = event1;
	}

	public TimedEvent getEvent2() {
		return event2;
	}

	public void setEvent2(TimedEvent event2) {
		this.event2 = event2;
	}

	public GameSystem getSys() {
		return sys;
	}

	public void setSys(GameSystem sys) {
		this.sys = sys;
	}

	public BufferedImage getCutIn() {
		return cutIn;
	}

	public void setCutIn(BufferedImage cutIn) {
		this.cutIn = cutIn;
	}

	public static GameState getgState() {
		return gState;
	}

	public static void setgState(GameState gState) {
		Game.gState = gState;
	}

	public static CHARACTER getcChosen() {
		return cChosen;
	}

	public static void setcChosen(CHARACTER cChosen) {
		Game.cChosen = cChosen;
	}

	
	

	
}
