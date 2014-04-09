package menu;
/**
* Description:
* execute main menu
* @author Team 6
* @version 1.45
* @since 2014-04-06
*/
import game.Game;
import game.Game.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import network.Client;
import story.Story;
import system.GameSystem;
import system.GameSystem.STATE;
//Note: if the Menu class get too fat with information and gets confusing, you should create
//additional classes
public class Menu {
	//you can delete these 3 Rectangle objects. They are only used to track the position of text
	//However, I have changed menu input from mouse to keyboard because keyboard is a lot easier to code.
	public MenuDeath mDeath;
	public MenuChar mChar;
	public MenuScore mScore;
	//public MenuNetwork mNetwork;
	public MenuDifficulty mDifficulty;
	public MenuTwoPlayer mTwoPlayer;
	public MenuHighscore mHighscore;
	
	public static BufferedImage logo;
	public BufferedImage startImage,startImageOn,quitImage,quitImageOn,networkImage,networkImageOn;
	
	
	public static int X_START = 70;
	public static int Y_START = GameSystem.ABSHEIGHT-80;
	public static int POINTER_X_START,POINTER_Y_START;
	public static int SPACING = 47;
	
	
	private static String myIp="192.168.0.101";
	
	private static String self="127.0.0.1";
	
	Thread client;
	Thread clientSelf;
	
	//the bufferedImageLoader is something that I made to save steps in loading images.
	BufferedImage start;
	BufferedImage menu;
	BufferedImage help;
	public static BufferedImage pointer;
	
	public Game game;
	Image gif;
	
	/**
	 * used to track state of game and change what gets rendered;
	 *enum: only 1 state can be true at a time.
	 *
	 */
	public static enum MENUSTATE{
		MAIN,
		CHOOSECHAR,
		SAVE,
		LOAD,
		SETTING,
		DEATH,
		SCORE,
		NETWORK,
		DIFFICULTY,
		TWO_PLAYERS
	};
	
	/**
	 * determines what menu option is currently selected by the user
	 *"selected" options will light up
	 */
	public static enum SELECTED{
		START,
		NETWORK,
		QUIT,
	};
	
	//Initializes the two states.
	public static SELECTED selected = SELECTED.START;
	public static MENUSTATE mState = MENUSTATE.MAIN;
	
	public Menu(Game game){
		this.game=game;
		
		mChar = new MenuChar(game);
		mDeath = new MenuDeath();
		mScore = new MenuScore();
		//mNetwork = new MenuNetwork();
		mDifficulty = new MenuDifficulty();
		mTwoPlayer = new MenuTwoPlayer();
		mHighscore = new MenuHighscore();

		menu=GameSystem.loader.loadImage("/image/menu/test.png");
		pointer=GameSystem.loader.loadImage("/image/menu/griefSeed2.png");
		logo=GameSystem.loader.loadImage("/image/menu/magibomb.png");
		startImage=GameSystem.loader.loadImage("/image/menu/start2.png");
		startImageOn=GameSystem.loader.loadImage("/image/menu/start.png");
		quitImage=GameSystem.loader.loadImage("/image/menu/quit2.png");
		quitImageOn=GameSystem.loader.loadImage("/image/menu/quit.png");
		networkImage=GameSystem.loader.loadImage("/image/menu/network2.png");
		networkImageOn=GameSystem.loader.loadImage("/image/menu/network.png");
		
		POINTER_X_START=X_START-pointer.getWidth();
		POINTER_Y_START=Y_START-pointer.getHeight();
	}
	
	/**this method is called automatically by GameSystem
	* use this method to update variables continuously
	* this method will be called exactly 30 times per second.
	*/
	public void tick(){
		SPACING=300;
		if(mState==MENUSTATE.CHOOSECHAR){
			mChar.tick();
		}
		else if(mState==MENUSTATE.DEATH){
			mDeath.tick();
		}
	}
	
