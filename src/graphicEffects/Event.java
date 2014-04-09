package graphicEffects;

/**
* <b>Description:</b>
* <br>
* Defines time and duration of events
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class Event {
	private int duration;
	private int durationTimer;
	private int value;
	private boolean on;
	
	/**
	 * uses current status to determine next iteration
	 */
	public void tick(){
		durationTimer++;
		if(durationTimer>duration){
			stop();
		}
	}
	public boolean isOn(){
		return on;
	}
	/**
	 * defines start
	 * @param duration the duration of the event
	 */
	public void start(int duration){
		this.duration=duration;
		this.durationTimer=0;
		this.on=true;
	}
	/**
	 * defines start
	 * @param duration, value
	 */
	public void start(int duration,int value){
		this.duration=duration;
		this.durationTimer=0;
		this.on=true;
	}
	public void stop(){
		if(!on) return;
		on=false;
	}
	public int getValue(){
		return value;
	}
}
