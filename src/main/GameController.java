package main;

import javax.swing.JOptionPane;

import components.gameboard.GameBoardController;
import components.gamechat.GameChatController;
import components.playerarea.PlayerAreaController;

public class GameController {
	
	private GameModel model;
	
	private GameView view;
		
	private GameChatController chatController;
	
	private GameBoardController boardController;
	
	private PlayerAreaController playerController1, playerController2;
	
	
	

	public GameController(GameView view, GameModel model) {
		this.view = view;
		this.model = model;		
	}
	
	public void start() {
		view.setVisible(true);		
		promptForPlayerNames();
		initializeControllers();
		startGame();
	}
	
	public void initializeControllers() {
		chatController = new GameChatController(model.getGameChatModel(), view.getGameChat());
		boardController = new GameBoardController(model.getGameBoardModel(), view.getGameBoard());
		playerController1 = new PlayerAreaController(model.getPlayerAreaModel1(), view.getPlayerArea(1));
		playerController2 = new PlayerAreaController(model.getPlayerAreaModel2(), view.getPlayerArea(2));
	}

	private void promptForPlayerNames() {
	    String player1Name = JOptionPane.showInputDialog(view, "Enter Player 1's name:", "Player Names", JOptionPane.PLAIN_MESSAGE);
	    String player2Name = JOptionPane.showInputDialog(view, "Enter Player 2's name:", "Player Names", JOptionPane.PLAIN_MESSAGE);

	    model.setPlayer1Name(player1Name);
	    model.setPlayer2Name(player2Name);

	    view.getPlayerArea(1).setPlayerName(1, player1Name);
	    view.getPlayerArea(2).setPlayerName(2, player2Name);
	}
	
	
	private void startGame() {
		int confirm = JOptionPane.showConfirmDialog(view, "Start the game?", "Confirm", JOptionPane.YES_NO_OPTION);
	    if (confirm == JOptionPane.YES_OPTION) {
	    	playerController1.startGame();
	    	playerController2.startGame();
	    	playerController1.startTurn();
	    }
	}
	
	public int place() {
		return model.getGameBoardModel().getCurrentPlayer();
	}
}
