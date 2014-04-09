package animation;

import java.awt.Image;

import system.GameSystem;

/**
* <b>Description:</b>
* <br>
* Details attributes to .gif files
* <br>Includes details about dimensions and position
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class GifAttribute {
	private int size = GameSystem.GRID_SIZE;
	private final double scale = GameSystem.GRID_SIZE*1.5;
	private final int yShift = GameSystem.GRID_SIZE/10;
	
	private int width,height,x,y;
	private Image image;
	
	/**
	 * defines .gif image attributes
	 * @param gif an Image
	 * @return gif attributes
	 */
	public GifAttribute(Image gif){
		this.image=gif;
		height=(int)scale;
		while(image.getHeight(null)==-1&&image.getWidth(null)==-1){
			width=-1;
		}
		width=image.getWidth(null)*height/image.getHeight(null);
		x= (size-width)/2;
		y= size-height+yShift;
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public Image getImage() {
		return image;
	}

	
}
