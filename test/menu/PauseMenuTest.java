package menu;

import java.awt.event.KeyEvent;

import junit.framework.Assert;
import org.junit.Test;

import system.GameSystem;
import system.GameSystem.STATE;
import game.Game;

public class PauseMenuTest {
	
	@Test
	public void pauseMenuTest() throws NullPointerException{
		GameSystem sys = new GameSystem();
		Game game = new Game(sys);
		try{
			final PauseMenu pMenu = new PauseMenu(game);
			Assert.assertEquals(game, pMenu.game);
		} catch(Exception e){
			Assert.fail();
		}
	}
	
	@Test
	public void pauseMenuKeyPressTest(){
		GameSystem sys = new GameSystem();
		Game game = new Game(sys);
		sys.state = STATE.PAUSE;
		PauseMenu pMenu = new PauseMenu(game);
		pMenu.keyPressed(KeyEvent.VK_P);
		Assert.assertEquals(pMenu.pauseState, PauseMenu.RESUME);
		Assert.assertEquals(pMenu.settingState, PauseMenu.CONTROLS);
		Assert.assertEquals(pMenu.pState, PauseMenu.PauseState.PAUSE);
		sys.state = STATE.PAUSE;
		//Chose a random selection from the menu to test
		pMenu.keyPressed(KeyEvent.VK_Z);
		Assert.assertEquals(sys.state, STATE.GAME);
		pMenu.pState = PauseMenu.PauseState.SETTINGS;
		pMenu.settingState = PauseMenu.BACK;
		pMenu.keyPressed(KeyEvent.VK_Z);
		Assert.assertEquals(pMenu.pState, PauseMenu.PauseState.PAUSE);
		Assert.assertEquals(pMenu.settingState, PauseMenu.CONTROLS);
	}
	
}
