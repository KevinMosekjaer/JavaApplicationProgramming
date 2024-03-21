package components.gamechat;

import java.util.LinkedList;

/**
 * 
 * @author Kevin Mosekjaer
 */
public class GameChatModel {
	
	/**
	 * LinkedList holding messages
	 */
	private LinkedList<String> messages = new LinkedList<>();
	
	/**
	 * Constructor
	 */
	public GameChatModel() {
		
	}
		
	/**
	 * Function to add message to the LL
	 * 
	 * @param message message
	 */
	public void addMessage(String message) {
		messages.add(message);
	}
	
	/**
	 * Function to get last element, ie. message from LL
	 * 
	 * @return message
	 */
	public String getMessage() {
		return messages.getLast();
	}

}
