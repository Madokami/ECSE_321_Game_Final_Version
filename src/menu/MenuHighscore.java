package menu;

import game.Game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import data.GameData;
import system.GameSystem;

public class MenuHighscore implements GeneralMenu{
	public BufferedImage background;
	
	MenuHighscore(){
		background = GameSystem.loader.loadImage("/image/white6.png");
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(background, 25, 10, GameSystem.GAME_WIDTH - 45, GameSystem.GAME_HEIGHT - 35, null);
		g.setFont(new Font("serif",Font.BOLD,40));
		g.drawString("High Score",350,75);
		g.setFont(new Font("serif",Font.BOLD,20));
		//g.setFont(new Font("mistral",Font.BOLD,20));
		int yShift = 35;
		for(int i=1;i<=Game.lastStage;i++){
			g.drawString("Stage "+i +": "+ GameSystem.GAME_DATA.HIGH_SCORE[i], 30, 75+i*yShift);
		}
		
	}

	@Override
	public void renderSelected(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int key) {
		if(key==GameSystem.CANCEL){
			Menu.mState=Menu.MENUSTATE.MAIN;
			GameSystem.playCancel();
		}
	}

}