	/**
	 * this method draws stuff. this method will only activate when GameSystem.state is set to MENU
	 * this method will be called continuously. 
	 * NOTE: if you need to update a variable for example: update a position variable every time the game loop finish, do it in tick()
	 * because tick() is designed to be ran 60 times per second, and if it lags during intense times, 
     	 * it will be ran bonus times later to make up for the lack of update.
	 * on the other hand, the render method has no restrictions on how many times it's ran per second.
	 * @param g current graphic
	 */
	public void render(Graphics g){
		Font f1 = new Font("arial",Font.BOLD,50);
		g.setFont(f1);
		g.setColor(Color.BLACK);
		g.drawImage(menu, 0, 0,GameSystem.ABSWIDTH+10,GameSystem.ABSHEIGHT+10, null);
		//could omit the width and height in drawString or drawImage
		//g.drawString(GameSystem.TITLE, GameSystem.ABSWIDTH/3, 100);
		g.drawImage(logo, GameSystem.ABSWIDTH/18, GameSystem.ABSHEIGHT/20, null);
		if(mState==MENUSTATE.MAIN){
			
			//g.drawString("Story", X_START-5, Y_START);
			//g.drawString("Arcade", X_START-2+SPACING, Y_START);
			g.drawImage(startImage,X_START, Y_START,null);
			g.drawImage(networkImage,X_START+SPACING, Y_START,null);
			g.drawImage(quitImage,X_START+2*SPACING, Y_START,null);
			//g.drawString("Quit", X_START-2+3*SPACING, Y_START);
		}
		//make sure renderSelected is under renderCurrentState or else you won't see the changes
		renderCurrentState(g);
		renderSelected(g);
	}
	
	/**
	 * this method simply filters what to draw out based on the current Menu.SELECTED state
	 * @param g current graphic
	 */
	public void renderSelected(Graphics g){
		if(mState==MENUSTATE.MAIN){
			if(selected==SELECTED.START){
				//g.setColor(Color.LIGHT_GRAY);
				g.drawImage(startImageOn,X_START, Y_START,null);
				//g.drawImage(pointer, POINTER_X_START, POINTER_Y_START, null);
			}
			else if(selected==SELECTED.NETWORK){
				//g.setColor(Color.LIGHT_GRAY);
				g.drawImage(networkImageOn,X_START+SPACING, Y_START,null);
				//g.drawImage(pointer, POINTER_X_START+SPACING, POINTER_Y_START, null);
			}
			else if(selected==SELECTED.QUIT){
				//g.setColor(Color.LIGHT_GRAY);
				g.drawImage(quitImageOn,X_START+2*SPACING, Y_START,null);
				//g.drawImage(pointer, POINTER_X_START+2*SPACING, POINTER_Y_START, null);
			}
		}
	
	}
	
	/**
	 * change what is render based on the current MENUSTATE
	 * @param g current graphic
	 */
	public void renderCurrentState(Graphics g){
		if(mState == MENUSTATE.MAIN){
			return;
		}
		else if(mState == MENUSTATE.CHOOSECHAR){
			//draws a black background for now. Change it to something nicer :D
			mChar.render(g);
		}
		else if(mState == MENUSTATE.DEATH){
			mDeath.render(g);
		}
		else if(mState == MENUSTATE.SCORE){
			mScore.render(g);
		}
		else if(mState == MENUSTATE.NETWORK){
			mHighscore.render(g);
		}
		else if(mState == MENUSTATE.DIFFICULTY){
			mDifficulty.render(g);
		}
		else if(mState == MENUSTATE.TWO_PLAYERS){
			mTwoPlayer.render(g);
		}
		
	}
	
