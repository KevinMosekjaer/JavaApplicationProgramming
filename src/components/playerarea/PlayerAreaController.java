package components.playerarea;

import components.gameboard.GameBoardController;
import main.GameController;

public class PlayerAreaController {
	
		
	private PlayerAreaModel model;
	private PlayerArea view;
	private ControllableTimer gameTimer, turnTimer;
	
	    
	public PlayerAreaController(PlayerAreaModel model, PlayerArea view) {
		this.model = model;
	    this.view = view;
	    gameTimer = new ControllableTimer(view);
	    turnTimer = new ControllableTimer(view);
	    //this.turnTimer = new ControllableTimer(view, model.getPlayerNumber());
	    //this.turnTimer = new ControllableTimer(view);
	}
	
	public void startGame() {
        gameTimer.start();
    }
	  /*  
	public void startTurn() {
        model.startTurn();
        turnTimer.start();
        updateView();
    }
    */
	
	public void startTurn() {
		turnTimer.start();

    }
	
	public void endTurn() {
        turnTimer.stop();

    }
	
	public void resetTurnTimer() {
		turnTimer.reset();
		turnTimer.start();
	}


	    public void incrementGamesWon() {
	        model.incrementGamesWon();
	        updateView();
	    }
	    
	    private String formatTime(long milliseconds) {
	        long seconds = (milliseconds / 1000) % 60;
	        long minutes = (milliseconds / (1000 * 60)) % 60;
	        return String.format("%02d:%02d", minutes, seconds);
	    }
	    
	    public void updateView() {
	        view.updateGameTimer(formatTime(model.getTotalGameTime()));
	        view.updateGamesWon(model.getGamesWon());
	        view.setPlayerName(model.getPlayerNumber(), model.getPlayerName());   
	        view.updateNextMove(model.getTurnActive());
	        view.updateTurnTimer(formatTime(model.getCurrentTurnTime()));
	    }
	    /*
	    public void indicateTurn(boolean isPlayerOneTurn) {
	        if (isPlayerOneTurn) {
	            view.setNextMove(model.getPlayerName() + "'s Turn");
	        } else {
	            view.setNextMove("Other Player's Turn"); 
	        }
	    }
	    */    
	}


