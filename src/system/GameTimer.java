package system;
/**
* Description:
* Build a game timer and show it on the game screen
* @author Team 6
* @version 1.45
* @since 2014-04-06
*/
import graphicEffects.IntToImage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class GameTimer {
	public int timeCounter;
	public static int sGameTime;
	public static int mGameTime;
	public static BufferedImage[] seconds;
	public static BufferedImage[] minutes;
	
	public GameTimer(){
		timeCounter=0;
		sGameTime=0;
		mGameTime=0;
		seconds=IntToImage.toImageSmall(sGameTime);
		minutes=IntToImage.toImageSmall(mGameTime);
		
	}
	
	/**
	 * Build a timer
	 */
	public void tick(){
		timeCounter++;
		if(timeCounter>=30){
			timeCounter=0;
			sGameTime++;
			seconds=IntToImage.toImageSmall(sGameTime);
		}
		if(sGameTime>=60){
			sGameTime=0;
			mGameTime++;
			seconds=IntToImage.toImageSmall(sGameTime);
			minutes=IntToImage.toImageSmall(mGameTime);
		}
		
	}

	/**
	 * Draw the timer onto game screen
	 * @param g current game graphic
	 */
	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("arial",Font.HANGING_BASELINE,20));
		//g.drawString(Integer.toString(mGameTime), GameSystem.ABSWIDTH-50, 20);
		g.drawString(":",GameSystem.ABSWIDTH-36,26);
		//g.drawString(Integer.toString(sGameTime), GameSystem.ABSWIDTH-30, 20);
		
		for(int i=0;i<minutes.length;i++){
			g.drawImage(minutes[i], GameSystem.ABSWIDTH-50+16*i, 12, null);
		}
		
		
		
		for(int i=0;i<seconds.length;i++){
			g.drawImage(seconds[i], GameSystem.ABSWIDTH-30+16*i, 12, null);
		}
		
	}
	
	/**
	 * reset timer from 0
	 */
	public static void resetTimer(){
		sGameTime=0;
		mGameTime=0;
		seconds=IntToImage.toImageSmall(sGameTime);
		minutes=IntToImage.toImageSmall(mGameTime);
	}
}
