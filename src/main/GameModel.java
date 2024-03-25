package main;

import components.gameboard.GameBoardModel;
import components.gamechat.GameChatModel;
import components.playerarea.PlayerAreaModel;

public class GameModel {
	
	private GameChatModel chatModel;
	private GameBoardModel boardModel;
	private PlayerAreaModel playerModel1, playerModel2;
	
	public GameModel() {
		initializeModels();
	}
	
	public void initializeModels() {
		chatModel = new GameChatModel();
		boardModel = new GameBoardModel();
		playerModel1 = new PlayerAreaModel(1, "");
		playerModel2 = new PlayerAreaModel(2, "");
	}
	
	public GameChatModel getGameChatModel() {
		return this.chatModel;
	}
	
	public GameBoardModel getGameBoardModel() {
		return this.boardModel;
	}
	
	public PlayerAreaModel getPlayerAreaModel1() {
	    return playerModel1;
	}

	public PlayerAreaModel getPlayerAreaModel2() {
	    return playerModel2;
	}
	
	public void setPlayer1Name(String name) {
	    playerModel1.setPlayerName(name);
	}

	public void setPlayer2Name(String name) {
	    playerModel2.setPlayerName(name);
	}
}
