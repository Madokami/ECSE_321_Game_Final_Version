package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import system.GameSystem;
import utility.BufferedImageLoader;

/**
* <b>Description:</b>
* <br>
* Renders stage graphics based on current stage level
* <br>Images are displayed at designated positions on grid map
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class StageRenderer {
	private int size = GameSystem.GRID_SIZE;
	private BufferedImageLoader loader = new BufferedImageLoader();
	private BufferedImage corn = loader.loadImage("/image/stage/stage1/corn.png");
	private BufferedImage bara = loader.loadImage("/image/stage/stage1/bara1.png");
	private BufferedImage bar1 = loader.loadImage("/image/stage/stage1/bar1.png");
	private BufferedImage bar2 = loader.loadImage("/image/stage/stage1/bar2.png");
	private BufferedImage hyoushiki = loader.loadImage("/image/stage/stage1/hyoushiki.png");
	private BufferedImage amedama = loader.loadImage("/image/stage/stage2/amedama.png");
	private BufferedImage ashiba1 = loader.loadImage("/image/stage/stage2/ashiba1.png");
	private BufferedImage ashiba2 = loader.loadImage("/image/stage/stage2/ashiba2.png");
	private BufferedImage ashiba3 = loader.loadImage("/image/stage/stage2/ashiba3.png");
	private BufferedImage ashiba4 = loader.loadImage("/image/stage/stage2/ashiba4.png");
	private BufferedImage candy = loader.loadImage("/image/stage/stage2/candy.png");
	private BufferedImage cyusya1 = loader.loadImage("/image/stage/stage2/cyusya1.png");
	private BufferedImage cyusya2 = loader.loadImage("/image/stage/stage2/cyusya2.png");
	private BufferedImage no3 = loader.loadImage("/image/stage/stage2/no3.png");
	private BufferedImage no2 = loader.loadImage("/image/stage/stage2/no2.png");
	private BufferedImage no6 = loader.loadImage("/image/stage/stage2/no6.png");
	private BufferedImage no4 = loader.loadImage("/image/stage/stage2/no4.png");
	private BufferedImage syujuturamp = loader.loadImage("/image/stage/stage2/syujuturamp.png");
	private BufferedImage tv1 = loader.loadImage("/image/stage/stage3/tv1.png");
	private BufferedImage tv2 = loader.loadImage("/image/stage/stage3/tv2.png");
	private BufferedImage tv3 = loader.loadImage("/image/stage/stage3/tv3.png");
	private BufferedImage bill1 = loader.loadImage("/image/stage/stage4/bill1.png");
	private BufferedImage bill2 = loader.loadImage("/image/stage/stage4/bill2.png");
	private BufferedImage clane = loader.loadImage("/image/stage/stage4/clane.png");
	private BufferedImage panel = loader.loadImage("/image/stage/stage4/panel.png");
	private BufferedImage tank = loader.loadImage("/image/stage/stage4/tank.png");
	private BufferedImage tesuri = loader.loadImage("/image/stage/stage4/tesuri.png");
	
	
	/**
	 * renders graphics
	 * @param g graphic object
	 */
	public void render1(Graphics g){
		g.drawImage(corn, 14*size, 0*size,size,size, null);
		g.drawImage(corn, 10*size, 2*size,size,size, null);	
		g.drawImage(corn, 11*size, 2*size,size,size, null);	
		g.drawImage(corn, 12*size, 2*size,size,size, null);
		g.drawImage(corn, 14*size, 7*size,size,size, null);
		g.drawImage(corn, 15*size, 7*size,size,size, null);
		g.drawImage(hyoushiki, 7*size, 5*size,size,size, null);
		g.drawImage(hyoushiki, 8*size, 5*size,size,size, null);
		g.drawImage(bar1, 2*size, 2*size,3*size,size, null);
		g.drawImage(bar2, 9*size, 7*size,size,3*size, null);
	}
	/**
	 * renders graphics
	 * @param g graphic object
	 */
	public void render2(Graphics g){
		g.drawImage(corn, 2*size, 3*size,size,size, null);
		g.drawImage(corn, 13*size, 3*size,size,size, null);
		g.drawImage(corn, 2*size, 6*size,size,size, null);
		g.drawImage(corn, 13*size, 6*size,size,size, null);
		g.drawImage(hyoushiki, 7*size, 0*size,size,size, null);
		g.drawImage(hyoushiki, 8*size, 0*size,size,size, null);
		g.drawImage(hyoushiki, 7*size, 9*size,size,size, null);
		g.drawImage(hyoushiki, 8*size, 9*size,size,size, null);
		g.drawImage(bara, 7*size, 4*size,size,size, null);
		g.drawImage(bara, 8*size, 4*size,size,size, null);
		g.drawImage(bara, 7*size, 5*size,size,size, null);
		g.drawImage(bara, 8*size, 5*size,size,size, null);
	}
	/**
	 * renders graphics
	 * @param g graphic object
	 */
	public void render3(Graphics g){
		g.drawImage(corn, 0*size, 0*size,size,size, null);
		g.drawImage(corn, 15*size, 0*size,size,size, null);
		g.drawImage(corn, 0*size, 9*size,size,size, null);
		g.drawImage(corn, 15*size, 9*size,size,size, null);
		g.drawImage(bar1, 4*size, 7*size,3*size,size, null);
		g.drawImage(bar1, 9*size, 7*size,3*size,size, null);
		g.drawImage(hyoushiki, 7*size, 7*size,size,size, null);
		g.drawImage(hyoushiki, 8*size, 7*size,size,size, null);
		g.drawImage(bara, 2*size, 3*size,size,size, null);
		g.drawImage(bara, 13*size, 3*size,size,size, null);
		g.drawImage(bara, 2*size, 6*size,size,size, null);
		g.drawImage(bara, 13*size, 6*size,size,size, null);
	}
	/**
	 * renders graphics
	 * @param g graphic object
	 */
	public void render4(Graphics g){
		g.drawImage(candy, 0*size, 0*size,size,2*size, null);
		g.drawImage(no2, 2*size, 3*size,size,size, null);
		g.drawImage(cyusya1, 13*size, 0*size,size,2*size, null);
		g.drawImage(no4, 2*size, 6*size,size,size, null);
		g.drawImage(no6, 13*size, 3*size,size,size, null);
		g.drawImage(no3, 13*size, 6*size,size,size, null);
		g.drawImage(amedama, 0*size, 9*size,size,size, null);
		g.drawImage(amedama, 15*size, 9*size,size,size, null);
		g.drawImage(ashiba1, 4*size, 7*size,2*size,size, null);
		g.drawImage(ashiba4, 10*size, 7*size,2*size,size, null);
	}
	/**
	 * renders graphics
	 * @param g graphic object
	 */
	public void render5(Graphics g){
		g.drawImage(candy, 0*size, 0*size,size,2*size, null);
		g.drawImage(syujuturamp, 7*size, 0*size,2*size,size, null);
		g.drawImage(syujuturamp, 7*size, 9*size,2*size,size, null);
		g.drawImage(ashiba2, 7*size, 3*size,2*size,size, null);
		g.drawImage(ashiba3, 7*size, 6*size,2*size,size, null);
		g.drawImage(cyusya1, 0*size, 8*size,size,2*size, null);
	}
	/**
	 * renders graphics
	 * @param g graphic object
	 */
	public void render6(Graphics g){
		g.drawImage(no3, 3*size, 2*size,size,size, null);
		g.drawImage(no6, 12*size, 2*size,size,size, null);
		g.drawImage(cyusya1, 6*size, 4*size,size,2*size, null);
		g.drawImage(cyusya2, 9*size, 4*size,size,2*size, null);
		g.drawImage(amedama, 12*size, 7*size,size,size, null);
	}
	/**
	 * renders graphics
	 * @param g graphic object
	 */
	public void render7(Graphics g){
		g.drawImage(tv2, 0*size, 1*size,size,size, null);
		g.drawImage(tv2, 1*size, 1*size,size,size, null);
		g.drawImage(tv1, 7*size, 2*size,size,size, null);
		g.drawImage(tv1, 15*size, 4*size,size,size, null);
		g.drawImage(tv1, 8*size, 2*size,size,size, null);
		g.drawImage(tv3, 1*size, 4*size,4*size,size, null);
		g.drawImage(tv3, 2*size, 6*size,4*size,size, null);
		g.drawImage(tv3, 7*size, 8*size,4*size,size, null);
	}
	/**
	 * renders graphics
	 * @param g graphic object
	 */
	public void render8(Graphics g){
		g.drawImage(tv1, 3*size, 2*size,size,size, null);
		g.drawImage(tv1, 4*size, 2*size,size,size, null);
		g.drawImage(tv1, 5*size, 2*size,size,size, null);
		g.drawImage(tv3, 8*size, 4*size,4*size,size, null);
		g.drawImage(tv3, 5*size, 8*size,4*size,size, null);
		g.drawImage(tv1, 2*size, 4*size,size,size, null);
		g.drawImage(tv1, 2*size, 5*size,size,size, null);
		g.drawImage(tv2, 11*size, 0*size,size,size, null);
		g.drawImage(tv2, 12*size, 0*size,size,size, null);
		g.drawImage(tv2, 13*size, 0*size,size,size, null);
		g.drawImage(tv1, 2*size, 6*size,size,size, null);
		g.drawImage(tv1, 0*size, 8*size,size,size, null);
		g.drawImage(tv1, 0*size, 9*size,size,size, null);
		g.drawImage(tv1, 15*size, 7*size,size,size, null);
		g.drawImage(tv1, 15*size, 6*size,size,size, null);
		g.drawImage(tv2, 2*size, 9*size,size,size, null);
	}
	/**
	 * renders graphics
	 * @param g graphic object
	 */
    public void render9(Graphics g){
    	
	}
	/**
	 * renders graphics
	 * @param g graphic object
	 */
    public void render10(Graphics g){
    	g.drawImage(bill2, 0*size, 1*size,2*size,4*size, null);
    	g.drawImage(tank, 4*size, 1*size,4*size,size, null);
    	g.drawImage(bill1, 14*size, 4*size,size,2*size, null);
    	
    }
	/**
	 * renders graphics
	 * @param g graphic object
	 */
    public void render11(Graphics g){
    	g.drawImage(tank, 2*size, 9*size,4*size,size, null);
    	g.drawImage(bill2, 10*size, 3*size,2*size,4*size, null);
    	g.drawImage(panel, 2*size, 2*size,4*size,2*size, null);
    	g.drawImage(tesuri, 0*size, 5*size,2*size,size, null);
    	g.drawImage(clane, 5*size, 0*size,2*size,size, null);
    }
    public void render12(Graphics g){
    	g.drawImage(panel, 2*size, 3*size,2*size,size, null);
    	g.drawImage(tank, 9*size, 4*size,4*size,size, null);
    	g.drawImage(clane, 4*size, 6*size,2*size,size, null);
    	g.drawImage(panel, 9*size, 7*size,2*size,size, null);
    }
}
