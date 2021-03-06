package system;
/**
* Description:
* Game system
* @author Team 6
* @version 1.45
* @since 2014-04-06
*/
import game.Game;
import game.Input;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;

import data.GameData;
import data.SpriteData;
import story.Story;
import utility.BufferedImageLoader;
import menu.Menu;
import menu.PauseMenu;

public class GameSystem extends Canvas implements Runnable {
	public static int CONFIRM,CANCEL,UP,DOWN,LEFT,RIGHT,ULT,UTILITY;
	public static int CONFIRM2,CANCEL2,UP2,DOWN2,LEFT2,RIGHT2,ULT2,UTILITY2;

	public static final int GRID_SIZE = 56;
	public static final int WIDTH = 300;
	public static final int HEIGHT = WIDTH * 9 / 12;
	public static final int SCALE = 3;
	public static final int ABSWIDTH = WIDTH * SCALE;
	public static final int ABSHEIGHT = HEIGHT * SCALE;
	public static final int GAME_WIDTH = ABSWIDTH;
	public static final int GAME_HEIGHT = ABSHEIGHT - 96;
	public static final int GRIDW = GAME_WIDTH / GRID_SIZE;
	public static final int GRIDH = GAME_HEIGHT / GRID_SIZE;
	public static final String TITLE = "Magibomb";
	public static BufferedImageLoader loader = new BufferedImageLoader();

	private JFrame frame;
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage loadingImage = loader.loadImage("/image/menu/loading.png");
	private BufferedImage image = new BufferedImage(WIDTH * SCALE, HEIGHT
			* SCALE, BufferedImage.TYPE_INT_RGB);
	public static Music musicPlayer;
	public static SpriteData spriteData;

	private Menu menu;
	private Story story;
	private Game game;
	private PauseMenu pauseMenu;

	public static boolean musicOn;
	public static boolean mute = false;
	
	//used for offline 2 player mode
	public static boolean TWO_PLAYER_MODE=false;
	public static boolean PLAYER_ONE_CHOSEN=false;

	//used for LAN
	public static String getCommand=null;
	public static String sendCommand=null;
	public static String sendCommandSelf=null;
	public static boolean signalComplete=true;
	public static boolean LAN_TWO_PLAYER_MODE=false;
	public static boolean isPlayerOne = true;
	public static boolean otherPlayerIsReady=false;
	
	public static GameData GAME_DATA;
	
	public static int serialNumber;
	
	public static enum STATE {
		MENU, GAME, PAUSE, LOAD, STORY,
	};

	public static STATE state;
	
	/**
	 * initialize data
	 */
	public void init() {
		spriteData = new SpriteData();
		game = new Game(this);
		menu = new Menu(game);
		story = new Story();
		pauseMenu = new PauseMenu(game);
		// m = new Menu2(this);
		this.requestFocus();
		// bgmPlayer=new Music();
		musicPlayer = new Music();
		// event listeners
		// this.addKeyListener(new Input(this));
		this.addKeyListener(new Input(this));
		setDefaultKeyLayout();
		GAME_DATA= new GameData();
		loadGame();
		GameSystem.turnOnBgm("/sound/music/title.wav");
		state = STATE.MENU;
		
	}
	/**
	 * set default keyboard key function
	 */	
	public static void setDefaultKeyLayout(){
		CONFIRM=KeyEvent.VK_Z;
		CANCEL=KeyEvent.VK_X;
		UP=KeyEvent.VK_UP;
		DOWN=KeyEvent.VK_DOWN;
		LEFT=KeyEvent.VK_LEFT;
		RIGHT=KeyEvent.VK_RIGHT;
		ULT=KeyEvent.VK_C;
		UTILITY=KeyEvent.VK_SPACE;
	}
	/**
	 * set keyboard key function for two player mode
	 */
	public static void setTwoPlayerKeyLayout(){
		CONFIRM=KeyEvent.VK_COMMA;
		CANCEL=KeyEvent.VK_PERIOD;
		ULT=KeyEvent.VK_SLASH;
		UP=KeyEvent.VK_UP;
		DOWN=KeyEvent.VK_DOWN;
		LEFT=KeyEvent.VK_LEFT;
		RIGHT=KeyEvent.VK_RIGHT;
		UTILITY=KeyEvent.VK_CONTROL;
		
		CONFIRM2=KeyEvent.VK_Z;
		CANCEL2=KeyEvent.VK_X;
		ULT2=KeyEvent.VK_C;
		UP2=KeyEvent.VK_W;
		DOWN2=KeyEvent.VK_S;
		LEFT2=KeyEvent.VK_A;
		RIGHT2=KeyEvent.VK_D;
		UTILITY2=KeyEvent.VK_SPACE;
	}
	
	/**
	 * execute start() and init()
	 * @param args command args input
	 */
	public static void main(String[] args) {
		GameSystem sys = new GameSystem();
		sys.start();
		sys.init();
		
	}

