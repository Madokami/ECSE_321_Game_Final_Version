package powerUps;

import data.SpriteData;
import player.Player;
import game.Game;

/**
* <b>Description:</b>
* <br>
* Defines PowerUp based on coordinate and effect on player
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class PowerUps_HpUp extends PowerUps{
	/**
	 * defines power ups
	* @param x coordinate X
	 * @param y coordinate Y
	 * @param game instance of Game
	 */
	public PowerUps_HpUp(int x, int y, Game game) {
		super(x, y, game);
		setSs(SpriteData.upgrades);
		setImage(getSs().grabImage(10,14,getSsWidth(),getSsHeight()));
	}


	public void applyEffect(Player player) {
		player.hp+=player.maxHp/3;
	}

}
