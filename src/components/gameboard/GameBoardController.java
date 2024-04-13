package components.gameboard;
import java.util.ArrayList;
import java.util.List;

import observers.ConnectionObserver;
import observers.GameObserver;
import observers.IncomingObserver;
import observers.OutgoingObserver;

/**
 * Class holds controller for game board
 *
 * @author Kevin Mosekjaer
 */
public class GameBoardController implements GameObserver, IncomingObserver, ConnectionObserver { 

	/**
	 * game board model
	 */
	private GameBoardModel model;

	/**
	 * game board view
	 */
	private GameBoard view;

	/**
	 * boolean variables related to remote connection
	 */
	private boolean waiting=true, playerSet=false, remote=false, otherAgain, meAgain;

	/**
	 * list of game observers
	 */
	private List<GameObserver> gameObservers = new ArrayList<>();

	/**
	 * list of outgoing observers
	 */
	private List<OutgoingObserver> outgoingObservers = new ArrayList<>();

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

	/**
	 * Function for adding outgoing observers
	 * @param outgoingObserver o
	 */
	public void addOutgoingObserver(OutgoingObserver outgoingObserver) {
		outgoingObservers.add(outgoingObserver);
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
	 * @param gameObserver o
	 */
	public void addGameObserver(GameObserver gameObserver) {
		gameObservers.add(gameObserver);
	}

	/**
	 * Notifies listeners of turn change
	 */
	private void notifyTurnChange() {
		for (GameObserver gameObserver : gameObservers) {
			gameObserver.changeTurn(model.getCurrentPlayer());
		}
	}

	/**
	 * notifies listeners of game finishing
	 */
	private void notifyGameFinished() {
		boolean reset;
		for (GameObserver gameObserver : gameObservers) {
			gameObserver.gameFinished(model.checkWinner());
		}
		model.setAbleToPlace(false);
		meAgain=false;
		otherAgain=false;
		if(reset = view.displayWinner(model.checkWinner())) {
			if(remote) {
				meAgain=true;
				outgoingObservers.forEach(outgoingObserver -> outgoingObserver.restartOutgoing("rr", model.getThisPlayer(), "playAgain"));
				model.resetGrid();
				view.resetGameBoard();
			} else {
				model.resetGrid();
				view.resetGameBoard();
				model.setAbleToPlace(true);
			}
		} else {
			if(remote) {
				meAgain=false;
			}
		}
	}

	/**
	 * Function for notifying game observers of game starting
	 */
	private void notifyGameStart() {
		for (GameObserver gameObserver : gameObservers) {
			gameObserver.gameStart();
		}
	}

	/**
	 * Checks if piece can be placed, resets game, checks if game can continue
	 * @param col c
	 */
	public void placePiece(int col) {
		if(!model.getHasStarted()) { // if game has not started
			if(remote) { // check if remote
				if(!waiting) { // not waiting for game to start 
					model.setHasStarted(true);
				} else if(waiting) { // waiting for game to start
					if(view.startGamePrompt()) {
						outgoingObservers.forEach(outgoingObserver -> outgoingObserver.startGameOutgoing("sg", model.getThisPlayer(), "request"));
						return;
					}
				}
			} else { // end check if remote
				model.setCurrentPlayer(1);
				model.setThisPlayer(1);
				if(view.startGamePrompt()) { // if not remote prompt to start game
					model.setHasStarted(true);
					notifyGameStart();
					return;
				} 
			}
		}
		if(!model.getAbleToPlace()) { // if not able to place pieces	
			if(remote) { // if remote			
				if(meAgain==true && otherAgain==true) { // this player and other player want to play again
					model.setAbleToPlace(true);
					gameObservers.forEach(gameObserver -> gameObserver.sendChat("Game starting!"));
				} else if(meAgain==true && otherAgain==false) { // this player wants to play again other does not
					outgoingObservers.forEach(outgoingObserver -> outgoingObserver.restartOutgoing("rr", model.getThisPlayer(), "request"));
					gameObservers.forEach(gameObserver -> gameObserver.sendChat("Waiting For other player to respond to request..."));
					return;
				} else if(meAgain==false && otherAgain==true){ // this player does not want to play again other does
					if(view.playAgain()) { // prompts this player if wants to play again
						outgoingObservers.forEach(outgoingObserver -> outgoingObserver.restartOutgoing("rr", model.getThisPlayer(), "playAgain"));
					} else { // if false exit function
						return;
					}
				} else { // both dont want to play prompt to play on click
					if(view.playAgain()) { // ask this player if wants to play again
						outgoingObservers.forEach(outgoingObserver -> outgoingObserver.restartOutgoing("rr", model.getThisPlayer(), "request"));
						return;
					} else { // if no exit function
						return;
					}
				}
			} else { // if not remote
				if(view.playAgain()) { // if yes reset game and return
					model.resetGrid();
					view.resetGameBoard();
					model.setAbleToPlace(true);
					return;
				} else { // if no return with cant place piece
					model.setAbleToPlace(false);
					return;
				}			
			}
		}
		if(remote) { // if remote
			if(model.getThisPlayer() != model.getCurrentPlayer()) { // check if it is this players turn
				view.notYourTurn();
				return;
			}
		}
		boolean placed = model.placePiece(col);
		if(placed) { // if place piece successful
			updateAllGridIcons();
			if(remote) { // check if remote
				outgoingObservers.forEach(outgoingObserver -> outgoingObserver.gameMoveOutgoing("gm", model.getCurrentPlayer(), col));
			}
			if (model.checkWinner() != 0) { // if game finished
				notifyGameFinished();
			} else { // game not finished
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

	/**
	 * Implemented function for game move incoming from other user
	 * @param player p
	 * @param col c
	 */
	@Override
	public void gameMoveIncoming(int player, int col) {
		if (model.getThisPlayer() == model.getCurrentPlayer()) {
			return;
		}
		if (model.placePiece(col)) {
			updateAllGridIcons();
			if (model.checkWinner() != 0) {
				notifyGameFinished();
			} else {
				model.switchPlayer();
				notifyTurnChange();
			}
		} 
	}

	/**
	 * Implemented function for restart incoming 
	 * @param player p
	 * @param message m
	 */
	@Override
	public void restartIncoming(int player, String message) {
		if(message.equals("request")) {
			boolean answer = view.restartGamePrompt(player);
			if(answer) {
				//meAgain=true;
				outgoingObservers.forEach(outgoingObserver -> outgoingObserver.restartOutgoing("rr", model.getThisPlayer(), "accept"));
				gameObservers.forEach(gameObserver -> gameObserver.restartGame());
				model.setCurrentPlayer(player);
			} else {
				outgoingObservers.forEach(outgoingObserver -> outgoingObserver.restartOutgoing("rr", model.getThisPlayer(), "decline"));
			}
		} else if(message.equals("accept")) {
			//otherAgain=true;
			model.setCurrentPlayer(model.getThisPlayer());
			gameObservers.forEach(gameObserver -> gameObserver.restartGame());
		} else if(message.equals("denied")){
			gameObservers.forEach(gameObserver -> gameObserver.sendChat("Player " + player + " does not want to restart :C"));
		} else if(message.equals("playAgain")){
			otherAgain = true;
		}
	}

	/**
	 * Implemented function for start game incoming
	 * @param player p
	 * @param message m
	 */
	@Override
	public void startGameIncoming(int player, String message) {
		if(!playerSet) {
			if(player == 1) {
				model.setThisPlayer(2);
			} else if(player == 2) {
				model.setThisPlayer(1);
			}
		}
		if(message.equals("request")) {
			if(view.startGamePromptOther()) {
				if(model.getThisPlayer()==1) {
					model.setCurrentPlayer(2);
				} else {
					model.setCurrentPlayer(1);
				}
				outgoingObservers.forEach(outgoingObserver -> outgoingObserver.startGameOutgoing("sg", model.getThisPlayer(), "accept"));
				waiting=false;
				notifyGameStart();
			}
		} else if(message.equals("accept")) {
			if(model.getThisPlayer()==1) {
				model.setCurrentPlayer(1);
			} else {
				model.setCurrentPlayer(2);
			}
			waiting=false;
			model.setHasStarted(true);
			model.setHasStarted(true);
			notifyGameStart();
		}		
	}

	/**
	 * Implemented function for active connection
	 * @param c c
	 */
	@Override
	public void connected(int c) {
		remote = true;
	}

	/**
	 * Implemented function for disconnect from client/server
	 * @param c c
	 */
	@Override
	public void disconnected(int c) {
		model.setAbleToPlace(false);
		model.resetGrid();
		view.resetGameBoard();
		remote = false;
	}

}
