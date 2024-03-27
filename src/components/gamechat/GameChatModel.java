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
	 * Player number
	 */
	private int playerNumber = 1;
	
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
	 * @return message m
	 */
	public String getMessage() {
		return messages.getLast();
	}
	
	/**
	 * Setter for player number
	 * @param playerNumber n
	 */
	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}
	
	/**
	 * Getter for player number
	 * @return num
	 */
	public int getPlayerNumber() {
		return playerNumber;
	}

}
