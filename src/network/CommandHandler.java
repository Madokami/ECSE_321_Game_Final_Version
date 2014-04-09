package network;

import gameObject.Controller;
import gameObject.GameObject;
import gameObject.MovableObject;

import java.util.LinkedList;

import system.GameSystem;

/**
* <b>Description:</b>
* <br>Inputed command is executed based on existing conditions
* <br>Commands are inputed as Strings and then are executed accordingly
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class CommandHandler {
	private Controller controller;
	private LinkedList<Command> commands = new LinkedList<Command>();
	
	public CommandHandler(Controller controller){
		this.controller = controller;
	}
	
	/**
	 * Commands are executed based on current conditions
	 */
	public void tick(){
		readCommand();
		
		for(int i=0;i<commands.size();i++){
			excuteCommand(commands.get(i));
		}
		commands=new LinkedList<Command>();
		
		/*
		while(commands.size()>0){
			executeFirstCommand();
		}
		*/
	}
	
	public void executeFirstCommand(){
		if(commands.size()>0){
			excuteCommand(commands.get(0));
			commands.remove(0);
		}
	}

	/**
	 * Commands corresponding to different directional movements as well as stopping are executed
	 */
	public void excuteCommand(Command command){
		
		GameObject target = null;
		for(int i=0;i<controller.everything.size();i++){
			if(controller.everything.get(i).serialNumber==command.getKey()){
				target = controller.everything.get(i);
				break;
			}
		}
		
		String action = command.getCommand();
		if(action.equals("moveLeft")){
			((MovableObject) target).moveLeft();
			System.out.println("moveLeft excecuted");
		}
		else if(action.equals("moveRight")){
			((MovableObject) target).moveRight();
			//controller.getPlayer().moveRight();
			System.out.println("moveRight excecuted");
		}
		else if(action.equals("moveUp")){
			((MovableObject) target).moveUp();
			//controller.getPlayer().moveUp();
		}
		else if(action.equals("moveDown")){
			((MovableObject) target).moveDown();
			//controller.getPlayer().moveDown();
			System.out.println("moveDown excecuted");
		}
		else if(action.equals("moveStop")){
			controller.getPlayer().buttonReleased=true;
		}
	}
	
	/**
	 * Reads String command
	 */
	public void readCommand(){
		if(GameSystem.getCommand==null) return;
		
		String string = GameSystem.getCommand;
		int index;
		String keyString,action = null;
		int key = 0;
		
		while(string.length()>0){
			index = string.indexOf(",");
			action = string.substring(0, index);
			System.out.println(action);
			string=string.substring(index+1, string.length());
			System.out.println(string);
			index = string.indexOf(",");
			keyString = string.substring(0,index);
			key = Integer.parseInt(keyString);
			string = string.substring(index+1, string.length());
			if(action!=null){
				action.trim();
				Command command = new Command(action,key);
				commands.add(command);
				System.out.println("added" + action + " " + key);
			}
		}
		GameSystem.getCommand=null;
		System.out.println("while loop done");
	}
}
