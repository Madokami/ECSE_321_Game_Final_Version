package graphicEffects;
/**
* Description:
* Convert an integer to an image
* @author Team 6
* @version 1.0
* @since 2014-03-27
*/
import gameObject.SpriteSheet;

import java.awt.image.BufferedImage;

import data.SpriteData;

public class IntToImage {
	 
	 /**
	 * Convert an integer to a image 
	 * @param value the integer value to be converted
	 * @return a BufferedImage type array which has the integer on the images 
	 */
	public static BufferedImage[] toImageSmall(int value){
		SpriteSheet numbers = SpriteData.num_18_16;
		
		String temp = Integer.toString(value);
		char[] nums = temp.toCharArray();
		BufferedImage[] ret= new BufferedImage[nums.length];
		
		for(int i=0;i<nums.length;i++){
			ret[i]=numbers.grabImage(Character.getNumericValue(nums[i])+1, 1, 18, 16);
		}
		return ret;
	}
	/**
	 * Convert an integer to a image 
	 * @param value the integer value to be converted
	 * @return a BufferedImage type array which has the integer on the images 
	 */
	public static BufferedImage[] toImageGriefSyndrome(int value){
		SpriteSheet numbers = SpriteData.num_20_36;
		
		String temp = Integer.toString(value);
		char[] nums = temp.toCharArray();
		BufferedImage[] ret= new BufferedImage[nums.length];
		
		for(int i=0;i<nums.length;i++){
			ret[i]=numbers.grabImage(Character.getNumericValue(nums[i])+1, 1, 20, 36);
		}
		return ret;
	}
	
	/**
	 * Convert an integer to a image 
	 * @param value the integer value to be converted
	 * @return a BufferedImage type array which has the integer on the images 
	 */
	public static BufferedImage[] toImageDamage(int value){
		SpriteSheet numbers = SpriteData.numDamage;
		
		String temp = Integer.toString(value);
		char[] nums = temp.toCharArray();
		BufferedImage[] ret= new BufferedImage[nums.length];
		
		for(int i=0;i<nums.length;i++){
			ret[i]=numbers.grabImage(Character.getNumericValue(nums[i])+1, 1, 27, 42);
		}
		return ret;
	}
}
