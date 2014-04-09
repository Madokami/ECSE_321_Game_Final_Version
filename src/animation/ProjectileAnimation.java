package animation;

import projectile.Projectile;
import gameObject.GameObject.ORIENTATION;

/**
* <b>Description:</b>
* <br>
* Defines animation of projectile while constantly checking conditions to determine its path and velocity
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class ProjectileAnimation {
	private double counter;
	private int frames;
	private ImageSequence currentSequence;
	private Projectile owner;

	/**
	 * defines projectile object
	 * @param projectile object
	 */
	
	public ProjectileAnimation(Projectile o){
		this.owner=o;
	}
	/**
	 * plays series of frames based on orientation and dimensions
	 */
	public void animate(){
		if(owner.orientation==ORIENTATION.RIGHT||owner.orientation==ORIENTATION.DOWN){
			owner.setImage(currentSequence.getImage((int)counter));
			owner.setImageWidth(currentSequence.getWidth());
			owner.setImageHeight(currentSequence.getHeight());
			owner.renderXShift=currentSequence.getX();
			owner.renderYShift=currentSequence.getY();
		}
		else if(owner.orientation==ORIENTATION.LEFT){
			owner.setImage(currentSequence.getImage((int)counter));
			owner.setImageWidth(-currentSequence.getWidth());
			owner.setImageHeight(currentSequence.getHeight());
			owner.renderXShift=currentSequence.getX()+currentSequence.getWidth();
			owner.renderYShift=currentSequence.getY();
		}
		else if(owner.orientation==ORIENTATION.UP){
			owner.setImage(currentSequence.getImage((int)counter));
			owner.setImageWidth(currentSequence.getWidth());
			owner.setImageHeight(-currentSequence.getHeight());
			owner.renderXShift=currentSequence.getX();
			owner.renderYShift=currentSequence.getY()+currentSequence.getHeight();
		}
	}
	/**
	 * uses current status to determine next iteration
	 */
	public void tick(){
		
		if(currentSequence!=null){
			counter=counter%frames;
			animate();
			counter+=currentSequence.getAnimationSpeed();
		}
	}
	/**
	 * defines sequence of images during animation
	 * @param sequence object
	 */
	public void startSequence(ImageSequence sequence){
		currentSequence=sequence;
		counter=0;
		if(currentSequence!=null){
			frames=currentSequence.getFrames();
		}
	}
}
