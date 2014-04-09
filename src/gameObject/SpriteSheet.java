package gameObject;

import java.awt.image.BufferedImage;

/**
* <b>Description:</b>
* <br>
* Defines SpriteSheet from image file
* <br>Utilizes regions of image bounded by specific coordinates
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class SpriteSheet {

	private BufferedImage image;
	
	/**
	 * creates a SpriteSheet object
	 */
	public SpriteSheet(BufferedImage img){
		this.image = img;
	}
	
	/**
	 * Grabs a sub image at the given coordinates of given width and height
	 * @return buffered image at targeted spot
	 */
	public BufferedImage grabImage(int x,int y, int width, int height){
		BufferedImage img = image.getSubimage((x*width-width), (y*height-height), width, height);
		return img;
	}
}
