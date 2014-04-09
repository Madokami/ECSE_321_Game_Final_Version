package graphicEffects;

import gameObject.GameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
* <b>Description:</b>
* <br>Renders damage value and graphics based on input GameObject
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class DamageRenderer {
	private BufferedImage[][] damageArray;
	private int[] counter;
	private int currentCounter;
	private final int renderDuration=15;
	private GameObject owner;
	private int randX[],randY[];
	private Random rand;
	private int randRange=40;
	
	private int x;
	private int y;
	
	/**
	 * renders damage
	 * @panam game object o
	 * @return render damage
	 */
	public DamageRenderer(GameObject o){
		owner = o;
		rand=new Random();
		damageArray=new BufferedImage[10][4];
		counter=new int[10];
		randX=new int[10];
		randY=new int[10];
		for(int i=0;i<counter.length;i++){
			counter[i]=20;
		}
		currentCounter=0;
	}
	/**
	 * renders damage
	 * @panam damage value
	 * @return render damage value
	 */
	public void renderDamage(int damageValue){
		if(counter[currentCounter]<renderDuration) {
			currentCounter++;
		}
		if(currentCounter>=10) currentCounter=0;
		counter[currentCounter]=0;
		damageArray[currentCounter]=IntToImage.toImageDamage(damageValue);
		randX[currentCounter]=rand.nextInt(randRange);
		randY[currentCounter]=rand.nextInt(randRange);
		//these 2 lines make sure that the damage does not move with the GameObject;
		x=(int)owner.getXAbsolute();
		y=(int)owner.getYAbsolute();
	}
	/**
	 * uses current conditions to determine next iteration
	 */
	public void tick(){
		for(int i=0;i<counter.length;i++){
			counter[i]++;
		}
	}
	/**
	 * renders graphics
	 * @panam graphic object
	 * @return render graphic
	 */
	public void render(Graphics g){
		for(int i=0;i<counter.length;i++){
			if(counter[i]<renderDuration){
				for(int j=0;j<damageArray[i].length;j++){
					g.drawImage(damageArray[i][j], x+randX[i]-randRange/2+j*24,  y+randY[i]-damageArray[i][j].getHeight()-randRange/2, null);
				}
			}
		}
	}
}
