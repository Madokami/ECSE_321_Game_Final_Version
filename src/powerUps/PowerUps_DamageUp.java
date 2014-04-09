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
public class PowerUps_DamageUp extends PowerUps{
	/**
	 * defines power ups
	 * @param coordinates, game object
	 */
	public PowerUps_DamageUp(int x, int y, Game game) {
		super(x, y, game);
		setSs(SpriteData.upgrades);
		setImage(getSs().grabImage(9,19,getSsWidth(),getSsHeight()));
	}

	@Override
	/**
	 * increases bomb strength by 5
	 * @param player object
	 */
	public void applyEffect(Player player) {
		player.bombStrength+=5;
	}
	
}
