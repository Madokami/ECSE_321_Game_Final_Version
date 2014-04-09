package animation;

import java.awt.Image;

import system.GameSystem;

/**
* <b>Description:</b>
* <br><br>
* Responsible for calling and defining methods for different .gif files
* <br><br>
* Parameters are placed to control size of animated images
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class AnimationParameters {
	private int size = GameSystem.GRID_SIZE;
	private final double scale = GameSystem.GRID_SIZE*1.5;
	private final int yShift = GameSystem.GRID_SIZE/10;
	private GifAttribute walkGif,standGif,damagedGif,deathGif;
	
	public AnimationParameters(){
		
	}
	
	public GifAttribute getWalkGif() {
		return walkGif;
	}
	public void setWalkGif(Image walkGif) {
		this.walkGif = new GifAttribute(walkGif);
	}
	public GifAttribute getStandGif() {
		return standGif;
	}
	public void setStandGif(Image standGif) {
		this.standGif = new GifAttribute(standGif);
	}
	public GifAttribute getDamagedGif() {
		return damagedGif;
	}
	public void setDamagedGif(Image damagedGif) {
		this.damagedGif = new GifAttribute(damagedGif);
	}
	public GifAttribute getDeathGif() {
		return deathGif;
	}
	public void setDeathGif(Image deathGif) {
		this.deathGif = new GifAttribute(deathGif);
	}
	
	
}
