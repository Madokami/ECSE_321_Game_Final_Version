package animation;

import player.Player;
import gameObject.GameObject;
import gameObject.MovableObject;
import gameObject.MovableObject.ANIMATION;
import gameObject.MovableObject.FACING;

/**
 * <b>Description:</b>
 * <br><br>
 * Different images are animated based on orientation and sequence
 * <br><br>
 * Different methods of animations include utilizing:
 * <li>different files for different frames corresponding to each image
 * <li>spritesheets that are divided into quadrangles
 * <li>.gif files that are displayed based on orientation
 * <br><br>
 * @author      Team 6
 * @version     1.0
 * @since       2014-03-31
 */
public class Animate {
	
	/**
	Displays different images based on their orientations
	<br>
	Each frame of corresponding image is displayed consecutively
	@param game object o
	@return animation of o
	*/
	public static void animate(GameObject o){
		if(o.direction.equals("up")){
			double count = o.i%o.getFrames();
			o.setImage(o.getSs().grabImage((int)count + o.getSsX(),o.getSsY()+3,o.getSsWidth(),o.getSsHeight()));
			o.i+=o.MS;
		}
		else if(o.direction.equals("down")){
			double count = o.i%o.getFrames();
			o.setImage(o.getSs().grabImage((int)count + o.getSsX(),o.getSsY(),o.getSsWidth(),o.getSsHeight()));
			o.i+=o.MS;
		}
		else if(o.direction.equals("left")){
			double count = o.i%o.getFrames();
			o.setImage(o.getSs().grabImage((int)count + o.getSsX(),o.getSsY()+1,o.getSsWidth(),o.getSsHeight()));
			o.i+=o.MS;
		}
		else if(o.direction.equals("right")){
			double count = o.i%o.getFrames();
			o.setImage(o.getSs().grabImage((int)count + o.getSsX(),o.getSsY()+2,o.getSsWidth(),o.getSsHeight()));
			o.i+=o.MS;
		}
		else if(o.direction.equals("stand")){
			//double count = o.i%8;
			//o.image=o.ssStand.grabImage((int)count+1,1,48,66);
			//o.i+=o.MS;
		}
	}
	
	/**
	Retrieves different frames of image from spritesheet
	<br>
	Divides spritesheet into equal quadrangles corresponding to individual frames
	@param player object p
	@return animation of p
	*/
	public static void animateGem(Player p) {
		p.soulGemImage=p.soulGemSprite.grabImage(1, 1, Player.soulGemWidth, Player.soulGemHeight);
		if(1-p.soul/p.maxSoul>=0.25&&1-p.soul/p.maxSoul<0.5){
			double count = p.soulGemCounter%8;
			p.soulGemImage=p.soulGemSprite.grabImage(1,2+(int)count,Player.soulGemWidth, Player.soulGemHeight);
			p.soulGemCounter+=p.soulGemAnimationSpeed;
		}
		else if(1-p.soul/p.maxSoul>=0.5&&1-p.soul/p.maxSoul<0.75){
			double count = p.soulGemCounter%8;
			p.soulGemImage=p.soulGemSprite.grabImage(2,2+(int)count,Player.soulGemWidth, Player.soulGemHeight);
			p.soulGemCounter+=p.soulGemAnimationSpeed;
		}
		else if(1-p.soul/p.maxSoul>=0.75&&1-p.soul/p.maxSoul<1){
			double count = p.soulGemCounter%8;
			p.soulGemImage=p.soulGemSprite.grabImage(3,2+(int)count,Player.soulGemWidth, Player.soulGemHeight);
			p.soulGemCounter+=p.soulGemAnimationSpeed;
		}
		else if(1-p.soul/p.maxSoul==1){
			p.soulGemImage=p.soulGemSprite.grabImage(2, 1, Player.soulGemWidth, Player.soulGemHeight);
		}
	}
	
