package main;

import components.gamechat.GameChat;
import components.gamechat.GameChatController;

public class GameController {
	
	private GameModel model;
	private GameView view;
	
	
	private GameChatController chatController;
	private GameChat chatView;
	
	
	

	public GameController(GameView view, GameModel model) {
		this.view = view;
		this.model = model;
		
	}
	
	public void start() {
		view.setVisible(true);
		initializeControllers();
	}
	
	public void initializeControllers() {
		//GameChat chatView = new GameChat();
		chatController = new GameChatController(model.getGameChatModel(), view.getGameChat());
	}
}
