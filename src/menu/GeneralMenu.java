package menu;
/**
* Description:
* execute menu
* @author Team 6
* @version 1.0
* @since 2014-03-27
*/
import java.awt.Graphics;

public interface GeneralMenu {
	public void tick();
	public void render(Graphics g);
	public void renderSelected(Graphics g);
	public void keyPressed(int key);
}
