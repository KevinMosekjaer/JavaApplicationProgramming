package components.playerarea;

import java.util.Locale;
import java.util.ResourceBundle;

import observers.IncomingObserver;
import observers.MenuObserver;
import observers.Observer;

/**
 * Class holding player area controller
 *
 * @author Kevin Mosekjaer
 */
public class PlayerAreaController implements Observer, MenuObserver, IncomingObserver {

	/**
	 * holds player area model
	 */
	private PlayerAreaModel model;

	/**
	 * holds player area view
	 */
	private PlayerArea view;

	/**
	 * holds timers
	 */
	private ControllableTimer gameTimer, turnTimer;

	/**
	 * holds resource bundle
	 */
	private ResourceBundle bundle;

	/**
	 * holds locale
	 */
	private Locale locale;

	/**
	 * Constructir
	 * @param model m
	 * @param view v
	 */
	public PlayerAreaController(PlayerAreaModel model, PlayerArea view) {
		this.model = model;
		this.view = view;
		this.gameTimer = new ControllableTimer(updateGameTimerTask);
		this.turnTimer = new ControllableTimer(updateTurnTimerTask);
	}

	/**
	 * Updates game timer
	 */
	private Runnable updateGameTimerTask = () -> {
		long elapsed = System.currentTimeMillis() - gameTimer.getStartTime();
		view.updateGameTimer(formatTime(elapsed));
	};

	/**
	 * Updates turn timer
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
	 * starts game timer
	 */
	public void startGameTimer() {
		gameTimer.start();
	}

	/**
	 * starts turn
	 */
	public void startTurn() {
		turnTimer.start();
		view.updateNextMove("Your Turn!");
	}

	/**
	 * ends turn
	 */
	public void endTurn() {
		turnTimer.stop();

	}

	/**
	 * reset and starts turn timer
	 */
	public void resetTurnTimer() {
		turnTimer.reset();
		turnTimer.start();
	}

	/**
	 * formats time sections
	 * @param milliseconds m
	 * @return time
	 */
	private String formatTime(long milliseconds) {
		long seconds = (milliseconds / 1000) % 60;
		long minutes = (milliseconds / (1000 * 60)) % 60;
		return String.format("%02d:%02d", minutes, seconds);
	}

	/**
	 * Updates view
	 */
	public void updateView() {
		view.updateGameTimer(formatTime(model.getTotalGameTime()));
		view.updateGamesWon(model.getGamesWon());
		view.setPlayerName(model.getPlayerNumber(), model.getPlayerName());
		view.updateTurnTimer(formatTime(model.getCurrentTurnTime()));
	}

	/**
	 * Implemented change turn function
	 */
	@Override
	public void changeTurn(int currentPlayer) {
		boolean currentPlayerTurn = this.model.getPlayerNumber() == currentPlayer;
		if(currentPlayerTurn) {
			view.updateNextMove("Your Turn!");
			resetTurnTimer();
		} else {
			view.updateNextMove("Other player turn!");
			model.setPiecesPlaced(model.getPiecesPlaced() + 1);
			view.updatePiecesPlaced(model.getPiecesPlaced());
			turnTimer.reset();
			turnTimer.stop();
		}
	}

	/**
	 * implemented game finished function
	 */
	@Override
	public void gameFinished(int playerNumber) {
		model.setGamesWon(playerNumber);
		view.updateGamesWon(model.getGamesWon());
		endTurn();
		model.setPiecesPlaced(0);
		view.updatePiecesPlaced(model.getPiecesPlaced());
	}

	/**
	 * implemented restart game function
	 */
	@Override
	public void restartGame() {
		model.setPiecesPlaced(0);
		view.updatePiecesPlaced(model.getPiecesPlaced());
		if(model.getPlayerNumber()==1) {
			resetTurnTimer();
			view.updateNextMove("Your Turn!");
		} else {
			turnTimer.reset();
			view.updateNextMove("Other player turn!");
		}
	}

	/**
	 * implemented change language function
	 */
	@Override
	public void changeLanguage(String language) {
		locale = new Locale(language);
		bundle = ResourceBundle.getBundle("messagebundles.MessageBundle", locale);
		view.updatePlayerLabels(bundle);
	}

	@Override
	public void gameStart() {
		if(model.getPlayerNumber()==1) {
			startGameTimer();
			startTurn();
		} else {
			startGameTimer();
		}
	}

	@Override
	public void restartIncoming(int player, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nameIncoming(int player, String name) {
		model.setPlayerName(name);	
		view.setPlayerName(player, name);
	}

}


