package main;

import javax.swing.JOptionPane;

import components.gameboard.GameBoardController;
import components.gamechat.GameChatController;
import components.playerarea.PlayerAreaController;

/**
 * Class contains main controller for game
 * 
 * @author Kevin Mosekjaer
 */
public class GameController {
	
	/**
	 * GameModel model
	 */
	private GameModel model;
	
	/**
	 * GameView view
	 */
	private GameView view;
		
	/**
	 * GameChatController chat controller
	 */
	private GameChatController chatController;
	
	/**
	 * GameBoardController boardController
	 */
	private GameBoardController boardController;
	
	/**
	 * PlayerAreaController object for player 1 and 2
	 */
	private PlayerAreaController playerController1, playerController2;
	
	/**
	 * Holds current player
	 */
	private int currentPlayer = 1;
		
	/**
	 * Constructor for GameController
	 * @param view game view
	 * @param model game model
	 */
	public GameController(GameView view, GameModel model) {
		this.view = view;
		this.model = model;		
	}
	
	/**
	 * Function to initialize controllers and initiate game start
	 */
	public void start() {
		view.setVisible(true);		
		promptForPlayerNames();
		initializeControllers();
		startGame();
	}
	
	/**
	 * Function to initialize sub controllers
	 */
	public void initializeControllers() {
		chatController = new GameChatController(model.getGameChatModel(), view.getGameChat());
		boardController = new GameBoardController(model.getGameBoardModel(), view.getGameBoard());
		playerController1 = new PlayerAreaController(model.getPlayerAreaModel1(), view.getPlayerArea(1));
		playerController2 = new PlayerAreaController(model.getPlayerAreaModel2(), view.getPlayerArea(2));
		boardController.addTurnListener(playerController1);
		boardController.addTurnListener(playerController2);
		boardController.addTurnListener(chatController);
	}

	/**
	 * Function to prompt for user names
	 */
	private void promptForPlayerNames() {
	    String player1Name = JOptionPane.showInputDialog(view, "Enter Player 1's name:", "Player Names", JOptionPane.PLAIN_MESSAGE);
	    String player2Name = JOptionPane.showInputDialog(view, "Enter Player 2's name:", "Player Names", JOptionPane.PLAIN_MESSAGE);

	    model.setPlayer1Name(player1Name);
	    model.setPlayer2Name(player2Name);

	    view.getPlayerArea(1).setPlayerName(1, player1Name);
	    view.getPlayerArea(2).setPlayerName(2, player2Name);
	}
	
	/**
	 * Function to ask user if they want to start the game
	 */
	private void startGame() {
		int confirm = JOptionPane.showConfirmDialog(view, "Start the game?", "Confirm", JOptionPane.YES_NO_OPTION);
	    if (confirm == JOptionPane.YES_OPTION) {
	    	playerController1.startGameTimer();
	    	playerController2.startGameTimer();
	    	playerController1.startTurn();
	    }
	}
	
	/**
	 * Function to get current player
	 * @return return current player
	 */
	public int place() {
		return model.getGameBoardModel().getCurrentPlayer();
	}
	
	/**
	 * Function to switch turns
	 */
	public void switchTurns() {
		currentPlayer = currentPlayer == 1 ? 2 : 1;
		playerController1.changeTurn(currentPlayer);
		playerController2.changeTurn(currentPlayer);
	}
}
