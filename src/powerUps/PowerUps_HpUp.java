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
	 * @param coordinates, game object
	 */
	public PowerUps_HpUp(int x, int y, Game game) {
		super(x, y, game);
		setSs(SpriteData.upgrades);
		setImage(getSs().grabImage(10,14,getSsWidth(),getSsHeight()));
	}

	@Override
	/**
	 * increases hp by 20
	 * @param player object
	 */
	public void applyEffect(Player player) {
		player.hp+=20;
	}

}