	/**
	 * detects user keyboard input. the parameter "key" is passed down from GameSystem.keyPressed
	 * @param key key pressed on the keyboard
	 */
	public void keyPressed(int key) {
		if(mState==MENUSTATE.CHOOSECHAR){
			mChar.keyPressed(key);
		}
		else if(mState==MENUSTATE.DEATH){
			mDeath.keyPressed(key);
		}
		else if(mState==MENUSTATE.SCORE){
			mScore.keyPressed(key);
		}
		else if(mState==MENUSTATE.NETWORK){
			mHighscore.keyPressed(key);
		}
		else if(mState == MENUSTATE.DIFFICULTY){
			mDifficulty.keyPressed(key);
		}
		else if(mState == MENUSTATE.TWO_PLAYERS){
			mTwoPlayer.keyPressed(key);
		}
		//when "down" is pressed
		else if(key==GameSystem.RIGHT){
			//if the current selected mode is story
			if(mState==MENUSTATE.MAIN){
				if(Menu.selected==Menu.SELECTED.START){
					//change the current selected mode to arcade
					Menu.selected=Menu.SELECTED.NETWORK;
					GameSystem.playSwitch();
				}
				else if(Menu.selected==Menu.SELECTED.NETWORK){
					Menu.selected=Menu.SELECTED.QUIT;
					GameSystem.playSwitch();
				}
				else if(Menu.selected==Menu.SELECTED.QUIT){
					Menu.selected=Menu.SELECTED.START;
					GameSystem.playSwitch();
				}
			}
		}
		else if(key==GameSystem.LEFT){
			if(mState==MENUSTATE.MAIN){
				if(Menu.selected==Menu.SELECTED.NETWORK){
					Menu.selected=Menu.SELECTED.START;
					GameSystem.playSwitch();
				}
				else if(Menu.selected==Menu.SELECTED.QUIT){
					Menu.selected=Menu.SELECTED.NETWORK;
					GameSystem.playSwitch();
				}
				else if(Menu.selected==Menu.SELECTED.START){
					Menu.selected=Menu.SELECTED.QUIT;
					GameSystem.playSwitch();
				}
			}
		}
		
		else if(key==GameSystem.CONFIRM){
			if(mState==MENUSTATE.MAIN){
				//if currently story mode is selected
				if(Menu.selected==Menu.SELECTED.START){
					GameSystem.playConfirm();
					toDifficulty();
				}
				//similarly if arcade mode is selected
				else if(Menu.selected==Menu.SELECTED.NETWORK){
					//change Menu state to CHOOSECHAR state.
					//StartClient.init();
					String fdaIp="132.216.48.176";
					String homeIp="192.168.0.101";
					String mcgillIp="142.157.59.166";
					if(client==null){
						client = new Thread(new Client("142.157.164.180"));
						client.start();
					}
					GameSystem.playConfirm();
					toNetwork();
				}
				else if(Menu.selected==Menu.SELECTED.QUIT){
					System.exit(0);
				}
			}
			
		}
		
	}
	
	/**
	 * switch to story mode
	 */
	public static void toStoryMode() {
		GameSystem.turnOffBgm();
		Story.loadFile(Game.curLevel);
		GameSystem.state=STATE.STORY;
	}
	
	/**
	 * switch to "choose character" menu
	 */
	public static void toChooseChar(){
		MenuChar.setChooseChar();
		MenuChar.yShift=1;
		Menu.mState=Menu.MENUSTATE.CHOOSECHAR;
	}
	
	/**
	 * switch to two players menu
	 */
	public static void toTwoPlayers(){
		Menu.mState=Menu.MENUSTATE.TWO_PLAYERS;
	}
	
	/**
	 * switch to character status menu
	 */
	public static void toCharStats(){
		MenuChar.setDisplayStats();
		Menu.mState=Menu.MENUSTATE.CHOOSECHAR;
	}
	
	/**
	 * switch to Network menu
	 */
	public static void toNetwork(){
		Menu.mState=Menu.MENUSTATE.NETWORK;
	}
	
	/**
	 * set to game mode
	 */
	public static void toGameMode() {
		GameSystem.turnOffBgm();
		Game.gState=GameState.WAIT;
		GameSystem.state=STATE.GAME;
	}
	/**
	  * back to main menu
	  * 2 ways to play background music
	  * only the first player plays the background music
	  */
	public static void backToMenu() {
		GameSystem.turnOffBgm();
		System.gc();
		GameSystem.LAN_TWO_PLAYER_MODE=false;
		GameSystem.turnOnBgm("/sound/music/title.wav");
		Menu.mState=MENUSTATE.MAIN;
		GameSystem.state=GameSystem.STATE.MENU;
		
	}
	
	/**
	 * switch to difficulty menu
	 */
	public static void toDifficulty(){
		Menu.mState=MENUSTATE.DIFFICULTY;
	}
}
