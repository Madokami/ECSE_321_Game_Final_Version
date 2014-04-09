package story;

/**
* Description:
* Story mode of the game
* @author Team 6
* @version 1.3
* @since 2014-04-04
*/

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



import system.GameSystem;
import system.GameSystem.STATE;
import utility.BufferedImageLoader;
import menu.Menu;
import menu.Menu.MENUSTATE;

public class Story {
	static BufferedImageLoader loader;
	private static BufferedImage spriteLeft,spriteMiddle,spriteRight;
	private static BufferedImage background,mdTextBox,textBox,naTextBox,hoTextBox,maTextBox,saTextBox,kyTextBox,qbTextBox;
	public static Rectangle helpButton = new Rectangle(GameSystem.WIDTH/2 + 120, 250,100,50);
	public static Rectangle quitButton = new Rectangle(GameSystem.WIDTH/2 + 120, 350,100,50);
	static BufferedReader br;
	int w = 320;
	int h = 400;
	static String path;
	static String[] lines = new String[4];
	static int lineNum;
	private static boolean speaking;
	
	private int shiftX,shiftY;
	
	public Story(){
		loader = new BufferedImageLoader();
		//sprite = loader.loadImage("/image/mdStand1.png");
		spriteRight = loader.loadImage("/image/talk/md_reg_happy.png");
		background = loader.loadImage("/image/tempBg.png");
		mdTextBox = loader.loadImage("/image/story/mdTextBox.png");
		textBox = loader.loadImage("/image/story/naTextBox.png");
		naTextBox = loader.loadImage("/image/story/naTextBox.png");
		hoTextBox = loader.loadImage("/image/story/hoTextBox.png");
		kyTextBox = loader.loadImage("/image/story/kyTextBox.png");
		saTextBox = loader.loadImage("/image/story/saTextBox.png");
		maTextBox = loader.loadImage("/image/story/maTextBox.png");
		spriteLeft=null;
		spriteMiddle=null;
		speaking = false;
			//path=getClass().getResource("/script/script.txt").getFile();
			//path = URLDecoder.decode(path);
		
		/*
			path = "system/script/script.txt";
			try {
				br = new BufferedReader(new FileReader(path));
				//line1 = br.readLine();
				lineNum=0;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		readNextLine();
		*/
	}
	
	/**
	 * initialize shiftX and shiftY
	 */
	public void tick() {
			shiftX=125;
			shiftY=110;
	}
	
