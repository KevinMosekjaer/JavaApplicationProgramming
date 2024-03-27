package main;

import components.gameboard.GameBoardModel;
import components.gamechat.GameChatModel;
import components.playerarea.PlayerAreaModel;

/**
 * Class holds main Model for game
 * 
 * @author Kevin Mosekjaer
 */
public class GameModel {
	
	/**
	 * GameChatModel object
	 */
	private GameChatModel chatModel;
	
	/**
	 * GameBoardModel object
	 */
	private GameBoardModel boardModel;
	
	/**
	 * PlayerAreaModel object
	 */
	private PlayerAreaModel playerModel1, playerModel2;
	
	/**
	 * Constructor for GameModel
	 */
	public GameModel() {
		initializeModels();
	}
	
	/**
	 * Function to initialize sub models
	 */
	public void initializeModels() {
		chatModel = new GameChatModel();
		boardModel = new GameBoardModel();
		playerModel1 = new PlayerAreaModel(1, "");
		playerModel2 = new PlayerAreaModel(2, "");
	}
	
	/**
	 * Getter for GameChatModel
	 * @return game chat model
	 */
	public GameChatModel getGameChatModel() {
		return this.chatModel;
	}
	
	/**
	 * Getter for GameBoardModel
	 * @return game board model
	 */
	public GameBoardModel getGameBoardModel() {
		return this.boardModel;
	}
	
	/**
	 * Getter for PlayerAreaModel
	 * @return Player area model 1
	 */
	public PlayerAreaModel getPlayerAreaModel1() {
	    return playerModel1;
	}

	/**
	 * Getter for PlayerAreaModel
	 * @return player area model 2
	 */
	public PlayerAreaModel getPlayerAreaModel2() {
	    return playerModel2;
	}
	
	/**
	 * Setter for player 1 name
	 * @param name name 1
	 */
	public void setPlayer1Name(String name) {
	    playerModel1.setPlayerName(name);
	}

	/**
	 * Setter for player 2 name
	 * @param name name 2
	 */
	public void setPlayer2Name(String name) {
	    playerModel2.setPlayerName(name);
	}
}
