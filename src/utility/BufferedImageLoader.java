package utility;
/**
* Description:
* Load images with accessible buffer of image data
* @author Team 6
* @version 1.0
* @since 2014-03-27
*/

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLDecoder;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	private BufferedImage image;
	private Image gif;
	
	/**
	 * load image file
	 * @param path the source path of image file
	 * @return a BufferedImage type variable which is converted from the image file
	 */
	public BufferedImage loadImage(String path){
		try {
			image = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
	
	/**
	 * Load gif file
	 * @param path source path of the gif image file
	 * @return an Image type variable which is converted from the gif image file
	 */
	public Image loadGif(String path){
		path = getClass().getResource(path).getFile();
		path = URLDecoder.decode(path);
		gif = Toolkit.getDefaultToolkit().createImage(path);
		return gif;
	}
}
