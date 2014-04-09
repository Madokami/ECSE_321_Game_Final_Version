package animation;

import gameObject.MovableObject;
import gameObject.MovableObject.ANIMATION;
import gameObject.MovableObject.FACING;

/**
* <b>Description:</b>
* <br>
* Different images are animated based on orientation and sequence
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class Animation {
	private double counter;
	private int frames;
	private ImageSequence currentSequence,nextSequence;
	private MovableObject owner;
	private boolean goToNext,oneTimeSequence,sequencePlaying;
	
	/**
	* Defines MoveableObject
	* @param o instance of a MovableObject
	* @return Animation
	*/
	public Animation(MovableObject o){
		this.owner=o;
	}
	
	/**
	* Animation is displayed using specific coordinates of original image
	*/
	public void animate(){
		if(owner.isDontFlip()){
			owner.setImage(currentSequence.getImage((int)counter));
			owner.setImageWidth(currentSequence.getWidth());
			owner.setImageHeight(currentSequence.getHeight());
			owner.renderXShift=currentSequence.getX();
			owner.renderYShift=currentSequence.getY();
			return;
		}
		
		
		if(owner.getFacing()==FACING.RIGHT){
			owner.setImage(currentSequence.getImage((int)counter));
			owner.setImageWidth(currentSequence.getWidth());
			owner.setImageHeight(currentSequence.getHeight());
			owner.renderXShift=currentSequence.getX();
			owner.renderYShift=currentSequence.getY();
		}
		else{
			owner.setImage(currentSequence.getImage((int)counter));
			owner.setImageWidth(-currentSequence.getWidth());
			owner.setImageHeight(currentSequence.getHeight());
			owner.renderXShift=currentSequence.getX()+currentSequence.getWidth();
			owner.renderYShift=currentSequence.getY();
		}
	}
	
	/**
	* Method is called repeatedly to check current status quo
	* <br>
	* Animation is then executed based on the existing conditions
	*/
	public void tick(){
		
		if(currentSequence!=null){
			counter=counter%frames;
			animate();
			counter+=currentSequence.getAnimationSpeed();
			if(counter>=frames){
				if(oneTimeSequence) counter=frames-1;
				if(goToNext) {
					if(nextSequence!=null) {
						sequencePlaying=false;
						owner.setAnimation(ANIMATION.STAND);
						startSequence(nextSequence);
					}
				}
				
			}
		}
	}
	
	/**
	* Displays animated image at beginning of cycle
	* @param sequence a ImageSequence
	*/
	public void startSequence(ImageSequence sequence){
		if(sequencePlaying) return;
		if(oneTimeSequence) return;
		goToNext=false;
		oneTimeSequence=false;
		currentSequence=sequence;
		counter=0;
		if(currentSequence!=null){
			frames=currentSequence.getFrames();
		}
	}
	
	/**
	* Displays animated image at the beginning of cycle
	* @param sequence first imageSequcence to be played
	* @param sequence next ImageSequence to be played
	*/
	public void startSequence(ImageSequence sequence, ImageSequence nextSequence ){
		if(oneTimeSequence) return;
		sequencePlaying=true;
		goToNext=true;
		oneTimeSequence=false;
		currentSequence=sequence;
		this.nextSequence=nextSequence;
		counter=0;
		if(currentSequence!=null){
			frames=currentSequence.getFrames();
		}
	}
	
	/**
	* Displays animated image at the beginning of cycle
	* @param sequence a ImageSequence
	*/
	public void startOneTimeSequence(ImageSequence sequence){
		oneTimeSequence=true;
		goToNext=false;
		currentSequence=sequence;
		counter=0;
		if(currentSequence!=null){
			frames=currentSequence.getFrames();
		}
	}

}
