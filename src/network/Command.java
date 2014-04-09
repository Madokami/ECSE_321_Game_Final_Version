package network;

/**
* <b>Description:</b>
* <br>Command is executed based on input key
* @author Team 6
* @version 1.0
* @since 2014-03-31
*/
public class Command {
	private String command;
	private int key;
	
	/**
	 * defines command
	 */
	public Command(String command,int key){
		this.command = command;
		this.key = key;
	}
	
	public String getCommand(){
		return this.command;
	}
	
	public int getKey(){
		return this.key;
	}
}
