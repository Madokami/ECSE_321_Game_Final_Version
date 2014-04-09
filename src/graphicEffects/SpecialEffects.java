package graphicEffects;
/**
* Description:
* Special effects of the game
* @author Team 6
* @version 1.0
* @since 2014-03-27
*/
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import system.GameSystem;
import utility.BufferedImageLoader;

public class SpecialEffects {
	private int fadeWhite,fadeWhiteReversed;
	private boolean isFadeWhite=false;
	private boolean isFadeWhiteReversed=false;
	
	private BufferedImage[] white; //white0,white1,white2,white3,white4,white5,white6,white7,white8,white9;
	private BufferedImageLoader loader;
	
	public SpecialEffects(){
		fadeWhite=0;
		loader = new BufferedImageLoader();
		white=new BufferedImage[10];
		for(int i=0;i<10;i++){
			String s = "/image/white".concat(Integer.toString(i)).concat(".png");
			white[i]=loader.loadImage(s);
		}
		/*
		white0=loader.loadImage("/image/white0.png");
		white1=loader.loadImage("/image/white1.png");
		white2=loader.loadImage("/image/white2.png");
		white3=loader.loadImage("/image/white3.png");
		white4=loader.loadImage("/image/white4.png");
		white5=loader.loadImage("/image/white5.png");
		white6=loader.loadImage("/image/white6.png");
		white7=loader.loadImage("/image/white7.png");
		white8=loader.loadImage("/image/white8.png");
		white9=loader.loadImage("/image/white9.png");
		*/
		
	}
	
	/**
	 * Update fadeWhite and fadeWhiteReversed variables
	 */
	public void tick(){
		if(fadeWhite>=10){
			isFadeWhite=false;
		}
		fadeWhite++;
		if(fadeWhiteReversed>=10){
			isFadeWhiteReversed=false;
		}
		fadeWhiteReversed++;
		
		
	}
	
	/**
	 * draw white fading effect
	 * @param g current graphic
	 */
	private void fadeWhite(Graphics g){
		if(!isFadeWhite){
			return;
		}
		else if(fadeWhite>=10){
			return;
		}
		//do stuff
		g.drawImage(white[fadeWhite], 0, 0,GameSystem.ABSWIDTH+10,GameSystem.ABSHEIGHT+10,null);
			
	}
	
	/**
	 * start white fading effect
	 */
	public void startFadeWhite(){
		if(isFadeWhite){
			return;
		}
		fadeWhite=0;
		isFadeWhite=true;
	}
	
	/**
	 * call fadeWhite() and fadeWhiteReversed
	 * @param g current graphic
	 */
	public void render(Graphics g) {
		fadeWhite(g);
		fadeWhiteReversed(g);
	}
	
	/**
	 * draw reversed white fading effect
	 * @param g current graphic
	 */
	private void fadeWhiteReversed(Graphics g){
		if(!isFadeWhiteReversed){
			return;
		}
		else if(fadeWhiteReversed>=10){
			return;
		}
		//do stuff
		g.drawImage(white[9-fadeWhiteReversed], 0, 0,GameSystem.ABSWIDTH+10,GameSystem.ABSHEIGHT+10,null);
			
	}
	
	/**
	 * start reversed white fading effect
	 */
	public void startFadeWhiteReversed() {
		if(isFadeWhiteReversed){
			return;
		}
		fadeWhiteReversed=0;
		isFadeWhiteReversed=true;
		
	}
}
