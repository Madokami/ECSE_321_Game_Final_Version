package menu;
/**
* Description:
* Character Death menu
* @author Team 6
* @version 1.4
* @since 2014-04-06
*/
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import system.GameSystem;
import system.GameSystem.STATE;
import utility.BufferedImageLoader;
import menu.Menu.MENUSTATE;

public class MenuDeath{
	private BufferedImageLoader loader;
	private BufferedImage dBackground, dText;
	public static enum DEATH{
		RESTART,
		BACKTOMENU,
	}
	
	public static DEATH dSelected = DEATH.RESTART;
	
	public MenuDeath(){
		loader = new BufferedImageLoader();
		dBackground = loader.loadImage("/image/gameOverBg.png");
		dText = loader.loadImage("/image/gameOverText.png");
	}
	public void tick(){
		
	}
	
	/**
	 * Draw menu button onto screen
	 * @param g current graphic
	 */
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.drawImage(dBackground,0, 0, GameSystem.ABSWIDTH+10, GameSystem.ABSHEIGHT+10,null);
		g.setFont(new Font("arial",Font.BOLD,45));
		g.setColor(Color.BLACK);
		g.drawImage(dText, GameSystem.ABSWIDTH-GameSystem.ABSWIDTH/3-110, 100,null);
		g.setFont(new Font("arial",Font.ITALIC,30));
		g.drawString("Restart Level",GameSystem.ABSWIDTH/2+100,300);
		g.drawString("Back to Menu",GameSystem.ABSWIDTH/2+100,400);
		renderSelected(g);
	}
	
	/**
	 * 	
	 * draw "selected" menu button
	 * @param g current graphic
	 */
	public void renderSelected(Graphics g) {
			if(dSelected==DEATH.RESTART){
				g.setColor(Color.RED);
				g.drawString("Restart Level",GameSystem.ABSWIDTH/2+100,300);
			}
			else if(dSelected==DEATH.BACKTOMENU){
				g.setColor(Color.RED);
				g.drawString("Back to Menu",GameSystem.ABSWIDTH/2+100,400);
			} 		
	}
	
	/**
	 * restart the game or back to menu depending on keyboard key pressed
	 * @param key keyboard key pressed
	 */
	public void keyPressed(int key) {
		if(key==GameSystem.DOWN){
			if(dSelected==DEATH.RESTART){
				dSelected=DEATH.BACKTOMENU;
			}
			else if(dSelected==DEATH.BACKTOMENU){
				dSelected=DEATH.RESTART;
			}
			GameSystem.playSwitch();
		}
		else if(key==GameSystem.UP){
			if(dSelected==DEATH.BACKTOMENU){
				dSelected=DEATH.RESTART;
			}	
			else if(dSelected==DEATH.RESTART){
				dSelected=DEATH.BACKTOMENU;
			}
			GameSystem.playSwitch();
		}
		else if(key==GameSystem.CONFIRM){
			if(dSelected==DEATH.BACKTOMENU){
				GameSystem.TWO_PLAYER_MODE=false;
				GameSystem.PLAYER_ONE_CHOSEN=false;
				Menu.backToMenu();
				GameSystem.playConfirm();
			}
			else if(dSelected==DEATH.RESTART){
				Menu.toGameMode();
				GameSystem.playConfirm();
			}
		}
	}
	
	
	
	
}
