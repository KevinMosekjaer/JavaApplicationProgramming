package components.gamechat;

import java.util.LinkedList;

/**
 * Class holds game chat model
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
	private int myPlayerNumber, otherPlayerNumber, currentPlayer=1, playerNumber=1;

	/**
	 * player names
	 */
	private String myPlayerName, otherPlayerName;


	/**
	 * Constructor
	 */
	public GameChatModel() {

	}

	/**
	 * Function to add message to the LL
	 * @param message message
	 */
	public void addMessage(String message) {
		messages.add(message);
	}

	/**
	 * Function to get last element, ie. message from LL
	 * @return message m
	 */
	public String getMessage() {
		return messages.getLast();
	}

	/**
	 * Setter for player number
	 * @param num n
	 */
	public void setPlayerNumber(int num) {
		this.playerNumber=num;
	}

	/**
	 * Getter for player number
	 * @return num
	 */
	public int getPlayerNumber() {
		return playerNumber;
	}

	/**
	 * Setter for this player number
	 * @param playerNumber n
	 */
	public void setMyPlayerNumber(int playerNumber) {
		this.myPlayerNumber = playerNumber;
	}

	/**
	 * Getter for this player number
	 * @return num
	 */
	public int getMyPlayerNumber() {
		return myPlayerNumber;
	}

	/**
	 * Setter for player names
	 * @param playerNumber p
	 * @param name n
	 */
	public void setPlayerName(int playerNumber, String name) {
		if(playerNumber == myPlayerNumber) {
			this.myPlayerName = name;
		} else {
			this.otherPlayerName = name;
		}
	}

	/**
	 * Getter for player names
	 * @param playerNumber p
	 * @return name n
	 */
	public String getPlayerName(int playerNumber) {
		if(playerNumber == myPlayerNumber) {
			return myPlayerName;
		} else {
			return otherPlayerName;
		}
	}
}
