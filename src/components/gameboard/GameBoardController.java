package components.gameboard;
import main.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 * Class holds controller for game board
 * 
 * @author Kevin Mosekjaer
 */
public class GameBoardController {

	/**
	 * 
	 */
	private GameBoardModel model;
	
	/**
	 * 
	 */
	private GameBoard view;
	
	/**
	 * 
	 */
	private List<Observer> turn = new ArrayList<>();
	
	/**
	 * 
	 * @param model
	 * @param view
	 */
	public GameBoardController(GameBoardModel model, GameBoard view) {
		this.model = model;
		this.view = view;
		initializeController();
	}
	
	/**
	 * 
	 */
	private void initializeController() {
		model.setAbleToPlace(true);
		for (int i = 0; i < model.COL; i++) {
            int column = i;
            view.getPlacePieceButton(i).addActionListener(e -> placePiece(column));
        }
	}
	
	/**
	 * 
	 * @param listener
	 */
	public void addTurnListener(Observer listener) {
        turn.add(listener);
    }

	/**
	 * 
	 */
    private void notifyTurnChange() {
        for (Observer listener : turn) {
            listener.changeTurn(model.getCurrentPlayer());
        }
    }
    
    /**
     * 
     */
    private void NotifyGameFinished() {
    	boolean reset;
    	for (Observer listener : turn) {
            listener.gameFinished(model.getCurrentPlayer());
        }   	
    	if(reset = view.displayWinner(model.checkWinner())) {
    		model.resetGrid();
    		view.resetGameBoard();
    	} else {
    		model.setAbleToPlace(false);
    	}
    }
    

    /**
     * 
     * @param col
     */
    public void placePiece(int col) {
		if(!model.getAbleToPlace()) {
			if(view.playAgain()) {
				model.setAbleToPlace(true);
				model.resetGrid();
				view.resetGameBoard();
			}
			return;
		}
        boolean placed = model.placePiece(col);
        if (placed) { 
        	updateAllGridIcons();
            int winner = model.checkWinner();
            if (winner != 0) {
            	NotifyGameFinished();
            } else {
                model.switchPlayer();
                notifyTurnChange();
            }
        }
    }

    /**
     * 
     */
    public void updateAllGridIcons() {
        for (int i= 0; i< GameBoardModel.ROW; i++) {
            for (int j = 0; j < GameBoardModel.COL; j++) {
                int player = model.getState(i, j);
                view.updateGridIcon(i, j, player);
            }
        }
    }

}
