package components.gameboard;
import java.util.ArrayList;
import java.util.List;

import observers.ConnectionObserver;
import observers.IncomingObserver;
import observers.MenuObserver;
import observers.Observer;
import observers.OutgoingObserver;

/**
 * Class holds controller for game board
 *
 * @author Kevin Mosekjaer
 */
public class GameBoardController implements MenuObserver, IncomingObserver, ConnectionObserver { 

	/**
	 * game board model
	 */
	private GameBoardModel model;

	/**
	 * game board view
	 */
	private GameBoard view;

	private boolean waiting=true, playerSet=false, remote=false;

	/**
	 * List of observers
	 */
	private List<Observer> turn = new ArrayList<>();

	private List<OutgoingObserver> outgoing = new ArrayList<>();

	/**
	 * Constructor
	 * @param model model
	 * @param view view
	 */
	public GameBoardController(GameBoardModel model, GameBoard view) {
		this.model = model;
		this.view = view;
		initializeController();
	}

	public void addGameSend(OutgoingObserver move) {
		outgoing.add(move);
	}

	/**
	 * Function to initialize controller
	 */
	private void initializeController() {
		model.setAbleToPlace(true);
		for (int i = 0; i < GameBoardModel.COL; i++) {
			int column = i;
			view.getPlacePieceButton(i).addActionListener(e -> placePiece(column));
		}
	}

	/**
	 * Adds observer
	 * @param listener l
	 */
	public void addTurnListener(Observer listener) {
		turn.add(listener);
	}

	/**
	 * Notifies listeners of turn change
	 */
	private void notifyTurnChange() {
		for (Observer listener : turn) {
			listener.changeTurn(model.getCurrentPlayer());
		}
	}

	/**
	 * notifies listeners of game finishing
	 */
	private void notifyGameFinished() {
		boolean reset;
		for (Observer listener : turn) {
			listener.gameFinished(model.checkWinner());
		}
		if(reset = view.displayWinner(model.checkWinner())) {
			model.resetGrid();
			view.resetGameBoard();
		} else {
			model.setAbleToPlace(false);
		}
	}

	private void notifyGameStart() {
		System.out.println("Calling game start from game board");
		for (Observer listener : turn) {
			listener.gameStart();
		}
	}

	/**
	 * Checks if piece can be placed, resets game, checks if game can continue
	 * @param col c
	 */
	public void placePiece(int col) {
		System.out.println("inside place piece in game board controller");
		if(!model.getHasStarted()) {
			if(remote) { // check if remote
				if(!waiting) {
					System.out.println("This player number in not waiting: " + model.getThisPlayer());
					model.setHasStarted(true);
					notifyGameStart();
				} else if(waiting) {
					if(view.startGamePrompt()) {
						System.out.println("This player number in waiting: " + model.getThisPlayer());
						outgoing.forEach(out -> out.startGameOutgoing("sg", model.getCurrentPlayer(), "request"));
						return;
					}
				}
			} else { // end check if remote
				if(view.startGamePrompt()) {
					model.setHasStarted(true);
					notifyGameStart();
					return;
				}
			}
			//return;
		}
		if(!model.getAbleToPlace()) {
			if(view.playAgain()) {
				model.setAbleToPlace(true);
				model.resetGrid();
				view.resetGameBoard();
			}
			return;
		}
		if(remote) {
			if(model.getThisPlayer() != model.getCurrentPlayer()) {
				view.notYourTurn();
				return;
			}
		}
		System.out.println("About to place piece from place piece game controller");
		boolean placed = model.placePiece(col);
		if(placed) {
			if(remote) {
				outgoing.forEach(out -> out.gameMoveOutgoing("gm", model.getCurrentPlayer(), col));
			}
			updateAllGridIcons();
			int winner = model.checkWinner();
			if (winner != 0) {
				notifyGameFinished();
			} else {
				model.switchPlayer();
				notifyTurnChange();
			}
		}
	}

	/**
	 * Updates icons in view
	 */
	public void updateAllGridIcons() {
		for (int i= 0; i< GameBoardModel.ROW; i++) {
			for (int j = 0; j < GameBoardModel.COL; j++) {
				int player = model.getState(i, j);
				view.updateGridIcon(i, j, player);
			}
		}
	}

	/**
	 * Getter for game board view
	 * @return view
	 */
	public GameBoard getGameBoardView() {
		return this.view;
	}

	/**
	 * Getter for gameBoard model
	 * @return model
	 */
	public GameBoardModel getGameBoardModel() {
		return this.model;
	}

	/**
	 * Implemented restart game function
	 */
	@Override
	public void restartGame() {
		model.setAbleToPlace(true);
		model.resetGrid();
		view.resetGameBoard();

	}

	@Override
	public void gameMoveIncoming(int player, int col) {
		if (model.getThisPlayer() == model.getCurrentPlayer()) {
			System.out.println("It's not your turn, please wait.");
			return;
		}
		if (model.placePiece(col)) {
			updateAllGridIcons();
			model.switchPlayer();
			notifyTurnChange();
			int winner = model.checkWinner();
			if (winner != 0) {
				notifyGameFinished();
			}
		} else {
			System.out.println("Move could not be placed. Column may be full.");
		}
	}



	@Override
	public void restartIncoming(int player, String message) {
		// TODO Auto-generated method stub

	}


	@Override
	public void startGameIncoming(int player, String message) {
		if(!playerSet) {
			System.out.println("Setting player number");
			if(player == 1) {
				model.setThisPlayer(2);
			} else if(player == 2) {
				model.setThisPlayer(1);
			}
		}
		System.out.println("Inside start game incoming in game board controller");
		if(message.equals("request")) {
			System.out.println("request start");
			if(view.startGamePromptOther()) {
				if(model.getThisPlayer()==1) {
					model.setCurrentPlayer(2);
				} else {
					model.setCurrentPlayer(1);
				}
				System.out.println("Current player: " + model.getCurrentPlayer());
				System.out.println("This player: " + model.getThisPlayer());
				outgoing.forEach(out -> out.startGameOutgoing("sg", model.getThisPlayer(), "accept"));
				waiting=false;
				notifyGameStart();
			}
		} else if(message.equals("accept")) {
			System.out.println("accept start");
			if(model.getThisPlayer()==1) {
				model.setCurrentPlayer(1);
			} else {
				model.setCurrentPlayer(2);
			}
			waiting=false;
			model.setHasStarted(true);
			notifyGameStart();
		}		
	}

	@Override
	public void connected(int c) {
		remote = true;
	}

	@Override
	public void disconnected(int c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeLanguage(String language) {
		// TODO Auto-generated method stub
		
	}

}
