package player;


import game.Game;
import gameObject.MovableObject;
import gameObject.SpriteSheet;
import gameObject.MovableObject.ANIMATION;
import graphicEffects.IntToImage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import data.PlayerData;
import network.MultiplayerStats;
import playerVoice.PlayerVoice;
import powerUps.PowerUps;
import animation.Animate;
import system.GameSystem;
import system.LevelUp;
import system.Physics;
import utility.BufferedImageLoader;

/**
* <b>Description:</b>
* <br>
* Overall Player class that extends MovableObject
* <br><br>
* Incorporates attributes such as player image, health and mp, voice, etc.
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public abstract class Player extends MovableObject{
	public BufferedImage soulGemImage;
	public SpriteSheet soulGemSprite;
	public static final int soulGemWidth=64;
	public static final int soulGemHeight=96;
	public double soulGemCounter=0;
	public final double soulGemAnimationSpeed = 0.3;
	public String name;
	
	private int bombCount = 2;
	
	public BufferedImage expBar;
	public BufferedImage skillPlaceholder;
	public BufferedImage skill1;
	public BufferedImage skill2;
	public BufferedImage skill3;
	public BufferedImage skillUlt;
	
	public int skill1Cost,skill2Cost,skill3Cost,skillUltCost;
	public int skill1Cd,skill1Timer,skill2Cd,skill2Timer,skill3Cd,skill3Timer;
	
	public String tempName = "comming soon";
	public String skill1Name,skill2Name,skill3Name,skillUltName;
	
	public BufferedImageLoader loader;
	public double maxSoul;
	public double soul;
	public double mp,maxMp,maxHp;
	
	public SpriteSheet status;
	public BufferedImage okStatus,midDamageStatus,highDamageStatus,despairStatus,statusBg;
	public static final int W_STATUS = 128;
	public static final int H_STATUS = 128;
	
	
	public PlayerData pData;
	
	public int level;
	public BufferedImage[] levelImage;
	public BufferedImage[] soulGemValueImage;
	protected BufferedImage playerBackground;
	
	public double expCurrent;
	public int BP;
	public static int SCORE;
	
	public LevelUp levelUpdater;
	
	public int speedChangeDuration;
	public int speedChangeTimer;
	public int spdOriginal;
	
	public boolean damageMediumPlayed,damageHeavyPlayed,soulGemDarkSoundPlayed;
	
	private PlayerVoice pVoice;
	
	public boolean dying;
	public int dyingDuration;
	public int dyingDurationTimer;

	/**
	 * Defines player
	 * <br><br>
	 * <b>Inputs:</b>
	 * <br><b>x</b>,<b>y</b> - coordinates of the player
	 * <br><b>game</b> - Game object
	 * @param coordinates, game object
	 */
	public Player(int x, int y, Game game) {
		super(x, y, game);
		
		skill1Name=skill2Name=skill3Name=skillUltName=tempName;
		skill1Name = "place bomb";
		skill2Name = "kick bomb";
		skill3Name = "restore mp";
		skillUltCost = 50;
		skill1Cost = 20;
		skill2Cost = 30;
		skill1Cd=15;
		skill2Cd=90;
		skill1Timer=30;
		skill2Timer=90;
		skill3Cd=150;
		skill3Timer=150;
		
		loader = new BufferedImageLoader();
		bombStrength = 50;
		bombLength=3;
		hp=100;
		mp=100;
		maxHp=hp;
		maxMp=mp;
		maxSoul=500;
		soul=maxSoul;
		spd=6;
		spdOriginal=spd;
		soulGemDarkSoundPlayed=false;
		skillPlaceholder=loader.loadImage("/image/skills/skillPlaceholder.png");
		skill1=skill2=skill3=skillUlt=skillPlaceholder;
		skill1=loader.loadImage("/image/skills/placeBomb.png");
		skill2=loader.loadImage("/image/skills/kickBomb.png");
		skill3=loader.loadImage("/image/skills/restoreMana.png");
		
		
		expBar=loader.loadImage("/image/expBar.png");
		
		statusBg=loader.loadImage("/image/statusBg.png");
		
		pData = game.getPlayerData();
		levelUpdater = new LevelUp();

		collisionWidth=56;
		collisionHeight=56;
		
		soulGemValueImage=IntToImage.toImageGriefSyndrome((int)soul);
	}

	/**
	 * Checks present conditions in order to determine outcome of player object
	 */
	public void tick(){
		super.tick();
		skill3Cost = (int) (maxMp/2);
		skill1Timer++;
		skill2Timer++;
		skill3Timer++;
		if(GameSystem.LAN_TWO_PLAYER_MODE){
			if(this==Game.getPlayer()){
				if(positionUpdateTimer>15){
					positionUpdateTimer=0;
					sendCommand("updateX"+Double.toString(x));
					sendCommand("updateY"+Double.toString(y));
				}
			}
		}
	
		playPlayerHpMissingVoices();
		searchForPowerups();
		dyingDurationTimer++;
		if(dyingDurationTimer>dyingDuration){
			stopDying();
		}
		if(dying){
			//animation=ANIMATION.DYING;
			setVelX(0);
			setVelY(0);
		}
		
		if(hp<=0){
			hp=0;
			startDying(90);
		}
		else if(hp>this.maxHp){
			hp=maxHp;
		}
		
		if(!dying){
			if(soul>0){
				if(hp<maxHp){
					soul--;
					hp=hp+maxHp*0.0015;
					soulGemValueImage=IntToImage.toImageGriefSyndrome((int)soul);
				}
			}
			if(mp<maxMp){
				mp=mp+maxMp*0.001;
			}
			
		}
		
		levelUpdater.checkIfLevelUp(this);
		Animate.animateGem(this);
	}
	
	public void render(Graphics g){
		super.render(g);
	}

	public abstract void useUltimate();
		
	
	public void setStatusImages() {
		okStatus = status.grabImage(1, 1, W_STATUS, H_STATUS);
		midDamageStatus = status.grabImage(2, 1, W_STATUS, H_STATUS);
		highDamageStatus = status.grabImage(3, 1, W_STATUS, H_STATUS);
		despairStatus = status.grabImage(4, 1, W_STATUS, H_STATUS);
		
	}
	
	/**
	 * Renders graphics based on dimensions and position as well as colour
	 * @param graphic object
	 */
	public void renderSkills(Graphics g){
		int spacing = 100;
		int size = 70;
		int x = GameSystem.ABSWIDTH/2+10;
		int y = GameSystem.GAME_HEIGHT+25;
		
		g.drawImage(skill1, x,y,size,size,null);
		g.drawImage(skill2, x+spacing,y,size,size,null);
		g.drawImage(skill3, x+2*spacing,y,size,size,null);
		g.drawImage(skillUlt, x+3*spacing,y,size,size,null);
		
		g.setColor(Color.WHITE);
		g.drawString(skill1Name, x, y);
		g.drawString(skill2Name, x+spacing, y);
		g.drawString(skill3Name, x+2*spacing, y);
		g.drawString(skillUltName, x+3*spacing, y);
		
		g.setColor(new Color(0f,0f,0f,.8f));
		
		if(this.ultyCd-this.ultyTimer>0){
			int time = (this.ultyCd-this.ultyTimer)/30;
			BufferedImage[] cd = IntToImage.toImageSmall(time);						
			g.fillRect(x+3*spacing, y, size, (this.ultyCd-this.ultyTimer)*size/this.ultyCd);
			
			
			for(int i=0;i<cd.length;i++){
				g.drawImage(cd[i], x+3*spacing+spacing/2+i*12, y+spacing/2, null);
			}
		}
		if(skill1Cd-skill1Timer>0){
			int time = (skill1Cd-skill1Timer)/30;
			BufferedImage[] cd = IntToImage.toImageSmall(time);						
			g.fillRect(x, y, size, (skill1Cd-skill1Timer)*size/skill1Cd);
			
			
			for(int i=0;i<cd.length;i++){
				g.drawImage(cd[i], x+spacing/2+i*12, y+spacing/2, null);
			}
		}
		if(skill2Cd-skill2Timer>0){
			int time = (skill2Cd-skill2Timer)/30;
			BufferedImage[] cd = IntToImage.toImageSmall(time);						
			g.fillRect(x+spacing, y, size, (skill2Cd-skill2Timer)*size/skill2Cd);
			
			
			for(int i=0;i<cd.length;i++){
				g.drawImage(cd[i], x+spacing+spacing/2+i*12, y+spacing/2, null);
			}
		}
		if(skill3Cd-skill3Timer>0){
			int time = (skill3Cd-skill3Timer)/30;
			BufferedImage[] cd = IntToImage.toImageSmall(time);						
			g.fillRect(x+2*spacing, y, size, (skill3Cd-skill3Timer)*size/skill3Cd);
			
			
			for(int i=0;i<cd.length;i++){
				g.drawImage(cd[i], x+2*spacing+spacing/2+i*12, y+spacing/2, null);
			}
		}
		
		g.setFont(new Font("serif",Font.BOLD,25));
		g.setColor(Color.BLUE);
		g.drawString(Integer.toString(skill1Cost), x, y+70);
		g.drawString(Integer.toString(skill2Cost), x+spacing, y+70);
		g.setColor(Color.MAGENTA);
		g.drawString(Integer.toString(skill3Cost), x+2*spacing, y+70);
		g.setColor(Color.BLUE);
		g.drawString(Integer.toString(skillUltCost), x+3*spacing, y+70);
	}
	
	/**
	 * renders player status
	 * @param graphic object
	 */
	public void renderPlayerStatus(Graphics g){
		int y = GameSystem.ABSHEIGHT-Player.H_STATUS-6;
		g.setFont(new Font("arial",Font.BOLD,11));
		if(soul==0){
				g.drawImage(despairStatus,135, y,null);
				return;
		}
		if(hp/maxHp>0.6){
			g.drawImage(okStatus,135, y,null);
		}
		else if(hp/maxHp<=0.6&&hp/maxHp>0.3){
			g.drawImage(midDamageStatus,135, y,null);
		}
		else if(hp/maxHp<=0.3){
			g.drawImage(highDamageStatus,135, y,null);
		}
	}
	
	/**
	 * renders player soulgem
	 * @param graphic object
	 */
	public void renderSoulGem(Graphics g){
		g.setFont(new Font("serif",Font.BOLD,12));
		g.setColor(Color.LIGHT_GRAY);
		g.drawString(name, 160, GameSystem.ABSHEIGHT-H_STATUS+3);
		g.drawImage(soulGemImage,GameSystem.GAME_WIDTH/2-430,GameSystem.GAME_HEIGHT-10,null);
		for(int i=0;i<soulGemValueImage.length;i++){
			g.drawImage(soulGemValueImage[i],GameSystem.GAME_WIDTH/2-430+i*20,GameSystem.GAME_HEIGHT+60,null);
		}
	}
	/**
	 * renders experience
	 * @param graphic object
	 */
	public void renderExp(Graphics g){
		g.drawImage(expBar, 115+34, GameSystem.GAME_HEIGHT+93, null);
		double ratio = this.expCurrent/this.levelUpdater.expRequired;
		g.setColor(Color.YELLOW);
		g.fillRect(120+34, GameSystem.GAME_HEIGHT+96, (int)(ratio*67), 4);
	}
	/**
	 * renders player level
	 * @param graphic object
	 */
	public void renderPlayerLevel(Graphics g){
		for(int i=0;i<levelImage.length;i++){
			g.drawImage(levelImage[i],131+i*12,GameSystem.GAME_HEIGHT+89,null);
		}
	}
	
	
	public abstract void updatePlayerData();
	/**
	 * determines level of health of player
	 */
	private void playPlayerHpMissingVoices(){
		if(getAnimation()==ANIMATION.DYING){
			return;
		}
		
		String hpRange = checkPlayerHpRange();
		if(hpRange.equals("good")){
			damageMediumPlayed=false;
			damageHeavyPlayed=false;
			return;
		}
		else if(hpRange.equals("damaged")){
			damageHeavyPlayed=false;
			if(!damageMediumPlayed) getpVoice().playDamagedMediumSound();
			damageMediumPlayed=true;
		}
		else if(hpRange.equals("severe")){
			damageMediumPlayed=true;
			if(!damageHeavyPlayed) getpVoice().playDamagedHeavySound();
			damageHeavyPlayed=true;
		}
		if(soul<=0){
			if(!soulGemDarkSoundPlayed) getpVoice().playSoulGemDarkSound();
			soulGemDarkSoundPlayed=true;
		}
	}
	/**
	 * determines player hp range
	 */
	private String checkPlayerHpRange(){
		String ret=null;
		if(hp/maxHp>0.6){
			ret="good";
		}
		else if(hp/maxHp<=0.6&&hp/maxHp>0.3){
			ret="damaged";
		}
		else if(hp/maxHp<=0.3){
			ret="severe";
		}
		return ret;
	}
	/**
	 * accelerates or decelerates player
	 * @param value, duration
	 */
	public void changeSpeed(int value, int duration){
		speedChangeDuration=duration;
		speedChangeTimer=0;
		spdOriginal=spd;
		this.spd=value;
		refreshMovementSpeed();
	}
	public void restoreSpeed(){
		spd=spdOriginal;
	}
	/**
	 * searches for power ups
	 */
	private void searchForPowerups(){
		if(getAnimation()==ANIMATION.DYING){
			return;
		}
		int tempNum = Physics.collision(this, game.getPowerUpList());
		if(tempNum!=-1){
			PowerUps temp=game.getPowerUpList().get(tempNum);
			temp.applyEffect(this);
			temp.remove();
			getpVoice().playItemFoundSound();
		}
	}


	public void increaseSpeed(int i) {
		if(spd<25) spd++;
	}
	public void stopDying(){
		if(!dying) return;
		game.getController().removePlayer(this);
		if(this==Game.getPlayer()||this==Game.getPlayer2()){
			game.setPlayerIsAlive(false);
		}
	}
	/**
	 * plays animation of player dying
	 * @param duration
	 */
	public void startDying(int duration){
		if(!dying){ 
			if(getAnimation()!=ANIMATION.DYING) {
				setAnimation(ANIMATION.DYING);
				if(dead!=null) sequence.startOneTimeSequence(dead);
			}
			getpVoice().playDeathSound();
			GameSystem.turnOffBgm();
			dying=true;
			dyingDuration=duration;
			dyingDurationTimer=0;
		}
	}
	public void remove(){
		this.game.getController().removePlayer(this);
	}
	/**
	 * loads multiplayer data from save slot
	 */
	public void loadFromMultiplayerData(){
		hp=MultiplayerStats.HP;
		mp=MultiplayerStats.MP;
		bombStrength=MultiplayerStats.BOMBS;
		bombLength=MultiplayerStats.BOMBL;
		//this.level=MultiplayerStats.LEVEL;
		this.spd=MultiplayerStats.SPD;
		BP=MultiplayerStats.BP;
		soul=MultiplayerStats.SOUL;
	}
	
	public BufferedImage getPlayerBackground(){
		return this.playerBackground;
	}
	/**
	 * defines ability for player
	 */
	public void useAbility1(){
		if(mp<skill1Cost||dying||Physics.onTopOfBomb(this, controller.getBList())!=-1){
			return;
		}
		if(skill1Timer>skill1Cd){
			placeBomb(bombStrength, bombLength, 60);
			mp-=skill1Cost;
			skill1Timer=0;
		}
		else{
			GameSystem.playError();
			getpVoice().playCdSound();
		}
	}
	/**
	 * defines ability for player
	 */
	public void useAbility2(){
		if(mp<skill2Cost||dying){
			return;
		}
		if(skill2Timer>skill2Cd){
			this.kickBomb();
			mp-=skill2Cost;
			skill2Timer=0;
		}
		else{
			GameSystem.playError();
			getpVoice().playCdSound();
		}
	}
	/**
	 * defines ability for player
	 */
	public void useAbility3(){
		if(skill3Timer<skill3Cd){
			GameSystem.playError();
			getpVoice().playCdSound();
			return;
		}
		if(soul>skill3Cost){
			soul-=skill3Cost;
			mp=maxMp;
			skill3Timer=0;
			soulGemValueImage=IntToImage.toImageGriefSyndrome((int)soul);
		}
		
	}

	public PlayerVoice getpVoice() {
		return pVoice;
	}

	public void setpVoice(PlayerVoice pVoice) {
		this.pVoice = pVoice;
	}

	public int getBombCount() {
		return bombCount;
	}

	public void setBombCount(int bombCount) {
		this.bombCount = bombCount;
	}
}
