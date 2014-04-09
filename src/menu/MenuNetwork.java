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

import network.Client;
import menu.Menu.MENUSTATE;
import system.GameSystem;

public class MenuNetwork implements GeneralMenu{
	//private BufferedImage backGround;
	public String stateChosen="hostGame";
	public MenuNetwork(){
		//backGround=GameSystem.loader.loadImage("/image/menu/multBg.png");
	}
	
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * draw menu button "Host" and "Join"
	 */
	public void render(Graphics g) {
		//g.drawImage(backGround, 0, 0, GameSystem.ABSWIDTH+10,GameSystem.ABSHEIGHT+10,null);
		//g.drawImage(backGround, -400, 0,null);
		g.drawString("Host", Menu.X_START-5, Menu.Y_START);
		g.drawString("Join", Menu.X_START-2+2*Menu.SPACING, Menu.Y_START);
		renderSelected(g);
	}

	/**
	 * draw "selected" menu button
	 */
	public void renderSelected(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		if(stateChosen=="hostGame"){
			g.drawString("Host", Menu.X_START-5, Menu.Y_START);
			g.drawImage(Menu.pointer, Menu.X_START-5-Menu.pointer.getWidth(), Menu.Y_START-Menu.pointer.getHeight(), null);
		}
		else if(stateChosen=="joinGame"){
			g.drawString("Join", Menu.X_START-2+2*Menu.SPACING, Menu.Y_START);
			g.drawImage(Menu.pointer, Menu.X_START-5-Menu.pointer.getWidth()+2*Menu.SPACING, Menu.Y_START-Menu.pointer.getHeight(), null);
		}
		
	}

	/**
	 * select host or join depending on keyboard key pressed
	 * @param key keyboard key pressed
	 */
	public void keyPressed(int key) {
		// TODO Auto-generated method stub
		if(key==GameSystem.CONFIRM){
			GameSystem.LAN_TWO_PLAYER_MODE=true;
			
			if(stateChosen=="hostGame"){
				GameSystem.isPlayerOne=true;
				GameSystem.sendCommandToOther("setCurrentStage"+Integer.toString(Game.curLevel));
				
			}
			else if(stateChosen=="joinGame"){
				GameSystem.isPlayerOne=false;
			}
				GameSystem.playConfirm();
				Menu.toChooseChar();
		}
		else if(key==GameSystem.CANCEL){
			Menu.mState=MENUSTATE.MAIN;
			GameSystem.playCancel();
		}
		else if(key==GameSystem.RIGHT){
			if(stateChosen=="hostGame"){
				setState("joinGame");
			}
			GameSystem.playSwitch();
		}
		else if(key==GameSystem.LEFT){
			if(stateChosen=="joinGame"){
				setState("hostGame");
			}
			GameSystem.playSwitch();
		}
	}
	
	/**
	 * update chosen state
	 * @param state current state
	 */
	public void setState(String state){
		this.stateChosen=state;
	}
}
