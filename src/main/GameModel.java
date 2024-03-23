package main;

import components.gamechat.GameChatModel;

public class GameModel {
	
	private GameChatModel chatModel;
	
	public GameModel() {
		initializeModels();
	}
	
	public void initializeModels() {
		chatModel = new GameChatModel();
	}
	
	public GameChatModel getGameChatModel() {
		return this.chatModel;
	}

}
