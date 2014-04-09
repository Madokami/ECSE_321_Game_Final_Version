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
	 * Draws image
	 * @param image
	 * @return buffered image
	 */
	public SpriteSheet(BufferedImage img){
		this.image = img;
	}
	
	/**
	 * Grabs image
	 * @param coordinates, dimensions
	 * @return buffered image
	 */
	public BufferedImage grabImage(int x,int y, int width, int height){
		BufferedImage img = image.getSubimage((x*width-width), (y*height-height), width, height);
		return img;
	}
}
