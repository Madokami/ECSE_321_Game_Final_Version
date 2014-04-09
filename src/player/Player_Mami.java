package player;

import data.SpriteData;
import playerVoice.MaVoice;
import projectile.Projectile_TiroFinale;
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
* <br>Contains features such as sound and abilites
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class Player_Mami extends Player{
	/**
	* defines new character
	* @param coordinates, game object
	*/
	public Player_Mami(int x, int y, Game game) {
		super(x, y, game);
		playerBackground = MenuChar.maBg;
		
		setpVoice(new MaVoice());
		
		name="  Mami";
		skillUlt = loader.loadImage("/image/skills/maSkillUlt.png");
		skillUltName="Tiro Finale";
		
		
		run=new ImageSequence("/image/spriteSheet/actors/player/mami/run",8);
		stand=new ImageSequence("/image/spriteSheet/actors/player/mami/stand",7);
		damage=new ImageSequence("/image/spriteSheet/actors/player/mami/damage",5);		
		dead=new ImageSequence("/image/spriteSheet/actors/player/mami/dead",13);
		sequence.startSequence(stand);
		
		soulGemSprite=SpriteData.gem_mami;
		soulGemImage=soulGemSprite.grabImage(1, 1, soulGemWidth, soulGemHeight);
		
		status = SpriteData.maStatus;
		setStatusImages();
		
		
		pData.loadPlayerStatus(this);
		
		
		levelImage=IntToImage.toImageSmall(level);
		soulGemValueImage=IntToImage.toImageGriefSyndrome((int)soul);
		maxHp=hp;
		maxMp=mp;
		maxSoul=soul;
	}
	/**
	 * checks current status to determine next iteration
	 */
	public void tick(){
		super.tick();
		if(isChannelling()){
			if(mp<maxMp*0.03/30+50/30.0){
				stopChannelling();
				GameSystem.stopSound();
			}
			mp-=maxMp*0.03/30+50/30.0;
		}
	}
	/**
	* applies unique character attributes
	*/	
	public void useUltimate(){
		if(isChannelling()==true) {
			return;
		}
		
		if(ultyTimer<ultyCd){
			GameSystem.playError();
			this.getpVoice().playCdSound();
			return;
		}
		
		if(mp>skillUltCost){
			mp-=skillUltCost;
			setChannelling(true);
			game.getController().addEntity(new Projectile_TiroFinale(this.getxGridNearest(),getyGridNearest(),game,this));
			setVelX(0);
			setVelY(0);
			GameSystem.playSoundLoop("/sound/effects/sunray_beam_loop.wav");
		}
	}
	
	public void updatePlayerData(){
		pData.upDatePlayerData(this);
	}
}
