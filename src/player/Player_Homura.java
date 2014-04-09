package player;

import game.Game;
import graphicEffects.IntToImage;

import java.awt.Graphics;
import java.awt.Image;

import data.SpriteData;
import playerVoice.HoVoice;
import animation.ImageSequence;
import menu.MenuChar;
import system.GameSystem;

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
public class Player_Homura extends Player{
	Image damaged;

	/**
	* defines new character
	* @param x coordinate X
	 * @param y coordinate Y
	 * @param game instance of Game
	*/
	public Player_Homura(int x, int y, Game game) {
		
		super(x, y, game);
		playerBackground = MenuChar.hoBg;
		
		name="Homura";
		skillUltName="Time Stop";
		this.skillUlt=loader.loadImage("/image/skills/hoSkillUlt.png");
		
		//this.moveRightGif=loader.loadGif("/image/spriteSheet/actors/player/homura/run.gif");
		run=new ImageSequence("/image/spriteSheet/actors/player/homura/run",8);
		stand=new ImageSequence("/image/spriteSheet/actors/player/homura/stand",8);
		damage=new ImageSequence("/image/spriteSheet/actors/player/homura/damage",4);		
		dead=new ImageSequence("/image/spriteSheet/actors/player/homura/dead",14);
		ulty=new ImageSequence("/image/spriteSheet/actors/player/homura/ulty",7);
		ulty.setAnimationSpeed(0.3);
		sequence.startSequence(stand);
		
		setpVoice(new HoVoice());
		
		soulGemSprite=SpriteData.gem_homura;
		soulGemImage=soulGemSprite.grabImage(1, 1, soulGemWidth, soulGemHeight);
		
		status = SpriteData.hoStatus;
		setStatusImages();
		
	
		pData.loadPlayerStatus(this);
		
		levelImage=IntToImage.toImageSmall(level);
		soulGemValueImage=IntToImage.toImageGriefSyndrome((int)soul);
		maxHp=hp;
		maxMp=mp;
		maxSoul=soul;

		ultyCd = 450;
		ultyTimer=ultyCd;
		skillUltCost=100;
	}
	/**
	 * renders graphics
	 * @param g graphics object
	 */
	public void render(Graphics g){
		super.render(g);
	}
	/**
	* applies unique character attributes
	*/
	public void useUltimate(){
		if(ultyTimer<ultyCd){
			GameSystem.playError();
			this.getpVoice().playCdSound();
			return;
		}
		if(mp-skillUltCost<0){
			return;
		}
		else{
			mp=mp-skillUltCost;
			getpVoice().playUltimateSound();
			sequence.startSequence(ulty, stand);
			setVelX(0);
			setVelY(0);
			//game.event1.startEvent(2000, "timeStopCutIn");
			game.event2.startEvent(6000, "timeStop");
			ultyTimer=0;
			GameSystem.playSoundLoop("/sound/effects/chronosphere_tick.wav");
		}
	}
	
	public void updatePlayerData(){
		pData.upDatePlayerData(this);
	}
}
