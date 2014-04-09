package menu;
/**
* Description:
* Game difficulty menu
* @author Team 6
* @version 1.45
* @since 2014-04-06
*/
import game.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import menu.Menu.MENUSTATE;
import system.GameSystem;

public class MenuDifficulty implements GeneralMenu{
	private double easy = 0.5;
	private double medium = 1;
	private double hard = 2;
	private BufferedImage normalImage,normalImageOn,hardImage,hardImageOn,brutalImage,brutalImageOn;
	private enum SELECTED{
		EASY,
		MEDIUM,
		HARD
	}
	
	private SELECTED selected = SELECTED.MEDIUM;
	
	public MenuDifficulty(){
		normalImage = GameSystem.loader.loadImage("/image/menu/normal2.png");
		normalImageOn = GameSystem.loader.loadImage("/image/menu/normal.png");
		hardImage = GameSystem.loader.loadImage("/image/menu/hard2.png");
		brutalImage = GameSystem.loader.loadImage("/image/menu/brutal2.png");
		hardImageOn = GameSystem.loader.loadImage("/image/menu/hard.png");
		brutalImageOn = GameSystem.loader.loadImage("/image/menu/brutal.png");
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Draw menu bottom onto screen 
	 */
	public void render(Graphics g) {
		g.drawImage(normalImage,Menu.X_START, Menu.Y_START,null);
		g.drawImage(hardImage,Menu.X_START+Menu.SPACING, Menu.Y_START,null);
		g.drawImage(brutalImage,Menu.X_START+2*Menu.SPACING, Menu.Y_START,null);
		
		renderSelected(g);
		
	}

	@Override
	/**
	 * draw "selected" menu button
	 */
	public void renderSelected(Graphics g) {
		if(selected==SELECTED.EASY){
			g.drawImage(normalImageOn,Menu.X_START, Menu.Y_START,null);
			//g.drawImage(Menu.pointer, Menu.POINTER_X_START,Menu.POINTER_Y_START, null);
		}
		else if(selected==SELECTED.MEDIUM){
			g.drawImage(hardImageOn,Menu.X_START+Menu.SPACING, Menu.Y_START,null);
			//g.drawImage(Menu.pointer, Menu.POINTER_X_START+Menu.SPACING,Menu.POINTER_Y_START, null);
		}
		else if(selected==SELECTED.HARD){
			g.drawImage(brutalImageOn,Menu.X_START+2*Menu.SPACING, Menu.Y_START,null);
			//g.drawImage(Menu.pointer, Menu.POINTER_X_START+2*Menu.SPACING,Menu.POINTER_Y_START, null);
		}
		
	}

	@Override
	/**
	 * select game difficulty depending on keyboard key pressed
	 * @param key keyboard key pressed
	 */
	public void keyPressed(int key) {
		if(key==GameSystem.RIGHT){
			if(selected==SELECTED.EASY){
				selected=SELECTED.MEDIUM;
			}
			else if(selected==SELECTED.MEDIUM){
				selected=SELECTED.HARD;
			}
			else if(selected==SELECTED.HARD){
				selected=SELECTED.EASY;
			}
			GameSystem.playSwitch();
		}
		else if(key==GameSystem.LEFT){
			if(selected==SELECTED.EASY){
				selected=SELECTED.HARD;
			}
			else if(selected==SELECTED.MEDIUM){
				selected=SELECTED.EASY;
			}
			else if(selected==SELECTED.HARD){
				selected=SELECTED.MEDIUM;
			}
			GameSystem.playSwitch();
		}
		else if(key==GameSystem.CONFIRM){
			if(selected==SELECTED.EASY){
				Game.DIFFICULTY = easy;
			}
			else if(selected==SELECTED.MEDIUM){
				Game.DIFFICULTY = medium;
			}
			else if(selected==SELECTED.HARD){
				Game.DIFFICULTY = hard;
			}
			GameSystem.playConfirm();
			Menu.toTwoPlayers();
		}
		else if(key==GameSystem.CANCEL){
			GameSystem.playCancel();
			Menu.mState=MENUSTATE.MAIN;
		}
		
	}

}