	/**
	 * Draw story mode background images
	 * @param g current graphic
	 */
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		Font f1 = new Font("arial",Font.PLAIN,22);
		g.setFont(f1);
		g.setColor(Color.WHITE);
		//g.fillRect(0, 340, GameSystem.ABSWIDTH+10, 200);
		g.drawImage(background, 0+shiftX, 0+shiftY, null);
		g.drawImage(spriteRight,450+shiftX,87+shiftY,null);
		g.drawImage(spriteLeft,50+shiftX,87+shiftY,null);
		g2d.setColor(Color.WHITE);
		//g2d.draw(textBox);
		//g2d.fill(textBox);
		//g.drawImage(naTextBox,-90,370,900,225,null);
		if(speaking){
			g.drawImage(textBox,-50+shiftX,330+shiftY,null);
		}
		g.setColor(Color.BLACK);
		renderLines(g);
	}
	
	/**
	 * Draw story script string lines
	 * @param g current graphic
	 */
	private void renderLines(Graphics g) {
		try{
			for(int i=0;i<lineNum;i++){
				g.drawString(lines[i],120+shiftX, 408+i*20+shiftY);
			}
		}
		catch(NullPointerException e){
			try {
				br=new BufferedReader(new FileReader(path));
				lineNum=0;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			GameSystem.state=GameSystem.STATE.GAME;
		}
		
	}
	
	/**
	 * If "z" or "enter" key pressed on the keyboard, call readNextLine()
	 * If "x" key pressed, call toMenu()
	 * @param e pressed keyboard key 
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key==KeyEvent.VK_ENTER||key==KeyEvent.VK_Z){
			readNextLine();
		}
		else if(key==KeyEvent.VK_X){
			toMenu();
		}
		
	}
	
	/**
	 * read next line of story script
	 */
	private static void readNextLine(){
		if(lineNum>3){
			lineNum=0;
		}
		try{
		lines[lineNum]=br.readLine();
		}catch(Exception abc){
			System.out.println("can't read from line");
			Menu.toGameMode();
		}
		while(isSpecialString(lines[lineNum])){
			lineNum=0;
			try {
				lines[lineNum]=br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		lineNum++;
	}
	/**
	 * Check if the script line has special meaning
	 * @param line the current script line 
	 * @return a boolean indicating whether the line has special meaning
	 */
	private static boolean isSpecialString(String line){
		if(isEndOfSection(line)){
			changeToGameState();
			//also should load some file or else get nullpointer
			return true;
		}
		else if(isCharacterName(line)){
			return true;
		}
		else if(isSetSprite(line)){
			return true;
		}
		else if(isPlayAudio(line)){
			return true;
		}
		else if(isChangeBackground(line)){
			return true;
		}
		else if(line.equals("")){
			return true;
		}
		else if(line.startsWith("//")){
			return true;
		}
		return false;
	}
/**
 * Go to game state
 */
	private static void changeToGameState() {	
		GameSystem.turnOffBgm();
		Menu.toGameMode();
	}

	/**
	 * Checking if the end of a script section has reached
	 * @param s current script line
	 * @return a boolean indicating whether the end of a script section has reached
	 */
	private static boolean isEndOfSection(String s) {
		if(s.equals("END")){
			return true;
		}
		return false;
	}

	/**
	 * Checking if the script line is stating a character name
	 * @param s current script line
	 * @return a boolean indicating whether the script line is stating a character name
	 */
	private static boolean isCharacterName(String s) {
		if(s.equals("MADOKA:")){
			textBox=mdTextBox;
			speaking = true;
			return true;
		}
		else if(s.equals("HOMURA:")){
			textBox=hoTextBox;
			speaking = true;
			return true;
		}
		else if(s.equals("SAYAKA:")){
			textBox=saTextBox;
			speaking = true;
			return true;
		}
		else if(s.equals("KYOUKO:")){
			textBox=kyTextBox;
			speaking = true;
			return true;
		}
		else if(s.equals("MAMI:")){
			textBox=maTextBox;
			speaking = true;
			return true;
		}
		else if(s.equals("QB:")){
			textBox=qbTextBox;
			speaking = true;
			return true;
		}
		else if(s.equals("NARRATOR:")){
			textBox=naTextBox;
			speaking = true;
			return true;
		}
		return false;
	}
	/**
	 * Checking if the script requires to set sprite
	 * @param line current script line
	 * @return a boolean indicating whether the script requires to set sprite
	 */
	private static boolean isSetSprite(String line){	
		if(line.equals("SP_R:NULL")){
			spriteRight=null;
			return true;
		}
		else if(line.equals("SP_M:NULL")){
			spriteMiddle=null;
			return true;
		}
		else if(line.equals("SP_L:NULL")){
			spriteLeft=null;
			return true;
		}
		else if(line.startsWith("SP_L:")){
			spriteLeft=loader.loadImage(line.substring(5));
			return true;
		}
		else if(line.startsWith("SP_M:")){
			spriteMiddle=loader.loadImage(line.substring(5));
			return true;
		}
		else if(line.startsWith("SP_R:")){
			spriteRight=loader.loadImage(line.substring(5));
			return true;
		}
		return false;
	}
	
	/**
	 * Checking if the script requires to play audio
	 * @param line current script line
	 * @return a boolean indicating whether the script requires to play audio
	 */
	private static boolean isPlayAudio(String line){
		if(line.equals("AUDIO_BGM:NULL")){
			GameSystem.turnOffBgm();
		}
		else if(line.startsWith("AUDIO_BGM:")){
			GameSystem.turnOnBgm(line.substring(10));
			return true;
		}
		else if(line.startsWith("AUDIO_VOICE:")){
			GameSystem.playVoice(line.substring(12));
			return true;
		}
		else if(line.startsWith("AUDIO_SOUND:")){
			GameSystem.playSound(line.substring(12));
			return true;
		}
		return false;
	}
	
	/**
	 * Checking if the script requires to change background
	 * @param line current script line
	 * @return a boolean indicating whether the script requires to change background
	 */
	public static boolean isChangeBackground(String line){
		if(line.startsWith("BG:")){
			background=loader.loadImage(line.substring(3));
			return true;
		}
		return false;
	}
	
	/**
	 * Go to  main menu
	 */
	public void toMenu(){
		GameSystem.state=STATE.MENU;
		Menu.mState=MENUSTATE.MAIN;
		
	}
	
	public static void loadFile(int level){
		path = "system/script/script";
		path = path+Integer.toString(level);
		path = path+".txt";
		try {
			br = new BufferedReader(new FileReader(path));
			//line1 = br.readLine();
			lineNum=0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Menu.toGameMode();
		}
		readNextLine();
	}
}