	public GameSystem() {
		setMinimumSize(new Dimension(ABSWIDTH, ABSHEIGHT));
		setMaximumSize(new Dimension(ABSWIDTH, ABSHEIGHT));
		setPreferredSize(new Dimension(ABSWIDTH, ABSHEIGHT));
		// this.setSize(WIDTH*SCALE, HEIGHT*SCALE);
		frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setLayout(new BorderLayout());
		frame.setLayout(new BorderLayout());
		// frame.setSize(WIDTH*SCALE, HEIGHT*SCALE);
		// frame.add(this,BorderLayout.CENTER);
		frame.add(this, BorderLayout.CENTER);
		// frame.add(content,BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		musicOn = false;
	}
	/**
	 * Run timer
	 */
	public void run() {
		long lastTime = System.nanoTime();
		final double amountOfTicks = 30.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta = delta + (now - lastTime) / ns;
			lastTime = now;
			if (delta > 1) {
				tick();
				render();
				updates++;
				delta--;
			}

			//render();

			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println(updates + "ticks, frame" + frames);
				// System.out.println(p.curX+" "+p.curY);
				updates = 0;
				frames = 0;
			}
		}
		stop();

	}
	/**
	 * call tick() method depending on game state
	 */
	private void tick() {
		if (state == STATE.GAME) {
			game.tick();
		} else if (state == STATE.MENU) {
			menu.tick();
		} else if (state == STATE.STORY) {
			story.tick();
		}
		else if(state==STATE.PAUSE){
			pauseMenu.tick();
		}
	}

	/**
	 * Call render() method depending on game state
	 */
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		// /////////////////////////
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(loadingImage, GameSystem.ABSWIDTH-loadingImage.getWidth(),GameSystem.ABSHEIGHT-loadingImage.getHeight(),null);
		if (state == STATE.GAME || state == STATE.PAUSE) {
			//System.out.println(state);
			game.render(g);
			if (state == STATE.PAUSE) {
				pauseMenu.render(g);
			}
		} else if (state == STATE.MENU) {
			menu.render(g);
		} else if (state == STATE.STORY) {
			story.render(g);
		}
		
		// /////////////////////////
		g.dispose();
		bs.show();
	}

	/**
	 * start system
	 */
	private synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * stop system
	 */
	private synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(1);
	}

	/**
	 * call keyPressed() method depending on game state
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		// int save = KeyEvent.getExtendedKeyCodeForChar('s');
		if (state == STATE.MENU) {
			menu.keyPressed(key);
		}
		// in story state
		else if (state == STATE.STORY) {
			story.keyPressed(e);
		}
		// in game state
		else if (state == STATE.GAME) {
			game.keyPressed(key);
		} 
		// in pause state
		else if (state == STATE.PAUSE) {
			pauseMenu.keyPressed(key);
		}
		return;

	}
	
	/**
	 * send command to game player
	 * @param s command string
	 */
	public static synchronized void sendCommand(String s){
		if(!GameSystem.LAN_TWO_PLAYER_MODE){
			return;
		}
		
		if(GameSystem.sendCommand!=null){
			GameSystem.sendCommand=GameSystem.sendCommand.concat("!"+s+",-1;");
		}
		else{
			GameSystem.sendCommand="!"+s+",-1;";
		}
		
		if(GameSystem.sendCommandSelf!=null){
			GameSystem.sendCommandSelf=GameSystem.sendCommandSelf.concat("!"+s+",-1;");
		}
		else{
			GameSystem.sendCommandSelf="!"+s+",-1;";
		}
	}
	
	/**
	 * send command to other game player
	 * @param s command string
	 */
	public static synchronized void sendCommandToOther(String s){
		if(!GameSystem.LAN_TWO_PLAYER_MODE){
			return;
		}
		
		if(GameSystem.sendCommand!=null){
			GameSystem.sendCommand=GameSystem.sendCommand.concat("!"+s+",-1;");
		}
		else{
			GameSystem.sendCommand="!"+s+",-1;";
		}
	}

	/**
	 * call keyRelease() in game.java if in game state
	 * @param e
	 */	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (state == STATE.GAME) {
			game.keyReleased(key);
		}
	}
	/**
	 * turn on background music
	 */
	public static void turnOnBgm() {

		musicPlayer.playMusic("/sound/sisPuellaMagica.wav");
		musicOn = true;
	}
	/**
	 * this will play the .wav file indicated given the url
	 * @param url url of the music source
	 */
	public static void turnOnBgm(String url) {

		System.out.println("turnOnBgm Called");
		musicPlayer.playMusic(url);
	}

	/**
	 * turn off background music
	 */
	public static void turnOffBgm() {
		musicPlayer.stopMusic();
	}

	/**
	 * play character voice
	 * @param path source path of voice sound file
	 */
	public static void playVoice(String path) {
		musicPlayer.playVoice(path);
	}

	/**
	 * play sound
	 * @param path source path of sound file
	 */
	public static void playSound(String path) {
		musicPlayer.playSound(path);
	}
	public static void playSoundLoop(String path){
		musicPlayer.playSoundLoop(path);
	}
	public static void stopSound(){
		musicPlayer.stopSound();
	}

	/**
	 * play "switch" sound
	 */
	public static void playSwitch() {
		musicPlayer.playSound("/sound/switch1.wav");
	}

	/**
	 * play "confirm" sound
	 */
	public static void playConfirm() {
		try{
			musicPlayer.playSound("/sound/choice2.wav");
		} catch(NullPointerException e){} 
	}

	/**
	 * play "cancel" sound
	 */
	public static void playCancel() {
		musicPlayer.playSound("/sound/cancel2.wav");
	}
	/**
	 * play "error" sound
	 */
	public static void playError() {
		musicPlayer.playSound("/sound/failure1.wav");
	}
	/**
	 * set music volume
	 * @param value integer indicating music volume
	 */	
	public static void setMusicVolume(int value){
		musicPlayer.setMusicVolume(value);
	}
	
	/**
	 * load game data
	 */
	public void loadGame() {
		try {
			FileInputStream fileIn = new FileInputStream("system/save/game.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			GAME_DATA = (GameData) in.readObject();
			GAME_DATA.loadGame(game);
			menu.mChar.handler = new AttributeHandler(game);
			System.out.println("Game loaded");
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("GameData class not found");
			c.printStackTrace();
			return;
		}
	}

}
