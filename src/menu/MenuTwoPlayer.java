package menu;
/**
* Description:
* Two player menu
* @author Team 6
* @version 1.45
* @since 2014-04-06
*/
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import system.GameSystem;

public class MenuTwoPlayer implements GeneralMenu{
	private BufferedImage onePlayer,onePlayerOn,twoPlayers,twoPlayersOn;
	
	private enum SELECTED{
		ONE_PLAYER,
		TWO_PLAYERS,
	}
	
	private SELECTED selected = SELECTED.ONE_PLAYER;
	
	public MenuTwoPlayer(){
		onePlayer=GameSystem.loader.loadImage("/image/menu/1player2.png");
		onePlayerOn=GameSystem.loader.loadImage("/image/menu/1player.png");
		twoPlayers=GameSystem.loader.loadImage("/image/menu/2players2.png");
		twoPlayersOn=GameSystem.loader.loadImage("/image/menu/2players.png");
		
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Draw menu button "1 player" or "2 player"
	 * @param g current graphic
	 */
	public void render(Graphics g) {
		g.drawImage(onePlayer,Menu.X_START, Menu.Y_START,null);
		g.drawImage(twoPlayers,Menu.X_START+2*Menu.SPACING, Menu.Y_START,null);
		
		this.renderSelected(g);
	}

	@Override
	/**
	 * Draw selected menu button "1 player" or "2 player"
	 * @param g current graphic
	 */
	public void renderSelected(Graphics g) {
		if(selected==SELECTED.ONE_PLAYER){
			g.drawImage(onePlayerOn,Menu.X_START, Menu.Y_START,null);
			//g.drawImage(Menu.pointer, Menu.POINTER_X_START, Menu.POINTER_Y_START, null);
		}
		else if(selected==SELECTED.TWO_PLAYERS){
			g.drawImage(twoPlayersOn,Menu.X_START+2*Menu.SPACING, Menu.Y_START,null);
			//g.drawImage(Menu.pointer, Menu.POINTER_X_START+2*Menu.SPACING, Menu.POINTER_Y_START, null);
		}
		
	}

	@Override
	/**
	 * Set player information depending on keyboard key pressed
	 * @param key keyboard key pressed
	 */
	public void keyPressed(int key) {
		if(key==GameSystem.CONFIRM){
			if(selected==SELECTED.ONE_PLAYER){
				GameSystem.setDefaultKeyLayout();
				Menu.toChooseChar();
			}
			else if(selected==SELECTED.TWO_PLAYERS){
				GameSystem.TWO_PLAYER_MODE=true;
				Menu.toChooseChar();
			}
			GameSystem.playConfirm();
		}
		else if(key==GameSystem.CANCEL){
			GameSystem.playCancel();
			GameSystem.TWO_PLAYER_MODE=false;
			Menu.toDifficulty();
			GameSystem.setDefaultKeyLayout();
		}
		else if(key==GameSystem.RIGHT){
			if(selected==SELECTED.ONE_PLAYER){
				selected=SELECTED.TWO_PLAYERS;
			}
			else if(selected==SELECTED.TWO_PLAYERS){
				selected=SELECTED.ONE_PLAYER;
			}
			GameSystem.playSwitch();
		}
		else if(key==GameSystem.LEFT){
			if(selected==SELECTED.ONE_PLAYER){
				selected=SELECTED.TWO_PLAYERS;
			}
			else if(selected==SELECTED.TWO_PLAYERS){
				selected=SELECTED.ONE_PLAYER;
			}
			GameSystem.playSwitch();
		}
		
	}

}