	/**
	Displays .gif animaions based on their corresponding orientations
	@param moveable object o
	@return animation of gif
	*/
	public static void animateWithGif(MovableObject o){
		if(o.getAnimation()==ANIMATION.MOVELEFT) {
			o.setImage(o.getAnimationParameters().getWalkGif().getImage());
			o.setImageWidth(-o.getAnimationParameters().getWalkGif().getWidth());
			o.setImageHeight(o.getAnimationParameters().getWalkGif().getHeight());
			o.renderXShift=o.getAnimationParameters().getWalkGif().getX()+o.getAnimationParameters().getWalkGif().getWidth();
			o.renderYShift=o.getAnimationParameters().getWalkGif().getY();
		}
		else if(o.getAnimation()==ANIMATION.MOVERIGHT) {
			o.setImage(o.getAnimationParameters().getWalkGif().getImage());
			o.setImageWidth(o.getAnimationParameters().getWalkGif().getWidth());
			o.setImageHeight(o.getAnimationParameters().getWalkGif().getHeight());
			o.renderXShift=o.getAnimationParameters().getWalkGif().getX();
			o.renderYShift=o.getAnimationParameters().getWalkGif().getY();
		}
		else if(o.getAnimation()==ANIMATION.MOVEUP||o.getAnimation()==ANIMATION.MOVEDOWN){
			if(o.getFacing()==FACING.RIGHT){
				o.setImage(o.getAnimationParameters().getWalkGif().getImage());
				o.setImageWidth(o.getAnimationParameters().getWalkGif().getWidth());
				o.setImageHeight(o.getAnimationParameters().getWalkGif().getHeight());
				o.renderXShift=o.getAnimationParameters().getWalkGif().getX();
				o.renderYShift=o.getAnimationParameters().getWalkGif().getY();
			}
			else{
				o.setImage(o.getAnimationParameters().getWalkGif().getImage());
				o.setImageWidth(-o.getAnimationParameters().getWalkGif().getWidth());
				o.setImageHeight(o.getAnimationParameters().getWalkGif().getHeight());
				o.renderXShift=o.getAnimationParameters().getWalkGif().getX()+o.getAnimationParameters().getWalkGif().getWidth();
				o.renderYShift=o.getAnimationParameters().getWalkGif().getY();
			}
		}
		else if(o.getAnimation()==ANIMATION.STAND) {
			if(o.getFacing()==FACING.RIGHT){
				o.setImage(o.getAnimationParameters().getStandGif().getImage());
				o.setImageWidth(o.getAnimationParameters().getStandGif().getWidth());
				o.setImageHeight(o.getAnimationParameters().getStandGif().getHeight());
				o.renderXShift=o.getAnimationParameters().getStandGif().getX();
				o.renderYShift=o.getAnimationParameters().getStandGif().getY();
			}
			else{
				o.setImage(o.getAnimationParameters().getStandGif().getImage());
				o.setImageWidth(-o.getAnimationParameters().getStandGif().getWidth());
				o.setImageHeight(o.getAnimationParameters().getStandGif().getHeight());
				o.renderXShift=o.getAnimationParameters().getStandGif().getX()+o.getAnimationParameters().getStandGif().getWidth();
				o.renderYShift=o.getAnimationParameters().getStandGif().getY();
			}
		}
		else if(o.getAnimation()==ANIMATION.DAMAGED){
			if(o.getFacing()==FACING.RIGHT){
				o.setImage(o.getAnimationParameters().getDamagedGif().getImage());
				o.setImageWidth(o.getAnimationParameters().getDamagedGif().getWidth());
				o.setImageHeight(o.getAnimationParameters().getDamagedGif().getHeight());
				o.renderXShift=o.getAnimationParameters().getDamagedGif().getX();
				o.renderYShift=o.getAnimationParameters().getDamagedGif().getY();
			}
			else{
				o.setImage(o.getAnimationParameters().getDamagedGif().getImage());
				o.setImageWidth(-o.getAnimationParameters().getDamagedGif().getWidth());
				o.setImageHeight(o.getAnimationParameters().getDamagedGif().getHeight());
				o.renderXShift=o.getAnimationParameters().getDamagedGif().getX()+o.getAnimationParameters().getDamagedGif().getWidth();
				o.renderYShift=o.getAnimationParameters().getDamagedGif().getY();
			}
		}
		else if(o.getAnimation()==ANIMATION.DYING){
			if(o.getFacing()==FACING.RIGHT){
				o.setImage(o.getAnimationParameters().getDeathGif().getImage());
				o.setImageWidth(o.getAnimationParameters().getDeathGif().getWidth());
				o.setImageHeight(o.getAnimationParameters().getDeathGif().getHeight());
				o.renderXShift=o.getAnimationParameters().getDeathGif().getX();
				o.renderYShift=o.getAnimationParameters().getDeathGif().getY();
			}
			else{
				o.setImage(o.getAnimationParameters().getDeathGif().getImage());
				o.setImageWidth(-o.getAnimationParameters().getDeathGif().getWidth());
				o.setImageHeight(o.getAnimationParameters().getDeathGif().getHeight());
				o.renderXShift=o.getAnimationParameters().getDeathGif().getX()+o.getAnimationParameters().getDeathGif().getWidth();
				o.renderYShift=o.getAnimationParameters().getDeathGif().getY();
			}
		}
	}

	/*
	public static void animateWithGif(Player_Kyouko o){
		if(o.animation==ANIMATION.MOVELEFT) {
			o.image=o.moveLeftGif;
			o.imageWidth=120;
			o.imageHeight=60;
			o.renderXShift=-40;
			o.renderYShift=-20;
		}
		else if(o.animation==ANIMATION.MOVERIGHT) {
			o.image=o.moveRightGif;
			o.imageWidth=120;
			o.imageHeight=60;
			o.renderXShift=-40;
			o.renderYShift=-20;
		}
		//else if(o.animation==ANIMATION.STAND) o.image=o.standGif;
		else if(o.animation==ANIMATION.JUMPRIGHT) o.image=o.jumpRightGif;
		else if(o.animation==ANIMATION.JUMPLEFT) o.image=o.jumpLeftGif;
		else if(o.animation==ANIMATION.JUMPUP) o.image=o.jumpUpGif;
		else if(o.animation==ANIMATION.JUMPDOWN) o.image=o.jumpDownGif;
		else if(o.animation==ANIMATION.UPATTACK) o.image=o.upAttackGif;
	}
	*/

}
