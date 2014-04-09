package network;
/**
* Description:
* Game server
* @author Team 6
* @version 1.0
* @since 2014-03-27
*/
import game.Game;
import gameObject.GameObject;
import gameObject.MovableObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import system.GameSystem;
import menu.MenuChar;

public class Server implements Runnable
{
	private Game game;
	
	public static String ss;
	private String commands;
	private int index;
	public void run() 
	{
		init();
	}
	public Server(Game game){
		this.game=game;
	}
	
	/**
	 * initialize game server information
	 */
	public void init() 
	{
		try
		{
		int port=8899;
		ServerSocket server=new ServerSocket(port);
		Socket socket=server.accept();
		InputStreamReader reader=new InputStreamReader(socket.getInputStream());
		boolean end=false;
		char[] chars=new char[1000];
		String temp=null;
		int length = 0;
		
		System.out.println("server started");
		while (!end){
			//System.out.println("server is running");
				//System.out.println(length);
				length=reader.read(chars);
			//System.out.println("checkpoint 0");
			if(length<=0){
				temp="";
			}
			else{
				temp=new String(chars,0,length);
			}
			//System.out.println("checkpoint 1");
			System.out.println(temp);
			
			if(commands==null){
				commands=temp;
			}
			else{
				commands=commands.concat(temp);
			}
			//System.out.println("checkpoint 2");
			
			while (commands.indexOf("!")!=-1&&commands.indexOf(";")!=-1&&commands.indexOf(";")>commands.indexOf("!"))
			{
				String oneCommand=commands.substring(commands.indexOf("!")+1,commands.indexOf(";"));
				System.out.println(commands);
				commands=commands.substring(commands.indexOf(";")+1, commands.length());
				//handle oneCommand here
				
				//System.out.println(oneCommand);
				executeOneCommand(oneCommand);
				
				chars=new char[1000];
			}
			//System.out.println("checkpoint 3");

		}
		System.out.println("server is closed");
		reader.close();
		socket.close();
		server.close();
		} catch(IOException e)
		{
			
		}
	}
	
	/**
	 * execute command received from client
	 * @param oneCommand command received from client
	 */
	private void executeOneCommand(String oneCommand){
		String command=oneCommand.substring(0,oneCommand.indexOf(","));
		String indexString = oneCommand.substring(oneCommand.indexOf(",")+1,oneCommand.length());
		index = Integer.parseInt(indexString);
		GameObject target=null;
		if(index!=-1){
			target = searchForIndex(index);
		}
		if(target!=null||index==-1){
			sendCommand(command,target);
		}
	}
	
	/**
	 * Send command to game
	 * @param command command received from client
	 * @param target GameObject that executes command order
	 */
	private void sendCommand(String command,GameObject target){
		if(index!=-1){
			if(command.equals("moveRight")){
				((MovableObject) target).moveRight();
			}
			else if(command.equals("moveLeft")){
				((MovableObject) target).moveLeft();
			}
			else if(command.equals("moveUp")){
				((MovableObject) target).moveUp();
			}
			else if(command.equals("moveDown")){
				((MovableObject) target).moveDown();
			}
			else if(command.equals("moveStop")){
				((MovableObject) target).moveStop();
			}
			else if(command.equals("placeBomb")){
				target.placeBomb(((MovableObject) target).bombStrength, ((MovableObject) target).bombLength, 45);
			}
			else if(command.equals("ult")){
				((MovableObject)target).useUltimate();
			}
			else if(command.startsWith("updateX")){
				double x = Double.parseDouble(command.substring(7, command.length()));
				((MovableObject)target).setXPosition(x);
			}
			else if(command.startsWith("updateY")){
				double y = Double.parseDouble(command.substring(7, command.length()));
				((MovableObject)target).setYPosition(y);
			}
		}
		else{
			if(command.equals("sendHp")){
				MultiplayerStats.HP=MenuChar.handler.hpCur;
				
			}
			else if(command.equals("sendMp")){
				MultiplayerStats.MP=MenuChar.handler.mpCur;
			}
			else if(command.equals("sendBP")){
				MultiplayerStats.BP=MenuChar.handler.BPCur;
			}
			else if(command.equals("sendSpd")){
				MultiplayerStats.SPD=MenuChar.handler.spdCur;
			}
			else if(command.equals("sendBombs")){
				MultiplayerStats.BOMBS=MenuChar.handler.bombStrengthCur;
			}
			else if(command.equals("sendBombL")){
				MultiplayerStats.BOMBL=MenuChar.handler.bombLengthCur;
			}
			else if(command.equals("sendSoul")){
				MultiplayerStats.SOUL=MenuChar.handler.soulCur;
			}
			else if(command.equals("setMd")){
				Game.cChosenP2=Game.CHARACTER.MADOKA;
			}
			else if(command.equals("setHo")){
				Game.cChosenP2=Game.CHARACTER.HOMURA;
			}
			else if(command.equals("setSa")){
				Game.cChosenP2=Game.CHARACTER.SAYAKA;
			}
			else if(command.equals("setMa")){
				Game.cChosenP2=Game.CHARACTER.MAMI;
			}
			else if(command.equals("setKy")){
				Game.cChosenP2=Game.CHARACTER.KYOUKO;
			}
			else if(command.equals("ready")){
				GameSystem.otherPlayerIsReady=true;
			}
			else if(command.startsWith("setCurrentStage")){
				String stageString = command.substring(15, command.length());
				int stageNum = Integer.parseInt(stageString);
				//stageNum=3;
				MultiplayerStats.CURSTAGE = stageNum;
			}
		}
	}
	
	/**
	 * Search for GameObject in game
	 * @param index index of the GameObject
	 * @return the GameObject found
	 */
	private GameObject searchForIndex(int index){
		GameObject ret = null;
		if(game.getController()!=null){
			for(int i=0;i<game.getController().everything.size();i++){
				if(game.getController().everything.get(i).serialNumber==index){
					ret = game.getController().everything.get(i);
					break;
				}
			}
		}
		
		return ret;
	}
}
