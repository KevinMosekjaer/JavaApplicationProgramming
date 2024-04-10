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

	private String myPlayerName, otherPlayerName;


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


	/*
	 * Need to figure out what player names to use/keep and which to get rid of
	 */

	public void setOtherPlayerNumber(int playerNumber) {
		this.otherPlayerNumber = playerNumber;
	}

	public void setCurrentPlayer(int player) {
		this.currentPlayer=player;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public int getOtherPlayerNumber() {
		return otherPlayerNumber;
	}

	public void setMyPlayerNumber(int playerNumber) {
		this.myPlayerNumber = playerNumber;
	}

	public int getMyPlayerNumber() {
		return myPlayerNumber;
	}


	public void setPlayerName(int playerNumber, String name) {
		if(playerNumber == myPlayerNumber) {
			this.myPlayerName = name;
		} else {
			this.otherPlayerName = name;
		}
	}

	public String getPlayerName(int playerNumber) {
		if(playerNumber == myPlayerNumber) {
			return myPlayerName;
		} else {
			return otherPlayerName;
		}
	}
}
