package game;

import enemy.Enemy;
import enemy.Enemy_1_1;
import enemy.Enemy_1_2;
import enemy.Enemy_1_3;
import enemy.Enemy_2_1;
import enemy.Enemy_3_1;
import enemy.Enemy_3_2;
import enemy.Enemy_4_1;
import enemy.Enemy_4_2;
import enemy.Enemy_4_3;
import enemy.Enemy_5_1;
import enemy.Enemy_5_2;
import enemy.Enemy_Boss_1;
import enemy.Enemy_Boss_2_small;
import enemy.Enemy_Boss_3;
import enemy.Enemy_Boss_5;
import gameObject.Controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import network.MultiplayerStats;
import bricks.HitableBrick;
import bricks.PlaceHolder;
import player.Player;
import player.Player_Homura;
import player.Player_Kyouko;
import player.Player_Madoka;
import player.Player_Mami;
import player.Player_Sayaka;
import system.GameSystem;
import system.GameTimer;
import utility.BufferedImageLoader;

/**
* <b>Description:</b>
* <br>
* Loads graphics corresponding to each stage
* <br>Each level has its own designated map layout of obstacles
* <br>Enemies and characters are also loaded onto map display based on stage level
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class LevelLoader {
	Game game;
	BufferedImageLoader loader;
	String title;
	private Image gif;
	private String path;
	private long renderStageStart;
	private int duration;
	
	/**
	 * loads level
	 * @param game instance of Game
	 */
	public LevelLoader(Game game){
		this.game=game;
		loader = new BufferedImageLoader();
		//path = getClass().getResource("/witch1.gif").getFile();
		//path = URLDecoder.decode(path);
		//gif = Toolkit.getDefaultToolkit().createImage(path);
		gif=loader.loadGif("/witch1.gif");
	}
	/**
	 * loads level for two players at the same time
	 */
	public void load(){
		int stage = game.getCurLevel();
		
		
		if(GameSystem.LAN_TWO_PLAYER_MODE){
			if(!GameSystem.isPlayerOne){
				stage = MultiplayerStats.CURSTAGE;
			}
		}
		
		
		
		reset();
		if(GameSystem.TWO_PLAYER_MODE){
			GameSystem.setTwoPlayerKeyLayout();
		}
		else{
			GameSystem.setDefaultKeyLayout();
		}
		if(stage==1){
			stage1();
		}
		else if(stage==2){
			stage2();
		}
		else if(stage==3){
			stage3();
		}
		else if(stage==4){
			stage4();
		}
		else if(stage==5){
			stage5();
		}
		else if(stage==6){
			stage6();
		}
		else if(stage==7){
			stage7();
		}
		else if(stage==8){
			stage8();
		}
		else if(stage==9){
			stage9();
		}
		else if(stage==10){
			stage10();
		}
		else if(stage==11){
			stage11();
		}
		else if(stage==12){
			stage12();
		}
		updateList();
	}
	/**
	 * stage design using integers that correspond to grid images as well as music and state
	 */
	public void stage1(){
		GameSystem.turnOnBgm("/sound/music/stage1.wav");
		game.setBackground(loader.loadImage("/image/stage/ch1Bg.jpg"));
		//created the player depending on the player chosen state
		int[][] data = new int[][]{
				{2,11,0,0,0,0,2,2,0,0,0,2,2,2,3,2},
				{2,0,0,0,0,0,2,2},
				{2,2,3,3,3,0,0,0,0,0,3,3,3,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,11},
				{0,0,0,0,2,2,2,2},
				{0,0,0,0,2,2,2,3,3,0,0,0,2,2},
				{2,0,0,0,0,0,0,0,0,2},
				{2,2,2,0,0,0,0,0,0,3,0,0,0,0,3,3},
				{2,1,0,0,0,2,2,0,0,3,11,0,2,2,2,2},
				{2,0,0,9,0,2,2,0,0,3,0,0,2,2,2,2},
		};
		this.loadFromArray(data);
	}
	
	/**
	 * stage design using integers that correspond to grid images as well as music and state
	 */
	public void stage2(){
		GameSystem.turnOnBgm("/sound/music/stage1.wav");
		game.setBackground(loader.loadImage("/image/stage/ch1Bg.jpg"));
		int[][] data = new int[][]{
				{2,2,2,0,0,0,0,3,3,0,0,0,0,2,2,2},
				{2,2,11,0,0,2,0,2,2,0,2,0,12,0,2,2},
				{2,0,0,0,2,0,2,0,0,2,0,2,0,0,0,2},
				{0,0,3,2,0,2,0,2,2,0,2,0,2,3,0,0},
				{0,2,0,2,2,0,1,3,3,0,0,2,2,0,2,0},
				{0,2,0,2,2,0,0,3,3,9,0,2,2,0,2,0},
				{0,0,3,2,0,2,0,2,2,0,2,0,2,3,0,0},
				{2,12,0,0,2,0,2,0,0,2,0,2,0,0,0,2},
				{2,2,0,0,0,2,0,2,2,0,2,0,0,13,2,2},
				{2,2,2,0,0,0,0,3,3,0,0,0,0,2,2,2},

		};
		this.loadFromArray(data);
	}
	/**
	 * stage design using integers that correspond to grid images as well as music and state
	 */
	public void stage3(){
		GameSystem.turnOnBgm("/sound/music/boss1.wav");
		game.setBackground(loader.loadImage("/image/stage/ch1Bg.jpg"));
		int[][] data = new int[][]{
				{3,2,0,2,0,11,0,0,0,0,0,0,2,0,2,3},
				{2,0,2,0,2,0,0,0,0,0,0,2,0,2,0,2},
				{0,2,0,0,0,0,0,10,0,0,0,0,12,0,2,0},
				{2,0,3,0,0,0,0,0,0,0,0,0,0,3,0,2},
				{0,2,0,2,2,2,0,0,0,0,2,2,2,0,2,0},
				{0,2,0,2,2,2,0,0,0,0,2,2,2,0,2,0},
				{2,0,3,0,0,0,0,0,0,0,0,0,0,3,0,2},
				{0,2,0,0,3,3,3,3,3,3,3,3,0,0,2,0},
				{2,0,2,0,2,0,0,0,0,0,0,2,0,2,0,2},
				{3,2,0,2,1,0,0,0,0,0,0,9,2,0,2,3},

		};
		this.loadFromArray(data);
	}
	
		public void stage4(){
		GameSystem.turnOnBgm("/sound/music/stage2.wav");

		game.setBackground(loader.loadImage("/image/stage/ch1Bg.jpg"));
		int[][] data = new int[][]{
				{3,0,0,2,2,0,0,0,21,2,2,0,0,3,2,2},
				{3,1,9,2,2,0,0,0,0,2,2,0,0,3,2,2},
				{0,2,0,0,0,0,0,21,0,0,0,0,21,0,2,0},
				{2,0,3,0,0,0,0,0,0,0,0,0,0,3,0,2},
				{0,2,0,2,2,2,0,0,0,0,2,2,2,0,2,0},
				{0,2,0,2,2,2,0,0,0,0,2,2,2,0,2,0},
				{2,0,3,0,0,0,0,0,0,0,0,0,0,3,0,2},
				{0,2,0,0,3,3,0,21,0,0,3,3,0,0,2,0},
				{2,0,2,0,2,0,0,0,0,0,21,2,0,2,0,2},
				{3,2,0,2,0,0,0,0,0,0,0,0,2,0,2,3},

		};
		this.loadFromArray(data);
	}
	/**
	 * stage design using integers that correspond to grid images as well as music and state
	 */
	public void stage5(){
		GameSystem.turnOnBgm("/sound/music/stage2.wav");
		game.setBackground(loader.loadImage("/image/stage/ch1Bg.jpg"));
		int[][] data = new int[][]{
				{3,0,0,2,2,0,0,3,3,0,0,2,2,0,21,0},
				{3,1,9,2,0,2,0,0,0,0,2,0,2,0,0,0},
				{0,2,2,2,0,0,2,2,2,2,0,0,2,2,2,0},
				{0,2,0,0,2,0,0,3,3,0,0,2,0,0,2,0},
				{0,0,2,0,0,2,0,0,21,0,2,0,0,2,0,0},
				{0,0,2,0,0,2,0,0,0,0,2,0,0,2,0,0},
				{0,2,0,0,2,0,0,3,3,0,0,2,0,0,2,0},
				{0,2,0,2,0,0,2,2,2,2,0,0,2,21,2,0},
				{3,21,0,2,0,2,0,0,0,0,2,0,2,0,2,0},
				{3,0,0,2,2,0,0,3,3,0,0,2,2,0,0,0},

		};
		this.loadFromArray(data);
	}
	/**
	 * stage design using integers that correspond to grid images as well as music and state
	 */
	public void stage6(){
		GameSystem.turnOnBgm("/sound/music/boss1.wav");
		game.setBackground(loader.loadImage("/image/stage/ch1Bg.jpg"));
		int[][] data = new int[][]{
				{0,1,9,2,2,0,0,2,2,0,0,2,2,0,0,0},
				{0,0,2,0,0,2,0,0,0,0,2,0,0,21,2,0},
				{0,2,0,3,2,0,0,2,2,0,0,2,3,0,2,0},
				{0,2,0,2,0,0,2,0,0,2,0,0,2,0,2,0},
				{2,2,2,2,2,0,3,20,0,3,0,2,2,2,2,2},
				{2,2,2,2,2,0,3,0,0,3,0,2,2,2,2,2},
				{0,2,0,2,0,0,2,0,0,2,0,0,2,0,2,0},
				{0,2,21,0,2,0,0,2,2,0,0,2,3,0,2,0},
				{0,0,2,0,0,2,0,0,0,0,2,0,0,21,2,0},
				{0,0,0,2,2,0,0,2,2,0,0,2,2,0,0,0},


		};
		this.loadFromArray(data);
	}
	
	/**
	 * stage design using integers that correspond to grid images as well as music and state
	 */
	public void stage7(){
		GameSystem.turnOnBgm("/sound/music/stage3.wav");
		game.setBackground(loader.loadImage("/image/stage/ch1Bg.jpg"));
		int[][] data = new int[][]{
				{1,9,0,0,2,2,2,2,2,0,0,2,2,2,2,2},
				{3,3,0,0,0,0,0,0,0,31,0,0,0,0,0,0},
				{0,2,2,2,0,0,2,3,3,2,0,0,2,2,2,2},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{2,3,3,3,3,0,0,0,2,2,2,2,2,0,0,3},
				{0,0,0,31,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,3,3,3,3,0,0,2,2,2,2,0,0,2,2},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,31,0,0},
				{2,2,2,2,2,0,0,3,3,3,3,0,0,2,2,2},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},



		};
		this.loadFromArray(data);
	}
	/**
	 * stage design using integers that correspond to grid images as well as music and state
	 */
	public void stage8(){
		GameSystem.turnOnBgm("/sound/music/stage3.wav");
		game.setBackground(loader.loadImage("/image/stage/ch1Bg.jpg"));
		int[][] data = new int[][]{
				{2,2,2,2,2,2,2,2,2,2,2,3,3,3,2,2},
				{1,9,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{0,0,2,3,3,3,2,2,2,2,2,2,2,2,0,2},
				{0,0,2,0,0,31,0,0,0,0,0,0,0,2,0,2},
				{0,0,3,0,2,2,2,2,3,3,3,3,0,2,0,2},
				{0,0,3,0,2,0,0,0,0,0,0,2,0,2,0,2},
				{31,0,3,0,2,0,2,2,2,2,2,2,0,2,32,3},
				{0,0,2,0,2,0,32,0,0,0,0,0,0,2,0,3},
				{3,0,2,0,2,3,3,3,3,2,2,2,2,2,0,2},
				{3,0,3,0,0,0,0,0,0,0,0,0,0,0,0,2},

		};
		this.loadFromArray(data);
	}
	/**
	 * stage design using integers that correspond to grid images as well as music and state
	 */
	public void stage9(){
		GameSystem.turnOnBgm("/sound/music/boss1.wav");
		game.setBackground(loader.loadImage("/image/stage/ch1Bg.jpg"));
		int[][] data = new int[][]{
				{1,9,0,2,0,2,2,2,2,2,2,0,2,0,0,31},
				{0,0,0,2,0,2,2,2,2,2,2,0,2,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{2,2,0,0,0,0,0,0,0,0,0,0,0,0,2,2},
				{0,0,0,2,0,0,0,30,0,0,0,0,2,0,0,0},
				{0,0,0,2,0,0,0,0,0,0,0,0,2,0,0,0},
				{2,2,0,0,0,0,0,0,0,0,0,0,0,0,2,2},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,2,0,2,2,2,2,2,2,0,2,0,0,0},
				{32,0,0,2,0,2,2,2,2,2,2,0,2,0,0,31},
		};
		this.loadFromArray(data);
	}
	/**
	 * stage design using integers that correspond to grid images as well as music and state
	 */
	public void stage10(){
		GameSystem.turnOnBgm("/sound/music/stage5.wav");
		game.setBackground(loader.loadImage("/image/stage/ch1Bg.jpg"));
		int[][] data = new int[][]{
				{9,1,0,0,0,0,0,0,0,0,0,2,0,0,0,0},
				{3,3,0,0,3,3,3,3,2,2,2,2,2,0,51,0},
				{3,3,0,0,2,0,0,0,0,0,0,2,0,0,0,0},
				{3,3,0,2,0,0,0,0,0,0,0,2,0,0,0,0},
				{3,3,0,0,2,0,0,0,0,0,0,2,0,0,3,0},
				{2,2,0,2,0,52,0,2,0,2,2,2,0,0,3,0},
				{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,2,2,2,2,0,0,0,0,0,0,2,2,2},
				{51,0,0,0,0,2,2,2,0,0,0,0,0,0,0,2},
				{2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,2},
		};
		this.loadFromArray(data);
	}
	/**
	 * stage design using integers that correspond to grid images as well as music and state
	 */
	public void stage11(){
		GameSystem.turnOnBgm("/sound/music/stage5.wav");
		game.setBackground(loader.loadImage("/image/stage/ch1Bg.jpg"));
		int[][] data = new int[][]{
				{2,2,0,0,0,3,3,0,0,0,0,0,2,2,0,0},
				{2,2,0,0,0,0,0,0,2,2,2,0,2,2,0,0},
				{51,0,3,3,3,3,0,0,0,0,0,0,51,0,0,0},
				{0,0,3,3,3,3,0,0,0,0,3,3,0,0,0,0},
				{0,0,0,0,0,2,2,0,0,0,3,3,0,0,2,2},
				{3,3,0,0,0,2,2,0,0,0,3,3,0,0,2,2},
				{0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,0},
				{2,0,0,2,2,2,2,0,0,0,0,0,2,2,0,0},
				{2,9,0,0,0,0,0,0,0,0,0,0,2,2,0,0},
				{0,1,3,3,3,3,0,0,0,0,52,0,2,2,2,2},
		};
		this.loadFromArray(data);
	}
	/**
	 * stage design using integers that correspond to grid images as well as music and state
	 */
	public void stage12(){
		GameSystem.turnOnBgm("/sound/music/boss2.wav");
		game.setBackground(loader.loadImage("/image/stage/ch1Bg.jpg"));
		int[][] data = new int[][]{
				{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,52,0,0,0,0,50,0,0,0,0,0,0,0,2},
				{2,0,3,3,0,0,0,0,0,0,0,51,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,3,3,3,3,0,0,2},
				{2,0,0,0,1,0,0,0,0,0,0,9,0,0,0,2},
				{2,0,0,0,3,3,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,3,3,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
		};
		this.loadFromArray(data);
	}
	
	/**
	 * resets stage
	 */
	public void reset(){
		GameSystem.otherPlayerIsReady=false;
		GameSystem.serialNumber=0;
		Game.TITLE_DONE=false;
		GameTimer.resetTimer();
		
		Player.SCORE=0;
		game.setVictory(false);
		game.setController(new Controller(game));
		game.setPlayerIsAlive(true);
		game.setEnemyCount(0);
	}
	
	/**
	 * List of different game objects are updated
	 * <br>ex. projectiles, bricks, etc.
	 */
	public void updateList(){
		game.setBombList(game.getController().getBList());
		game.setEnemyList(game.getController().getEList());
		game.setBrickList(game.getController().getBrickList());
		game.setPlaceHolderList(game.getController().getPlaceHolderList());	
		game.setPowerUpList(game.getController().getPList());
		game.setFireList(game.getController().fireList);
		game.setProjectileList(game.getController().getProjectileList());
		
		game.setWallArray(game.getController().getWallArray());
		game.setBombArray(game.getController().getBombArray());
	}
	
	/**
	 * renders start
	 * @param duration time the title is rendered
	 */
	public void renderStart(int duration){
		this.renderStageStart = System.currentTimeMillis();
		this.duration = duration;
	}
	
	/**
	 * renders graphics
	 * @param g graphic object
	 */
	public void render(Graphics g){
		title = "Stage".concat(" ").concat(Integer.toString(game.getCurLevel()));
		g.drawImage(gif, 100, 100, null);
		g.setFont(new Font("arial",Font.BOLD,32));
		g.setColor(Color.RED);
		g.drawString(title, 270, 240);
		if(System.currentTimeMillis()-renderStageStart>duration){
			Game.TITLE_DONE=true;
			if(GameSystem.LAN_TWO_PLAYER_MODE){
				Game.TITLE_DONE=true;
				GameSystem.sendCommandToOther("ready");
			}
		}
	}
	
	/**
	 * Creates player from one of the five character classes
	 * @param i x location of the GameObject to be created
	 * @param j y location of the GameObject to be created
	 */
	private void createPlayer(int i, int j) {
		if(game.cChosen==Game.CHARACTER.MADOKA){
			game.getController().createPlayer(new Player_Madoka(i,j,game));
		}
		else if(game.cChosen==Game.CHARACTER.HOMURA){
			game.getController().createPlayer(new Player_Homura(i,j,game));
		}
		else if(game.cChosen==Game.CHARACTER.SAYAKA){
			game.getController().createPlayer(new Player_Sayaka(i,j,game));
		}
		else if(game.cChosen==Game.CHARACTER.MAMI){
			game.getController().createPlayer(new Player_Mami(i,j,game));
		}
		else if(game.cChosen==Game.CHARACTER.KYOUKO){
			game.getController().createPlayer(new Player_Kyouko(i,j,game));
		}
	}
	
	/**
	 * Creates player2 from one of the five character classes
	 * @param i x location of the GameObject to be created
	 * @param j y location of the GameObject to be created
	 */
	private void createPlayer2(int i, int j) {
		if(game.cChosenP2==Game.CHARACTER.MADOKA){
			game.getController().createPlayer(new Player_Madoka(i,j,game));
		}
		else if(game.cChosenP2==Game.CHARACTER.HOMURA){
			game.getController().createPlayer(new Player_Homura(i,j,game));
		}
		else if(game.cChosenP2==Game.CHARACTER.SAYAKA){
			game.getController().createPlayer(new Player_Sayaka(i,j,game));
		}
		else if(game.cChosenP2==Game.CHARACTER.MAMI){
			game.getController().createPlayer(new Player_Mami(i,j,game));
		}
		else if(game.cChosenP2==Game.CHARACTER.KYOUKO){
			game.getController().createPlayer(new Player_Kyouko(i,j,game));
		}
	}
	
	private void assignPlayer(){
		game.setPlayer(game.getController().getPlayer());
	}
	private void assignPlayer2(){
		game.setPlayer2(game.getController().getPlayer());
	}
	
	/**
	 * Loads map graphics data
	 * @param mapData 2 dimensional int array resembling the map
	 */
	private void loadFromArray(int[][] mapData){
		for(int i=0;i<mapData.length;i++){
			for(int j=0;j<mapData[i].length;j++){
				
				if(mapData[i][j]==1){
					if(GameSystem.LAN_TWO_PLAYER_MODE){
						if(GameSystem.isPlayerOne){
							createPlayer(j+1,i+1);
							assignPlayer();
						}
						else{
							createPlayer2(j+1,i+1);
							assignPlayer2();
						}
					}
					else{
						createPlayer(j+1,i+1);
						assignPlayer();
					}
					
				}
				
				else if(mapData[i][j]==9){
					if(GameSystem.LAN_TWO_PLAYER_MODE){
						if(!GameSystem.isPlayerOne){
							createPlayer(j+1,i+1);
							assignPlayer();
						}
						else{
							createPlayer2(j+1,i+1);
							assignPlayer2();
						}
					}
					else if(GameSystem.TWO_PLAYER_MODE){
						createPlayer2(j+1,i+1);
						assignPlayer2();
					}
				}
				
				else if(mapData[i][j]==11){
					addEnemy(new Enemy_1_1(j+1,i+1,game));
				}
				else if(mapData[i][j]==12){
					addEnemy(new Enemy_1_2(j+1,i+1,game));
				}
				else if(mapData[i][j]==13){
					addEnemy(new Enemy_1_3(j+1,i+1,game));
				}
				else if(mapData[i][j]==10){
					addEnemy(new Enemy_Boss_1(j+1,i+1,game));
				}
				else if(mapData[i][j]==21){
					addEnemy(new Enemy_2_1(j+1,i+1,game));
				}
				else if(mapData[i][j]==20){
					addEnemy(new Enemy_Boss_2_small(j+1,i+1,game));
				}
				else if(mapData[i][j]==31){
					addEnemy(new Enemy_3_1(j+1,i+1,game));
				}
				else if(mapData[i][j]==32){
					addEnemy(new Enemy_3_2(j+1,i+1,game));
				}
				else if(mapData[i][j]==30){
					addEnemy(new Enemy_Boss_3(j+1,i+1,game));
				}
				else if(mapData[i][j]==41){
					addEnemy(new Enemy_4_1(j+1,i+1,game));
				}
				else if(mapData[i][j]==42){
					addEnemy(new Enemy_4_2(j+1,i+1,game));
				}
				else if(mapData[i][j]==43){
					addEnemy(new Enemy_4_3(j+1,i+1,game));
				}
				
				else if(mapData[i][j]==51){
					addEnemy(new Enemy_5_1(j+1,i+1,game));
				}
				else if(mapData[i][j]==52){
					addEnemy(new Enemy_5_2(j+1,i+1,game));
				}
				else if(mapData[i][j]==50){
					addEnemy(new Enemy_Boss_5(j+1,i+1,game));
				}
				else if(mapData[i][j]==2){
					addBrick(new HitableBrick(j+1,i+1,game));
				}
				else if(mapData[i][j]==3){
					addPlaceHolder(new PlaceHolder(j+1,i+1,game));
				}
			}
		}
	}
	
	private void addEnemy(Enemy e){
		game.getController().addEntity(e);
		game.setEnemyCount(game.getEnemyCount() + 1);
	}
	private void addBrick(HitableBrick b){
		game.getController().addEntity(b);
	}
	private void addPlaceHolder(PlaceHolder b){
		game.getController().addEntity(b);
	}
}
