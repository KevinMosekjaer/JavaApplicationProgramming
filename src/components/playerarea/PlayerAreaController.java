package components.playerarea;

import main.Observer;

/**
 * 
 * 
 * @author mosek
 */
public class PlayerAreaController implements Observer {

	/**
	 * 
	 */
	private PlayerAreaModel model;

	/**
	 * 
	 */
	private PlayerArea view;

	/**
	 * 
	 */
	private ControllableTimer gameTimer, turnTimer;

	/**
	 * 
	 * @param model
	 * @param view
	 */
	public PlayerAreaController(PlayerAreaModel model, PlayerArea view) {
		this.model = model;
		this.view = view;
		this.gameTimer = new ControllableTimer(updateGameTimerTask);
		this.turnTimer = new ControllableTimer(updateTurnTimerTask);
	}

	/**
	 * 
	 */
	private Runnable updateGameTimerTask = () -> {
		long elapsed = System.currentTimeMillis() - gameTimer.getStartTime();
		view.updateGameTimer(formatTime(elapsed));
	};

	/**
	 * 
	 */
	private Runnable updateTurnTimerTask = () -> {
		if (!turnTimer.isRunning()) {
			view.updateTurnTimer("00:00");
		} else {
			long elapsed = System.currentTimeMillis() - turnTimer.getStartTime();
			view.updateTurnTimer(formatTime(elapsed));
		}
	};

	/**
	 * 
	 */
	public void startGameTimer() {
		gameTimer.start();
	}

	/**
	 * 
	 */
	public void startTurn() {
		turnTimer.start();
		view.updateNextMove("Your Turn!");
	}

	/**
	 * 
	 */
	public void endTurn() {
		turnTimer.stop();

	}

	/**
	 * 
	 */
	public void resetTurnTimer() {
		turnTimer.reset();
		turnTimer.start();
	}

	/**
	 * 
	 * @param milliseconds
	 * @return
	 */
	private String formatTime(long milliseconds) {
		long seconds = (milliseconds / 1000) % 60;
		long minutes = (milliseconds / (1000 * 60)) % 60;
		return String.format("%02d:%02d", minutes, seconds);
	}

	/**
	 * 
	 */
	public void updateView() {
		view.updateGameTimer(formatTime(model.getTotalGameTime()));
		view.updateGamesWon(model.getGamesWon());
		view.setPlayerName(model.getPlayerNumber(), model.getPlayerName());   
		view.updateTurnTimer(formatTime(model.getCurrentTurnTime()));
	}

	/**
	 * 
	 */
	@Override
	public void changeTurn(int currentPlayer) {
		boolean currentPlayerTurn = this.model.getPlayerNumber() == currentPlayer;
		if(currentPlayerTurn == true) {
			this.view.updateNextMove("Your Turn!");
			turnTimer.reset();
			turnTimer.start();
		} else {
			this.view.updateNextMove("Other player turn!");
			model.setPiecesPlaced(model.getPiecesPlaced() + 1);
			view.updatePiecesPlaced(model.getPiecesPlaced());
			turnTimer.reset(); 
			turnTimer.stop();
		}
	}

	/**
	 * 
	 */
	@Override
	public void gameFinished(int playerNumber) {
		model.setGamesWon(playerNumber);
		view.updateGamesWon(model.getGamesWon());
		endTurn();
		model.setPiecesPlaced(0);
		view.updatePiecesPlaced(model.getPiecesPlaced());
	}    
}


