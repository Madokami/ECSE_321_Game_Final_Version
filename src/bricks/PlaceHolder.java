package bricks;

import game.Game;

/**
* <b>Description:</b>
* <br>
* PlaceHolder class that extends Brick
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class PlaceHolder extends Brick{

	/**
	 * sets place holder
	 * @param coordinates, game object
	 * @return place holder
	 */
	public PlaceHolder(int x, int y, Game game) {
		super(x, y, game);
		// TODO Auto-generated constructor stub
		setImage(null);
	}

}
