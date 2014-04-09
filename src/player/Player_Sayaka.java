package player;

import data.SpriteData;
import playerVoice.SaVoice;
import projectile.Projectile_SaDash;
import animation.ImageSequence;
import menu.MenuChar;
import system.GameSystem;
import game.Game;
import graphicEffects.IntToImage;

/**
* <b>Description:</b>
* <br>
* Defines new individual player
* <br>Utilizes unique image to display player
* <br>Corresponds to input coordinates of grid map
* <br>Contains features such as sound and abilities
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class Player_Sayaka extends Player{
	
	/**
	 * defines new character
	* @param x coordinate X
	 * @param y coordinate Y
	 * @param game instance of Game
	 */
	public Player_Sayaka(int x, int y, Game game) {
		super(x, y, game);
		playerBackground = MenuChar.saBg;
		
		this.skillUlt=loader.loadImage("/image/skills/saSkillUlt.png");
		this.skillUltName="Dash";
		setpVoice(new SaVoice());
		status = SpriteData.saStatus;
		setStatusImages();
		
		dash= new ImageSequence("/image/spriteSheet/actors/player/sayaka/dash",3);
		
		name="Sayaka";
		
		run=new ImageSequence("/image/spriteSheet/actors/player/sayaka/run",8);
		stand=new ImageSequence("/image/spriteSheet/actors/player/sayaka/stand",5);
		damage=new ImageSequence("/image/spriteSheet/actors/player/sayaka/damage",4);		
		dead=new ImageSequence("/image/spriteSheet/actors/player/sayaka/dead",9);
		sequence.startSequence(stand);
		
		soulGemSprite=SpriteData.gem_sayaka;
		soulGemImage=soulGemSprite.grabImage(1, 1, soulGemWidth, soulGemHeight);
		
		
		pData.loadPlayerStatus(this);
		
		
		levelImage=IntToImage.toImageSmall(level);
		soulGemValueImage=IntToImage.toImageGriefSyndrome((int)soul);
		maxHp=hp;
		maxMp=mp;
		maxSoul=soul;
		
		ultyCd=60;
		ultyTimer=60;
	}
	/**
	 * applies unique character attributes
	 */
	public void useUltimate(){
		/*
		game.event1.startEvent(1000, "sayakaCutIn");
		//game.event2.startEvent(5000, "timeStop");
		playUltimateSound();
		GameSystem.musicPlayer.playSwoosh();
		*/
		if(ultyTimer<ultyCd){
			GameSystem.playError();
			this.getpVoice().playCdSound();
			return;
		}
		if(mp>skillUltCost){
			mp-=skillUltCost;
			int time = 30;
			int chargeSpd = 40;
			GameSystem.musicPlayer.playSwoosh();
			this.setInvincible(time);
			controller.addEntity(new Projectile_SaDash(getxGridNearest(),getyGridNearest(),game,this,time));
			
			startCharge(chargeSpd,time);
			ultyTimer=0;
		}
	}
	/**
	 * updates player data
	 */
	public void updatePlayerData(){
		pData.upDatePlayerData(this);
	}
}
