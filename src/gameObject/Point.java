package gameObject;

/**
* <b>Description:</b>
* <br>
* Defines point on grid map
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class Point {
	private int x;
	private int y;
	
	/**
	 * defines point
	 * @param x and y coordinates
	 */
	public Point(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	//getX
	public int getX(){
		return x;
	}
	
	//getY
	public int getY(){
		return y;
	}
}
